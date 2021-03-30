package com.nagarro.javaAdvanceAssignment2.input;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.nagarro.javaAdvanceAssignment2.hibernateUtil.HibernateSF;
import com.nagarro.javaAdvanceAssignment2.model.Constants;

import javax.persistence.TypedQuery;

public class UserInputValidator extends Constants {
    public static Date validateDate(String str) {
        dateFormat.setLenient(false);
        Date validTill = null;
        try {
            validTill = dateFormat.parse(str);
        } catch (ParseException e) {
            System.err.println("Date not in appropriate(dd-MM-yyyy) format, Enter Again: ");
        }
        return validTill;
    }

    public static String validateSource(String src) {
        Session session = HibernateSF.sf.openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        TypedQuery<String> query = session.createQuery("select distinct depLoc from Flight");
        List<String> flightSet = query.getResultList();
        session.getTransaction().commit();
        session.close();
        for (String flight : flightSet) {
            if (flight.equalsIgnoreCase(src))
                return flight;
        }
        System.err.print("Flights from no such stations found, Kindly Enter Again: ");
        return null;
    }

    public static String validateDestination(String destination) {
        Session session = HibernateSF.sf.openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        TypedQuery<String> query = session.createQuery("select distinct arrLoc from Flight");
        List<String> flightSet = query.getResultList();
        session.getTransaction().commit();
        session.close();
        for (String flight : flightSet) {
            if (flight.equalsIgnoreCase(destination))
                return flight;
        }
        System.err.print("Flights to no such stations found, Kindly Enter Again: ");
        return null;
    }

    public static String validateFlightClass(String str) {
        if (str.equalsIgnoreCase("E"))
            return str.toUpperCase();
        else if (str.equalsIgnoreCase("B"))
            return "EB";
        else {
            System.err.print("Flight Class entered Inappropriately, Enter Again :");
            return null;
        }
    }

    public static int validateOutputPreference(int i) {
        if ((i == 1) || (i == 2))
            return i;
        else {
            System.err.print("Output preference entered Inappropriately, Enter Again : ");
            return 0;
        }
    }
}
