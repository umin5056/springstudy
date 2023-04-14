package com.gdu.app03.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public class FirstServiceImpl implements IFirstService {
	
	// 예외 발생 시 예외 메세지를 화면으로 전달하기
	
	

	@Override
	public Person execute1(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			String name = request.getParameter("name");
			name = name.isEmpty() ? "홍길동" : name; // 사용자가 입력한 이름이 없으면 빈 문자열을 전달받는다.
			
			String strAge = request.getParameter("age");
			strAge = strAge.isEmpty() ? "0" : strAge; // 사용자가 입력한 나이가 없으면 빈 문자열을 전달받는다.
			
			// 0~100 범위를 벗어난 경우 예외 발생시키기
			if(Integer.parseInt(strAge) < 0 || Integer.parseInt(strAge) >100) {
				throw new RuntimeException("나이를 다시 입력하세요");
			}
			return new Person(name, Integer.parseInt(strAge)); // ajax success 함수로 넘기는 
		}catch(Exception e) {
			try {
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(e.getMessage()); // ajax error함수로 넘기는 예외 메세지
				out.flush();
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return null;
		}
	}
	
	@Override
	public Map<String, Object> execute2(String name, int age) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("age", age);
		return map;
	}

	public Map<String, Object> execute3(Person person) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", person.getName());
		map.put("age", person.getAge());
		
		return map;
			
	}

}
