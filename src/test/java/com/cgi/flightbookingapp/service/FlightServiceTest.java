package com.cgi.flightbookingapp.service;

import com.cgi.flightbookingapp.DTO.flightDTO.FlightDTOMapper;
import com.cgi.flightbookingapp.repository.FlightRepository;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;
    
    @Mock
    private FlightDTOMapper flightDTOMapper;
    
    private AutoCloseable autoCloseable;
    
    @InjectMocks 
    private FlightService serviceUnderTest;
    
    
}
