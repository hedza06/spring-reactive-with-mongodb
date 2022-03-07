package com.hedza06.reactivespringwithmongo.controllers;

import com.hedza06.reactivespringwithmongo.entities.Employee;
import com.hedza06.reactivespringwithmongo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<Flux<Employee>> all()
    {
        Flux<Employee> employeeFlux = employeeService.findAll();
        return new ResponseEntity<>(employeeFlux, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Mono<Employee>> oneById(@PathVariable String id)
    {
        Mono<Employee> employeeMono = employeeService.findById(id);
        return new ResponseEntity<>(employeeMono, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mono<Employee>> create(@RequestBody Employee employee)
    {
        Mono<Employee> employeeMono = employeeService.save(employee);
        return new ResponseEntity<>(employeeMono, HttpStatus.CREATED);
    }
}
