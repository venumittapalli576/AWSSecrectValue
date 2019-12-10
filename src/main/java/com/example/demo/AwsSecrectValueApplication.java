package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsSecrectValueApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSecrectValueApplication.class, args);
		GetSecretValue.getSecret();
	}

}
