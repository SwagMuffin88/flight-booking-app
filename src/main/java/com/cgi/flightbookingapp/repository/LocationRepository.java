package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByAirportNameShort(String originCode);
}
