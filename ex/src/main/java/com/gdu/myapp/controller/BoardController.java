package com.gdu.myapp.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.myapp.domain.BoardDTO;

@Controller
public class BoardController {
	/*
		아래 두가지 매핑은 동일하다.
		@GetMapping("/board") // http://localhost:9090/myapp/board 
		@GetMapping("board") // http://localhost:9090/myapp/board 
	 */
	
	@GetMapping("/") // http://localhost:9090/myapp을 가리킨다. (contextPath 경로)
	// 반환 타입 String : 이동할 jsp명을 반환한다. 반환된 이름은 servlet-context의 뷰리졸버에 의해 rendering된다.
	public String index() {
		return "index";
	}
	
//	
//	// <a>, location
//	@GetMapping("/detail.do")
//	public void getDetail(HttpServletRequest request) {
//		String boardNo = request.getParameter("boardNo");
//		String title = request.getParameter("title");
//		System.out.println(boardNo + ", " + title);
//	}
//	
//	// <form>
//	@PostMapping("/detail.do")
//	public void postDetail(HttpServletRequest request) {
//		String boardNo = request.getParameter("boardNo");
//		String title = request.getParameter("title");
//		System.out.println(boardNo + ", " + title);
//	}
//	
//	
//	@GetMapping("/detail.do")
//	public void getDetail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo,
//						  @RequestParam(value="title", required= false, defaultValue = "빈제목") String title) {
//		System.out.println(boardNo + ", " + title);
//	}
//	@PostMapping("/detail.do")
//	public void postDetail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo,
//			  @RequestParam(value="title", required= false, defaultValue = "빈제목") String title) {
//	System.out.println(boardNo + ", " + title);
//	}
//	
//	@GetMapping("/detail.do")
//	public void getDetail(BoardDTO board) {
//		System.out.println(board); // 객체를 출력하면 toString()이 호출
//	}
//	
//	@PostMapping("/detail.do")
//	public void postDetail(BoardDTO board) {
//		System.out.println(board); // 객체를 출력하면 toString()이 호출
//	}
	
	
	/*
	    Model
	   	request와 다른 점이 무엇인가. 왜 사용하는가
	   	: 
	    1. 주 목적 		: jsp로 forward할 데이터(attribute)를 저장하는 용도
	    2. 속성 저장하는 곳 : request에 저장함
	    3. 컨트롤러에서만 선언 가능
	 */
	
//	@GetMapping("/detail.do")
//	public String getDetail(HttpServletRequest request, Model model) {
//		
//		Optional<String> opt1 = Optional.ofNullable(request.getParameter("boardNo"));
//		int boardNo = Integer.parseInt(opt1.orElse("0"));
//		
//		Optional<String> opt2 = Optional.ofNullable(request.getParameter("title"));
//		String title = opt2.orElse("빈제목");
//
//		System.out.println(boardNo + ", " + title);
//		
//		model.addAttribute("boardNo", boardNo);
//		model.addAttribute("title", title);
//		
//		return "detail";
//		
//	}
//	
//
//	@PostMapping("/detail.do")
//	public String postDetail(HttpServletRequest request, Model model) {
//		Optional<String> opt1 = Optional.ofNullable(request.getParameter("boardNo"));
//		int boardNo = Integer.parseInt(opt1.orElse("0"));
//		
//		Optional<String> opt2 = Optional.ofNullable(request.getParameter("title"));
//		String title = opt2.orElse("빈제목");
//
//		System.out.println(boardNo + ", " + title);
//		
//		model.addAttribute("boardNo", boardNo);
//		model.addAttribute("title", title);
//		
//		return "detail";
//	}
	
//	@GetMapping("/detail.do")
//	public String getDetail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo,
//						  @RequestParam(value="title", required= false, defaultValue = "빈제목") String title,
//						  Model model) {
//		System.out.println(boardNo + ", " + title);
//		model.addAttribute("boardNo", boardNo);
//		model.addAttribute("title", title);
//		
//		return "detail";
//	}
//	@PostMapping("/detail.do")
//	public String postDetail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo,
//						  @RequestParam(value="title", required= false, defaultValue = "빈제목") String title,
//						  Model model) {
//		System.out.println(boardNo + ", " + title);
//		model.addAttribute("boardNo", boardNo);
//		model.addAttribute("title", title);
//	
//	return "detail";
//	}
	
	@GetMapping("/detail.do")
	public String getDetail(BoardDTO board) { 
		// 파라미터를 객체로 받으면 자동으로 Model에 저장된다. 속성명은 boardDTO로 저장(클래스를 이용해 속성명을 저장)
		return "datail";
	
	}
	
	@PostMapping("/detail.do")
	public String postDetail(@ModelAttribute("board") BoardDTO board) {
		// Model에 저장하는 속성명을 "board"로 바꿔 저장한다.
		return "detail";
	}
	
	
	
	
	
	
	
	
}
