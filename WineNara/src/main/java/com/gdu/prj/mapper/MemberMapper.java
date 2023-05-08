package com.gdu.prj.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.prj.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO login(MemberDTO memberDTO);
	public MemberDTO autoLogin(String id);
	public int update(MemberDTO memberDTO) throws SQLException;
	public int signup(MemberDTO memberDTO) throws SQLException;
	public int delete(int memberNo);
	
}
