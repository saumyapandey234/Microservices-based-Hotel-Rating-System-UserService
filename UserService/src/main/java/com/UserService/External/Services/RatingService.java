package com.UserService.External.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.UserService.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

  // get all ratings by userId

  // Post
  @PostMapping("/ratings/")
  public Rating createRating(Rating values);// when we dont created values and have to pass it thuogh body then we use
                                            // Map<String,objects> values type to pass via key value pair but we have
                                            // created our rating class object so we can directly use it.

  // put
  @PutMapping("/ratings/{ratingId}")
  public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

  // delete
  @PostMapping("/ratings/{ratingId}")
  public void deleteRating(@PathVariable("ratingId") String ratingId);

}
