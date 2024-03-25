package com.example.napster_project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Codechallenge", version = "v1", description = "Documentation of Codechallenge"))
@SpringBootApplication
public class NapsterProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NapsterProjectApplication.class, args);
    }

}
