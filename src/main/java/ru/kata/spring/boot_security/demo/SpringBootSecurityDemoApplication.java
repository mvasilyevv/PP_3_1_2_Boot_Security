package ru.kata.spring.boot_security.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.config.DatabaseInit;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		context.getBean(DatabaseInit.class).addFirstUser();
		context.getBean(DatabaseInit.class).addSecondUser();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}


