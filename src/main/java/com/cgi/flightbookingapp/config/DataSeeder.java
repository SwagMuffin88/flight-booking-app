package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
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
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.cgi.flightbookingapp.model.seat.Placement.*;

/*  The purpose of this class is to initialize database with pre-made dataset and thus eliminate the need
* to manually create data for testing purposes from scratch. Randomizing seat availability for each flight 
* is also performed in this class.*/

// Based on Studyeasy article: https://studyeasy.org/course-articles/spring-boot-articles/s03l04-adding-seed-data-in-the-database/

@Component @RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
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
            planeList = createAndAddPlanes();
        }
        
        if (locationList.size() == 0) {
            locationList = createAndAddLocations();
        }
        
        if (flightList.size() == 0) {
            flightList = planeList.stream()
                            .flatMap(plane -> {
                                try {
                                    return createFlightsForPlane(plane)
                                            .stream();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .toList();
            
        }
//        System.out.println(planeList.get(0).getFlights().size());
        
    }

    // Creates new instances of plane objects and adds them to database.  
    private List<Plane> createAndAddPlanes() {
        Plane plane = new Plane();
        plane.setName("Airbus 1");
        plane.setNumRows(20);
        plane.setNumColumns(4);
        
        plane = planeRepository.save(plane);
        
        List<Seat> newSeats = generateSeatsForPlane(plane);
        plane.setSeats(newSeats);

        // todo create flights for plane

        return planeRepository.saveAll(List.of(plane));
    }

    // Creates new instances of location entities and adds them to database.
    private List<Location> createAndAddLocations() {
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
        
        return locationRepository.saveAll(List.of(tln, dub, lis));
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
    
    // Creates new flights, adds them to plane.
    private List<Flight> createFlightsForPlane(Plane plane) throws ResourceNotFoundException {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();
        List<Location> allLocations = new ArrayList<>(getLocationsFromDb());
        
        Location origin = getOriginLocation("TLN");
        List<Location> destinations = getDestinations(allLocations, origin);
        
        // Create 10 flights with randomized locations and dates
        generateGivenNrOfFlights(plane, flights, random, origin, destinations, 10);
        
        plane.setFlights(flights);
        planeRepository.save(plane);

        return flightRepository.saveAll(flights);
    }

    // Extracted from previous method.
    private void generateGivenNrOfFlights(
            Plane plane, List<Flight> flights, Random random, 
            Location origin, List<Location> destinations, int n) {
        
        // Initial departure time
        LocalDateTime departureTime = LocalDateTime.now().plusDays(1);

        for (int i = 0; i <= n; i++) {
            Flight flight = new Flight();

            flight.setOrigin(origin);
            flight.setDestination(
                    destinations.get(random.nextInt(0, destinations.size()))
            );
            flight.setDepartureTime(departureTime);
            flight.setPlane(plane);
            
            // Generate seats for individual flight based on the plane layout
            List<FlightSeat> flightSeats = getSeatsFromPlaneAndAssignToFlight(flight, plane);
            flight.setFlightSeats(flightSeats);
            
            flights.add(flight);

            // Calculate next departure time
            int extraHours = random.nextInt(6);
            departureTime = departureTime.plusHours(24 + extraHours);
        }
    }

    private List<Location> getLocationsFromDb() {
        return locationRepository.findAll();
    }
    
    //Returns origin location based on given airport code.
    private Location getOriginLocation(String originCode) throws ResourceNotFoundException {
        return locationRepository.findByAirportNameShort(originCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Origin location not found"));
    }
    
    // Filters all locations, excluding the origin location.
    private List<Location> getDestinations(List<Location> allLocations, Location origin) {
        return allLocations.stream()
                .filter(l -> !l.getId()
                                .equals(origin.getId()
                                ))
                .collect(Collectors.toList());
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
            
            flightSeat.setNumber(planeSeat.getNumber());
            flightSeat.setPlacement(planeSeat.getPlacement());
            flightSeat.setPlane(planeSeat.getPlane());
            flightSeat.setFlight(flight); // Assign to the flight
            flightSeat.setAvailable(random.nextBoolean()); // Randomly set availability

            flightSeats.add(flightSeat);
        }

        return flightSeatRepository.saveAll(flightSeats); // Persist seats
    }
    
    
    
}
