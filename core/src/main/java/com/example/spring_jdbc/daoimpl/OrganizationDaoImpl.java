package com.example.spring_jdbc.daoimpl;

import com.example.spring_jdbc.dao.OrganizationDao;
import com.example.spring_jdbc.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public OrganizationDaoImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        template.getJdbcTemplate().setDataSource(dataSource);
    }

    @Override
    public boolean create(Organization org) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(org);
        String statement = "INSERT INTO organization (company_name, year_of_incorporation," +
                " postal_code, employee_count, slogan) VALUES (:companyName, :yearOfIncorporation, " +
                ":postalCode, :employeeCount, :slogan)";

        return template.update(statement, params) == 1;
    }

    @Override
    public Organization getOrganization(Integer id) {
        SqlParameterSource params = new MapSqlParameterSource("ID", id);
        String statement = "SELECT * FROM organization WHERE id = :ID";
        return template.queryForObject(statement, params, new OrganizationMapper());
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return template.query("SELECT * FROM organization;", new OrganizationMapper());
    }

    @Override
    public boolean delete(Organization org) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(org);
        String statement = "DELETE FROM organization WHERE id=:id";
        return template.update(statement, params) == 1;
    }

    @Override
    public boolean update(Organization org) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(org);
        String statement = "UPDATE organization SET slogan = :slogan WHERE id = :id";
        return template.update(statement, params) == 1;
    }

    @Override
    public void cleanup() {
        String statement = "TRUNCATE TABLE organization";
        template.getJdbcTemplate().execute(statement);
    }
}
