package com.neosoft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springboot.config.Configuration;
import com.neosoft.springboot.model.Limits;

@RestController
public class LimitsController {
    
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
	}
}
