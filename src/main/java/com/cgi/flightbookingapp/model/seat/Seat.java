package com.cgi.flightbookingapp.model.seat;

import com.cgi.flightbookingapp.model.Plane;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Entity @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    
    @Enumerated(EnumType.STRING)
    private Placement placement;
    
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id != null && id.equals(seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
