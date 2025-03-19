package com.cgi.flightbookingapp.controller;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController @RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    
    @GetMapping("/all")
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        try {
            return new ResponseEntity<>(
                    flightService.getAllFlights(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            throw new RuntimeException(); // specify exception
        }
    }
}
