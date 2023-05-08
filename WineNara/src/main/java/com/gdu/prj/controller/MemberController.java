package com.gdu.prj.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.prj.domain.MemberDTO;
import com.gdu.prj.service.MemberService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
	
	private MemberService memberService;

	@GetMapping("/login.page")
	public String loginPage(HttpServletRequest request, HttpSession session) {
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					session.setAttribute("userId", cookie.getValue());
				}
			}
		}else {
			session.removeAttribute("userId");
		}
		
		return "member/login";
	}
	
	@PostMapping("/login.do")
	public String login(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		MemberDTO member = memberService.login(request);
		if(member != null) {
			
			session.setAttribute("member", member);
			// 자동 로그인 설정
			String autoLogin = request.getParameter("autoLogin");
			if(autoLogin != null) {
				Cookie userId = new Cookie("userId", member.getId());
				userId.setMaxAge(60*60*24*30);
				userId.setPath(request.getContextPath());
				response.addCookie(userId);
			}
			
			// 아이디 기억하기
			String rememberId = request.getParameter("rememberId");
			if(rememberId != null) {
				Cookie userId = new Cookie("userId", member.getId());
				userId.setMaxAge(60*60*24*30);
				response.addCookie(userId);
			}else { // 체크 안했으면 쿠키 삭제
				Cookie userId = new Cookie("userId", member.getId());
				userId.setMaxAge(0);
				response.addCookie(userId);
			}
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인하세요')");
				out.println("location.href='" + request.getContextPath() + "/member/login.page'");
				out.println("</script>");
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		return "index";
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					Cookie userId = new Cookie("userId", cookie.getValue());
					userId.setMaxAge(0);
					userId.setPath(request.getContextPath());
					response.addCookie(userId);
				}
			}
		}
		return "index";
	}
	
	@GetMapping("/detail.page")
	public String detailPage() {
		return "member/detail";
	}
	
	@PostMapping("/update.do")
	public String update(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addFlashAttribute("updateResult", memberService.update(request));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("updateResult", 0);
			return "redirect:/member/detail.page";
		}
		return "redirect:/member/logout.do";
	}
	
	@GetMapping("/signup.page")
	public String signupPage() {
		return "member/signup";
	}
	
	@PostMapping("/signup.do")
	public String signup(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addFlashAttribute("insertResult", memberService.signup(request));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("insertResult", 0);
			return "redirect:/member/signup.page";
		}
		return "redirect:/member/login.page";
	}
	
	@GetMapping("/delete.do")
	public String delete(Integer memberNo, RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addFlashAttribute("deleteResult", memberService.delete(memberNo));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("deleteResult", 0);
			return "redirect:/member/detail.page";
		}
		return "redirect:/";
	}
	
}
