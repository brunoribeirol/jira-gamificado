package com.cesar.school.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {
        // todas as classes @Entity dos módulos core/domain
        "com.cesar.school.core",

        // se ainda houver entidades mapeadas na infraestrutura
        "com.cesar.school.infrastructure.persistence.entity"
})
@ComponentScan(basePackages = {
        // camada de aplicação (caso use @Service, @UseCase, etc.)
        "com.cesar.school.application",

        // camada de infraestrutura (repositories, configurações)
        "com.cesar.school.infrastructure",

        // a própria camada web/presentation
        "com.cesar.school.presentation"
})
public class JiraGamificadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraGamificadoApplication.class, args);
    }
}
