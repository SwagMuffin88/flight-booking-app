package com.cgi.flightbookingapp.repository;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.Placement;
import com.cgi.flightbookingapp.model.seat.Seat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class SeatRepositoryTest {
    
    @Autowired
    private SeatRepository seatRepositoryUnderTest;
    
    Seat s1;
    Seat s2;
    Plane testPlane;
    
    @Autowired
    private PlaneRepository planeRepository;
    
    @BeforeEach
    void setUp() {
        testPlane = new Plane(
                null,
                "Test plane",
                20,
                4,
                null,
                new ArrayList<>()
        );
        
        List<Seat> testSeats = List.of(
                s1 = new Seat(
                        null,
                        "1A",
                        Placement.WINDOW,
                        testPlane
                ),
                s2 = new Seat(
                        null,
                        "1B",
                        Placement.AISLE,
                        testPlane)
        );
        
        testPlane.setSeats(testSeats);
        
        planeRepository.save(testPlane);
        
        seatRepositoryUnderTest.saveAll(testSeats);
    }

    @AfterEach
    void tearDown() {
        seatRepositoryUnderTest.deleteAll();
    }
    
    @Test
    void returnsListOfSeatsRelatedToPlane() {
        List<Seat> expected = 
                seatRepositoryUnderTest.findByPlaneId(testPlane.getId());

        Assertions.assertNotEquals(0, expected.size());
        Assertions.assertEquals(2, expected.size());
        Assertions.assertEquals(s1.getId(), expected.get(0).getId());
    }
    
}
