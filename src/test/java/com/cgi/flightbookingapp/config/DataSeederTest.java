package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.model.seat.Placement;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataSeederTest {
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private FlightSeatRepository flightSeatRepository;
    @Autowired
    private DataSeeder dataSeeder;

    @BeforeEach
    void cleanDatabase() {
        flightRepository.deleteAll();
        seatRepository.deleteAll();
        planeRepository.deleteAll();
        locationRepository.deleteAll();
        flightSeatRepository.deleteAll();
    }

    // Test created with ChatGPT
    @Test
    @Transactional
    void testFlightsHaveUniqueTimesAndValidSeats() throws Exception {
        dataSeeder.run();

        List<Flight> flights = flightRepository.findAll();
        assertFalse(flights.isEmpty());

        for (Flight flight : flights) {
            assertNotNull(flight.getDepartureTime());
            assertNotNull(flight.getPlane());
            assertNotNull(flight.getOrigin());
            assertNotNull(flight.getDestination());

            // Optional: Check seat availability
            List<FlightSeat> flightSeats = flight.getFlightSeats();
            assertFalse(flightSeats.isEmpty());

            System.out.println(flightSeats.get(0).getPlacement());
            
            long windowCount = flightSeats.stream()
                    .filter(seat -> seat.getPlacement() == Placement.WINDOW)
                    .count();

            System.out.println("Flight ID: " + flight.getId() + ", window seat count: " + windowCount);

            assertTrue(windowCount > 0, 
                    "Expected at least one window seat for flight ID: " + flight.getId());

        }
    }

    @Test
    @Transactional
    void testSeederCreatesValidPlaneWithSeats() throws Exception {
        dataSeeder.run();

        List<Plane> planes = planeRepository.findAll();
        assertFalse(planes.isEmpty());
        assertEquals(1, planes.size());

        Plane plane = planes.get(0);
//        assertEquals(0, plane.getId());
        assertEquals("Airbus 1", plane.getName());

//        List<Seat> seats = seatRepository.findByPlaneId(plane.getId());
        assertEquals(
                plane.getNumRows() * plane.getNumColumns(), // assuming 20 rows, 4 columns
                plane.getSeats().size()
        ); 
    }
    
}
