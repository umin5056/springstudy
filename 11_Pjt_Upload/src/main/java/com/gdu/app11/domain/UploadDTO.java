package com.gdu.app11.domain;

import java.sql.Timestamp;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadDTO {

	private int uploadNo;
	private String uploadTitle;
	private String uploadContent;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private int attachCount; // UPLOAD 테이블에는 없는 칼럼이지만, 목록 보기에서 정보를 저장하기 위해서 사용
	
}
