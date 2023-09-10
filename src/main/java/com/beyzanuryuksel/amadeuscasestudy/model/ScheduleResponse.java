package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponse {
    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String utcOffset;

    private AirportResponse departureAirport;

    private AirportResponse arrivalAirport;

    private Boolean isActive;
}
