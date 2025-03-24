package com.cgi.flightbookingapp.model.seat;

import com.cgi.flightbookingapp.model.Flight;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@PrimaryKeyJoinColumn(name = "seat_id")
public class FlightSeat extends Seat{
    
    private boolean isAvailable;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
