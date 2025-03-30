package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.exception.ResourceNotFoundException;
import com.cgi.flightbookingapp.repository.FlightRepository;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;
    
    @Mock
    private FlightDTOMapper flightDTOMapper;
    
    private AutoCloseable autoCloseable;
    
    @InjectMocks 
    private FlightService serviceUnderTest;

    @Test
    void throwExceptionWhenFlightWithIdNotFound() {
        Long nonExistentId = 999L;
        
        when(
                flightRepository.findById(nonExistentId))
                .thenReturn(Optional.empty()
                );

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> serviceUnderTest.getFlightAsDTOById(nonExistentId)
        );

        assertEquals(
                "Flight with id 999 not found", 
                exception.getMessage()
        );
        verify(flightRepository, times(1)).findById(nonExistentId);
    }
    
    
}
