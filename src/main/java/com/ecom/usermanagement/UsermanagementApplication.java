package com.ecom.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class UsermanagementApplication {

	public static void main(String[] args) {
		log.info("Booting usermanageting app...");
		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
