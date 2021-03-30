package com.nagarro.javaAdvanceAssignment2.input;

import com.nagarro.javaAdvanceAssignment2.model.Constants;
import com.nagarro.javaAdvanceAssignment2.model.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class InputAcceptor extends Constants {
    public static UserInput enterInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String source;
        String destination;
        String flightClass;
        int outputPreference;
        Date flightDate = null;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("                    Welcome to Flight Enquiry Portal                    ");
        System.out.println("------------------------------------------------------------------------");
        System.out.print("DEP LOC: ");

        while ((source = UserInputValidator.validateSource(br.readLine())) == null) {
            continue;
        }

        System.out.print("ARR LOC: ");
        while ((destination = UserInputValidator.validateDestination(br.readLine())) == null) {
            continue;
        }

        System.out.print("Flight Class(E/B): ");
        while ((flightClass = UserInputValidator.validateFlightClass(br.readLine())) == null) {
            continue;
        }

        System.out.print("Date Of Travel(DD-MM-YYYY): ");
        while ((flightDate = UserInputValidator.validateDate(br.readLine())) == null) {
            continue;
        }

        System.out.print("Output preference(Sort by Fare/Duration)):\n(Enter 1/2): ");
        while ((outputPreference = UserInputValidator.validateOutputPreference(Integer.parseInt(br.readLine()))) == 0) {
            continue;
        }

        return new UserInput(source, destination,
                flightClass, flightDate, outputPreference);
    }
}
