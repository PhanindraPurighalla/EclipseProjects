package com.aarnasolutions.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.aarnasolutions")
@EnableJpaRepositories(basePackages = "com.aarnasolutions.repo")
@EntityScan(basePackages = "com.aarnasolutions.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}