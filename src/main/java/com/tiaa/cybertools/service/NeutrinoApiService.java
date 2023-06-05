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
public class NeutrinoApiService {
	
	public Map<String, String> getIpStatusNA(String ipaddress) {
		
		Map<String,String> resp = new HashMap<String, String>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("User-ID", "cybertoolsapi");
		headers.set("API-Key", "cTPT1npPSlpotUDLmuJxvN16fFw0JeEiJ0CMz82X7RPy39Jr");

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String requrl = "https://neutrinoapi.net/host-reputation?host=" + ipaddress;

		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(requrl, HttpMethod.GET, entity, JSONObject.class);
		
		System.out.println(responseEntity.getBody());
		
		JSONObject j = responseEntity.getBody();
		Boolean i = JsonPath.read(j, "$.is-listed");
		String s = "Non Malicious";
		if (i) {
			s = "Malicious";
		}
		System.out.println(s);
		resp.put("tool", "Neutrino");
		resp.put("reputation", s);
		resp.put("output", responseEntity.getBody().toString());
		return resp;
	
	}

}
