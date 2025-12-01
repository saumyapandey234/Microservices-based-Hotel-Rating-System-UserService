package com.HotelService.Services;

import java.util.List;

import com.HotelService.entities.Hotel;

public interface HotelService {

  Hotel createHotel(Hotel hotel);

  // getAll hotels
  List<Hotel> getAllHotels();

  // get single hotel
  Hotel getHotel(String hotelId);

}
