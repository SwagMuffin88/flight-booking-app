package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightDTOMapper flightDTOMapper;
    
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightDTOMapper)
                .collect(Collectors.toList());
    }
    
}
