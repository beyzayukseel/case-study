package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirportRequest;
import com.beyzanuryuksel.amadeuscasestudy.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<AirportResponse> getAirportById(@RequestParam Long id) {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> saveAirport(@RequestBody AirportRequest airport) {
        airportService.createAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body("Airport saved successfully!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> updateAirport(@RequestBody UpdateAirportRequest airport) {
        return ResponseEntity.ok(airportService.updateAirport(airport));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<String> deleteAirport(@RequestParam Long id) {
        airportService.softDeleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

}
