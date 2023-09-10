package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.model.FlightResponse;
import com.beyzanuryuksel.amadeuscasestudy.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> getAllFlightsByCriteria(@RequestParam String departureIataCode,
                                                                        @RequestParam String arrivalIataCode,
                                                                        @RequestParam String departureDatetime,
                                                                        @RequestParam(required = false) Optional<String> arrivalDateTime) {
        return ResponseEntity.ok(flightService.getAllFlightsByCriteria(
                departureIataCode, arrivalIataCode, departureDatetime, arrivalDateTime));

    }

    @GetMapping
    public ResponseEntity<Flight> getFlightById(@RequestParam Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/flightNumber")
    public ResponseEntity<Flight> getFlightByFlightNumber(@RequestParam String flightNumber) {
        return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
    }

    @PostMapping
    public ResponseEntity<String> saveFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return ResponseEntity.ok("Flight saved successfully!");
    }

    @PutMapping
    public ResponseEntity<String> updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
        return ResponseEntity.ok("Flight updated successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFlight(@RequestParam Long id) {
        flightService.softDeleteFlight(id);
        return ResponseEntity.ok("Flight deleted successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getFlightStatus() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

}
