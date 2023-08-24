package com.portfolio.ohousev1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OhouseV1Application {

    public static void main(String[] args) {
        SpringApplication.run(OhouseV1Application.class, args);
    }

}
