package com.beyzanuryuksel.amadeuscasestudy.controller;

import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<Schedule> getScheduleById(@RequestParam Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @GetMapping("/departure/{departureAirportId}")
    public ResponseEntity<List<Schedule>> getScheduleByDepartureAirportId(@PathVariable Long departureAirportId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByDepartureAirportId(departureAirportId));
    }

    @GetMapping("/arrival/{arrivalAirportId}")
    public ResponseEntity<List<Schedule>> getScheduleByArrivalAirportId(@PathVariable Long arrivalAirportId) {
        return ResponseEntity.ok(scheduleService.getSchedulesBArrivalAirportId(arrivalAirportId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @PostMapping
    public ResponseEntity<String> saveSchedule(@RequestBody Schedule schedule) {
        scheduleService.createSchedule(schedule);
        return ResponseEntity.ok("Schedule saved successfully!");
    }

    @PutMapping
    public ResponseEntity<String> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok("Schedule updated successfully!");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSchedule(@RequestParam Long id) {
        scheduleService.softDeleteSchedule(id);
        return ResponseEntity.ok("Schedule deleted successfully!");
    }

    @GetMapping("/betweenDates")
    public ResponseEntity<List<Schedule>> getAllSchedulesBetweenDates(@RequestParam LocalDateTime departureTimeFirst,
                                                                      @RequestParam LocalDateTime departureTimeSecond) {
        return ResponseEntity.ok(scheduleService.getSchedulesByDate(departureTimeFirst, departureTimeSecond));
    }
}
