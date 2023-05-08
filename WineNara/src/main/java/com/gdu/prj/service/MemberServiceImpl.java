package com.gdu.prj.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.prj.domain.MemberDTO;
import com.gdu.prj.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper memberMapper;

	@Override
	public MemberDTO login(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		
		return memberMapper.login(member);
	}

	@Override
	public MemberDTO autoLogin(String id) {
		
		return memberMapper.autoLogin(id);
	}
	
	@Override
	public int update(HttpServletRequest request) throws Exception {
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		
		MemberDTO member = new MemberDTO();
		
		member.setMemberNo(memberNo);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);
		
		int updateResult = memberMapper.update(member); 
		
		System.out.println(member);
		System.out.println(updateResult);
		
		return updateResult;
	}

	@Override
	public int signup(HttpServletRequest request) throws Exception {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		
		MemberDTO member = new MemberDTO();
		
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		member.setPhoneNumber(phoneNumber);
		member.setAddress(address);

		int insertResult = memberMapper.signup(member);
		
		return insertResult;
	}
	
	@Override
	public int delete(int memberNo) {
		return memberMapper.delete(memberNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
