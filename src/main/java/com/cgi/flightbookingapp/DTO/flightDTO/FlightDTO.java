package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTO;
import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTO;
import com.cgi.flightbookingapp.model.seat.FlightSeat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FlightDTO(
        Long id,
        LocationDTO origin,
        LocationDTO destination,
        String planeName,     // Instead of a Plane DTO, which causes cyclical dependency
        LocalDateTime departureTime,
        List<FlightSeatDTO> seats,
        BigDecimal price
) {
}
