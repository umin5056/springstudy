package com.gdu.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.prj.service.WineService;

import lombok.AllArgsConstructor;

@RequestMapping("/wine")
@AllArgsConstructor
@Controller
public class WineController {

	private WineService wineService;
	
	@GetMapping("/getAllItems.do")
	public String getAllItems(Model model) {
		System.out.println("dto" + wineService.getAllItems());
		model.addAttribute("wineList", wineService.getAllItems());
		
		return "index";
	}
	
}
