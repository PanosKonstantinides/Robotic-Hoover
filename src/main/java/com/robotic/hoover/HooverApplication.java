package com.robotic.hoover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * The starting point of the application.
 * 
 * @author panos
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Hoover API", version = "1.0", description = "Hoover"))
public class HooverApplication {

	/**
	 * The main method. Starts the application.
	 * 
	 * @param args	the parameters that can be passed ot this method.
	 */
	public static void main(String[] args) {
		SpringApplication.run(HooverApplication.class, args);
	}

}
