package com.jay.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;


@Slf4j
@SpringBootApplication
public class MyWebAppApplication {

	public static void main(String[] args) {
		run(MyWebAppApplication.class, args);
	}

}
