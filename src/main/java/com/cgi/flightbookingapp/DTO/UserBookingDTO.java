package com.cgi.flightbookingapp.DTO;

import com.cgi.flightbookingapp.model.seat.Seat;

import java.util.List;
import java.util.Map;

public record UserBookingDTO(
        String bookingId,
        Map<String, Seat> passengerSeats
) {
}
