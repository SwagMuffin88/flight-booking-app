package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTOMapper;
import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightDTOMapper flightDTOMapper;
    private final FlightSeatDTOMapper flightSeatDTOMapper;

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightDTOMapper)
                .collect(Collectors.toList());
    }
    
    private Flight getFlightById(Long id) throws ResourceNotFoundException {
        return flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight with id " + id + " not found")
                );
    }

    public FlightDTO getFlightAsDTOById(Long id) throws ResourceNotFoundException {
        return flightDTOMapper.apply(
                getFlightById(id)
        );
    }

    private List<FlightSeat> getFlightSeatsForFlight(Long flightId) throws ResourceNotFoundException {
        return getFlightById(flightId)
                .getFlightSeats();
    }
    
    public List<FlightSeatDTO> getFlightSeatsForFlightAsDTO(Long flightId) throws ResourceNotFoundException {
        List<FlightSeat> flightSeats = getFlightSeatsForFlight(flightId);
        return flightSeats.stream()
                .map(flightSeatDTOMapper)
                .toList();
    }
    
    // get all flights by destination
    // get all flights within date range
    // get flights ordered by price
    // get flights ordered by date -> earliest first
    
}
