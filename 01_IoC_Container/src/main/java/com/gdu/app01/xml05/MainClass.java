package com.gdu.app01.xml05;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml04/app-context.xml");
		
		Person p = ctx.getBean("p", Person.class);
		
		List<String> hobbies = p.getHobbies();
		for(int i=0; i<hobbies.size(); i++) {
			System.out.println("취미 : " + hobbies.get(i));
		}
		Set<String> contacts = p.getContacts();
		for(String contact : contacts) {
			System.out.println("연락처 : " + contact);
		}
		
		Map<String, String> friends = p.getFriends();
		for(Entry<String, String> entry : friends.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		
	}

}
