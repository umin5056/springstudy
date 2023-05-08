package com.gdu.prj.service;

import org.springframework.stereotype.Service;

import com.gdu.prj.domain.WineDTO;
import com.gdu.prj.mapper.WineMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class WineSerivceImpl implements WineService {

	private WineMapper wineMapper;
	
	@Override
	public WineDTO getAllItems() {
		System.out.println("여기?");
		return wineMapper.getAllItems();
	}

}
