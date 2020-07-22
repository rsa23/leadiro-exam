package com.leadiro.starter.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leadiro.starter.service.NameService;
import com.leadiro.starter.service.starter.ConcreteNameService;
import com.leadiro.starter.service.starter.dto.Name;

@RestController
public class NameController{

	@Autowired
	ConcreteNameService nameService;
	
	@PostMapping(value = "/parse/name")
	public List<Name> processNameClean(@RequestBody List<Name> name) {

		return nameService.nameClean(name);
	}

}
