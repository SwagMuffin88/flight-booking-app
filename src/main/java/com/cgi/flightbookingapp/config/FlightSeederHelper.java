package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.model.seat.Seat;
import com.cgi.flightbookingapp.repository.FlightSeatRepository;
import com.cgi.flightbookingapp.repository.PlaneRepository;
import com.cgi.flightbookingapp.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component @RequiredArgsConstructor
public class FlightSeederHelper {
    
    private final PlaneRepository planeRepository;
    private final SeatRepository seatRepository;
    private final FlightSeatRepository flightSeatRepository;

    protected List<Seat> getSeatsFromPlane(Plane plane) {
        return seatRepository.findByPlaneId(plane.getId());
    }

    // Creates a list of flight seats based on plane seat layout
    protected List<FlightSeat> assignPlaneSeatsToFlight(Flight flight, List<Seat> planeSeats) {
        List<FlightSeat> flightSeats = new ArrayList<>();
        Random random = new Random();

        for (Seat planeSeat : planeSeats) {
            FlightSeat flightSeat = new FlightSeat();

            flightSeat.setNumber(planeSeat.getNumber());
            flightSeat.setPlacement(planeSeat.getPlacement());
            flightSeat.setPlane(planeSeat.getPlane());
            flightSeat.setFlight(flight);
            flightSeat.setAvailable(random.nextBoolean());

            flightSeats.add(flightSeat);
        }

        return flightSeatRepository.saveAll(flightSeats);
    }
}
