package com.bazydanychtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@ComponentScan(basePackages = {"com.bazydanychtest"})*/
public class BazyDanychTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BazyDanychTestApplication.class, args);
	}

}
