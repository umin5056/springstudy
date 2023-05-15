package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;

	@ResponseBody
	@GetMapping(value="list.json" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<StaffDTO> list1() {
		return staffService.getStaffList1();
	}
//	@GetMapping("list.json")
	public ResponseEntity<List<StaffDTO>> list2() {
		return staffService.getStaffList2();
	}
	
//	@ResponseBody
//	@PostMapping(value="/add.do", produces="text/plain; charset=UTF-8")
	public String add1(HttpServletRequest request) {
		return staffService.addStaff(request);
	}
	
	@PostMapping(value="/add.do", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add2(StaffDTO staffDTO) {
		return staffService.addStaff(staffDTO);
	}
	
	@ResponseBody
	@GetMapping("/query.json")
	public StaffDTO query(@RequestParam(value="sno", required=true, defaultValue="0") String sno) {
		return staffService.query(sno);
	}
	
}
