package com.cgi.flightbookingapp.controller;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTO;
import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController @RequiredArgsConstructor
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    
    @GetMapping("/all")
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
            return new ResponseEntity<>(
                    flightService.getAllFlights(),
                    HttpStatus.OK
            );
    }
    
    // get flight by id
    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    flightService.getFlightAsDTOById(id),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
//     get flight seats
    @GetMapping("/flight/{id}/seats")
    public ResponseEntity<List<FlightSeatDTO>> getFlightSeatsById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    flightService.getFlightSeatsForFlightAsDTO(id),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
