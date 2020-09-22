package com.example.spring_jdbc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class Organization {
    private int id;
    private String companyName;
    private int yearOfIncorporation;
    private String postalCode;
    private int employeeCount;
    private String slogan;

    public Organization() {
    }

    public Organization(String companyName, int yearOfIncorporation, String postalCode, int employeeCount, String slogan) {
        this.companyName = companyName;
        this.yearOfIncorporation = yearOfIncorporation;
        this.postalCode = postalCode;
        this.employeeCount = employeeCount;
        this.slogan = slogan;
    }
}
