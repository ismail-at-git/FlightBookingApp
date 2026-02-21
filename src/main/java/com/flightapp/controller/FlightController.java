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
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        if (date == null) {
            List<Flight> flights = flightService.searchFlights(source, destination, null);
            model.addAttribute("flights", flights);
        } else {
            List<Flight> exactFlights = flightService.searchFlights(source, destination, date);
            List<Flight> allInRange = flightService.searchInRange(source, destination, date, 3);

            // Filter out exact matches from alternates
            List<Flight> alternateFlights = allInRange.stream()
                    .filter(f -> !f.getDate().equals(date))
                    .toList();

            model.addAttribute("flights", exactFlights);
            model.addAttribute("alternateFlights", alternateFlights);
            model.addAttribute("searchDate", date);
        }

        return "flight-results";
    }
}
