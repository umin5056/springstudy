package com.gdu.app01.xml_into_java;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// xml_into_java 디렉터리에 있는 app-context.xml에 등록된 bean을 전부 호출됨
@ImportResource("xml_into_java/app-context.xml")


@Configuration
public class AppContext {
		
	
	
}
