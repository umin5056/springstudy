package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
   @Configuration
	1) Spring Container에 Bean을 만들어 두는 Java 클래스라는 뜻
    2) Spring Bean Configuration File하고 같은 역할
 */

@Configuration
public class AppContext {

	// Bean을 만들고 싶으면 메소드를 만들면 됩니다. (Bean 하나 = 메소드 하나)
	
	/*
	   @Bean
	   	1) Bean을 만드는 메소드
	   	2) 반환타입이 Bean의 타입(<bean> 태그의 class 속성) 
	   	   메소드명이 Bean의 이름(<bean> 태그의 id 속성)
	 */
	@Bean
	public Contact contact1() {		// <bean id="contact1" class="Contact">
		Contact c = new Contact();  // default constructor
		c.setTel("02-2222-2222");	// setter <property name="tel" value="02-2222-2222"/>
		c.setFax("02-1111-1111");	// setter <property name="fax" value="20-1111-1111"/>
		return c;					// 반환한 객체 c가 Spring Container에 저장된다.
	}
	
	@Bean
	public User user1() {			// <bean id="user1" class="User">
		User u = new User();		// default constructor
		u.setId("man~~");			// setter <property name="id" value="man/>
		u.setContact(contact1());	// setter <property name="contact" ref="contact1"/>
		return u;					// 반환한 객체 u가 Spring Container에 저장된다.
	}
	
	@Bean(name = "contact2")		// name 속성이 사용되면 Bean의 id로 사용된다.
	public Contact qqq() {			// name 속성이 사용되면 더이상 메소드명이 id가 아니다.
		return new Contact("02-1344-4124", "02-4567-4124");
	}
	
	@Bean(name = "user2")			// name 속성이 사용되면 Bean의 id로 사용된다.
	public User aaa() {				// name 속성이 사용되면 더이상 메소드명이 id가 아니다.
		return new User("조우만", qqq());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
