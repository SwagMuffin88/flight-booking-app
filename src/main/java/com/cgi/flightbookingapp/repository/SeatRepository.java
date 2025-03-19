package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    
}
