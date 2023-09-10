package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateFlightRequest {

    private Long airplaneId;

    private Long scheduleId;

    private String currency;

    private Double amount;

    private Boolean isActive;
}
