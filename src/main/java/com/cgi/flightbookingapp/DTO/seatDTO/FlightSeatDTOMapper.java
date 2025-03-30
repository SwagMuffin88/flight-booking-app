package com.cgi.flightbookingapp.DTO.seatDTO;

import com.cgi.flightbookingapp.model.seat.FlightSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service @RequiredArgsConstructor
public class FlightSeatDTOMapper implements Function<FlightSeat, FlightSeatDTO> {
    
    @Override
    public FlightSeatDTO apply(FlightSeat seat) {
        return new FlightSeatDTO(
                seat.getNumber(),
                seat.isAvailable(),
                seat.getPlacement()
        );
    }
}
