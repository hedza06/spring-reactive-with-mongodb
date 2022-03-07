package com.hedza06.reactivespringwithmongo.producers;

import com.hedza06.reactivespringwithmongo.dao.EmployeeDao;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Component
public class EmployeeProducer {

    public Flux<EmployeeDao> stream(EmployeeDao employeeDao)
    {
        return Flux.fromStream(
            Stream.generate(() -> new EmployeeDao(employeeDao.getFullName() + " - " + Instant.now(), 30))
        ).delayElements(Duration.ofSeconds(1));
    }

}
