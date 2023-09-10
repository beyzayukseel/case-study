package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.converter.FlightConverter;
import com.beyzanuryuksel.amadeuscasestudy.entity.Airport;
import com.beyzanuryuksel.amadeuscasestudy.entity.Flight;
import com.beyzanuryuksel.amadeuscasestudy.entity.Schedule;
import com.beyzanuryuksel.amadeuscasestudy.exception.BusinessLogicException;
import com.beyzanuryuksel.amadeuscasestudy.model.FlightResponse;
import com.beyzanuryuksel.amadeuscasestudy.repository.FlightRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    private final AirportService airportService;

    private final ScheduleService scheduleService;

    private final FlightConverter flightConverter;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<FlightResponse> getAllFlightsByCriteria(String departureIataCode, String arrivalIataCode,
                                                        String departureTime, Optional<String> returnTime) {

        LocalDateTime departureLocalDatetime = LocalDateTime.parse(departureTime, formatter);
        Optional<LocalDateTime> returnDepartureLocalDatetime = null;

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
                .collect(Collectors.toList());
    }

    private Boolean checkDepartureAndArrivalTime(LocalDateTime departureTime, Optional<LocalDateTime> arrivalTime) {
        return arrivalTime.map(departureTime::isBefore).orElse(true);
    }

    private Optional<Airport> findAirportByIataCode(String iataCode) {
        return airportService.findByIataCode(iataCode);
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
