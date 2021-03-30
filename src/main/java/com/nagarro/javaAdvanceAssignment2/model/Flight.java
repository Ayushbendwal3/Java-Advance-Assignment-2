package com.nagarro.javaAdvanceAssignment2.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table
public class Flight extends Constants {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "FLIGHT_NO")
    private String flightNo;

    @Column(name = "DEP_LOC")
    private String depLoc;

    @Column(name = "ARR_LOC")
    private String arrLoc;

    @Column(name = "VALID_TILL")
    private Date validTill;

    @Column(name = "FLIGHT_TIME")
    private String flightTime;

    @Column(name = "FLIGHT_DURATION")
    private Double flightDuration;

    @Column(name = "FARE")
    private int fare;

    @Column(name = "SEAT_AVAILABILITY")
    private boolean seatAvailability;

    @Column(name = "FLIGHT_CLASS")
    private String flightClass;

    @ManyToOne
    private Airline airline;

    public Flight(){

    }

    public Flight(String flightNo, String depLoc, String arrLoc,
                  Date validTill, String flightTime, Double flightDuration,
                  int fare, boolean seatAvailability, String flightClass, Airline airline) {
        super();
        this.flightNo = flightNo;
        this.depLoc = depLoc;
        this.arrLoc = arrLoc;
        this.validTill = validTill;
        this.flightTime = flightTime;
        this.flightDuration = flightDuration;
        this.seatAvailability = seatAvailability;
        if (flightClass.equalsIgnoreCase("EB")) {
            fare = 140 * fare / 100;
        }
        this.fare = fare;
        this.flightClass = flightClass;
        this.airline = airline;
    }

    public int getId() {
        return id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getDepLoc() {
        return depLoc;
    }

    public String getArrLoc() {
        return arrLoc;
    }

    public Date getValidTill() {
        return validTill;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public Double getFlightDuration() {
        return flightDuration;
    }

    public int getFare() {
        return fare;
    }

    public boolean isSeatAvailability() {
        return seatAvailability;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public Airline getAirline() {
        return airline;
    }

    @Override
    public String toString() {
        return "flightNo=" + flightNo + ", depLoc=" + depLoc
                + ", arrLoc=" + arrLoc + ", validTill=" + dateFormat.format(validTill)
                + ", flightTime=" + flightTime + ", flightDuration="
                + String.format("%.2f", flightDuration) + ", fare=" + fare + ", seatAvailability="
                + seatAvailability + ", flightClass=" + flightClass;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((flightNo == null) ? 0 : flightNo.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        Flight other = (Flight) obj;
        if (arrLoc == null) {
            if (other.arrLoc != null)
                return false;
        } else if (!arrLoc.equals(other.arrLoc))
            return false;
        if (depLoc == null) {
            if (other.depLoc != null)
                return false;
        } else if (!depLoc.equals(other.depLoc))
            return false;
        if (flightNo == null) {
            if (other.flightNo != null)
                return false;
        } else if (!flightNo.equals(other.flightNo))
            return false;
        if (flightTime == null) {
            if (other.flightTime != null)
                return false;
        } else if (!flightTime.equals(other.flightTime))
            return false;
        if (seatAvailability != other.seatAvailability)
            return false;
        if (validTill == null) {
            return other.validTill == null;
        } else return validTill.equals(other.validTill);
    }
}
