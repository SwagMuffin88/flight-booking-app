package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Long> {
}
