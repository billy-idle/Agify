package com.github.agify.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorHttpExchangesConfiguration {

    @Value("${http-exchange-repository-capacity}")
    private int capacity;

    @Bean
    public HttpExchangeRepository httpExchangeRepository() {
        InMemoryHttpExchangeRepository httpExchangeRepository = new InMemoryHttpExchangeRepository();
        httpExchangeRepository.setCapacity(capacity);
        return httpExchangeRepository;
    }
}
