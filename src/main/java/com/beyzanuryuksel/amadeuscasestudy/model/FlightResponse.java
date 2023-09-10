package com.beyzanuryuksel.amadeuscasestudy.model;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FlightResponse {

    private Long id;

    private String flightNumber;

    private AirplaneResponse airplane;

    private ScheduleResponse schedule;

    private String currency;

    private Double amount;

    private Boolean isActive;
}
