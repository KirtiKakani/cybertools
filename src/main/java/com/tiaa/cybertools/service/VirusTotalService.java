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
public class VirusTotalService {
	
	public Map<String, String> getIpStatusVT(String ipaddress) {
		
		Map<String,String> resp = new HashMap<String, String>();

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("x-apikey", "08896ddaed537d12d0e44bec181dab787a7bc278069298c7d37b620218a238d5");

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String requrl = "https://www.virustotal.com/api/v3/ip_addresses/" + ipaddress;

		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(requrl, HttpMethod.GET, entity, JSONObject.class);
		
		System.out.println(responseEntity.getBody());
		
		JSONObject j = responseEntity.getBody();
		Integer i = JsonPath.read(j, "$.data.attributes.reputation");
		String s = "Non Malicious";
		if (i < 0) {
			s = "Malicious";
		}
		System.out.println(s);
		resp.put("tool", "VirusTotal");
		resp.put("reputation", s);
		resp.put("output", responseEntity.getBody().toString());
		return resp;

	}

}
