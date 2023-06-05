package com.tiaa.cybertools.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

@Service
public class CriminalIpService {

	public Map<String, String> getIpStatusCI(String ipaddress) {

		Map<String,String> resp = new HashMap<String, String>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("x-api-key", "OtcmUqt9WBFyWZv8GnhwDgBF8H4nMg95jz1oP9pObY4Tom4kLu04Wel63wXy");

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String requrl = "https://api.criminalip.io/v1/feature/ip/malicious-info?ip=" + ipaddress;

		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(requrl, HttpMethod.GET, entity,
				JSONObject.class);
		System.out.println(responseEntity.getBody());
		JSONObject j = responseEntity.getBody();
		Boolean i = JsonPath.read(j, "$.is_malicious");
		String s = "Non Malicious";
		if (i) {
			s = "Malicious";
		}
		System.out.println(s);
		resp.put("tool", "CriminalIP");
		resp.put("reputation", s);
		resp.put("output", responseEntity.getBody().toString());
		return resp;
		

	}
}
