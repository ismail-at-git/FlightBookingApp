package com.flightapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flights")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String source;
    private String destination;

    @Column(name = "flight_date")
    private LocalDate date;

    @Column(name = "flight_time")
    private LocalTime time;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seats")
    private int availableSeats;

    private BigDecimal price;
}
