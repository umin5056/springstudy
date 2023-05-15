package com.gdu.app12.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	// 크로스 사이트 스크립팅(Cross Site Scripting)
	public String preventXSS(String str) {
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt");
		return str;
	}
	
	// 인증코드 반환하기
	public String getRandomString(int count, boolean Letters, boolean Numbers) {
		return RandomStringUtils.random(count, Letters, Numbers);
	}
	
	// SHA-256 암호화하기
	/*
	   1. 입력 값을 256비트(32바이트) 암호화 처리하는 해시 알고리즘
	   2. 암호화는 가능하지만 복호화는 불가능한 알고리즘
	   3. 암호화된 결과를 저장하깅 위한 32바이트 byte 배열이 필요
	   4. 1바이트 -> 16진수로 변환해서 암호화된 문자열로 만든다.
	   5. 32바이트 -> 16진수로 변환하면 64글자가 생성 (DB칼럼의 크기를 VARCHAR2(64 BYTE)로 설정)
	   6. java.security 패키지를 이용
	 */
	
	public String getSha256(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
		}
		byte[] b = messageDigest.digest();	// 암호화된 32바이트 크기의 byte 배열 b가 생성된다.
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<b.length; i++) {
			sb.append(String.format("%2X", b[i])); // %X : 16진수를 의미, %2X : 2자리의 16진수를 의미 (x가 소문자면 결과 소문자, 대문자면 대문자)
		}
		return sb.toString();
	}
	
	
	
	
	
	
}
