package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFlightRequest {
    private Long id;

    private String flightNumber;

    private Long airplaneId;

    private Long scheduleId;

    private String currency;

    private Double amount;

    private Boolean isActive;
}
