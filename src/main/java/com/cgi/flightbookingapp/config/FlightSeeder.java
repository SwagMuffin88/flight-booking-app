package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.Location;
import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component @RequiredArgsConstructor
public class FlightSeeder {
    
    private final LocationSeeder locationSeeder;
    private final FlightRepository flightRepository;
    private final FlightSeederHelper flightSeederHelper;
    private final PlaneSeeder planeSeeder;

    // Creates new flights, adds them to plane.
    protected List<Flight> createFlightsForPlane(Plane plane) throws ResourceNotFoundException {
        List<Flight> flights = new ArrayList<>();

        Random random = new Random();
        List<Location> allLocations = locationSeeder.getLocationsFromDb();

        Location origin = locationSeeder.getOriginLocation("TLN");
        List<Location> destinations = locationSeeder.getDestinations(allLocations, origin);

        // Seat layout from plane
        List<Seat> planeSeats = flightSeederHelper.getSeatsFromPlane(plane);

        // Create 10 flights with randomized locations and dates
        flights = generateGivenNrOfFlights(plane, planeSeats, random, origin, destinations, 10);

        plane.setFlights(flights);
        planeSeeder.savePlane(plane);

        return flightRepository.saveAll(flights);
    }

    // Extracted from previous method.
    private List<Flight> generateGivenNrOfFlights(
            Plane plane, List<Seat> planeSeats, Random random,
            Location origin, List<Location> destinations, int n
    ) {
        List<Flight> flights = new ArrayList<>();

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
            List<FlightSeat> flightSeats = flightSeederHelper.assignPlaneSeatsToFlight(flight, planeSeats);

            flight.setFlightSeats(flightSeats);
            
            BigDecimal price = randomizePrice();
            flight.setPrice(price);

            flights.add(flight);

            // Calculate next departure time
            int extraHours = random.nextInt(6);
            departureTime = departureTime.plusHours(24 + extraHours);
        }
        return flights;
    }
    
    private BigDecimal randomizePrice() {
        double rawPrice = 135 + (Math.random() * 250);
        
        return BigDecimal.valueOf(Math.round(
                        rawPrice * 100.0) / 100.0
        );
    }
}
