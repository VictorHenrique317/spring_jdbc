package com.example.spring_jdbc;

import com.example.spring_jdbc.dao.OrganizationDao;
import com.example.spring_jdbc.dao.utils.DaoUtils;
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
	@Autowired private ApplicationContext context;
	@Autowired private OrganizationDao dao;
	@Autowired private DaoUtils daoUtils;

	public static void main(String[] args){
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

	@PostConstruct
	public void init(){
		dao.cleanup();
		// Creating seed data.
		daoUtils.createSeedData(dao);

		// List organizations
		List<Organization> orgs = dao.getAllOrganizations();
		daoUtils.printOrganizations(orgs, daoUtils.readOperation);

		// Create a new organization record
		Organization org = new Organization("General Electric", 1978, "98989", 5776, "Imagination at work");
		boolean isCreated = dao.create(org);
		daoUtils.printSuccessFailure(daoUtils.createOperation, isCreated);
		daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.readOperation);

		// get a single organization
		Organization org2 = dao.getOrganization(1);
		log.info(org2.toString());
		// Updating a slogan for an organization
		Organization org3 = dao.getOrganization(2);
		org3.setSlogan("We build ** awesome ** driving machines!");
		boolean isUpdated = dao.update(org3);
		daoUtils.printSuccessFailure(daoUtils.updateOperation, isUpdated);

		// Delete an organization
		boolean isDeleted = dao.delete(dao.getOrganization(3));
		daoUtils.printSuccessFailure(daoUtils.deleteOperation, isDeleted);
		daoUtils.printOrganizations(dao.getAllOrganizations(), daoUtils.deleteOperation);
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
