package com.gdu.app03.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.BmiVO;

public interface ISecondService {
	// public BmiVO execute1(HttpServletRequest request, HttpServletResponse response);
	// public Map<String, Object> execute2(BmiVO bmiVO);
	public ResponseEntity<BmiVO> execute1(HttpServletRequest request);
	public ResponseEntity<Map<String, Object>> execute2(BmiVO bmiVO);
}
