package com.gdu.app00.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app00.domain.NoticeDTO;
import com.gdu.app00.service.NoticeService;

@RequestMapping("/notice")
@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	@GetMapping("/notice.do")
	public String notice(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 현재 페이지
//		int page = request.getParameter("page").isEmpty() ? 1 : Integer.parseInt(request.getParameter("page"));
		// 전체 목록 수
		int totalRecordCnt = noticeService.selectAllNotice();
		// 1페이지에 표시할 목록 수
		int recordPerPage = 10;
		
		int begin = (page-1)*recordPerPage + 1;
		int end = begin + recordPerPage - 1;
		if(end > totalRecordCnt) {
			end = totalRecordCnt;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		
		List<NoticeDTO> noticeList = noticeService.getNotices(map);
		
		model.addAttribute("noticeList", noticeList);

		// 전체 페이지 수
		int totalPageCnt = totalRecordCnt / recordPerPage;
		// 22개의 게시글이 있으면 totalPageCnt는 3이 되어야함
		if(totalRecordCnt % recordPerPage > 0) {
			totalPageCnt++;
		}
		
		int pagePerBlock = 3;
		
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPageCnt) {
			endPage = totalPageCnt;
		}
		
		model.addAttribute("page", page);
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPageCnt", totalPageCnt);
		model.addAttribute("pagePerBlock", pagePerBlock);
		
		
		return "notice/notice";
	}
	
}
