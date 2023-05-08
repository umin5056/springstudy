package com.gdu.prj.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.prj.domain.MemberDTO;

public interface MemberService {
	public MemberDTO login(HttpServletRequest request);
	public MemberDTO autoLogin(String id);
	public int update(HttpServletRequest request) throws Exception;
	public int signup(HttpServletRequest request) throws Exception;
	public int delete(int memberNo);
	
}
