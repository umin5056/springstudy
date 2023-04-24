package com.gdu.app07.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app07.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/*
	 	데이터(속성) 저장 방법
	 	1. forward  : Model에 attribute로 저정한다.
	 	2. redirect : RedirectAttributes에 FlashAttribute로 저장한다. 
	 */

	@GetMapping("/list.do")
	// getBoardList 서비스가 반환한 ArrayList<BoardDTO>를 /WEB-INF/views/board/list.jsp로 전달한다.
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	// getBoardByNo() 서비스가 반환한 BoardDTO를 /WEB-INF/views/board/detail.jsp로 전달한다.
	@GetMapping("/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("b", boardService.getBoardByNo(request));
		return "board/detail";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	// addBoard() 서비스가 반환한 0 또는 1을 가지고 /board/list.do으로 이동(redirect)한다.
	// addBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/list.jsp에서 확인한다.	
	@PostMapping("/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", boardService.addBoard(request));
		return "redirect:/board/list.do";
	}
	
	// modifyBoard() 서비스가 반환한 0 또는 1을 가지고 /board/detail.do으로 이동(redirect)한다.
	// modifyBoard() 서비스가 반환한 0 또는 1은 /WEB-INF/views/board/detail.jsp에서 확인한다.	
	@PostMapping("/modify.do")
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modifyResult", boardService.modifyBoard(request));
		return "redirect:/board/detail.do?boardNo=" + request.getParameter("boardNo"); 
	}
	
	@PostMapping("/remove.do")
	public String remove (HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", boardService.removeBoard(request));
		return "redirect:/board/list.do"; 
	}
	
	// 트랜잭션 테스트
	@GetMapping("/tx.do")
	public void tx() {
		boardService.testTx();
		
	}
	
	
	
	
	
	
	
	
	
}
