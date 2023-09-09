package com.beyzanuryuksel.amadeuscasestudy.model.Amadeus;

import java.util.ArrayList;

public class Datum {
    public String type;
    public String id;
    public String source;
    public boolean instantTicketingRequired;
    public boolean nonHomogeneous;
    public boolean oneWay;
    public String lastTicketingDate;
    public int numberOfBookableSeats;
    public ArrayList<Itinerary> itineraries;
    public Price price;
    public PricingOptions pricingOptions;
    public ArrayList<String> validatingAirlineCodes;
    public ArrayList<TravelerPricing> travelerPricings;
}
