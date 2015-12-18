/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.controllers;

import com.bustrip.enums.TripDestinations;
import com.bustrip.models.Bus;
import com.bustrip.models.Trip;
import com.bustrip.services.TripService;
import com.bustrip.validators.BusValidation;
import com.bustrip.validators.TripValidator;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roman
 */
@WebServlet(name = "AddTrip", urlPatterns = {"/AddTrip"})
public class AddTrip extends HttpServlet {
    
    @EJB
    private BusValidation busValidation;
    
    @EJB
    private TripValidator tripValidator;
    
    @EJB
    private TripService tripService;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        //Default values
        request.setAttribute("price", "");
        request.setAttribute("hour", "");
        request.setAttribute("day", "");
        request.setAttribute("minute", "");
        request.setAttribute("busMake", "");
        request.setAttribute("busModel", "");
        request.setAttribute("busSeating", "");
        request.setAttribute("from", "Tallinn");
        request.setAttribute("to", "Tartu");
        

        RequestDispatcher view = request.getRequestDispatcher("addTripAndBus.jsp");   
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        //Error status by default 
        request.setAttribute("errors", false);

        
        //Trip requests
        String from = tripValidator.setFromAtt(request.getParameter("from"), request);
        String to = tripValidator.setToAtt(request.getParameter("to"), request);
        Integer price = tripValidator.checkPriceInput(request.getParameter("price"), request);
        Integer hour = tripValidator.checkHourInput(request.getParameter("hour"), request);
        Integer minute = tripValidator.checkMinutehInput(request.getParameter("minute"), request); 
        Integer day = tripValidator.checkDayInput(request.getParameter("day"), request);
        Integer month = Integer.valueOf(request.getParameter("month"));
        Integer year = Integer.valueOf(request.getParameter("year"));
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        
        Date tripTime = cal.getTime();
        //End trip requests
        
        
        //Bus requests
        String  busMake    =  busValidation.checkMakeInput(request.getParameter("busMake"), request); 
        String  busModel   =  busValidation.checkModelInput(request.getParameter("busModel"), request);
        Integer busSeating =  busValidation.checkSeatingInput(request.getParameter("busSeating"), request);
        //End bus requests
        

        //Check errors
        if((Boolean) request.getAttribute("errors")) {
            
            RequestDispatcher view_error = request.getRequestDispatcher("addTripAndBus.jsp");
            view_error.forward(request, response);
            
        } else {
            
            //Context session 
            getServletContext().setAttribute("success", "Trip successfully added!");
            
 
            //Add bus
            Bus bus = new Bus();
            bus.setBusMake(busMake);
            bus.setModelName(busModel);
            bus.setSeatingCapacity(busSeating);

            //Add trip
            Trip trip = new Trip();
            trip.setTripFrom(TripDestinations.valueOf(from));
            trip.setTripTo(TripDestinations.valueOf(to));
            trip.setPrice(price);
            trip.setTripTime(tripTime);
            trip.setBusDetails(bus); //OneToOne relationship


            /* DEBUG entity
            System.out.println(trip);
            System.out.println(bus);
            */
            
            //EM actions
            tripService.addTrip(trip, bus);

            response.sendRedirect(request.getContextPath() + "/addTrip.jsp");
        }
        
    }

}
