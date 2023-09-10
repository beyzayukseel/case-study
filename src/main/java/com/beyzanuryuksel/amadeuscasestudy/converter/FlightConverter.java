package com.beyzanuryuksel.amadeuscasestudy.converter;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FlightConverter {

    public FlightResponse convertToFlightResponseDto(Flight entity) {
        FlightResponse flightResponse = new FlightResponse();
        AirportResponse departureAirport = new AirportResponse();
        AirportResponse arrivalAirport = new AirportResponse();
        AirplaneResponse airplane = new AirplaneResponse();
        ScheduleResponse scheduleResponse = new ScheduleResponse();

        if (entity.getAirplane() != null){
            BeanUtils.copyProperties(entity.getAirplane(), airplane);
        }

        if (entity.getSchedule() != null){
            BeanUtils.copyProperties(entity.getSchedule(), scheduleResponse);
        }

        if (entity.getSchedule().getArrivalAirport() != null){
            BeanUtils.copyProperties(entity.getSchedule().getArrivalAirport(), arrivalAirport);
        }

        if (entity.getSchedule().getDepartureAirport() != null){
            BeanUtils.copyProperties(entity.getSchedule(), departureAirport);
        }

        scheduleResponse.setArrivalAirport(arrivalAirport);
        scheduleResponse.setDepartureAirport(departureAirport);

        flightResponse.setId(entity.getId());
        flightResponse.setFlightNumber(entity.getFlightNumber());
        flightResponse.setSchedule(scheduleResponse);
        flightResponse.setAmount(entity.getAmount());
        flightResponse.setCurrency(entity.getCurrency());
        flightResponse.setAirplane(airplane);

        return flightResponse;
    }

}
