package com.leadiro.starter.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ValidateService {

	Map<String, Boolean>  isValidEmailAdd(String email);
	Map<String, String>  isValidPostCode(String postCode);
}
