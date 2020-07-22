package com.leadiro.starter.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

/**
 * An authenticated account.
 */
@Data
public class AuthAccount {
    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
