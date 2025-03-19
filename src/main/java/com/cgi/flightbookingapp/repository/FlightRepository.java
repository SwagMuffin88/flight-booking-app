package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
