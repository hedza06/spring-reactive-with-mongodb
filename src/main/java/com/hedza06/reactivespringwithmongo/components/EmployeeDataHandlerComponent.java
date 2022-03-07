package com.hedza06.reactivespringwithmongo.components;

import com.hedza06.reactivespringwithmongo.entities.Employee;
import com.hedza06.reactivespringwithmongo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeDataHandlerComponent {

    private final EmployeeRepository employeeRepository;

    /**
     * Handling application ready event and:
     * - delete all records from DB
     * - add initial records for testing purpose
     */
    @EventListener(ApplicationReadyEvent.class)
    public void go()
    {
        var names = Flux.just("Heril", "Mark", "John", "Josh", "Markus")
            .map(name -> new Employee(null, name, new Date()))
            .flatMap(employeeRepository::save);

        employeeRepository
            .deleteAll()
            .thenMany(names)
            .thenMany(employeeRepository.findAll())
            .subscribe(employee -> log.info("Employee: {}", employee));
    }
}
