package com.gdu.app07.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.selectBoardList();
	}

	@Override
	public BoardDTO getBoardByNo(HttpServletRequest request) {
		// 파라미터 boardNo이 없으면(null, "") 0을 사용한다.
		String strBoardNo = request.getParameter("boardNo");
		int boardNo = 0;
		if(strBoardNo != null && strBoardNo.isEmpty() == false) {
			boardNo = Integer.parseInt(strBoardNo);
		}
		return boardDAO.selectBoardByNo(boardNo);
	}

	@Override
	public int addBoard(HttpServletRequest request) {
		
		// 파라미터 title, content, writer를 받아온다
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		// BoardDAO로 전달할 BoardDTO를 만든다.
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		return boardDAO.insertBoard(board);
	}

	@Override
	public int modifyBoard(HttpServletRequest request) {
		// 파라미터 title, content, boardNo를 받아온다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// BoardDAO로 전달할 BoardDTO를 만든다.
		BoardDTO board = new BoardDTO();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);
		
		return boardDAO.updateBoard(board);
	}

	@Override
	public int removeBoard(HttpServletRequest request) {
		// 파라미터 boardNo를 받아온다.
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		return boardDAO.deleteBoard(boardNo);
	}

}
