package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.converter.FlightConverter;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.model.CreateFlightRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.FlightResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateFlightRequest;
import com.beyzanuryuksel.amadeuscasestudy.repository.FlightRepository;
import com.beyzanuryuksel.amadeuscasestudy.util.NumberGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportService airportService;

    private final AirplaneService airplaneService;

    private final ScheduleService scheduleService;

    private final FlightConverter flightConverter;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<FlightResponse> getAllFlightsByCriteria(String departureIataCode, String arrivalIataCode,
                                                        String departureTime, Optional<String> returnTime) {

        LocalDateTime departureLocalDatetime = LocalDateTime.parse(departureTime, formatter);
        Optional<LocalDateTime> returnDepartureLocalDatetime = Optional.empty();

        if (returnTime.isPresent()) returnDepartureLocalDatetime = Optional.of(LocalDateTime.parse(returnTime.get(), formatter));

        boolean checkDates = checkDepartureAndArrivalTime(departureLocalDatetime, returnDepartureLocalDatetime);

        if (Boolean.FALSE.equals(checkDates)) throw new BusinessLogicException.NotValidDateEnteredException(
                "The entered departure date time must be before than arrival date!");

        Optional<Airport> departureAirport = findAirportByIataCode(departureIataCode);
        Optional<Airport> arrivalAirport = findAirportByIataCode(arrivalIataCode);

        if (departureAirport.isEmpty() || arrivalAirport.isEmpty()) throw new
                BusinessLogicException.NotValidDateEnteredException(
                "The entered departure date time must be before than arrival date!");

        List<Flight> getAllDepartureFlights = flightRepository.getAllByCriteria(departureAirport.get().getId(),
                arrivalAirport.get().getId(), departureLocalDatetime);

        List<Flight> getAllReturnFlightIfExist = new ArrayList<>();

        if(returnTime.isPresent()) {
            getAllReturnFlightIfExist = flightRepository.getAllByCriteria(arrivalAirport.get().getId(),
                    departureAirport.get().getId(), returnDepartureLocalDatetime.get());
        }

        List<Flight> flightList = new ArrayList<>();

        flightList.addAll(getAllDepartureFlights);

        flightList.addAll(getAllReturnFlightIfExist);

        return flightList.stream()
                .map(flightConverter::convertToFlightResponseDto)
                .toList();
    }

    private Boolean checkDepartureAndArrivalTime(LocalDateTime departureTime, Optional<LocalDateTime> arrivalTime) {
        return arrivalTime.map(departureTime::isBefore).orElse(true);
    }

    private Optional<Airport> findAirportByIataCode(String iataCode) {
        return airportService.findByIataCode(iataCode);
    }

    public FlightResponse getFlightById(Long id) {
        Flight flight =  flightRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Flight could not find! Flight id: " + id);
                    return new BusinessLogicException.NotFoundException("Flight not found");
                });

        return flightConverter.convertToFlightResponseDto(flight);
    }

    public FlightResponse getFlightByFlightNumber(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(
                () -> {
                    log.error("Flight could not find! Flight number: " + flightNumber);
                    return new BusinessLogicException.NotFoundException("Flight not found");
                });
        return flightConverter.convertToFlightResponseDto(flight);
    }

    public String createFlight(CreateFlightRequest flight) {
        Airplane airplane = airplaneService.getAirplaneById(flight.getAirplaneId());
        Schedule schedule = scheduleService.getScheduleById(flight.getScheduleId());

        Flight flightEntity = new Flight();
        flightEntity.setFlightNumber(NumberGenerator.generateNumber());
        flightEntity.setIsActive(flight.getIsActive());
        flightEntity.setAmount(flight.getAmount());
        flightEntity.setCurrency(flight.getCurrency());
        flightEntity.setAirplane(airplane);
        flightEntity.setSchedule(schedule);
        flightRepository.save(flightEntity);

        return "Flight successfully created!";
    }

    public String updateFlight(UpdateFlightRequest flight) {
        Flight getExistingFlight = flightRepository.findById(flight.getId()).orElseThrow(
                () -> {
                    log.error("Flight could not find! Flight id: " + flight.getId());
                    return new BusinessLogicException.NotUpdatedException("Flight could not found!");
                });
        if (getExistingFlight == null) {
            return "Flight cant be updated!";
        } else {
            Airplane airplane = airplaneService.getAirplaneById(flight.getAirplaneId());
            Schedule schedule = scheduleService.getScheduleById(flight.getScheduleId());

            Flight flightEntity = new Flight();
            flightEntity.setId(flight.getId());
            flightEntity.setFlightNumber(flight.getFlightNumber());
            flightEntity.setIsActive(flight.getIsActive());
            flightEntity.setAmount(flight.getAmount());
            flightEntity.setCurrency(flight.getCurrency());
            flightEntity.setAirplane(airplane);
            flightEntity.setSchedule(schedule);
            flightRepository.save(flightEntity);
            return "Flight updated successfully!";
        }
    }
    public void softDeleteFlight(Long id) {
        Flight getExistingFlight = flightRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Flight could not find! Flight id: " + id);
                    return new BusinessLogicException.CanNotDeletedException("Flight can not deleted!");
                });
        if (getExistingFlight != null) {
            getExistingFlight.setIsActive(false);
            flightRepository.save(getExistingFlight);
        }
    }

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream().map(flightConverter::convertToFlightResponseDto).toList();
    }
}
