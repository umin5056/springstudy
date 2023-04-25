package com.gdu.app00.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app00.domain.NoticeDTO;
import com.gdu.app00.repository.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDAO noticeDao;
	
	@Override
	public List<NoticeDTO> getNotices(Map map) {
		
		return noticeDao.selectNotices(map);
	}
	
	@Override
	public int selectAllNotice() {
		return noticeDao.selectAllNotice();
	}

}
