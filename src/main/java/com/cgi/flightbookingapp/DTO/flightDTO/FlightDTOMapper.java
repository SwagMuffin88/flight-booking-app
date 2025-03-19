package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTOMapper;
import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTOMapper;
import com.cgi.flightbookingapp.model.flight.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service @RequiredArgsConstructor
public class FlightDTOMapper implements Function<Flight, FlightDTO> {
    private final LocationDTOMapper locationDTOMapper;
//    private final PlaneDTOMapper planeDTOMapper;
    
    @Override
    public FlightDTO apply(Flight flight) {
        return new FlightDTO(
                locationDTOMapper.apply(
                        flight.getOrigin()), 
                locationDTOMapper.apply(
                        flight.getDestination()),
                flight.getPlane().getName(),
                flight.getDepartureTime()
        );
    }
}
