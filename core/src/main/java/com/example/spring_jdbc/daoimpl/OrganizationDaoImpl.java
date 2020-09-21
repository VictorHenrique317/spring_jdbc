package com.example.spring_jdbc.daoimpl;

import com.example.spring_jdbc.dao.OrganizationDao;
import com.example.spring_jdbc.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class OrganizationDaoImpl implements OrganizationDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate.setDataSource(dataSource);
    }

    @Override
    public void create(Organization org) {

    }

    @Override
    public Organization getOrganization(Integer id) {
        return null;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query("SELECT * FROM organization;", new OrganizationMapper());
    }

    @Override
    public boolean delete(Organization org) {
        return false;
    }

    @Override
    public boolean update(Organization org) {
        return false;
    }

    @Override
    public void cleanup() {

    }
}
