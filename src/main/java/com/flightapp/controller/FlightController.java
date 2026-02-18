package com.flightapp.controller;

import com.flightapp.model.Flight;
import com.flightapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public String searchFlights(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        List<Flight> flights = flightService.searchFlights(source, destination, date);
        model.addAttribute("flights", flights);
        model.addAttribute("searchDate", date);
        return "flight-results";
    }
}
