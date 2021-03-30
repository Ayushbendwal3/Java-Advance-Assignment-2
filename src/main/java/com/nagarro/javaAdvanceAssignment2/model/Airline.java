package com.nagarro.javaAdvanceAssignment2.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Airline {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "AIRLINE_NAME")
    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private Set<Flight> flights;


    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Airline other = (Airline) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }
}
