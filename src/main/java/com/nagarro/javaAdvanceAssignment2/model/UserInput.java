package com.nagarro.javaAdvanceAssignment2.model;

import java.util.Date;

public class UserInput extends Constants {
    private String depLoc;
    private String arrLoc;
    private String flightClass;
    private Date flightDate;
    private int outputPreference;

    public UserInput(String depLoc, String arrLoc,
                     String flightClass, Date flightDate,
                     int outputPreference) {
        this.depLoc = depLoc;
        this.arrLoc = arrLoc;
        this.flightClass = flightClass;
        this.flightDate = flightDate;
        this.outputPreference = outputPreference;
    }

    public String getDepLoc() {
        return depLoc;
    }

    public String getArrLoc() {
        return arrLoc;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public int getOutputPreference() {
        return outputPreference;
    }

    @Override
    public String toString() {
        return "depLoc=" + depLoc + ", arrLoc=" + arrLoc
                + ", flightDate=" + dateFormat.format(flightDate) + ", flightClass=" + flightClass
                + ", outputPreference=" + outputPreference;
    }
}
