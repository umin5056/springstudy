package com.gdu.app00.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app00.domain.NoticeDTO;

@Repository
public class NoticeDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "mybatis.mapper.notice.";
	
	public List<NoticeDTO> selectNotices(Map map){
		return sqlSessionTemplate.selectList(NS + "selectNotices", map);
	}
	
	public int selectAllNotice() {
		return sqlSessionTemplate.selectOne(NS + "selectAllNotice");
	}
	
	
}
