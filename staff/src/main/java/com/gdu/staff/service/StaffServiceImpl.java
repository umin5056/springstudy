package com.gdu.staff.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<StaffDTO> getStaffList1() {
		List<StaffDTO> staffList = staffMapper.getStaffList();
		return staffList;
	}
	
	@Override
	public ResponseEntity<List<StaffDTO>> getStaffList2() {
		List<StaffDTO> staffList = staffMapper.getStaffList();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<List<StaffDTO>>(staffList, header, HttpStatus.OK);
	}
	
	@Override
	public String addStaff(HttpServletRequest request) {
		try {
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			
			StaffDTO staffDTO = new StaffDTO();
			staffDTO.setSno(sno);
			staffDTO.setName(name);
			staffDTO.setDept(dept);
			
			staffMapper.addStaff(staffDTO); // 예시 : 사원번호가 5byte 초과 혹은 중복되거나 null인 경우 예외 발생
			
			return "사원 등록이 성공했습니다.";
		}catch(Exception e) {
			return "사원 등록이 실패했습니다."; // $.ajax의 error로 전달
		}
	}
	
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staffDTO) {
		try {
			staffMapper.addStaff(staffDTO);
			return new ResponseEntity<String>("사원 등록이 성공했습니다.", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("사원 등록이 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public StaffDTO query(String sno) {
		return staffMapper.getStaff(sno);
	}
	
}
