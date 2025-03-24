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

    // Creates new instances of location entities and adds them to database.
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

        // todo add 1-2 more locations

        return locationRepository.saveAll(List.of(tln, dub, lis));
    }
    protected List<Location> getLocationsFromDb() {
        return locationRepository.findAll();
    }

    //Returns origin location based on given airport code.
    protected Location getOriginLocation(String originCode) throws ResourceNotFoundException {
        return locationRepository.findByAirportNameShort(originCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Origin location not found"));
    }

    // Filters all locations, excluding the origin location.
    protected List<Location> getDestinations(List<Location> allLocations, Location origin) {
        return allLocations.stream()
                .filter(l -> !l.getId()
                        .equals(origin.getId()
                        ))
                .collect(Collectors.toList());
    }
}
