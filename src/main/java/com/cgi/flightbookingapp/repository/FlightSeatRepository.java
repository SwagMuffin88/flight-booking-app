package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.seat.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
}
