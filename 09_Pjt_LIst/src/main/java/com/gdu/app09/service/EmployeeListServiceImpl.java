package com.gdu.app09.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app09.domain.EmpDTO;
import com.gdu.app09.mapper.EmployeeListMapper;
import com.gdu.app09.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor // field에 자동으로 @Autowired
@Service
public class EmployeeListServiceImpl implements EmployeeListService {

	private EmployeeListMapper employeeListMapper;
	private PageUtil pageUtil;
	
	@Override
	public void getEmployeeListUsingPagination(HttpServletRequest request, Model model) {
		
		// 파라미터 page가 전달되지 않는 경우 page=1로 처리한다.
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		// 전체 레코드 개수를 구한다.
		int totalRecord = employeeListMapper.getEmployeeCount();
		
		// 세션에 있는 recordPerPage를 가져온다. 세션이 없는 경우 recordPerPage=10으로 처리한다.
		// request로 할 경우 주소창에 파라미터가 너무 많아져서 session으로 받아옴
		HttpSession session = request.getSession();
		Optional<Object> opt2 = Optional.ofNullable(session.getAttribute("recordPerPage"));
		int recordPerPage = (int)(opt2.orElse(10));
		
		// 파라미터 order가 전달되지 않는 경우 order=ASC로 처리한다.	
		Optional<String> opt3 = Optional.ofNullable(request.getParameter("order"));
		String order = opt3.orElse("ASC");
		
		// 파라미터 column이 전달되지 않는 경우 EMPLOYEE_ID로 한다
		Optional<String> opt4 = Optional.ofNullable(request.getParameter("column"));
		String column = opt4.orElse("EMPLOYEE_ID");
		
		// PageUtil(Pagination에 필요한 모든 정보) 계산하기
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		// DB로 보낼 Map 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		map.put("order", order);
		map.put("column", column);
		
		// begin ~ end 사이의 목록 가져오기
		List<EmpDTO> employees = employeeListMapper.getEmployeeListUsingPagination(map);
		
		// pagination.jsp로 전달할(forward)할 정보 저장하기
		model.addAttribute("employees", employees);
		model.addAttribute("pagination", pageUtil.getPagination(request.getRequestURI() + "?order=" + order + "&column=" + column));
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		switch(order) {
		// 현재 ASC 정렬이므로 다음 정렬은 DESC라고 알려준다.
		case "ASC" : model.addAttribute("order", "DESC"); break;
		// 현재 DESC 정렬이므로 다음 정렬은 ASC라고 알려준다.
		case "DESC" : model.addAttribute("order", "ASC"); break;
		}
		model.addAttribute("page", page);
	}
	
	@Override
	public Map<String, Object> getEmployeeListUsingScroll(HttpServletRequest request) {

		// 파라미터 page가 전달되지 않는 경우 page=1로 처리한다.
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		// 전체 레코드 개수를 구한다.
		int totalRecord = employeeListMapper.getEmployeeCount();
		
		// recordPerPage=9로 처리한다.
		int recordPerPage = 9;
		
		// PageUtil(Pagination에 필요한 모든 정보) 계산하기
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		// DB로 보낼 Map 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());

		// begin ~ end 사이의 목록 가져오기
		List<EmpDTO> employees = employeeListMapper.getEmployeeListUsingScroll(map);
		
		// scroll.jsp로 응답할 데이터
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("employees", employees);
		
		// 응답
		return resultMap;
		
		
		
		
		
	}

}
