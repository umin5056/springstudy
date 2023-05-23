package com.gdu.movie.batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.domain.QueryDTO;
import com.gdu.movie.mapper.MovieMapper;

//@Component
//@EnableScheduling
public class FindComedyScheduler {
	
	@Autowired
	private MovieMapper movieMapper;
	
//	@Scheduled(cron="0 1/1 * 1/1 * ?")
	public void execute() {
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setColumn("GENRE");
		queryDTO.setSearchText("코미디");
		
		List<MovieDTO> list = movieMapper.getMoviesByQuery(queryDTO);
		
		String fileName = "";
		String content = "";
		
		if(list.isEmpty()) {
			fileName = "/Users/woomin/Documents/error.txt";
			content = "코미디 검색 결과가 없습니다.";
		} else {
			fileName = "/Users/woomin/Documents/코미디.txt";

			for(MovieDTO movie : list) {
				content += "제목 : "  + movie.getTitle() + "\n";
				content += "장르 : "  + movie.getGenre() + "\n";
				content += "개요 : "  + movie.getDescription() + "\n";
				content += "평점 : "  + movie.getStar() + "\n";
			}
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName, true));
			
			bw.write(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	
	}
}
