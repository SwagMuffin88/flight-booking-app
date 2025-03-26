package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.PlaneRepository;
import com.cgi.flightbookingapp.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.cgi.flightbookingapp.model.seat.Placement.*;

@Component @RequiredArgsConstructor
public class PlaneSeeder {

    private final PlaneRepository planeRepository;
    private final SeatRepository seatRepository;

    // Creates new instances of plane objects and adds them to database.
    protected List<Plane> createAndAddPlanes() {
        Plane plane = new Plane();
        plane.setName("Airbus 1");
        plane.setNumRows(20);
        plane.setNumColumns(4);

        plane = planeRepository.save(plane);

        List<Seat> newSeats = generateSeatsForPlane(plane);
        plane.setSeats(newSeats);

        return planeRepository.saveAll(List.of(plane));
    }

    // Generates seat layout for individual plane.
    protected List<Seat> generateSeatsForPlane(Plane plane) {
        int numRows = plane.getNumRows();
        int numCol = plane.getNumColumns();

        List<Seat> generatedSeats = new ArrayList<>();

        for (int row = 1; row <= numRows; row++) {
            char letter = 'A';
            for (int seat = 0; seat < numCol; seat++) {
                Seat newSeat = new Seat();

                newSeat.setNumber(row + String.valueOf(letter++));
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
    
    protected void savePlane(Plane plane) {
        planeRepository.save(plane);
    }
}
