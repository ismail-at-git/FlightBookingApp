package com.flightapp.config;

import com.flightapp.model.Flight;
import com.flightapp.model.User;
import com.flightapp.repository.FlightRepository;
import com.flightapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, FlightRepository flightRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Create Admin User
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println("Admin user created: admin / admin123");
            }

            // Create Sample Flights if empty
            if (flightRepository.count() == 0) {
                Flight f1 = new Flight();
                f1.setAirline("Air India");
                f1.setSource("Delhi");
                f1.setDestination("Mumbai");
                f1.setDate(LocalDate.now().plusDays(10));
                f1.setTime(LocalTime.of(10, 30));
                f1.setTotalSeats(100);
                f1.setAvailableSeats(100);
                f1.setPrice(new BigDecimal("5000.00"));
                flightRepository.save(f1);

                Flight f2 = new Flight();
                f2.setAirline("IndiGo");
                f2.setSource("Delhi");
                f2.setDestination("Mumbai");
                f2.setDate(LocalDate.now().plusDays(10));
                f2.setTime(LocalTime.of(14, 00));
                f2.setTotalSeats(150);
                f2.setAvailableSeats(150);
                f2.setPrice(new BigDecimal("4500.00"));
                flightRepository.save(f2);

                System.out.println("Sample flights created.");
            }
        };
    }
}
