package com.hedza06.reactivespringwithmongo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDao {

    private String fullName;
    private Integer age;

    public EmployeeDao(String fullName) {
        this.fullName = fullName;
    }
}
