package com.practice.taskrobo.util;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	public static final String SECRET="SECRET";
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	public<T> T extractClaim(String token,Function<Claims,T> claimResolver){
		final Claims claim=extractAllClaims(token);
		return claimResolver.apply(claim);
	}
	private Claims extractAllClaims(String token2) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token2).getBody();
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(UserDetails userdetails) {
		Map<String,Object> claims=new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = userdetails.getAuthorities();
		claims.put("ROLE", authorities);
		return createToken(claims,userdetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
	}
	
	public boolean validateToken(String token,UserDetails userdetails) {
		final String username=extractUsername(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}
}
