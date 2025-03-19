package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    
}
