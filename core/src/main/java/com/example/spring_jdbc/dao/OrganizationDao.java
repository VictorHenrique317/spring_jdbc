package com.example.spring_jdbc.dao;

import com.example.spring_jdbc.domain.Organization;

import javax.sql.DataSource;
import java.util.List;

public interface OrganizationDao {

    // Set the data-source that will be required to create a connection to the datbase
    void setDataSource(DataSource dataSource);

    // Create a record in the organization table
    boolean create(Organization org);

    // Retrieve a single organization
    Organization getOrganization(Integer id);

    // Retrieve all organizations from the table
    List<Organization> getAllOrganizations();

    // Delete a specific organization on the table
    boolean delete(Organization org);

    // Update an existing organization
    boolean update(Organization org);

    void cleanup();
}
