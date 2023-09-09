package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<Airplane> getAirplane(@RequestParam Long id) {
        return ResponseEntity.ok(airplaneService.getAirplaneById(id));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Airplane>> getAirplaneByType(@RequestParam String type) {
        return ResponseEntity.ok(airplaneService.getAirplaneByType(type));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        return ResponseEntity.ok(airplaneService.getAllAirplanes());
    }

    @PostMapping
    public ResponseEntity<String> saveAirplane(@RequestBody Airplane airplane) {
        airplaneService.createAirplane(airplane);
        return ResponseEntity.status(HttpStatus.CREATED).body("Airplane saved successfully!");
    }

    @PutMapping
    public ResponseEntity<String> updateAirplane(@RequestBody Airplane airplane) {
        airplaneService.updateAirplane(airplane);
        return ResponseEntity.status(HttpStatus.OK).body("Airplane updated successfully!");
    }

}
