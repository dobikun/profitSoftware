/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.services;

import com.bustrip.models.Bus;
import com.bustrip.models.Driver;
import com.bustrip.models.Trip;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author Roman
 */
@Stateless
@LocalBean
public class TripService  {

    @PersistenceContext(unitName = "bustrip")
    private EntityManager em;
    
    public void addTrip(Trip trip, Bus bus) {
        em.persist(trip);
    }
    
    public List<Trip> getAllTrips() {
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t ORDER BY t.id DESC", Trip.class).setMaxResults(5);
        List<Trip> result = query.getResultList();

        return result;
    }
    
    public List<Driver> getDrivers() {
        TypedQuery<Driver> query = em.createQuery("SELECT d FROM Driver d ORDER BY d.id DESC", Driver.class);
        List<Driver> result = query.getResultList();

        return result;
    }
    
    public List<Trip> get5Trips() {
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t ORDER BY t.id DESC", Trip.class).setMaxResults(5);
        List<Trip> result = query.getResultList();

        return result;
    }
    
     public Trip getOneTrip(Integer tripId) {
        
        TypedQuery<Trip> tripQuery = em.createNamedQuery("Trip.findById", Trip.class);
        tripQuery.setParameter("id", Integer.valueOf(tripId));
        Trip tripResult = tripQuery.getSingleResult();

        return tripResult;
    }
} 
