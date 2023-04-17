package com.gdu.app03.service;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public interface IFourthService {

	public ResponseEntity<byte[]> display(String path, String filename);
		
		
}
	
