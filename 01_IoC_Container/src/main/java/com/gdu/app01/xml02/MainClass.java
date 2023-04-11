package com.gdu.app01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml02/app-context.xml");
		
		Academy aca = ctx.getBean("academy", Academy.class);
		
		System.out.println("이름 : " + aca.getName());
		System.out.println("도로명주소 : " + aca.getAddress().getDoroAddr());
		System.out.println("지번주소 : " + aca.getAddress().getJibeonAddr());
		System.out.println("전화번호 : " + aca.getAddress().getContact().getPhoneNo());
		System.out.println("팩스번호 : " + aca.getAddress().getContact().getFaxNo());
		
	}

}
