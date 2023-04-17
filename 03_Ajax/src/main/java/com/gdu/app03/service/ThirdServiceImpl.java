package com.gdu.app03.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.Contact;

public class ThirdServiceImpl implements IThirdService {

	@Override
	public ResponseEntity<Contact> execute1(Contact contact) {
		
		// 이름 또는 전화번호가 공백인 경우 ajax의 error 처리
		if(contact.getName().isEmpty() || contact.getTel().isEmpty()) {
			return new ResponseEntity<Contact>(HttpStatus.BAD_REQUEST);
		}
		
		// 이름과 전홥번호가 공백이 아닌 경우 입력된 값 그대로 다시 돌려보내기 ajax의 success 처리
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, String>> execute2(Map<String, String> map) {
		
		// 이름이 공백이거나, 전화번호의 글자수가 3글자 이하인 경우 ajax의 error 처리
		if(map.get("name").isEmpty() || map.get("tel").length() <= 3) {
			return new ResponseEntity<Map<String, String>>(HttpStatus.BAD_REQUEST);
		}
		
		// 이름은 그대로, 전화번호에 하이픈이 포함되어있으면 모두 제거 후, 돌려보내기 ajax의 success 처리
		map.put("tel", map.get("tel").replace("-", ""));
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
