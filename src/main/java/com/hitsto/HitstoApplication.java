package com.hitsto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HitstoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HitstoApplication.class, args);
	}

}
