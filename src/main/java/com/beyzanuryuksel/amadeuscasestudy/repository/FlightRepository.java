package com.beyzanuryuksel.amadeuscasestudy.repository;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.schedule.departureAirport.id = ?1 " +
            "AND f.schedule.arrivalAirport.id = ?2 AND f.schedule.departureTime = ?3")
    List<Flight> getAllByCriteria(Long departureAirportId, Long arrivalDepartureId, LocalDateTime departureDatetime);

    @Query
    Optional<Flight> findByFlightNumber(String flightNumber);

    @Query
    List<Flight> findAllByScheduleId(Long id);
}
