package com.UserService.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserService.entities.User;
import com.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserContoller {

  Logger logger = org.slf4j.LoggerFactory.getLogger(UserContoller.class);

  @Autowired
  private UserService userService;

  // create
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    // code
    User user1 = userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(user1);

  }

  // single user get
  @GetMapping("/{userId}")
  // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod =
  // "ratingHotelFallback")
  // @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
  @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
  public ResponseEntity<User> getSingleUser(@PathVariable int userId) {
    User user = userService.getUserById(userId);
    return ResponseEntity.ok(user);
  }

  // creating fallback method for circuit breaker

  public ResponseEntity<User> ratingHotelFallback(int userId, Throwable ex) {
    logger.info("Fallback is executed because service is down : ", ex.getMessage());
    User user = User.builder()
        .email("dummy@gmail.com")
        .name("Dummy")
        .about("This user is created because some service is down")
        .userId((userId)).build();
    return new ResponseEntity<>(user, HttpStatus.OK);

  }

  // get all user
  @GetMapping
  public ResponseEntity<List<User>> getAllUser() {
    List<User> user = userService.getAllUsers();
    return ResponseEntity.ok(user);
  }

  // delete user with given id
  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();

  }

  // any method u can create

  // update user
  @PostMapping("{userId}")
  public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int userId) {
    User updatedUser = userService.updateUser(user, userId);
    return ResponseEntity.ok(updatedUser);
  }

}
