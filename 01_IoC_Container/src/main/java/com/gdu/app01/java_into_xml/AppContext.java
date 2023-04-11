package com.gdu.app01.java_into_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
		
	@Bean
	public Publisher pub() {
		
		Publisher pub = new Publisher();
		pub.setName("우민 출판사");
		pub.setTel("010-1222-1111");
		return pub;
	}
	
	@Bean
	public Book book() {
		Book book = new Book();
		book.setTitle("우민일대기");
		book.setPublisher(pub());
		return book;
	}

}
