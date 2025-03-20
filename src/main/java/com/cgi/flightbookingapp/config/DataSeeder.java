package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.flight.Flight;
import com.cgi.flightbookingapp.model.flight.Location;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.FlightRepository;
import com.cgi.flightbookingapp.repository.LocationRepository;
import com.cgi.flightbookingapp.repository.PlaneRepository;
import com.cgi.flightbookingapp.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cgi.flightbookingapp.model.seat.Placement.*;

/*  The purpose of this class is to initialize database with pre-made dataset and thus eliminate the need
* to manually create data for testing purposes from scratch. */

// Based on Studyeasy article: https://studyeasy.org/course-articles/spring-boot-articles/s03l04-adding-seed-data-in-the-database/

@Component @RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    
//    private final PlaneService planeService;
    private final PlaneRepository planeRepository;
    private final LocationRepository locationRepository;
    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;
    
    // Override method for populating database with mock data. Executes in the beginning of running the application.
    @Override
    public void run(String... args) throws Exception {
        List<Plane> planeList = planeRepository.findAll();
        List<Location> locationList = locationRepository.findAll();
        List<Flight> flightList = flightRepository.findAll(); 
        
        if (planeList.size() == 0) {
            createAndAddPlanes();
        }
        
        if (locationList.size() == 0) {
            createAndAddLocations();
        }
        
    }

    // Creates new instances of plane objects and adds them to database. 
    // Uses repo.saveAll() method to enable adding more instances if necessary.
    private void createAndAddPlanes() {
        Plane plane = new Plane();
        plane.setName("Airbus 1");
        plane.setNumRows(20);
        plane.setNumColumns(4);
        
        List<Seat> newSeats = new ArrayList<>(
                generateSeatsForPlane(plane)
        );

        plane.setSeats(newSeats);
        // todo create flights for plane

        planeRepository.saveAll(List.of(plane));
    }

    // Creates new instances of location entities and adds them to database.
    private void createAndAddLocations() {
        Location tln = new Location();
        tln.setCity("Tallinn");
        tln.setCountry("Estonia");
        tln.setAirportNameShort("TLN");

        Location dub = new Location();
        dub.setCity("Dublin");
        dub.setCountry("Ireland");
        dub.setAirportNameShort("DUB");

        Location lis = new Location();
        lis.setCity("Lisbon");
        lis.setCountry("Portugal");
        lis.setAirportNameShort("LIS");

        // todo add 1-2 more locations
        
        locationRepository.saveAll(List.of(tln, dub, lis));
    }
    
    // Generates seat layout for individual plane.
    private List<Seat> generateSeatsForPlane(Plane plane) {
        int numRows = plane.getNumRows();
        int numCol = plane.getNumColumns();

        List<Seat> generatedSeats = new ArrayList<>();

        for (int row = 1; row <= numRows; row++) {
            char letter = 'A';
            for (int seat = 0; seat < numCol; seat++) {
                Seat newSeat = new Seat();

                newSeat.setNumber(String.valueOf(letter++) + row);
                newSeat.setPlane(plane);
                
                if (seat == 0 || seat == numCol - 1) {
                    newSeat.setPlacement(WINDOW);
                } else if (seat == numCol / 2 || seat == numCol / 2 - 1) {
                    newSeat.setPlacement(AISLE);
                } else {
                    newSeat.setPlacement(MIDDLE);
                }

                generatedSeats.add(newSeat);
            }
        }
        
        return seatRepository.saveAll(generatedSeats);
    }

    // todo create flights and copy plane seats to each flight

}
