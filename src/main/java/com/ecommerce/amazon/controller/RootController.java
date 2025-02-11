package com.ecommerce.amazon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RootController {
	
	@GetMapping("/")
	public String rootHandler() {
		return "welcome to the amazon backend APIs";
	}
	
	@GetMapping("/login")
	public String loginHandling() {
		return "Login Api called";
	}
	

}
