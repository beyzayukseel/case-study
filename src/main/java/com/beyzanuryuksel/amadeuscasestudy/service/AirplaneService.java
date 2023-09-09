package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//get all airplanes by giving a schedule

@RequiredArgsConstructor
@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    Airplane getAirplaneById(Long id) throws Exception {
        return airplaneRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found"));
    }

    List<Airplane> getAirplaneByType(String type) {
        return airplaneRepository.getAirplanesByType(type);
    }

    Airplane createAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    Airplane updateAirplane(Airplane airplane) throws Exception {
        Airplane getExistingAirplane = airplaneRepository.findById(airplane.getId()).orElseThrow(
                () -> new Exception("Customer not found"));
        if (getExistingAirplane == null) {
            return null;
        } else return airplaneRepository.save(airplane);
    }

    void softDeleteAirplane(Long id) throws Exception {
        Airplane getExistingAirplane = airplaneRepository.findById(id).orElseThrow(
                () -> new Exception("Customer not found"));
        if (getExistingAirplane != null) {
            getExistingAirplane.setIsActive(false);
            airplaneRepository.save(getExistingAirplane);
        }
    }

    List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }
}
