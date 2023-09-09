package com.beyzanuryuksel.amadeuscasestudy.model.Amadeus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Locations {
    @JsonProperty("BKK")
    public BKK bKK;
    @JsonProperty("MNL")
    public MNL mNL;
    @JsonProperty("SYD")
    public SYD sYD;
}
