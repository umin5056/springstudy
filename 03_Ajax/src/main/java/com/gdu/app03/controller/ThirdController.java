package com.gdu.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.Contact;
import com.gdu.app03.service.IThirdService;

@Controller
public class ThirdController {

	// field
	private IThirdService thirdService;
	
	// setter method
	@Autowired // setter method 형식의 자동 주입의 경우 @Autowired를 생략할 수 없다.
	public void method(IThirdService thirdService) {
		// Spring Container에서 IThirdService 타입의 Bean을 찾아서 매개변수에 주입한다.
		this.thirdService = thirdService;
	}
	
	@ResponseBody
	@PostMapping(value="/third/ajax1", produces="application/json")
	public ResponseEntity<Contact> ajax1(@RequestBody Contact contact) {
		// @RequestBody : 요청 본문(request body)에 포함된 json 데이터를 contact객체에 저장해주세요. post방식이 요청 본문에 데이터를 저장해서 보내줌.
		return thirdService.execute1(contact);
	}
	
	
}
