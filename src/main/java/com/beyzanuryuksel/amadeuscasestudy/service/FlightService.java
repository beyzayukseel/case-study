package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.repository.FlightRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportService airportService;

    private final ScheduleService scheduleService;

    public List<Flight> getAllFlightsByCriteria(Long departureAirportId, Long arrivalAirportId,
                                                LocalDateTime departureTime, Optional<LocalDateTime> arrivalTime) {

        boolean checkDates = checkDepartureAndArrivalTime(departureTime, arrivalTime);

        if (Boolean.FALSE.equals(checkDates)) throw new BusinessLogicException.NotValidDateEnteredException(
                "The entered departure date time must be before than arrival date!");

        boolean checkDepartureAirport = checkAirportExistence(departureAirportId);
        boolean checkArrivalAirport = checkAirportExistence(arrivalAirportId);

        if (Boolean.FALSE.equals(checkDepartureAirport) || Boolean.FALSE.equals(checkArrivalAirport)) throw new
                BusinessLogicException.NotValidDateEnteredException(
                "The entered departure date time must be before than arrival date!");

        List<Schedule> getAllProperSchedules = scheduleService.getAllSchedulesByCriteria(departureAirportId,
                arrivalAirportId, departureTime, arrivalTime);

        List<Flight> flightList = new ArrayList<>();

        getAllProperSchedules.forEach(schedule -> {
            List<Flight> flights = flightRepository.findAllByScheduleId(schedule.getId());
            flights.stream().map(flightList::add);
        });

        return flightList;
    }

    private Boolean checkDepartureAndArrivalTime(LocalDateTime departureTime, Optional<LocalDateTime> arrivalTime) {
        return arrivalTime.map(departureTime::isBefore).orElse(true);
    }

    private Boolean checkAirportExistence(Long id) {
        return airportService.checkAiportExistence(id);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight not found"));
    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight not found"));
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Flight flight) {
        Flight getExistingFlight = flightRepository.findById(flight.getId()).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight could not found!"));
        if (getExistingFlight == null) {
            return null;
        } else return flightRepository.save(flight);
    }

    public void softDeleteFlight(Long id) {
        Flight getExistingFlight = flightRepository.findById(id).orElseThrow(
                () -> new BusinessLogicException.NotFoundException("Flight could not found!"));
        if (getExistingFlight != null) {
            getExistingFlight.setIsActive(false);
            flightRepository.save(getExistingFlight);
        }
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
