package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.converter.AirplaneConverter;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.model.AirplaneResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.CreateAirplane;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirplane;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final AirplaneConverter airplaneConverter;

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Airplane could not find!");
                    return new BusinessLogicException.NotFoundException("Airplane not found");
                });
    }

    public List<AirplaneResponse> getAirplaneByType(String type) {

        return airplaneRepository.getAirplanesByType(type).stream().map(airplaneConverter::convertToAirplaneResponse).toList();
    }

    public String createAirplane(CreateAirplane airplane) {
        airplaneRepository.save(airplaneConverter.convertToEntity(airplane));
        return "Airplane successfully created!";
    }

    public String updateAirplane(UpdateAirplane airplane) {
        Airplane getExistingAirplane = airplaneRepository.findById(airplane.getId()).orElseThrow(
                () -> {
                    log.error("Airplane could not find!");
                    return new BusinessLogicException.NotFoundException("Airplane could not found!");
                });

        if (getExistingAirplane == null) {
            return null;
        } else {
            airplaneRepository.save(airplaneConverter.convertUpdateModelToEntity(airplane));
            return "Airplane successfully updated!";
        }
    }

    public List<AirplaneResponse> getAllAirplanes() {
        return airplaneRepository.findAll().stream().map(airplaneConverter::convertToAirplaneResponse).toList();
    }
}
