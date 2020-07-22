package com.leadiro.starter.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.leadiro.starter.service.starter.dto.Museum;
import com.leadiro.starter.service.starter.dto.Name;

@Service
public interface MuseumService {
	List<Museum> searchMuseumByKey(List<String> keyword);
	String getRecordById(String id);
}
