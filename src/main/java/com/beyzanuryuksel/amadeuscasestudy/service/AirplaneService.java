package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Airplane could not find!");
                    return new BusinessLogicException.NotFoundException("Airplane not found");
                });
    }

    public List<Airplane> getAirplaneByType(String type) {
        return airplaneRepository.getAirplanesByType(type);
    }

    public Airplane createAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public Airplane updateAirplane(Airplane airplane) {
        Airplane getExistingAirplane = airplaneRepository.findById(airplane.getId()).orElseThrow(
                () -> {
                    log.error("Airplane could not find!");
                    return new BusinessLogicException.NotFoundException("Airplane could not found!");
                });
        if (getExistingAirplane == null) {
            return null;
        } else return airplaneRepository.save(airplane);
    }

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }
}
