package com.generation.projeto1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })


public class PrimeiraAulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiraAulaApplication.class, args);
	}

}
