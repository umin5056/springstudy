package com.gdu.app00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
