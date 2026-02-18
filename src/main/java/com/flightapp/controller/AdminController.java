package com.flightapp.controller;

import com.flightapp.model.Flight;
import com.flightapp.service.BookingService;
import com.flightapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/dashboard";
    }

    @GetMapping("/flights/add")
    public String addFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "admin/add-flight";
    }

    @PostMapping("/flights/add")
    public String addFlight(@ModelAttribute Flight flight) {
        flight.setAvailableSeats(flight.getTotalSeats()); // Init available seats
        flightService.saveFlight(flight);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/admin/dashboard";
    }
}
