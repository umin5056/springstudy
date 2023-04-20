package com.gdu.app05.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	
	
	
	@Autowired
	BoardService service;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardList", service.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/insert.do")
	public String insert(BoardDTO board) {
		service.insertBoard(board);
		return "board/list";
	}
	
	@GetMapping("/modify.do")
	public String modify(BoardDTO board) {
		service.updateBoard(board);
		return "board/list";
	}
	
	@GetMapping("/detail.do")
	public String detail(int board_no) {
		service.getBoardByNo(board_no);
		return "/board/detail";
	}
}
