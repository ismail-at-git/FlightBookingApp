package com.flightapp.controller;

import com.flightapp.model.Booking;
import com.flightapp.model.Flight;
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @GetMapping("/book/{flightId}")
    public String showBookingPage(@PathVariable Long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        model.addAttribute("flight", flight);
        return "booking";
    }

    @PostMapping("/create")
    public String createBooking(
            @RequestParam("flightId") Long flightId,
            @RequestParam("passengerName") String passengerName,
            @RequestParam("email") String email,
            Model model) {

        try {
            Booking booking = bookingService.createBooking(flightId, passengerName, email);
            return "redirect:/bookings/confirmation/" + booking.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/flights/search?error=" + e.getMessage(); // Simplified error handling
        }
    }

    @GetMapping("/confirmation/{id}")
    public String showConfirmation(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        return "confirmation";
    }
}
