package com.cgi.flightbookingapp.DTO.seatDTO;

import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTO;
import com.cgi.flightbookingapp.DTO.planeDTO.PlaneDTOMapper;
import com.cgi.flightbookingapp.model.seat.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service @RequiredArgsConstructor
public class SeatDTOMapper implements Function<Seat, SeatDTO> {
    
//    private final PlaneDTOMapper planeDTOMapper;
    
    @Override
    public SeatDTO apply(Seat seat) {
        return new SeatDTO(
                seat.getNumber(),
                seat.isAvailable(),
                seat.getPlacement(),
                seat.getPlane().getName()
        );
    }
}
