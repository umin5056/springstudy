package com.gdu.app04.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.app04.domain.BoardDTO;

@Service
public interface BoardService {
	// 목록
	public List<BoardDTO> list();
	
	// 생성 
	public int add(BoardDTO boardDTO);
	// 삭제
	public int remove(int board_no);
	// 수정
	public int modify(BoardDTO boardDTO);
}
