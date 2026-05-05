package com.example.auth_service_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.auth_service_api.repository")
@EntityScan(basePackages = "com.example.common_library.entity")
public class AuthServiceApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApiApplication.class, args);
	}
}
