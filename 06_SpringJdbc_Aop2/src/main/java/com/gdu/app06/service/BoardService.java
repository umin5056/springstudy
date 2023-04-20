package com.gdu.app06.service;

import java.util.List;

import com.gdu.app06.domain.BoardDTO;

public interface BoardService {

	public List<BoardDTO> list();
	public int add(BoardDTO board);
	public BoardDTO detail(int board_no);
	public int remove(int board_no);
	public int modify(BoardDTO board);
	public void testTx();
}
