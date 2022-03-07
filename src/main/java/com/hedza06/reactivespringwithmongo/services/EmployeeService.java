package com.hedza06.reactivespringwithmongo.services;

import com.hedza06.reactivespringwithmongo.entities.Employee;
import com.hedza06.reactivespringwithmongo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Mono<Employee> save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
