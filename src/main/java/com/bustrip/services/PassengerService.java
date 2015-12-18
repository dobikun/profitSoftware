/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.services;

import com.bustrip.models.Passengers;
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
 * @author roman.gelokhov
 */
@Stateless
@LocalBean
public class PassengerService {

    @PersistenceContext(unitName = "bustrip")
    private EntityManager em;
    
    public void addPassenger(Passengers passenger) {
        em.persist(passenger);
    }
    
     public List<Passengers> getAllPassengers() {
        TypedQuery<Passengers> query = em.createQuery("SELECT p FROM Passengers p ORDER BY p.id DESC", Passengers.class);
        List<Passengers> result = query.getResultList();

        return result;
    }
     
     public List<Passengers> get5Passengers() {
        TypedQuery<Passengers> query = em.createQuery("SELECT p FROM Passengers p ORDER BY p.id DESC", Passengers.class).setMaxResults(5);
        List<Passengers> result = query.getResultList();

        return result;
    }
     
     public void addPassengerOnTrip(Integer passengerId, Integer tripId) {

        //http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
         
         
        //Getting the passengers by id
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Passengers> cqPassenger = builder.createQuery(Passengers.class);
        Root<Passengers> pRoot = cqPassenger.from(Passengers.class);
        cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
        TypedQuery<Passengers> pQuery = em.createQuery(cqPassenger);
        Passengers p = pQuery.getSingleResult();
         
        
        //Getting the trip by id
        builder = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cqTrip = builder.createQuery(Trip.class);
        Root<Trip> fRoot = cqTrip.from(Trip.class);
        cqTrip.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), tripId));
        TypedQuery<Trip> fQuery = em.createQuery(cqTrip);
        Trip t = fQuery.getSingleResult();
        

        //Associate the passenger with the trip
        List<Passengers> pList = t.getPassengers();
        pList.add(p);
        t.setPassengers(pList);

        p.getTrips().add(t); 
           
     }
}
