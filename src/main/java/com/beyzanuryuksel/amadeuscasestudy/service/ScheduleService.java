package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedulesByCriteria(Long departureAirportId, Long arrivalDepartureId, LocalDateTime departureTime,
                                                    Optional<LocalDateTime> arrivalTime ) {

        return scheduleRepository.getSchedulesByAirportIdForDepartureBetweenDates(departureAirportId, arrivalDepartureId, departureTime,
                arrivalTime);

    }

    public List<Schedule> getSchedulesByDepartureAirportId(Long id) {
        return scheduleRepository.findAllByDepartureAirportId(id);
    }

    public List<Schedule> getSchedulesBArrivalAirportId(Long id) {
        return scheduleRepository.findAllByArrivalAirportId(id);
    }

    public List<Schedule> getSchedulesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleRepository.getSchedulesForDepartureBetweenDates(startDate, endDate);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Schedule not found"));
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        Schedule getExistingSchedule = scheduleRepository.findById(schedule.getId()).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Schedule not found"));
        if (getExistingSchedule == null) {
            return null;
        } else return scheduleRepository.save(schedule);
    }

    public void softDeleteSchedule(Long id) {
        Schedule getExistingSchedule = scheduleRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Schedule not found"));
        if (getExistingSchedule != null) {
            getExistingSchedule.setIsActive(false);
            scheduleRepository.save(getExistingSchedule);
        }
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
