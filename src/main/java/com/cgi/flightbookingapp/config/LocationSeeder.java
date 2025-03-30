package com.cgi.flightbookingapp.config;

import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.model.Location;
import com.cgi.flightbookingapp.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class LocationSeeder {

    private final LocationRepository locationRepository;

    protected List<Location> createAndAddLocations() {
        Location tln = new Location();
        tln.setCity("Tallinn");
        tln.setCountry("Estonia");
        tln.setAirportNameShort("TLN");

        Location dub = new Location();
        dub.setCity("Dublin");
        dub.setCountry("Ireland");
        dub.setAirportNameShort("DUB");

        Location lis = new Location();
        lis.setCity("Lisbon");
        lis.setCountry("Portugal");
        lis.setAirportNameShort("LIS");

        return locationRepository.saveAll(List.of(tln, dub, lis));
    }
    protected List<Location> getLocationsFromDb() {
        return locationRepository.findAll();
    }

    protected Location getOriginLocation(String originCode) throws ResourceNotFoundException {
        return locationRepository.findByAirportNameShort(originCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Origin location not found"));
    }

    protected List<Location> getLocationsExcludingOrigin(List<Location> allLocations, Location origin) {
        return allLocations.stream()
                .filter(l -> !l.getId()
                        .equals(origin.getId()
                        ))
                .collect(Collectors.toList());
    }
}
