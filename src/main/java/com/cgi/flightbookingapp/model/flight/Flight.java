package com.cgi.flightbookingapp.model.flight;

import com.cgi.flightbookingapp.model.Plane;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity @Getter @Setter
@RequiredArgsConstructor 
public class Flight {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "origin_id")
    private Location origin;
    
    @OneToOne
    @JoinColumn(name = "destination_id")
    private Location destination;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "plane_id")
    private Plane plane;
    
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<FlightSeat> flightSeats;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id != null && id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
