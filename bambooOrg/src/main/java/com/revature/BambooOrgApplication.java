package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
@SpringBootApplication
public class BambooOrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(BambooOrgApplication.class, args);
	}

}
