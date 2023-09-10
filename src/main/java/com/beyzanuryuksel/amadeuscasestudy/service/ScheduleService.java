package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.converter.ScheduleConverter;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.model.CreateSchedule;
import com.beyzanuryuksel.amadeuscasestudy.model.ScheduleResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateSchedule;
import com.beyzanuryuksel.amadeuscasestudy.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleConverter scheduleConverter;
    private final AirportService airportService;

    public List<ScheduleResponse> getSchedulesByDepartureAirportId(Long id) {
        return scheduleRepository.findAllByDepartureAirportId(id)
                .stream()
                .map(scheduleConverter::convertToScheduleResponseDto)
                .toList();
    }

    public List<ScheduleResponse> getSchedulesBArrivalAirportId(Long id) {
        return scheduleRepository.findAllByArrivalAirportId(id)
                .stream()
                .map(scheduleConverter::convertToScheduleResponseDto)
                .toList();
    }

    public String createSchedule(CreateSchedule schedule) {
        Airport departureAirport = airportService.getAirportByIdForInternal(schedule.getDepartureAirportId());
        Airport arrivalAirport = airportService.getAirportByIdForInternal(schedule.getArrivalAirportId());
        Schedule scheduleEntity = new Schedule();
        scheduleEntity.setArrivalTime(schedule.getArrivalTime());
        scheduleEntity.setDepartureTime(schedule.getDepartureTime());
        scheduleEntity.setArrivalAirport(arrivalAirport);
        scheduleEntity.setDepartureAirport(departureAirport);
        scheduleEntity.setUtcOffset(schedule.getUtcOffset());
        scheduleEntity.setIsActive(schedule.getIsActive());
        scheduleRepository.save(scheduleEntity);
        return "Schedule successfully created!";
    }

    public List<ScheduleResponse> getSchedulesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleRepository.getSchedulesForDepartureBetweenDates(startDate, endDate)
                .stream()
                .map(scheduleConverter::convertToScheduleResponseDto)
                .toList();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Schedule could not find! Schedule id: " + id);
                    return new BusinessLogicException.NotFoundException("Schedule not found");
                });
    }

    public String updateSchedule(UpdateSchedule schedule) {
        Schedule getExistingSchedule = scheduleRepository.findById(schedule.getId()).orElseThrow(
                () -> new BusinessLogicException.NotUpdatedException("Schedule not found"));
        if (getExistingSchedule == null) {
            log.error("Schedule could not find! Schedule id: " + schedule.getId());
            return "Schedule could not updated!";
        } else {
            Airport departureAirport = airportService.getAirportByIdForInternal(schedule.getDepartureAirportId());
            Airport arrivalAirport = airportService.getAirportByIdForInternal(schedule.getArrivalAirportId());
            Schedule scheduleEntity = scheduleConverter.convertUpdateModelToEntity(schedule, departureAirport, arrivalAirport);
            scheduleRepository.save(scheduleEntity);
            return "Schedule updated successfully!";
        }
    }

    public void softDeleteSchedule(Long id) {
        Schedule getExistingSchedule = scheduleRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Schedule could not find! Schedule id: " + id);
                    return new BusinessLogicException.CanNotDeletedException("Schedule not found");
                });
        if (getExistingSchedule != null) {
            getExistingSchedule.setIsActive(false);
            scheduleRepository.save(getExistingSchedule);
        }
    }

    public List<ScheduleResponse> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleConverter::convertToScheduleResponseDto)
                .toList();
    }
}
