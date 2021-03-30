package com.nagarro.javaAdvanceAssignment2.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import com.nagarro.javaAdvanceAssignment2.model.Constants;
import com.nagarro.javaAdvanceAssignment2.model.Airline;
import com.nagarro.javaAdvanceAssignment2.input.DirectorReader;
import org.nagarro.javaAdvanceAssignment2.hibernateUtil.HibernateSF;

import javax.persistence.TypedQuery;

public class ModificationWatcher extends Constants implements Runnable {
    public static HashMap<String, Long> lastModifiedAt = new HashMap<>();

    public void run() {
        File[] files = file.listFiles();
        Session session;
        ArrayList<String> list = new ArrayList<>();
        for (File file : files) {
            if ((!(lastModifiedAt.containsKey(file.getName()))) ||
                    (file.lastModified() > lastModifiedAt.get(file.getName()))) {
                Airline airline = DirectorReader.readFile(file);
                session = HibernateSF.sf.openSession();
                session.beginTransaction();
                if (!(lastModifiedAt.containsKey(file.getName()))) {
                    session.persist(airline);
                } else {
                    @SuppressWarnings("unchecked")
                    TypedQuery<Airline> query = session.createQuery("from Airline where name = :string");
                    query.setParameter("string", file.getName());
                    try {
                        Airline a = query.getSingleResult();
                        a = session.load(Airline.class, a.getId());
                        session.delete(a);
                        session.persist(airline);
                    } catch (NonUniqueResultException e) {
                        System.err.println("No result found!!");
                    }
                }
                session.getTransaction().commit();
                session.close();
                lastModifiedAt.put(file.getName(), file.lastModified());
            }
            list.add(file.getName());
        }
        Set<String> fc = lastModifiedAt.keySet();
        Set<String> flightName = new HashSet<>(fc);

        if (fc.size() == list.size())
            return;

        for (String str : flightName) {
            if (!list.contains(str)) {
                session = HibernateSF.sf.openSession();
                session.beginTransaction();
                @SuppressWarnings("unchecked")
                TypedQuery<Airline> query = session.createQuery("from Airline where name = :string");
                query.setParameter("string", str);
                try {
                    Airline a = query.getSingleResult();
                    a = session.load(Airline.class, a.getId());
                    session.delete(a);
                    session.getTransaction().commit();
                } catch (NonUniqueResultException e) {
                    System.err.println("No result found!!");
                }
                lastModifiedAt.remove(str);
                session.close();
            }
        }
    }
}