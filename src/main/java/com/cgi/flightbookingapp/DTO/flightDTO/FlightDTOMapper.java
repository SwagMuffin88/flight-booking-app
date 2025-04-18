package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTOMapper;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTOMapper;
import com.cgi.flightbookingapp.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service @RequiredArgsConstructor
public class FlightDTOMapper implements Function<Flight, FlightDTO> {
    private final LocationDTOMapper locationDTOMapper;
    
    @Override
    public FlightDTO apply(Flight flight) {
        return new FlightDTO(
                flight.getId(),
                locationDTOMapper.apply(
                        flight.getOrigin()), 
                locationDTOMapper.apply(
                        flight.getDestination()),
                flight.getPlane().getName(),
                flight.getDepartureTime(),
                flight.getPrice()
        );
    }
}
