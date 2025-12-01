package com.HotelService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelService.Services.HotelService;
import com.HotelService.entities.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelControllers {

  @Autowired
  public HotelService hotelService;

  // create
  @PostMapping
  public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
  }

  // get single hotel
  @GetMapping("/{hotelId}")
  public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
  }

  // get all hotel
  @GetMapping
  public ResponseEntity<java.util.List<Hotel>> getAllHotels() {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotels());
  }
}
