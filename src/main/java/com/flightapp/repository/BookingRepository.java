package com.flightapp.repository;

import com.flightapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Add custom queries if needed, e.g., find bookings by email
}
