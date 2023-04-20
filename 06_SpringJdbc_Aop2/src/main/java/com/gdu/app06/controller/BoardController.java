package com.gdu.app06.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	// ParameterCheckAOP에 의해서 파라미터를 체크할 메소드의 이름은 모두 ParamCheck로 끝난다.
	
	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardList", service.list());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public String addParamCheck(BoardDTO board) {
		service.add(board);
		return "redirect:/board/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detailPkParamCheck(HttpServletRequest request, Model model) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		model.addAttribute("board", service.detail(board_no));
		return "board/detail";
	}
	
	@GetMapping("/remove.do")
	public String removeParamCheck(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		service.remove(board_no);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/modify.do") 
	public String modifyParamCheck(BoardDTO board) {
		service.modify(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no();
	}
	
	// 트랜잭션 처리 확인을 위한 testTx() 호출
	@GetMapping("/tx.do") // http://localhost:9090/app06
	public String tx() {
		service.testTx();
		return "redirect:/board/list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
