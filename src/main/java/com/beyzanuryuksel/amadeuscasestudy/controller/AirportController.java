package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<Airport> getAirportById(@RequestParam Long id) {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveAirport(@RequestBody Airport airport) {
        airportService.createAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body("Airport saved successfully!");
    }

    @PutMapping
    public ResponseEntity<String> updateAirport(@RequestBody Airport airport) throws Exception {
        airportService.updateAirport(airport);
        return ResponseEntity.ok("Airport updated successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAirport(@RequestParam Long id) throws Exception {
        airportService.softDeleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully!");
    }

}
