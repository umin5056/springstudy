package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Student student() {
		List<Integer> scores = new ArrayList<>();
		for(int i=0; i<5; i++) {
			scores.add((int) (Math.random()*101));
		}
		
		Set<String> awards = new HashSet<>();
		awards.add("이순신상");
		awards.add("장보고상");
		awards.add("조우민상");
		
		Map<String, String> contact = new HashMap<>();
		contact.put("addr", "금정동");
		contact.put("tel", "010-1111-1111");
		
		Student student =  new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		
		return student;
	}
	
}
