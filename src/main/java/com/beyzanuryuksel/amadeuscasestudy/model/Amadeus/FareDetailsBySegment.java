package com.beyzanuryuksel.amadeuscasestudy.model.Amadeus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FareDetailsBySegment {
    public String segmentId;
    public String cabin;
    public String fareBasis;
    @JsonProperty("class")
    public String myclass;
    public IncludedCheckedBags includedCheckedBags;
}
