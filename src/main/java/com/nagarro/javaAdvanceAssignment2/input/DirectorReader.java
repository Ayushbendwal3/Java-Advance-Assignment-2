package com.nagarro.javaAdvanceAssignment2.input;

import com.nagarro.javaAdvanceAssignment2.model.Airline;
import com.nagarro.javaAdvanceAssignment2.model.Constants;
import com.nagarro.javaAdvanceAssignment2.model.Flight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

public class DirectorReader extends Constants {
    public static Airline readFile(File file) {
        BufferedReader reader = null;
        Airline airline = new Airline();
        airline.setName(file.getName());
        HashSet<Flight> flights_set = new HashSet<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            line = reader.readLine();

            while (line != null) {
                Flight flight = manipulateLine(line, airline);
                line = reader.readLine();
                flights_set.add(flight);
            }
        } catch (IOException e) {
            System.err.println("Could not read the file");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Could not close the file");
                }
            }
        }
        airline.setFlights(flights_set);
        return airline;
    }

    private static Flight manipulateLine(String line, Airline airline) {
        StringTokenizer st = new StringTokenizer(line, "|");
        String flightNo = st.nextToken();
        String depLoc = st.nextToken();
        String arrLoc = st.nextToken();
        String validTillDate = st.nextToken();
        Date validTill = new Date();

        try {
            validTill = dateFormat.parse(validTillDate);
        } catch (ParseException e) {
            System.err.println("Date not in appropriate(dd-MM-yyyy) format");
        }

        String flightTime = st.nextToken();
        double flightDuration = Double.parseDouble(st.nextToken());
        int fare = Integer.parseInt(st.nextToken());
        String avail = st.nextToken();
        boolean seatAvailability;
        seatAvailability = avail.charAt(0) == 'Y';
        String flightClass = st.nextToken();

        return new Flight(flightNo, depLoc, arrLoc, validTill,
                flightTime, flightDuration, fare, seatAvailability, flightClass, airline);
    }
}
