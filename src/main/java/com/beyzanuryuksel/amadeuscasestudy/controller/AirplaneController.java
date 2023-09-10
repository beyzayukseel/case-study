package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.model.AirplaneResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.CreateAirplane;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirplane;
import com.beyzanuryuksel.amadeuscasestudy.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<List<AirplaneResponse>> getAirplaneByType(@RequestParam String type) {
        return ResponseEntity.ok(airplaneService.getAirplaneByType(type));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirplaneResponse>> getAllAirplanes() {
        return ResponseEntity.ok(airplaneService.getAllAirplanes());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> saveAirplane(@RequestBody CreateAirplane airplane) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airplaneService.createAirplane(airplane));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> updateAirplane(@RequestBody UpdateAirplane airplane) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.updateAirplane(airplane));
    }

}
