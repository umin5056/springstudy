package com.gdu.app00.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app00.domain.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "mybatis.mapper.member.";
	
	public MemberDTO selectLogin(MemberDTO member) {
		return sqlSessionTemplate.selectOne(NS + "selectLogin", member);
	};
	
	public int insertMember(MemberDTO member) {
		return sqlSessionTemplate.insert(NS + "insertMember", member);
	}
	
	public int deleteMember(int memberNo) {
		return sqlSessionTemplate.delete(NS + "deleteMember", memberNo);
	}
	
	
}
