package com.hedza06.reactivespringwithmongo.repositories;

import com.hedza06.reactivespringwithmongo.entities.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> { }
