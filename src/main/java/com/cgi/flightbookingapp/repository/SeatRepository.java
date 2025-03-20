package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByPlane(Plane plane);
}
