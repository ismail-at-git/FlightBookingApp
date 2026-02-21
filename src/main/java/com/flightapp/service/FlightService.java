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
        if (date == null) {
            return flightRepository.findBySourceAndDestination(source, destination);
        }
        return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
    }

    public List<Flight> searchInRange(String source, String destination, LocalDate date, int days) {
        if (date == null)
            return searchFlights(source, destination, null);
        return flightRepository.findBySourceAndDestinationAndDateBetween(
                source, destination, date.minusDays(days), date.plusDays(days));
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
