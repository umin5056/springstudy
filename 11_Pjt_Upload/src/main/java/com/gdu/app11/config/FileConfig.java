package com.gdu.app11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {

	@Bean
	public MultipartResolver multipartResolver() { // 만들어두면 스프링이 가져다가 사용
		CommonsMultipartResolver MultipartResolver = new CommonsMultipartResolver();
		MultipartResolver.setDefaultEncoding("UTF-8");
		MultipartResolver.setMaxUploadSize(1024 * 1024 * 100);		// 전체 첨부 파일의 크기 100MB
		MultipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);	// 첨부 파일 하나의 크기 10MB
		
		return MultipartResolver;
	}
	
}
