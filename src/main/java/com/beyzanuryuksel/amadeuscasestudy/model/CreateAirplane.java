package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAirplane {
    private String type;
    private String seatCapacity;
    private Boolean isActive;
}
