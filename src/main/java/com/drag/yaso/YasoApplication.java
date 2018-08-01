package com.drag.yaso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages = { "com.drag.yaso"})
@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
public class YasoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YasoApplication.class, args);
	}
}
