package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
