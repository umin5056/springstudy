package com.gdu.app11.service;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor // 모든 field의 Autowired 처리를 위해
@Service
public class UploadServiceImpl implements UploadService {

	//field
	private UploadMapper uploadMapper;
	private MyFileUtil myFileUtil;
	
	@Override
	public int addUpload(MultipartHttpServletRequest multipartRequest) {

		/* Upload 테이블에 UploadDTO 넣기 */
		
		/* Attach 테이블에 AttachDTO 넣기 */
		
		// 첨부된 파일 목록
		
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file" name="files">

		// 첨부된 파일의 유무 체크
		if(files != null && files.isEmpty()) {
			
			// 첨부된 파일 목록 순회
			for(MultipartFile multipartFile : files) {
				
				// 예외 처리
				try {
					
					// 첨부 파일의 저장 경로
					String path = myFileUtil.getPath();
					
					// 경로의 폴더가 없으면 폴더 생성
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();
					}
					
					// 경로 구분자
					String sep = Matcher.quoteReplacement(File.separator);
					
					// 첨부 파일의 원래 이름
					String originName = multipartFile.getOriginalFilename();
					originName = originName.substring(originName.lastIndexOf(sep) + 1); // IE브라우저는 전체 경로가 오기 때문에 마지막에 있는 파일명만 사용한다.
					
					// 첨부 파일의 저장 이름
					String filesystemName = myFileUtil.getFilesystemName(originName);
					
					// 첨부 파일의 File 객체 (HDD에 저장할 첨부 파일)
					File file = new File(dir, filesystemName);
					
					// 첨부 파일에 HDD에 저장
					multipartFile.transferTo(file);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
		return 0;
	}

}
