package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateSchedule {

    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String utcOffset;

    private Long departureAirportId;

    private Long arrivalAirportId;

    private Boolean isActive;
}
