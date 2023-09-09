package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.repository.FlightRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.List;

//get all flights by giving a schedule

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    Flight getFlightById(Long id) throws Exception {
        return flightRepository.findById(id).orElseThrow(
                () -> new Exception("Flight not found"));
    }

    Flight getFlightByFlightNumber(String flightNumber) throws Exception {
        return flightRepository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new Exception("Flight not found"));
    }

    Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    Flight updateFlight(Flight flight) throws Exception {
        Flight getExistingFlight = flightRepository.findById(flight.getId()).orElseThrow(
                () -> new Exception("Flight not found"));
        if (getExistingFlight == null) {
            return null;
        } else return flightRepository.save(flight);
    }

    void softDeleteFlight(Long id) throws Exception {
        Flight getExistingFlight = flightRepository.findById(id).orElseThrow(
                () -> new Exception("Flight not found"));
        if (getExistingFlight != null) {
            getExistingFlight.setIsActive(false);
            flightRepository.save(getExistingFlight);
        }
    }

    List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
