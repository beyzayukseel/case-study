package com.beyzanuryuksel.amadeuscasestudy.model.Amadeus;

import java.util.ArrayList;

public class TravelerPricing {
    public String travelerId;
    public String fareOption;
    public String travelerType;
    public Price price;
    public ArrayList<FareDetailsBySegment> fareDetailsBySegment;
}
