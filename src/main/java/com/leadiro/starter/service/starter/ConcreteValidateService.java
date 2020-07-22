package com.leadiro.starter.service.starter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.leadiro.starter.service.ValidateService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConcreteValidateService implements ValidateService{
	private static String URL_POSTCODE = "http://api.postcodes.io/postcodes/";

	public Map<String, Boolean>  isValidEmailAdd(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(regex);
		if (email == null)
			return ImmutableMap.of("r",false);
		return ImmutableMap.of("result",pat.matcher(email).matches());
	}

	public Map<String, String>  isValidPostCode(String postCode) {
		String region = "";
		try {
			URL url = new URL(URL_POSTCODE + postCode + "/validate");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return ImmutableMap.of("message",conn.getResponseMessage());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((region = br.readLine()) != null) {
				JSONObject obj = new JSONObject(region);
				if ((boolean) obj.get("result")) {
					URL url2 = new URL(URL_POSTCODE + postCode);
					HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
					conn2.setRequestMethod("GET");
					conn2.setRequestProperty("Accept", "application/json");
					if (conn2.getResponseCode() != 200) {
						return ImmutableMap.of("message",conn2.getResponseMessage());
					}
					BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));
					while ((region = br2.readLine()) != null) {
						obj = new JSONObject(region);
						JSONObject result = (JSONObject) obj.get("result");
						conn2.disconnect();
						return ImmutableMap.of("region",result.getString("region"));
					}
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ImmutableMap.of("message",postCode + " is an invalid post code");

	}


}
