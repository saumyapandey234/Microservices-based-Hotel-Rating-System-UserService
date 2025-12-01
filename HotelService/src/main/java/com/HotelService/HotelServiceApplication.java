package com.HotelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient
// it is optional to use @EnableEurekaClient annotation because spring boot
// application will
// automatically register itself with eureka server if spring eureka client
// dependency is added in pom.xml
public class HotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
	}

}
