package com.cgi.flightbookingapp.model;

import com.cgi.flightbookingapp.model.flight.Flight;
import com.cgi.flightbookingapp.model.seat.Seat;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Plane {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
    private int numRows;      
    private int numColumns;   

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL)
    private List<Flight> flights;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return id != null && id.equals(plane.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
