package com.flightapp.repository;

import com.flightapp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Find flights by source, destination, and date
    List<Flight> findBySourceAndDestinationAndDate(String source, String destination, LocalDate date);

    // Simple search for source and destination (if date not provided or flexible)
    List<Flight> findBySourceAndDestination(String source, String destination);
}
