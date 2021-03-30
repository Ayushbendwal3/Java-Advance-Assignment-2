package com.nagarro.javaAdvanceAssignment2.model;

import java.util.Comparator;

public class FlightDurationComparator implements Comparator<Flight>{
    public int compare(Flight a, Flight b){
        double x = a.getFlightDuration() - b.getFlightDuration();
        if(x < 0){
            return -1;
        }
        else if(x > 0){
            return 1;
        }
        else{
            return 0;
        }
    }
}
