package com.gdu.app01.xml04;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyDAO {

	// field
	private Connection con;
	
	//singleton pattern : app-context.xml에서 <bean> 태그의 scope 속성을 이용해 생성
	
	
	// method
	public Connection getConnection() {
		// Spring Container에 만들어 둔 MyConn Bean 가져오
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml04/app-context.xml");
		con = ctx.getBean("MyConn", MyConnection.class).getConnection();
		ctx.close();
		return con;
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void list() {
		con = getConnection();
	}
	
	
}
