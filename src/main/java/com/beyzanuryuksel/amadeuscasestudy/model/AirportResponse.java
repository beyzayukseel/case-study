package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportResponse {
    private Long id;
    private String name;
    private String city;
    private String country;
    private String countryCode;
    private String iataCode;
    private String icaoCode;
    private Boolean isActive;
}
