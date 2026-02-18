package com.flightapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "passenger_name")
    private String passengerName;

    private String email;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    private String status;
}
