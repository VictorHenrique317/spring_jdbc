package com.example.spring_jdbc;

import com.example.spring_jdbc.daoimpl.OrganizationDaoImpl;
import com.example.spring_jdbc.domain.Organization;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@SpringBootApplication
@PropertySource("classpath:organization.properties")
public class SpringJdbcApplication {
	@Autowired
	private ApplicationContext context;

	public static void main(String[] args){
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@PostConstruct
	public void init(){
		List<Organization> orgs =  context.getBean(OrganizationDaoImpl.class).getAllOrganizations();
		for (Organization org : orgs){
			log.info(org.getCompanyName());
		}
	}

    @Bean
	public DataSource dataSource(
			@Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password,
			@Value("${jdbc.driverClassName}") String driverClassName,
			@Value("${jdbc.url}") String url){

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		return dataSource;
	}

}
