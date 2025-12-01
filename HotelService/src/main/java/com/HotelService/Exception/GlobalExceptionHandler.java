package com.HotelService.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException ex) {
    Map map = new HashMap();
    map.put("message", ex.getMessage());
    map.put("success", false);
    map.put("status", 404);
    return ResponseEntity.status(404).body(map);

  }

}
