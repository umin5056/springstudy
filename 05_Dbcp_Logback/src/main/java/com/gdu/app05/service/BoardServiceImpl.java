package com.gdu.app05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app05.domain.BoardDTO;
import com.gdu.app05.repository.BoardDAO;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return dao.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(int board_no) {
		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int insertBoard(BoardDTO board) {
		return dao.insertBoard(board);
	}

	@Override
	public int updateBoard(BoardDTO board) {
		return dao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

}
