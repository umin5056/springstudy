package com.gdu.movie;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.movie.domain.MovieDTO;
import com.gdu.movie.mapper.MovieMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})


public class MovieTestCase {

	@Autowired
	private MovieMapper movieMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieTestCase.class);
	
	@Test
	public void 조회테스트() {
		List<MovieDTO> list = movieMapper.getAllMovies();
		LOGGER.info(list.toString());
		assertNotNull(list);
	}
	
	
}
