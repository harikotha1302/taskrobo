package com.practice.taskrobo.domain;

import org.springframework.stereotype.Component;

@Component
public class AuthenticateRequest {


	public String username;
	public String password;
	
	AuthenticateRequest(){
		
	}
	
	public AuthenticateRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
