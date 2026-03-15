package com.demo.restapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@OpenAPIDefinition(
	info = @Info(
			title = "ChatBot Api",
			version = "1.0",
			description = "API to test a IA model."
	)
)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}
}
