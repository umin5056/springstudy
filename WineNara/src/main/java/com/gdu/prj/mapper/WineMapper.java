package com.gdu.prj.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.prj.domain.WineDTO;

@Mapper
public interface WineMapper {

	public WineDTO getAllItems();
	
}
