package com.zulfan.personal_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class PersonalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalWebApplication.class, args);
	}

}
