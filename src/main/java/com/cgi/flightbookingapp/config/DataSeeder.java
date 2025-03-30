package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.Location;
import com.cgi.flightbookingapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

/*  
* The purpose of this class is to initialize database with pre-made dataset and thus eliminate the need
* to manually create data for testing and development purposes from scratch. Randomizing seat availability for each 
* flight is also performed in this class.
*
* Based on Studyeasy article: 
* https://studyeasy.org/course-articles/spring-boot-articles/s03l04-adding-seed-data-in-the-database/
* */

@Component @RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final PlaneRepository planeRepository;
    private final LocationRepository locationRepository;
    private final FlightRepository flightRepository;
    
    private final FlightSeeder flightSeeder;
    private final PlaneSeeder planeSeeder;
    private final LocationSeeder locationSeeder;
    
    // Executes in the beginning of running the application.
    @Override
    public void run(String... args) throws Exception {
        List<Plane> planeList = planeRepository.findAll();
        List<Location> locationList = locationRepository.findAll();
        List<Flight> flightList = flightRepository.findAll(); 
        
        if (planeList.isEmpty()) {
            planeList = planeSeeder.createAndAddNewPlanes();
        }
        
        if (locationList.isEmpty()) {
            locationList = locationSeeder.createAndAddLocations();
        }
        
        if (flightList.isEmpty()) {
            flightList = planeList.stream()
                            .flatMap(plane -> {
                                try {
                                    return flightSeeder.createFlightsAndAddToPlane(plane)
                                            .stream();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .toList();
        }
    }
}
