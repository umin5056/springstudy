package com.gdu.app01.java02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		Student student = ctx.getBean("student", Student.class);
		
		System.out.println("점수 : " + student.getScores().toString());
		System.out.println("수상 : " + student.getAwards().toString());
		System.out.println("연락처 : " + student.getContact());
		
		ctx.close();
	}

}
