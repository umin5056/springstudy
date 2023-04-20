package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public List<BoardDTO> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public int add(BoardDTO board) {
		// TODO Auto-generated method stub
		return dao.add(board);
	}

	@Override
	public BoardDTO detail(int board_no) {
		// TODO Auto-generated method stub
		return dao.detail(board_no);
	}

	@Override
	public int remove(int board_no) {
		// TODO Auto-generated method stub
		return dao.remove(board_no);
	}

	@Override
	public int modify(BoardDTO board) {
		// TODO Auto-generated method stub
		return dao.modify(board);
	}
	
	// AOP를 활용한 트랜잭션 처리 테스트
	@Override
	public void testTx() {
		
		// 2개 이상의 삽입, 수정, 삭제가 하나의 서비스에서 진행되는 경우에 트랜잭션 처리가 필요하다.
		
		// 성공하는 작업
		dao.add(new BoardDTO(0, "트랜잭션제목", "트랜잭션내용", "트랜잭션작성자", null, null)); // RollBack
		
		// 실패하는 작업
		dao.add(new BoardDTO());
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
