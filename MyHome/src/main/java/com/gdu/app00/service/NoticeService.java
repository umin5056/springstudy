package com.gdu.app00.service;

import java.util.List;
import java.util.Map;

import com.gdu.app00.domain.NoticeDTO;

public interface NoticeService {

	public List<NoticeDTO> getNotices(Map map);
	public int selectAllNotice();
}
