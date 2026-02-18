package com.flightapp.service;

import com.flightapp.model.Flight;
import com.flightapp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
