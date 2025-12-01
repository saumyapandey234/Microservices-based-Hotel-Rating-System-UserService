package com.HotelService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HotelService.Exception.ResourceNotFoundException;
import com.HotelService.Repo.HotelRepo;
import com.HotelService.entities.Hotel;

@Service
public class HotelServiceImpl implements HotelService {
  @Autowired
  private HotelRepo hotelRepo;

  @Override
  public Hotel createHotel(Hotel hotel) {
    // String hotelId = java.util.UUID.randomUUID().toString();
    // hotel.setId(hotelId);
    // it is included when we have used GenerationType.AUTO in entity class
    return hotelRepo.save(hotel);

  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepo.findAll();

  }

  @Override
  public Hotel getHotel(String hotelId) {
    return hotelRepo.findById(hotelId)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found" + hotelId));

  }

}
