package com.nagarro.javaAdvanceAssignment2.controller;

import com.nagarro.javaAdvanceAssignment2.input.InputAcceptor;
import com.nagarro.javaAdvanceAssignment2.model.Flight;
import com.nagarro.javaAdvanceAssignment2.model.FlightDurationComparator;
import com.nagarro.javaAdvanceAssignment2.model.FlightPriceComparator;
import com.nagarro.javaAdvanceAssignment2.model.UserInput;
import org.hibernate.Session;
import org.nagarro.javaAdvanceAssignment2.hibernateUtil.HibernateSF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Utility {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Flight> result = new ArrayList<>();
        String choice = null;
        UserInput uiObj;

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new ModificationWatcher(), 0, 3, TimeUnit.SECONDS);

        do {
            uiObj = InputAcceptor.enterInput();
            result.clear();
            Session session = HibernateSF.sf.openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Flight> flightSet = session.createQuery("from Flight").getResultList();
            session.getTransaction().commit();
            session.close();

            for (Flight f : flightSet) {
                if (f.getDepLoc().equalsIgnoreCase(uiObj.getDepLoc())
                        && f.getArrLoc().equalsIgnoreCase(uiObj.getArrLoc())
                        && f.getFlightClass().equalsIgnoreCase(uiObj.getFlightClass())
                        && (uiObj.getFlightDate().compareTo(f.getValidTill()) <= 0)
                        && f.isSeatAvailability())
                    result.add(f);
            }

            if (uiObj.getOutputPreference() == 1)
                Collections.sort(result, new FlightPriceComparator());
            else
                Collections.sort(result, new FlightDurationComparator());


            System.out.println("\nResult:");
            for (Flight f : result) {
                System.out.println(f);
            }
            System.out.print("\nWant to Exit (Enter Y/N): ");
            while (!((choice = br.readLine()).equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")))
                System.out.print("I could not Understand Enter Again:");
        } while (choice.equalsIgnoreCase("n"));
        service.shutdown();
    }
}
