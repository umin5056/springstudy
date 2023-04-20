package com.gdu.app06.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // Connection, PreparedStatement, ResultSet을 사용하는 스프링 클래
	private String sql;
	
	// 1. 목록
	public List<BoardDTO> list() {
		sql = "SELECT BOARD_NO, TITLE, WRITER, CONTENT, CREATED_AT, MODIFIED_AT FROM BOARD  ORDER BY BOARD_NO DESC";
		
		List<BoardDTO> boardList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
		
		return boardList;
	}
	
	// 2. 추가
	public int add(final BoardDTO BOARD) {
		sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setString(3, BOARD.getWriter());
			}
		});
		
		return result;
	}
	
	// 3. 상세
	public BoardDTO detail(final int BOARD_NO) { // 매개변수의 변조를 막기 위해서 final 처리한다. (예전에 매개변수 해킹 시도가 있었다.)
		sql = "SELECT BOARD_NO, TITLE, WRITER, CONTENT, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), BOARD_NO);
		return board;
	}
	
	// 4. 삭제
	public int remove(final int BOARD_NO) {
		sql = "DELETE FROM BOARD WHERE BOARD_NO=?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, BOARD_NO);
			}
		});
				
		return result;
	}
	
	// 5.수정
	public int modify(final BoardDTO BOARD) {
		sql = "UPDATE BOARD SET TITLE=?, CONTENT=?, MODIFIED_AT=TO_CHAR(SYSDATE, 'YY-MM-DD HH:MI:SS') WHERE BOARD_NO=?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, BOARD.getTitle());
				ps.setString(2, BOARD.getContent());
				ps.setInt(3, BOARD.getBoard_no());
			}
		});
		return result;
	}
	
}
