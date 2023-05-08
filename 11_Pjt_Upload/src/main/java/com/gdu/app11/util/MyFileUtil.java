package com.gdu.app11.util;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {

	// 경로 구분자
	private String sep = Matcher.quoteReplacement(File.separator);
	
	// String path 만들기
	public String getPath() {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		// 루트/storage/2023/05/08
		return "/Users/woomin/Documents/storage"+ sep + year + sep + String.format("%02d", month) + sep + String.format("%02d", day);
		
	}
	
	
	// String filesystemName 만들기
	public String getFilesystemName(String originName) {
		
		// 원래 첨부 파일의 확장자 꺼내기
		String extName = "";
		
		// 확장자에 마침표(.)가 포함된 예외 상황 처리
		if(originName.endsWith("tar.gz")) {
			extName = "tar.gz";
		} else {
			// String.split(정규식)
			// 정규식에서 마침표(.)는 모든 문자를 의미하므로 이스케이프 처리하거나 문자 클래스로 처리한다.
			// 이스케이프 처리 : \.
			// 문자클래스 처리 : [.]
			String[] array = originName.split("\\.");
			extName = array[array.length-1];
		}
		
		// 결과 반환
		// UUID.extName
		return UUID.randomUUID().toString().replace("-", "") + "." + extName;
		
	}
	
}
