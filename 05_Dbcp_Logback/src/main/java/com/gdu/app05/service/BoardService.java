package com.gdu.app05.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.app05.domain.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> getBoardList();
	public BoardDTO getBoardByNo(int board_no);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int board_no);
	
}
