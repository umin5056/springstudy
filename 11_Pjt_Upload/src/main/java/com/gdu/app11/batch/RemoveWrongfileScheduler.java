package com.gdu.app11.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor // field의 Autowired 처리
@EnableScheduling
@Component
public class RemoveWrongfileScheduler {

	// field
	private MyFileUtil myFileUtil;
	private UploadMapper uploadMapper;
	
	@Scheduled(cron="0 0 2 1/1 * ?") // www.cronmaker.com에서 생성한 크론식의 마지막 *는 지워야함
	public void execute() {
		
		// 어제 업로드 된 첨부 파일들의 정보를 가져오기
		List<AttachDTO> attachList = uploadMapper.getAttachListInYesterday();
		
		List<Path> pathList = new ArrayList<>();
		// List<AttachDTO> -> List<Path>로 변환하기 (Path : 경로)
		if(attachList != null && attachList.isEmpty() == false) {
			
			for(AttachDTO attachDTO : attachList) {
				pathList.add(new File(attachDTO.getPath(), attachDTO.getFilesystemName()).toPath()); // Path 만들기 : File객체.toPath()
				if(attachDTO.getHasThumbnail() == 1) {
					pathList.add(new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName()).toPath()); // Path 만들기 : File객체.toPath()
				}
			}
			
		}
		
		// 어제 업로드 된 경로
		String yesterdayPath = myFileUtil.getYesterdayPath();
		
		// 어제 업로드 된 파일 목록(HDD에서 확인) 중에서 DB에는 정보가 없는 파일 목록
		File dir = new File(yesterdayPath);
		File[] wrongFiles = dir.listFiles(new FilenameFilter() {
			
			@Override
			// true를 반환하면 File[]에 wrongFiles에 포함된다. 매개변수 File dir, String name은 HDD에 저장된 파일을 의미한다.
			public boolean accept(File dir, String name) {
				
				// DB에 있는 목록 : pathList				 - Path
				// HDD에 있는 파일 : File dir, String name  - File.toPath() 처리해서 Path로 변경해야 경로 비교 가능
				
				return pathList.contains(new File(dir,name).toPath()) == false;
			}
		});

		// File[] wrongFiles 모두 삭제
		if(wrongFiles != null && wrongFiles.length != 0) {
			for(File wrongFile : wrongFiles) {
				wrongFile.delete();
			}
		}
		
	}
	
}
