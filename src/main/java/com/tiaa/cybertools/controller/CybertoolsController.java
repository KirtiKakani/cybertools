package com.tiaa.cybertools.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.cybertools.service.CybertoolsService;

@RestController
@RequestMapping("cybertools")
public class CybertoolsController {

	@Autowired
	private CybertoolsService cybertoolsService;
	
	@GetMapping("getData/{ip}")
	public @ResponseBody List<Map<String, String>> getChartData(@PathVariable("ip") String ipaddress){
		System.out.println(ipaddress);
		return cybertoolsService.getIpStatus(ipaddress);
	}
	@GetMapping("getname/{name}")
	public String hello(@PathVariable("name") String name) {
		return "hello"+name;
	}
}
