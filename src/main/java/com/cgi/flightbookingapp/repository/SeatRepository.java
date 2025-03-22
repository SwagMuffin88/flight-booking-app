package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(
            "SELECT s FROM Seat s WHERE TYPE(s) = Seat AND s.plane.id = :planeId"
    )
    List<Seat> findByPlaneId(@Param("planeId") Long planeId);
    
}
