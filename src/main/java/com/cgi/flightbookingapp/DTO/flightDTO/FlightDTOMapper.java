package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTOMapper;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTOMapper;
import com.cgi.flightbookingapp.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightDTOMapper implements Function<Flight, FlightDTO> {
    private final LocationDTOMapper locationDTOMapper;
    private final FlightSeatDTOMapper flightSeatDTOMapper;
    
    @Override
    public FlightDTO apply(Flight flight) {

        List<FlightSeatDTO> seats = flight.getFlightSeats()
                .stream()
                .map(flightSeatDTOMapper)
                .collect(Collectors.toList());
        
        return new FlightDTO(
                flight.getId(),
                locationDTOMapper.apply(
                        flight.getOrigin()), 
                locationDTOMapper.apply(
                        flight.getDestination()),
                flight.getPlane().getName(),
                flight.getDepartureTime(),
//                seats,
                flight.getPrice()
        );
    }
}
