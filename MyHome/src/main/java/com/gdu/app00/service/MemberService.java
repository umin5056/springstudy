package com.gdu.app00.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app00.domain.MemberDTO;

public interface MemberService {
	public MemberDTO login(HttpServletRequest request);
	public int joinMember(HttpServletRequest request);
	public int deleteMember(HttpServletRequest request);
}
