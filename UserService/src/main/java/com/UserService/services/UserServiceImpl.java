package com.UserService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.UserService.Exceptions.ResourceNotFoundExceptions;
import com.UserService.External.Services.HotelService;
import com.UserService.Repo.UserRepo;
import com.UserService.entities.Hotel;
import com.UserService.entities.Rating;
import com.UserService.entities.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private HotelService hotelService;

  private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  // For clean debugging & production logging

  @Override
  public User saveUser(User user) {
    return userRepo.save(user);
  }

  // get single user
  public User getUserById(int userId) {

    // 1. Get user from DB
    User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundExceptions("User not found with id: " + userId));

    // 2. Fetch ratings from Rating Service (TYPE SAFE)
    // List<Rating> ratingList = restTemplate.exchange(
    // "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
    // HttpMethod.GET,
    // null,
    // new ParameterizedTypeReference<List<Rating>>() {
    // }).getBody();

    // 3. For each rating, fetch hotel
    // List<Rating> finalList = ratingList.stream().map(rating -> {

    // ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity(
    // "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
    // Hotel.class);

    // rating.setHotel(hotelEntity.getBody());
    // return rating;

    // }).collect(Collectors.toList());

    // 4. Set the correct list
    // user.setRatings(finalList);

    // return user;

    // Using Feign Client to fetch Hotel
    Rating[] ratings = restTemplate.getForObject(
        "http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
    List<Rating> ratingList = Arrays.asList(ratings);
    List<Rating> finalList = ratingList.stream().map(rating -> {
      Hotel hotel = hotelService.getHotel(rating.getHotelId());
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());
    user.setRatings(finalList);
    return user;
  }

  // ArrayList<Rating> ratings = restTemplate.getForObject(
  // "http://localhost:8088/ratings/users/1", ArrayList.class);
  // logger.info("Fetched Ratings: {}", ratings);
  // user.setRatings(ratings);
  // return user;

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public void deleteUser(int userId) {
    userRepo.deleteById(userId);
  }

  @Override
  public User updateUser(User user, int userId) {
    User existingUser = userRepo.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    existingUser.setName(user.getName());
    existingUser.setEmail(user.getEmail());
    existingUser.setAbout(user.getAbout());

    return userRepo.save(existingUser);
  }

}
