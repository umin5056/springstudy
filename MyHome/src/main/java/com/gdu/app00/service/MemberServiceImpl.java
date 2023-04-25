package com.gdu.app00.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app00.domain.MemberDTO;
import com.gdu.app00.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public MemberDTO login(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		
		return memberDao.selectLogin(member);
	}
	
	@Override
	public int joinMember(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		
		
		return memberDao.insertMember(member);
	}
	
	@Override
	public int deleteMember(HttpServletRequest request) {

		String strMemberNo = request.getParameter("memberNo");
		int memberNo = 0;
		if(strMemberNo.isEmpty() == false || strMemberNo != "") {
			memberNo = Integer.parseInt(strMemberNo);
		}
		
		return memberDao.deleteMember(memberNo);
	}

}
