package com.flightapp.service;

import com.flightapp.model.Booking;
import com.flightapp.model.Flight;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public Booking createBooking(Long flightId, String passengerName, String email) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getAvailableSeats() > 0) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightRepository.save(flight);

            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setPassengerName(passengerName);
            booking.setEmail(email);
            booking.setSeatNumber(flight.getTotalSeats() - flight.getAvailableSeats()); // Simple seat assignment
            booking.setBookingDate(LocalDateTime.now());
            booking.setStatus("CONFIRMED");

            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("No seats available");
        }
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public java.util.List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
