package com.beyzanuryuksel.amadeuscasestudy.converter;


import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirportRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AirportConverter {
    public AirportResponse convertToAirportResponseDto(Airport entity) {
        AirportResponse response = new AirportResponse();
        if (entity != null){
            BeanUtils.copyProperties(entity, response);
        }
        return response;
    }

    public Airport convertDtoToEntity(AirportRequest request) {
        Airport airport = new Airport();
        airport.setCity(request.getCity());
        airport.setCountry(request.getCountry());
        airport.setName(request.getName());
        airport.setIsActive(request.getIsActive());
        airport.setIataCode(request.getIataCode());
        airport.setIcaoCode(request.getIcaoCode());
        return airport;
    }

    public Airport convertUpdateDtoToEntity(UpdateAirportRequest request) {
        Airport airport = new Airport();
        if (request != null){
            BeanUtils.copyProperties(request, airport);
        }
        return airport;
    }
}
