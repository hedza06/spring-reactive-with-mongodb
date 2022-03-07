package com.hedza06.reactivespringwithmongo.config;

import com.hedza06.reactivespringwithmongo.dao.EmployeeDao;
import com.hedza06.reactivespringwithmongo.producers.EmployeeProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
public class WebSocketConfig {

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler)
    {
        return new SimpleUrlHandlerMapping() {
            {
                setUrlMap(Map.of("/ws/employee-test", webSocketHandler));
                setOrder(10);
            }
        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    WebSocketHandler webSocketHandler(EmployeeProducer employeeProducer)
    {
        return session -> {
            var response = session
                .receive()
                .map(WebSocketMessage::getPayloadAsText)
                .map(EmployeeDao::new)
                .flatMap(employeeProducer::stream)
                .map(EmployeeDao::getFullName)
                .map(session::textMessage);

            return session.send(response);
        };
    }
}
