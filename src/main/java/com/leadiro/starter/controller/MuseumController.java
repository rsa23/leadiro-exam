package com.leadiro.starter.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadiro.starter.service.MuseumService;
import com.leadiro.starter.service.starter.ConcreteMuseumService;
import com.leadiro.starter.service.starter.dto.Museum;

@RestController
public class MuseumController implements MuseumService{
	@Autowired
	ConcreteMuseumService museumService;
	
	@GetMapping(value = "/museum")
	public List<Museum> searchMuseumByKey(@RequestBody List<String> keyword) {
		return museumService.searchMuseumByKey(keyword);
	}

	@GetMapping(value = "/museum/")
	public String getRecordById(@RequestParam String id) {
		return museumService.getRecordById(id);
	}

}
