package com.leadiro.starter.controller;

import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.leadiro.starter.service.ValidateService;
import com.leadiro.starter.service.starter.ConcreteValidateService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class ValidateController {
	@Autowired
	ConcreteValidateService validateService; 
	
	@PostMapping(value = "/validate/email")
	public Map<String, Boolean>  processIsValidEmailAdd(@RequestParam String email) {
		return validateService.isValidEmailAdd(email);
	}

	@PostMapping(value = "/validate/postcode")
	public Map<String, String>  processIsValidPostCode(@RequestParam String postCode) {
		return validateService.isValidPostCode(postCode);

	}

}
