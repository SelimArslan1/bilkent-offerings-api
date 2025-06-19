package com.example.bilkent_offerings_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
public class BilkentOfferingsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilkentOfferingsApiApplication.class, args);
	}

}
