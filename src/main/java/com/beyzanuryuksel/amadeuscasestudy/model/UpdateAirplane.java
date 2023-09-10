package com.beyzanuryuksel.amadeuscasestudy.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAirplane {
    private Long id;
    private String type;
    private String seatCapacity;
    private Boolean isActive;
}
