package com.gdu.prj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class WineDTO {
	private int wineNo;
	private String korName;
	private String engName;
	private String caption;
	private String price;
}
