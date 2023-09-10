package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirportRequest {
    private String name;

    private String city;

    private String country;

    private String countryCode;

    private String iataCode;

    private String icaoCode;

    private Boolean isActive;
}
