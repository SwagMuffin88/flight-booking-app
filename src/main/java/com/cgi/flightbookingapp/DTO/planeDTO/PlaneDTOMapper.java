package com.cgi.flightbookingapp.DTO.planeDTO;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.DTO.seatDTO.SeatDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.SeatDTOMapper;
import com.cgi.flightbookingapp.model.Plane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PlaneDTOMapper implements Function<Plane, PlaneDTO> {
    private final FlightDTOMapper flightDTOMapper;
    
    private final SeatDTOMapper seatDTOMapper;
    
    @Override
    public PlaneDTO apply(Plane plane) {

        List<FlightDTO> flights = plane.getFlights()
                .stream()
                .map(flightDTOMapper)
                .collect(Collectors.toList());
        
        List<SeatDTO> seats = plane.getSeats()
                .stream()
                .map(seatDTOMapper)
                .collect(Collectors.toList());

        return new PlaneDTO(
                plane.getName(),
                plane.getNumRows(),
                plane.getNumColumns(),
                flights,
                seats
        );
    }
}
