package com.cgi.flightbookingapp.DTO.planeDTO;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.SeatDTO;

import java.util.List;

public record PlaneDTO(
        String name,
        int numRows,
        int numColumns,
        List<FlightDTO> flights,
        List<SeatDTO> seats
) {
}
