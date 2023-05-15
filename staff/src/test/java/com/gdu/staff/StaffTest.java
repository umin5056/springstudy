package com.gdu.staff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StaffTest {

	@Autowired
	private StaffMapper staffMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StaffTest.class);
	
	@Test
	public void a1삽입테스트() {
		StaffDTO staff = new StaffDTO("00003", "김떡순", "개발부", 100000);
		assertEquals(1, staffMapper.addStaff(staff)); // boardDAO.insertBoard(board)의 결과가 1이면 테스트 성공
	}
	
	@Test
	public void a2전체테스트() {
		List<StaffDTO> list = staffMapper.getStaffList();
		LOGGER.info(list.toString());
		assertNotNull(list);
	}
	
	@Test
	public void a3조회테스트() {
		StaffDTO staff = staffMapper.getStaff("00003");
		LOGGER.info(staff.toString());
		assertNotNull(staff);
	}
	
	
	
	
	
	
	
	
}
