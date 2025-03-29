package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTO;
import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTO;
import com.cgi.flightbookingapp.DTO.seatDTO.FlightSeatDTOMapper;
import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.model.Flight;
import com.cgi.flightbookingapp.model.seat.FlightSeat;
import com.cgi.flightbookingapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightDTOMapper flightDTOMapper;
    private final FlightSeatDTOMapper flightSeatDTOMapper;

    private List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    public List<FlightDTO> getAllFlightsAsDTOs() {
        return getAllFlights()
                .stream()
                .map(flightDTOMapper)
                .collect(Collectors.toList());
    }
    
    private Flight getFlightById(Long id) throws ResourceNotFoundException {
        return flightRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flight with id " + id + " not found")
                );
    }

    public FlightDTO getFlightAsDTOById(Long id) throws ResourceNotFoundException {
        return flightDTOMapper.apply(
                getFlightById(id)
        );
    }

    private List<FlightSeat> getFlightSeatsForFlight(Long flightId) throws ResourceNotFoundException {
        return getFlightById(flightId)
                .getFlightSeats();
    }
    
    public List<FlightSeatDTO> getFlightSeatsForFlightAsDTO(Long flightId) throws ResourceNotFoundException {
        List<FlightSeat> flightSeats = getFlightSeatsForFlight(flightId);
        return flightSeats.stream()
                .map(flightSeatDTOMapper)
                .toList();
    }


    // Get flights ordered given property - price, departure time (can have additional properties added)
    public List<FlightDTO> getAllFlightDTOsSortedByProperty(String property, String direction) {
        List<String> validProperties = List.of("price", "departureTime");
        
        if (!validProperties.contains(property)) {
            throw new IllegalArgumentException("Invalid sort property " + property);
        }

        Sort.Direction sortDirection = 
                direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        
        return 
                flightRepository
                .findAll(Sort.by(property))
                .stream()
                        .map(flightDTOMapper)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightDTOsByDestination(String airportNameShort) throws ResourceNotFoundException {
        List<FlightDTO> filteredFlights = getAllFlights()
                .stream()
                .filter(f -> 
                        f.getDestination()
                                .getAirportNameShort()
                                .equalsIgnoreCase(airportNameShort))
                .map(flightDTOMapper)
                .collect(Collectors.toList()
                );
        
        if (filteredFlights.isEmpty()) {
            throw new ResourceNotFoundException("No flights found for " + airportNameShort);
        }
         
        return filteredFlights;
    }
    
    
}
