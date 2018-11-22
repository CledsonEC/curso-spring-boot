package br.com.devdojo.awesome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@EnableAutoConfiguration
//@ComponentScan
//@Configuration
//Quando utiliza SpringBootApplication está utilizando @EnableAutoConfiguration,@ComponentScan e @Configuration  

@SpringBootApplication
public class SpringBootEssentialsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEssentialsApplication.class, args);
	}
}
