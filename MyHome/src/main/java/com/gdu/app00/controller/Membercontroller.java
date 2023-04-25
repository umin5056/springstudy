package com.gdu.app00.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app00.service.MemberService;

@RequestMapping("/member")
@Controller
public class Membercontroller {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/login.do")
	public String login(HttpServletRequest request, Model model) {
		
		model.addAttribute("member", memberService.login(request));
		
		return "index";
	}
	
	@GetMapping("/joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
	@PostMapping("/join.do")
	public String join(HttpServletRequest request) {
		memberService.joinMember(request);
		return "index";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}
	
	@PostMapping("/delete.do")
	public String delete(HttpServletRequest request) {
		memberService.deleteMember(request);
		return "index";
	}
}
