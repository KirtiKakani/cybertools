package com.tiaa.cybertools.service;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.jayway.jsonpath.JsonPath;

@Service
public class CybertoolsService {
	
	@Autowired
	private VirusTotalService vtservice;
	
	@Autowired
	private CriminalIpService ciservice;
	
	@Autowired
	private NeutrinoApiService naservice;

	public List<Map<String, String>> getIpStatus(String ipaddress) {
		// TODO Auto-generated method stub
		
		List<Map<String, String>> respList = new ArrayList<>();
		
		respList.add(vtservice.getIpStatusVT(ipaddress));
		respList.add(ciservice.getIpStatusCI(ipaddress));
		respList.add(naservice.getIpStatusNA(ipaddress));
		
		
		return respList;
	}

	

	

}
