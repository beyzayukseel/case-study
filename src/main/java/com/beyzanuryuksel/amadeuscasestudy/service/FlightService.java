package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
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

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight not found"));
    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight not found"));
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Flight flight) {
        Flight getExistingFlight = flightRepository.findById(flight.getId()).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight could not found!"));
        if (getExistingFlight == null) {
            return null;
        } else return flightRepository.save(flight);
    }

    public void softDeleteFlight(Long id) {
        Flight getExistingFlight = flightRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight could not found!"));
        if (getExistingFlight != null) {
            getExistingFlight.setIsActive(false);
            flightRepository.save(getExistingFlight);
        }
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
