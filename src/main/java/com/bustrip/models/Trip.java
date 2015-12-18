/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.models;

import com.bustrip.enums.TripDestinations;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Roman
 */
@NamedQuery(name="Trip.findById", query="SELECT t FROM Trip t WHERE t.id = :id")
@Entity
public class Trip implements Serializable {
    
    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private TripDestinations tripFrom;
    
    @Enumerated(EnumType.STRING)
    private TripDestinations tripTo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tripTime;
    
    private Integer price;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "bus_fk") 
    private Bus busDetails;

    @OneToMany(mappedBy = "tripForDriver")
    private List<Driver> drives;
    
    
    @ManyToMany
    @JoinTable(name = "t_p_join", joinColumns = @JoinColumn(name = "trip_fk"), inverseJoinColumns = @JoinColumn(name ="passenger_fk"))
    private List<Passengers> passengers;

    
    public List<Driver> getDrives() {
        return drives;
    }

    public void setDrives(List<Driver> drives) {
        this.drives = drives;
    }
    
    
    public List<Passengers> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passengers> passengers) {
        this.passengers = passengers;
    }

    public Bus getBusDetails() {
        return busDetails;
    }

    public void setBusDetails(Bus busDetails) {
        this.busDetails = busDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TripDestinations getTripFrom() {
        return tripFrom;
    }

    public void setTripFrom(TripDestinations tripFrom) {
        this.tripFrom = tripFrom;
    }

    public TripDestinations getTripTo() {
        return tripTo;
    }

    public void setTripTo(TripDestinations tripTo) {
        this.tripTo = tripTo;
    }

    public Date getTripTime() {
        return tripTime;
    }

    public void setTripTime(Date tripTime) {
        this.tripTime = tripTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    
    
    @Override
    public String toString() {
        return "Trip{" + "id=" + id + ", tripFrom=" + tripFrom + ", tripTo=" + tripTo + ", tripTime=" + tripTime + ", price=" + price + "Passengers: " + passengers + "Drivers: " + drives + '}';
    }
    
}
