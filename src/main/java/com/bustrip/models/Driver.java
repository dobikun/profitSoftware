/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Roman
 */
@NamedQuery(name="Driver.findById", query="SELECT d FROM Driver d WHERE d.id = :id")
@Entity
public class Driver implements Serializable {
    
    @Transient
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    
    private String lastName;
    
    private String driverLicense;

    @ManyToOne
    @JoinColumn(name = "trip_fk")
    private Trip tripForDriver;
    
    
    public Trip getTripForDriver() {
        return tripForDriver;
    }

    public void setTripForDriver(Trip tripForDriver) {
        this.tripForDriver = tripForDriver;
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "Driver{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", driverLicense=" + driverLicense  + '}';
    }
    
}
