package com.gdu.app01.java01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		/*
			@Configuration, @Bean으로 생성한 Bean을 가져올 때 사용하는 스프링 클래스
			AnnotationConfigApplicationContext
		 */
		
		// AppContext.java 파일에 있는 Bean을 호출 (@Configuration이 1개인 경우)
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		/* 
		   com.gdu.app01.java01 패키지에 있는 모든 Bean을 호출 (@Configuration이 여러개인 경우)
		   AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.gdu.app01.java01");
		*/
		
		User user1 = ctx.getBean("user1", User.class);
		System.out.println(user1.getId());
		System.out.println(user1.getContact().getTel());
		System.out.println(user1.getContact().getFax());
		
		
		User user2 = ctx.getBean("user2", User.class);
		System.out.println(user2.getId());
		System.out.println(user2.getContact().getTel());
		System.out.println(user2.getContact().getFax());
		
		ctx.close();
		
		
		
	}
	
	

}
