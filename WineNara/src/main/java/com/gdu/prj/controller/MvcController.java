package com.gdu.prj.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.prj.service.MemberService;

@Controller
public class MvcController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index(HttpServletRequest request, HttpSession session) {
		
			
			Cookie[] cookies = request.getCookies();
			
			if(cookies != null) {
				String userId = null;
				for(Cookie cookie : cookies) {
					System.out.println(cookie.getName());
					if(cookie.getName().equals("userId")) {
						userId = cookie.getValue();
					}
				}
				
				session.setAttribute("member", memberService.autoLogin(userId));
				
			}
		
		return "index";
	}
	
}
