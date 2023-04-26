package com.gdu.prd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
@Controller
public class ProductController {
	
	@GetMapping("/list.do")
	public String list() {
		
		return "product/list";
	}

}
