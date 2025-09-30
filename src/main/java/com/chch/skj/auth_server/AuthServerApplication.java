package com.chch.skj.auth_server;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@PostConstruct
	public void init() {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		
	}
}
