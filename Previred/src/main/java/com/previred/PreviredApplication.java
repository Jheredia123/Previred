package com.previred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PreviredApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreviredApplication.class, args);
	}

}
