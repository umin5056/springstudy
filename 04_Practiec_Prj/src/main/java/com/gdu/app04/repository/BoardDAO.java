package com.gdu.app04.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app04.domain.BoardDTO;


@Repository
public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection("jdbc.oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardDTO> list() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		return list;
	}
	
}
