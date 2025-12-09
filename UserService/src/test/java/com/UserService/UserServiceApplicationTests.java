package com.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UserService.External.Services.RatingService;
import com.UserService.entities.Rating;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createTaing() {
		Rating r = Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client")
				.build();
		ratingService.createRating(r);

		System.out.println("Rating created");

	}

}
