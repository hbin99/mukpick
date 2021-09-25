package com.jumpstd.mukpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MukpickApplication {

	public static void main(String[] args) {
		SpringApplication.run(MukpickApplication.class, args);
	}

}
