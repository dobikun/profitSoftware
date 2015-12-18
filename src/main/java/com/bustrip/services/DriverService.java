/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.services;

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
public class DriverService {

   @PersistenceContext(unitName = "bustrip")
    private EntityManager em;
    
    public void addDriverOnTrip(Driver driver) {
        em.persist(driver);
    }
    
     public List<Driver> get5Drivers() {
        TypedQuery<Driver> query = em.createQuery("SELECT d FROM Driver d ORDER BY d.id DESC", Driver.class).setMaxResults(5);
        List<Driver> result = query.getResultList();

        return result;
    }
     
     public void addDriverOnTrip(Integer driverId, Integer tripId) {

        //http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
         
         
        //Getting the passengers by id
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Driver> cqDriver = builder.createQuery(Driver.class);
        Root<Driver> dRoot = cqDriver.from(Driver.class);
        cqDriver.select(dRoot).where(builder.equal(dRoot.get("id").as(Integer.class), driverId));
        TypedQuery<Driver> pQuery = em.createQuery(cqDriver);
        Driver d = pQuery.getSingleResult();
         
        
        //Getting the trip by id
        builder = em.getCriteriaBuilder();
        CriteriaQuery<Trip> cqTrip = builder.createQuery(Trip.class);
        Root<Trip> fRoot = cqTrip.from(Trip.class);
        cqTrip.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), tripId));
        TypedQuery<Trip> fQuery = em.createQuery(cqTrip);
        Trip t = fQuery.getSingleResult();
        
        
        //Associate the driver with the trip
        List<Driver> dList = t.getDrives();
        dList.add(d);
        t.setDrives(dList);

        d.setTripForDriver(t); 
     }
     
      public Driver getOneDriver(Integer driverId) {
        
        TypedQuery<Driver> tripQuery = em.createNamedQuery("Driver.findById", Driver.class);
        tripQuery.setParameter("id", Integer.valueOf(driverId));
        Driver tripResult = tripQuery.getSingleResult();

        return tripResult;
    }
}
