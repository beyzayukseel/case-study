package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//get all airplanes by giving a schedule

@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Airplane not found"));
    }

    public List<Airplane> getAirplaneByType(String type) {
        return airplaneRepository.getAirplanesByType(type);
    }

    public Airplane createAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    public Airplane updateAirplane(Airplane airplane) {
        Airplane getExistingAirplane = airplaneRepository.findById(airplane.getId()).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Airplane could not found!"));
        if (getExistingAirplane == null) {
            return null;
        } else return airplaneRepository.save(airplane);
    }

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }
}
