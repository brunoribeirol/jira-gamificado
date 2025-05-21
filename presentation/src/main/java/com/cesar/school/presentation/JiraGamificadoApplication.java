package com.cesar.school.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.cesar.school.infrastructure.persistence.entity"
})
@ComponentScan(basePackages = "com.cesar.school")//{
//        "com.cesar.school.application",
//        "com.cesar.school.infrastructure.configuration",
//        "com.cesar.school.infrastructure.persistence.repository",
//        "com.cesar.school.presentation"
//})
public class JiraGamificadoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JiraGamificadoApplication.class, args);
    }
}
