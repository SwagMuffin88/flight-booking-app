package com.cgi.flightbookingapp.DTO.seatDTO;

import com.cgi.flightbookingapp.model.seat.Placement;

public record FlightSeatDTO(
        String number,
        boolean isAvailable,
        Placement placement,
        String planeName    // Use Plane name instead of Plane DTO to avoid circular dependency
) {
}
