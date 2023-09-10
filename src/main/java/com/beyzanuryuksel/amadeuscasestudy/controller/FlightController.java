package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.model.CreateFlightRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.FlightResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateFlightRequest;
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
    public ResponseEntity<FlightResponse> getFlightById(@RequestParam Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/number")
    public ResponseEntity<FlightResponse> getFlightByFlightNumber(@RequestParam String flightNumber) {
        return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
    }

    @PostMapping
    public ResponseEntity<String> saveFlight(@RequestBody CreateFlightRequest flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @PutMapping
    public ResponseEntity<String> updateFlight(@RequestBody UpdateFlightRequest flight) {
        return ResponseEntity.ok(flightService.updateFlight(flight));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFlight(@RequestParam Long id) {
        flightService.softDeleteFlight(id);
        return ResponseEntity.ok("Flight deleted successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getFlightStatus() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

}
