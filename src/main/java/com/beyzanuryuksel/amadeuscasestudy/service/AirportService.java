package com.beyzanuryuksel.amadeuscasestudy.service;


import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

//get all scheduled airport by between dates
@RequiredArgsConstructor
@Service
public class AirportService {

    private final AirportRepository airportRepository;

    Airport getAirportById(Long id) throws Exception {
        return airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not found"));
    }

    Airport getAirportByIataCode(String iataCode) throws Exception {
        return airportRepository.findByIataCode(iataCode).orElseThrow(
                () -> new Exception("Airport not found"));
    }

    Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    Airport updateAirport(Airport airport) throws Exception {
        Airport getExistingAirport = airportRepository.findById(airport.getId()).orElseThrow(
                () -> new Exception("Airport not found"));
        if (getExistingAirport == null) {
            return null;
        } else return airportRepository.save(airport);
    }

    List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    void softDeleteAirport(Long id) throws Exception {
        Airport getExistingAirport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not found"));
        if (getExistingAirport != null) {
            getExistingAirport.setIsActive(false);
            airportRepository.save(getExistingAirport);
        }
    }


}
