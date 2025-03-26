package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTO;
import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightDTO(
        Long id,
        LocationDTO origin,
        LocationDTO destination,
        String planeName,     // Instead of a Plane DTO, which causes cyclical dependency
        LocalDateTime departureTime,
        BigDecimal price
) {
}
