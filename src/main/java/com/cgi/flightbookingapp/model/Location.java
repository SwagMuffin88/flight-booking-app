package com.cgi.flightbookingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String airportNameShort;
    
    private String country;
    
    private String city;

    @OneToMany(mappedBy = "origin")
    private List<Flight> originFlights;

    @OneToMany(mappedBy = "destination")
    private List<Flight> destinationFlights;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id != null && id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
