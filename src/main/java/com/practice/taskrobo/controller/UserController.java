package com.practice.taskrobo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.taskrobo.domain.AuthenticateRequest;
import com.practice.taskrobo.domain.AuthenticateResponse;
import com.practice.taskrobo.service.MyUserDetailsService;
import com.practice.taskrobo.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authentication;
	
	@Autowired
	MyUserDetailsService userdetailsservice;
	
	@Autowired
	JwtUtil jwtutil;

	@PostMapping(value = "/authentication")
	public ResponseEntity<AuthenticateResponse> authenticateToken(@RequestBody AuthenticateRequest request) throws Exception {
		try {
			authentication.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
				throw new Exception("incorrect username and password",e);
		}
		
		final UserDetails userDetails=userdetailsservice.loadUserByUsername(request.getUsername());
		final String jwt=jwtutil.generateToken(userDetails);
		AuthenticateResponse response=new AuthenticateResponse(jwt);
		return new ResponseEntity<>(response,HttpStatus.OK);

	}
}
