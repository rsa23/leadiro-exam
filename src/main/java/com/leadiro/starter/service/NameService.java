package com.leadiro.starter.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.leadiro.starter.service.starter.dto.Name;

@Service
public interface NameService {
	List<Name> nameClean(List<Name> name);
}
