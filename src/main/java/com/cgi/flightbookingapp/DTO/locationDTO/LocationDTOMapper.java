package com.cgi.flightbookingapp.DTO.locationDTO;

import com.cgi.flightbookingapp.model.flight.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service @RequiredArgsConstructor
public class LocationDTOMapper implements Function<Location, LocationDTO> {
    
    @Override
    public LocationDTO apply(Location location) {
        return new LocationDTO(
                location.getAirportNameShort(),
                location.getCountry(),
                location.getCity()
        );
    }
}
