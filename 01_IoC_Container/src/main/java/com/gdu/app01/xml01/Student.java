package com.gdu.app01.xml01;

public class Student {
	
	// 필드
	private String stuNo;
	private String name;
	private Calculator calculator;
	
	// 생성자
	// 기본 생성자 사용

	// 메소드 (게터,세터)
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calculator getCalculator() {
		
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
}
