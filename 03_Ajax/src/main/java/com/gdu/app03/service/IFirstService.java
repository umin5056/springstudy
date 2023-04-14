package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app03.domain.Person;

public interface IFirstService {

	public Person execute1(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> execute2(String name, int age);
	public Map<String, Object> execute3(Person person);
	
}
