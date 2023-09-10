package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateSchedule {
    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String utcOffset;

    private Long departureAirportId;

    private Long arrivalAirportId;

    private Boolean isActive;
}
