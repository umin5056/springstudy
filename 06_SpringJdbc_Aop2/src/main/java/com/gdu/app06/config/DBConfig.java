package com.gdu.app06.config;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableAspectJAutoProxy // AOP 동작을 허용한다.
@EnableTransactionManagement
@Configuration
public class DBConfig {

	// DriverManagerDataSource Bean
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("GDJ61");
		dataSource.setPassword("1111");
		return dataSource;
	}
	
	// JdbcTemplate Bean (Connection, PreparedStatement, ResultSet
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource()); // DriverManagerDataSource Bean을 JdbcTemplate 생성자에 주입
	}
	
	// DataSourceTranscationManager Bean
	@Bean
	public TransactionManager transcationManager() {
		//DriverManagerDataSource Bean을 DataSourceTransactionManager 생성자에 삽
		return new DataSourceTransactionManager(dataSource()); 
	}
	
	// 아래 부분은 AOP를 이용해서 트랜잭션 처리를 하기 위한 Bean
	
	// TransactionInterceptor Bean
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		
		// 모든 트랜잭션 처리에서 Exception이 발생하면 Rollback을 수행하시오.
		RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
		ruleBasedTransactionAttribute.setName("*"); // 이름 상관없이 작동
		ruleBasedTransactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		source.setTransactionAttribute(ruleBasedTransactionAttribute);
		
		
		return new TransactionInterceptor(transcationManager(), source);
		
	}
	
	/*
		AOP 관련 핵심 용어 3가지
		1. 조인포인트(JoinPoint)  : AOP를 적용시킬 수 있는 메소드 전체  	- 목록, 상세, 삽입, 수정, 삭제
		2. 포인트컷(PointCut)	  : 조인포인트 중에서 AOP를 동작시킬 메소드	- 삽입, 수정, 삭제
		3. 어드바이스(Advice)	  : 포인트컷에 동작시킬 AOP 동작 전체		- 트랜잭션
	 */
	
	// Advisor Bean
	@Bean
	public Advisor advisor() {
		
		/*
		 	표현식(Expression) 작성 방법
		 	
		 	1. 형식
		 		execution(반환타입 패키지.클래스.메소드(매개변수))
		 	
		 	2. 의미
		 		1) 반환타입
		 			(1) *		: 모든 반환타입
		 			(2) void	: void 반환타입
		 			(3) !void	: void를 제외한 반환타입
		 		2) 매개변수
		 			(1) .. 	: 모든 매개변수
		 			(2) *	: 1개의 모든 매개변
		 */
		
		// 포인트컷 설정(어드바이스(트랜잭션)를 동작시킬 메소드)
		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		pointCut.setExpression("execution(* com.gdu.app06.service.BoardServiceImpl.*Tx(..))"); // BoardServiceImpl 클래스에 있는 메소드 중에서 이름이 Tx로 끝나는 메소
		
		// pointCut으로 등록된 메소드에 transactionInterceptor() 트랜잭션을 동작시킨다.	
		return new DefaultPointcutAdvisor(pointCut, transactionInterceptor());
		
	}
	
	
	
	
	
}
