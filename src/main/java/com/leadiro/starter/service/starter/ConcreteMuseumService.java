package com.leadiro.starter.service.starter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.leadiro.starter.service.MuseumService;
import com.leadiro.starter.service.starter.dto.Museum;

@Service
public class ConcreteMuseumService implements MuseumService {
	private static String URL = "https://collections.museumsvictoria.com.au/api/search";

	@Override
	public List<Museum> searchMuseumByKey(List<String> keyword) {

		List<Museum> list = new ArrayList<Museum>();
		try {
			String result;
			Museum museum = new Museum();
			JSONArray objResult;
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return null;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((result = br.readLine()) != null) {
				objResult = new JSONArray(result);

				System.out.println("result: " + result);

				for (int i = 0; i < objResult.length(); i++) {
					JSONObject museumObj = (JSONObject) objResult.get(i);

					if (!museumObj.isNull("keywords")) {
						JSONArray keywords = (JSONArray) museumObj.get("keywords");

						for (int j = 0; j < keywords.length(); j++) {
							String key = (String) keywords.get(j);
							for (String k : keyword) {
								if (key.equalsIgnoreCase(k)) {
									JSONArray media = (JSONArray) museumObj.get("media");
									for (int l = 0; l < media.length(); l++) {
										JSONObject book = (JSONObject) media.get(j);
										museum.setId(book.getString("id"));
										museum.setTitle(book.getString("caption"));
										list.add(museum);
									}
								}
							}

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String getRecordById(String id) {
		try {
			List<Object> list = new ArrayList<Object>();
			String result;
			JSONArray objResult;
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return null;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((result = br.readLine()) != null) {
				objResult = new JSONArray(result);
				System.out.println("result: " + result);
				for (int i = 0; i < objResult.length(); i++) {
					JSONObject museum = (JSONObject) objResult.get(i);
					JSONArray media = (JSONArray) museum.get("media");
					JSONObject book = new JSONObject();
					for (int j = 0; j < media.length(); j++) {
						book = (JSONObject) media.get(j);
						if (book.get("id").toString().equalsIgnoreCase(id)) {
							list.add(toMap(book));
							return book.toString();
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ImmutableMap.of("message", "id does not exist").toString();
	}

	private static Map<String, Object> toMap(JSONObject jsonobj) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keys = jsonobj.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			Object value = jsonobj.get(key);
			if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

}
