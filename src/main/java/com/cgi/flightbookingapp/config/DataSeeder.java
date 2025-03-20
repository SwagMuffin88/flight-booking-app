package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.Location;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    private final FlightSeatRepository flightSeatRepository;
    
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
    
    private List<Location> getDestinationsFromDb() {
        return locationRepository.findAll()
                        .stream()
                        .filter(l -> !l.getAirportNameShort().equals("TLN"))
                        .collect(Collectors.toList());
    } 
    
    // Create new flight, copy seat layout from associated plane
    private List<Flight> createFlightsForPlane(Plane plane) {
        List<Flight> flights = new ArrayList<>();
        
        // add routes
        // todo create list of destinations to choose randomly from

        // Initial logic created using ChatGPT
//        for (String[] route : routes) { // todo use different collection type
//            Flight flight = new Flight();
////            flight.setOrigin(route[0]);  todo set as "TLN"
////            flight.setDestination(route[1]); todo use list
//            flight.setDepartureTime(LocalDateTime.now().plusDays(new Random().nextInt(30)));
//            flight.setPlane(plane);
//            flight = flightRepository.save(flight); // Save flight first
//
//            // Generate seats for this flight based on the plane layout
//            List<FlightSeat> flightSeats = getSeatsFromPlaneAndAssignToFlight(flight, plane);
//            flight.setFlightSeats(flightSeats);
//
//            flights.add(flight);
//        }
        
        return null;
    }
    
    // Copies seat layout from specific plane to associated flight and assigns random availability
    // Initial version created using ChatGPT
    private List<FlightSeat> getSeatsFromPlaneAndAssignToFlight(Flight flight, Plane plane) {
        List<FlightSeat> flightSeats = new ArrayList<>();
        Random random = new Random();

        // Fetch the plane's seat layout
        List<Seat> planeSeats = seatRepository.findByPlane(plane);

        for (Seat planeSeat : planeSeats) {
            FlightSeat flightSeat = new FlightSeat();
            flightSeat.setFlight(flight); // Assign to the flight
            flightSeat.setAvailable(random.nextBoolean()); // Randomly set availability

            flightSeats.add(flightSeat);
        }

        return flightSeatRepository.saveAll(flightSeats); // Persist seats
    }
    
    

}
