package com.codeup.maktrak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MaktrakApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MaktrakApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MaktrakApplication.class);
	}

}
