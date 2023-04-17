package com.gdu.app03.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app03.domain.BmiVO;
import com.gdu.app03.service.ISecondService;

@Controller
public class SecondController {
	
	private ISecondService secondService;
	
	// @AllConstructor으로 @AutoWired를 대체할 수 있다.
	@Autowired // 생성자에서 @AutoWired는 생략할 수 있다.
	public SecondController(ISecondService secondService) {
		super();
		this.secondService = secondService;
	}
	
	// ResponseEntity 타입을 반환하면 @ResponseBody를 사용하지 않아도 된다.
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BmiVO> bmi1(HttpServletRequest request) {
		return secondService.execute1(request);
	}
	
	// ResponseEntity 타입을 반환하면 @ResponseBody를 사용하지 않아도 된다.
	@GetMapping("/second/bmi2") // produces가 없다. (반환 객체 ResponseEntity에 Content-Type을 작성해서 보냅니다.)
	public ResponseEntity<Map<String, Object>> bmi2(BmiVO bmiVO){
		return secondService.execute2(bmiVO);
	}
	
/*
	@ResponseBody
	@GetMapping(value="/second/bmi1", produces=MediaType.APPLICATION_JSON_VALUE) //MediaType.APPLICATION_JSON_VALUE == application/json; charset=utf-8
	public BmiVO bmi1(HttpServletRequest request, HttpServletResponse response) {
		return secondService.execute1(request, response);
	}
	
	@ResponseBody
	@GetMapping(value="/second/bmi2",  produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> bmi2(BmiVO bmiVO) {
		return secondService.execute2(bmiVO);
	}
*/
}
