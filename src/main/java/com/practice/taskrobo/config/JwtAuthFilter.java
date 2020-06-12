package com.practice.taskrobo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.practice.taskrobo.service.MyUserDetailsService;
import com.practice.taskrobo.util.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	MyUserDetailsService userdetailservice;
	
	@Autowired
	JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String autherization=request.getHeader("Autherization");
		String username=null;
		String jwt=null;
		
		if(autherization!=null && autherization.startsWith("BEARER ")) {
			jwt=autherization.substring(7);
			username=jwtutil.extractUsername(jwt);
		}else {
			filterChain.doFilter(request, response);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userdetails=userdetailservice.loadUserByUsername(username);
			if(jwtutil.validateToken(jwt, userdetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new 
						UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);				
			}
		}
		filterChain.doFilter(request, response);
	}
}
