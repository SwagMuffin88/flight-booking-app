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
                    flightService.getAllFlightsAsDTOs(),
                    HttpStatus.OK
            );
    }
    
    // Get specific individual flight
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

    // Get all flights filtered by destination
    @GetMapping("/flights/by-destination/{airportNameShort}")
    public ResponseEntity<List<FlightDTO>> getAllFlightsByDestination(@PathVariable String airportNameShort) {
        try {
            return new ResponseEntity<>(
                    flightService.getFlightDTOsByDestination(airportNameShort),
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all seats sorted by given property (price/departure time)
    @GetMapping("/flights/sorted-by/{property}")
    public ResponseEntity<List<FlightDTO>> getAllFlightsSortedByProperty(
            @PathVariable String property,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        try {
            return new ResponseEntity<>(
                    flightService.getAllFlightDTOsSortedByProperty(property, direction),
                    HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Get seats of individual flight
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
