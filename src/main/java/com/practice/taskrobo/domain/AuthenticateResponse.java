package com.practice.taskrobo.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class AuthenticateResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String token;
	
	public AuthenticateResponse() {
		
	}

	public AuthenticateResponse(String token) {
		this.token = token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	
}
