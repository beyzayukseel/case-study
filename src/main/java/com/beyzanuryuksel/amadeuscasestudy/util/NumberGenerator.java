package com.beyzanuryuksel.amadeuscasestudy.util;


public class NumberGenerator {

    private static final String TICKER_NUMBER_PREFIX = "TR";
    public static String generateNumber() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return TICKER_NUMBER_PREFIX + number;
    }
}
