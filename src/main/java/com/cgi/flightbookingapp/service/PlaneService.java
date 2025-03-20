package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTO;
import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTOMapper;
import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PlaneService {
    
    private final PlaneRepository planeRepository;
    private final PlaneDTOMapper planeDTOMapper;

    protected List<Plane> getAllPlanes() {    // Returns a list of Plane (database) objects 
        return planeRepository.findAll();
    }
    
    public List<PlaneDTO> getAllPlaneDTOs() {   // Returns a list of Plane Data Transfer Objects (visible to client)
        return getAllPlanes().stream()
                .map(planeDTOMapper)
                .collect(Collectors.toList());
    }
    
    //get plane by id (private)
    //get as DTO (public)
    
    protected Plane saveNewPlane(Plane plane) {
        return planeRepository.save(plane);
    }
}
