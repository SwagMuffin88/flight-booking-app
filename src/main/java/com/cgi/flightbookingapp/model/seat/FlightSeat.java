package com.cgi.flightbookingapp.model.seat;

import com.cgi.flightbookingapp.model.flight.Flight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter 
@RequiredArgsConstructor
@PrimaryKeyJoinColumn(name = "seat_id")
public class FlightSeat extends Seat{
    
    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
