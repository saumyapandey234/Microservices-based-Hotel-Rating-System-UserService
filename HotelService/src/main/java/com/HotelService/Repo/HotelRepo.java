package com.HotelService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HotelService.entities.Hotel;
public interface HotelRepo extends JpaRepository<Hotel, String> {

}
