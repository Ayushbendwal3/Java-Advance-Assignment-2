package com.nagarro.javaAdvanceAssignment2.model;

import java.util.Comparator;

public class FlightPriceComparator implements Comparator<Flight> {
    public int compare(Flight a, Flight b) {
        return a.getFare() - b.getFare();
    }
}
