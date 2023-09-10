package com.beyzanuryuksel.amadeuscasestudy.service;


import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Airport not found"));
    }

    public Optional<Airport> findByIataCode(String iataCode) {
        return airportRepository.findByIataCode(iataCode);
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Airport airport) throws Exception {
        Airport getExistingAirport = airportRepository.findById(airport.getId()).orElseThrow(
                () -> new Exception("Airport not found"));
        if (getExistingAirport == null) {
            return null;
        } else return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public void softDeleteAirport(Long id) throws Exception {
        Airport getExistingAirport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not found"));
        if (getExistingAirport != null) {
            getExistingAirport.setIsActive(false);
            airportRepository.save(getExistingAirport);
        }
    }
}
