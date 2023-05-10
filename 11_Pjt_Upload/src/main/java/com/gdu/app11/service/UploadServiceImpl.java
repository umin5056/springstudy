package com.gdu.app11.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor // 모든 field의 Autowired 처리를 위해
@Service
public class UploadServiceImpl implements UploadService {

	//field
	private UploadMapper uploadMapper;
	private MyFileUtil myFileUtil;
	
	/* ****** 권장사항 : pagination 처리 해보기 ****** */
	@Override
	public void getUploadList(Model model) {
		List<UploadDTO> uploadList = uploadMapper.getUploadList();
		
		model.addAttribute("uploadList", uploadList);
	}
	
	@Transactional(readOnly = true) // readOnly는 트랜잭션 성능의 향상을 위해 추가
	// ㄴ>2개의 쿼리문을 한 메소드에서 실행할 때 메소드 위에 추가하는 애너테이션
	@Override
	public int addUpload(MultipartHttpServletRequest multipartRequest) {

		/* Upload 테이블에 UploadDTO 넣기 */
		
		// 제목, 내용 파라미터
		String uploadTitle = multipartRequest.getParameter("uploadTitle");
		String uploadContent = multipartRequest.getParameter("uploadContent");
		
		// DB로 보낼 UploadDTO 만들기
		UploadDTO uploadDTO = new UploadDTO();
		uploadDTO.setUploadTitle(uploadTitle);
		uploadDTO.setUploadContent(uploadContent);
		
		// DB로 UploadDTO 보내기
		int uploadResult = uploadMapper.addUpload(uploadDTO);
		// ㄴ> <selectKey>에 의해서 uploadDTO의 uploadNo에 UPLOAD_SEQ.NEXTVAL 값이 저장된다.
		
		/* Attach 테이블에 AttachDTO 넣기 */
		
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file" name="files">

		// 첨부된 파일의 유무 체크
		if(files != null && files.isEmpty() == false) {
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
					
					/* 썸네일(첨부 파일이 이미지인 경우에만 썸네일이 가능) */
					// 첨부 파일의 Content-Type 확인
					String contentType = Files.probeContentType(file.toPath()); // 이미지 파일의 ContentType : image/jpeg, image/png, image/gif...

					// DB에 썸네일 유무 정보 처리
					boolean hasThumbnail = contentType != null && contentType.startsWith("image");

					// 첨부 파일의 Content-Type이 이미지로 확인되면 썸네일을 만듬
					if(hasThumbnail) {
						
						// HDD에 썸네일 저장 (thumbnailator 디펜던시)
						File thumbnail = new File(dir, "s_"  + filesystemName);
						Thumbnails.of(file)
							.size(50, 50)
							.toFile(thumbnail);
					}
					
					/* DB에 첨부 파일 정보 저장하기 */
					
					// DB로 보낼 AttachDTO 만들기
					AttachDTO attachDTO = new AttachDTO();
					attachDTO.setFilesystemName(filesystemName);
					attachDTO.setHasThumbnail(hasThumbnail ? 1 : 0);
					attachDTO.setOriginName(originName);
					attachDTO.setPath(path);
					attachDTO.setUploadNo(uploadDTO.getUploadNo());
					
					// DB로 AttachDTO 보내기
					uploadMapper.addAttach(attachDTO);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return uploadResult;
	}

	@Override
	public void getUploadByNo(int uploadNo, Model model) {
		model.addAttribute("upload", uploadMapper.getUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.getAttachList(uploadNo));
	}

	@Override
	public ResponseEntity<byte[]> display(int attachNo) {
		
		AttachDTO attachDTO = uploadMapper.getAttachByNo(attachNo);
		
		ResponseEntity<byte[]> image = null;
		try {
			File thumbnail = new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName());
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(thumbnail) , HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public ResponseEntity<Resource> download(int attachNo, String userAgent) {
		
		// 다운로드 할 첨부 파일의 정보 가져오기
		AttachDTO attachDTO = uploadMapper.getAttachByNo(attachNo);
		
		// 다운로드 할 첨부 파일의 File 객체 -> Resource 객체
		File file = new File(attachDTO.getPath(), attachDTO.getFilesystemName());
		Resource resource = new FileSystemResource(file);
		
		// 다운로드 할 첨부 파일의 존재 여부 확인(다운로드 실패)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		// 다운로드 횟수 증가하기
		uploadMapper.increaseDownloadCount(attachNo);
		
		// 다운로드 되는 파일명(첨부 파일의 원래 이름, UserAgent(브라우저)에 따른 인코딩 세팅)
		String originName = attachDTO.getOriginName();
		try {
			
			// IE (UserAgent에 Trident가 포함되어 있다.)
			if(userAgent.contains("Trident")) {
				// 인코딩했을 떄 " "은 "+"로 인코딩 되기 때문에 replace로 수정해줌
				originName = URLEncoder.encode(originName,"UTF-8").replace("+", " ");
			}
			
			// Edge (UserAgent에 Edg가 포함되어 있다.)
			else if(userAgent.contains("Edg")) {
				originName = URLEncoder.encode(originName,"UTF-8");
			}
			
			// 나머지 브라우저
			else {
				// "ISO-8859-1" : 확장 아스키 코드 (구글링해서 정리하기)
				originName = new String(originName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 응답 헤더 만들기 (Jsp/Servlet 코드)
		/*
			MultiValueMap<String, String> responseHeader = new HttpHeaders();
			responseHeader.add("Content-Disposition", "attachment; filename=" + originName);
			responseHeader.add("Content-Length", file.length() + "");
		*/
		// 다운로드 응답 헤더 만들기 (Spring 코드)
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.setContentDisposition(ContentDisposition
												.attachment()
												.filename(originName)
												.build());	
		return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Resource> downloadAll(int uploadNo) {
		
		// 모든 첨부 파일을 zip 파일로 압축해서 다운로드 하는 서비스
		
		// zip 파일이 저장될 경로
		String tempPath = myFileUtil.getTempPath();
		File dir = new File(tempPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// zip 파일의 이름
		String tempfileName = myFileUtil.getTempfileName();
		
		// zip 파일의 File 객체
		File zfile = new File(tempPath, tempfileName);
		
		// zip 파일을 생성하기 위한 Java IO Stream 선언
		BufferedInputStream bin = null;		// 각 첨부 파일을 읽어 들이는 스트림
		ZipOutputStream zout = null; 	// zip 파일을 만드는 스트림
		
		// 다운로드 할 첨부 파일들의 정보(경로, 원래 이름, 저장된 이름) 가져오기
		List<AttachDTO> attachList = uploadMapper.getAttachList(uploadNo);
		
		try {
				
			// ZipOutputStream 객체 생성
			zout = new ZipOutputStream(new FileOutputStream(zfile));
			
			// 첨부 파일들 순회하면서 읽어 들인 뒤 zip 파일에 추가하기 + 각 첨부 파일들의 다운로드 횟수 증가 시키기
			for(AttachDTO attachDTO : attachList) {
				
				// zip 파일에 추가할 첨부 파일 이름 등록(첨부 파일의 원래 이름)
				ZipEntry zipEntry = new ZipEntry(attachDTO.getOriginName());
				zout.putNextEntry(zipEntry);
				
				// zip 파일에 첨부 파일 추가
				bin = new BufferedInputStream(new FileInputStream(new File(attachDTO.getPath(), attachDTO.getFilesystemName())));
				// bin -> zout으로 파일 복사하기 (자바 버전)
				byte[] b = new byte[1024]; // 1KB단위로 복사하겠다.	
				int readByte =0;		   // 실제로 읽어 들인 바이트 수	
				
				while((readByte = bin.read(b)) != -1) {
					zout.write(b, 0, readByte);
				} 
				
				// 다 읽어 들이면 닫기
				bin.close();
				// 엔트리만 닫기
				zout.closeEntry();
				
				uploadMapper.increaseDownloadCount(attachDTO.getAttachNo());
			}
			// 모든 파일 복사가 끝나면 닫기
			zout.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 할 zip 파일의 File 객체 -> Resource 객체
		Resource resource = new FileSystemResource(zfile);
		
		// 다운로드 응답 헤더 만들기
		MultiValueMap<String, String> responseHeader = new HttpHeaders();
		responseHeader.add("Content-Disposition", "attachment; filename=" + tempfileName);
		responseHeader.add("Content-Length", zfile.length() + "");
		
		// 응답
		return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
	}
	
}
