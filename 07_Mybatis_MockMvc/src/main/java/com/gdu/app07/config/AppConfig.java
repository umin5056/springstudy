package com.gdu.app07.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// application.properties 파일의 속성을 읽어 오자!
@PropertySource(value = {"classpath:application.properties"})
// 트랜잭션 처리 허용하기
@EnableTransactionManagement
@Configuration
public class AppConfig {

   @Autowired
   private Environment env;
   
   // HikariConfig Bean
   @Bean
   public HikariConfig hikariConfig() {
      HikariConfig hikariConfig = new HikariConfig();
      hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
      hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
      hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
      hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
      return hikariConfig;
   }
   
   // HikariDataSource Bean
   @Bean(destroyMethod = "close")
   public HikariDataSource hikariDataSource() {
      return new HikariDataSource(hikariConfig());
   }
   
   // SqlSessionFactory Bean
   @Bean
   public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(hikariDataSource());
      bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
      bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
      return bean.getObject();
   }
   
   // SqlSessionTemplate Bean (기존의 SqlSession)
   @Bean
   public SqlSessionTemplate sqlSessionTemplate() throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory());
   }
   
   // TransactionManager Bean
   @Bean
   public TransactionManager transactionManager() {
      return new DataSourceTransactionManager(hikariDataSource());
   }
   
}