package com.gdu.app03.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmiVO {

	private double height;
	private double weight;
	private double bmi;
	private String obesity;
	
}
