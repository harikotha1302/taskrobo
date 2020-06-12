package com.practice.taskrobo.domain;

import org.springframework.stereotype.Component;

@Component
public class AuthenticateResponse {

	public String token;
	
	public AuthenticateResponse() {
		
	}

	public AuthenticateResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	
}
