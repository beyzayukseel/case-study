package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.departureTime BETWEEN ?1 AND ?2")
    List<Schedule> getSchedulesForDepartureBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query
    List<Schedule> findAllByDepartureAirportId(Long id);

    @Query
    List<Schedule> findAllByArrivalAirportId(Long id);
}
