package com.cgi.flightbookingapp.DTO.flightDTO;

import com.cgi.flightbookingapp.DTO.locationDTO.LocationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightDTO(
        Long id,
        LocationDTO origin,
        LocationDTO destination,
        String planeName,
        LocalDateTime departureTime,
        BigDecimal price
) {
}
