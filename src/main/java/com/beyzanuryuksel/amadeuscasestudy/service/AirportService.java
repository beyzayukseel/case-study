package com.beyzanuryuksel.amadeuscasestudy.service;


import com.beyzanuryuksel.amadeuscasestudy.converter.AirportConverter;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirportRequest;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportConverter airportConverter;

    public AirportResponse getAirportById(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Airport could not find! Airport id: " + id);
                    return new BusinessLogicException.NotFoundException("Airport not found");
                });
        return airportConverter.convertToAirportResponseDto(airport);
    }

    public Optional<Airport> findByIataCode(String iataCode) {
        return airportRepository.findByIataCode(iataCode);
    }

    public void createAirport(AirportRequest airport) {
        airportRepository.save(airportConverter.convertDtoToEntity(airport));
    }

    public String updateAirport(UpdateAirportRequest airport) {
        Airport getExistingAirport = airportRepository.findById(airport.getId()).orElseThrow(
                () -> {
                    log.error("Airport could not find! Airport id: " + airport.getId());
                    return new BusinessLogicException.NotUpdatedException("Airport not found");
                });
        if (getExistingAirport == null) {
            return "Airport could not updated!";
        } else {
            airportRepository.save(airportConverter.convertUpdateDtoToEntity(airport));
            return "Airport successfully updated!";
        }
    }

    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(airportConverter::convertToAirportResponseDto).toList();
    }

    public Airport getAirportByIdForInternal(Long id) {
        return airportRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Airport could not find! Airport id:" + id);
                    return new BusinessLogicException.NotFoundException("Airport not found");
                });
    }

    public void softDeleteAirport(Long id) {
        Airport getExistingAirport = airportRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.CanNotDeletedException("Airport can not deleted!"));
        if (getExistingAirport != null) {
            getExistingAirport.setIsActive(false);
            airportRepository.save(getExistingAirport);
        }
    }
}
