package com.beyzanuryuksel.amadeuscasestudy.converter;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.model.AirportResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.ScheduleResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateSchedule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter {

    public ScheduleResponse convertToScheduleResponseDto(Schedule entity) {

        ScheduleResponse scheduleResponse = new ScheduleResponse();
        AirportResponse departureAirport = new AirportResponse();
        AirportResponse arrivalAirport = new AirportResponse();

        if (entity.getArrivalAirport() != null){
            BeanUtils.copyProperties(entity.getArrivalAirport(), arrivalAirport);
        }

        if (entity.getDepartureAirport() != null){
            BeanUtils.copyProperties(entity, departureAirport);
        }

        scheduleResponse.setArrivalAirport(arrivalAirport);
        scheduleResponse.setDepartureAirport(departureAirport);

        return scheduleResponse;
    }

    public Schedule convertUpdateModelToEntity(UpdateSchedule schedule, Airport departureAirport, Airport arrivalAirport) {
        Schedule scheduleEntity = new Schedule();
        scheduleEntity.setId(schedule.getId());
        scheduleEntity.setUtcOffset(schedule.getUtcOffset());
        scheduleEntity.setDepartureTime(schedule.getDepartureTime());
        scheduleEntity.setArrivalTime(schedule.getArrivalTime());
        scheduleEntity.setIsActive(schedule.getIsActive());
        scheduleEntity.setDepartureAirport(departureAirport);
        scheduleEntity.setArrivalAirport(arrivalAirport);
        return scheduleEntity;
    }
}
