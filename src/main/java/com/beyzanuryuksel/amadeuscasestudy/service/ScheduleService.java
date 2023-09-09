package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    List<Schedule> getSchedulesByDepartureAirportId(Long id) {
        return scheduleRepository.findAllByDepartureAirportId(id);
    }

    List<Schedule> getSchedulesBArrivalAirportId(Long id) {
        return scheduleRepository.findAllByArrivalAirportId(id);
    }

    List<Schedule> getScheduleByDate(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return scheduleRepository.getSchedulesForDepartureBetweenDates(startDate, endDate);
    }

    Schedule getScheduleById(Long id) throws Exception {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new Exception("Schedule not found"));
    }

    Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    Schedule updateSchedule(Schedule schedule) throws Exception {
        Schedule getExistingSchedule = scheduleRepository.findById(schedule.getId()).orElseThrow(
                () -> new Exception("Schedule not found"));
        if (getExistingSchedule == null) {
            return null;
        } else return scheduleRepository.save(schedule);
    }

    void softDeleteSchedule(Long id) throws Exception {
        Schedule getExistingSchedule = scheduleRepository.findById(id).orElseThrow(
                () -> new Exception("Schedule not found"));
        if (getExistingSchedule != null) {
            getExistingSchedule.setIsActive(false);
            scheduleRepository.save(getExistingSchedule);
        }
    }

    List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
