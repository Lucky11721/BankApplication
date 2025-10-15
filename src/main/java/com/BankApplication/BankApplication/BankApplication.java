package com.BankApplication.BankApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Lucky Bank Application",
				description = "Backend Rest ApIs for Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Lucky",
						email = "gautamlucky@gmail.com",
						url = "https://github.com/Lucky11721/BankApplication"

				)

		)
)
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
