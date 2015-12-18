/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.controllers;

import com.bustrip.services.PassengerService;
import com.bustrip.services.TripService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roman.gelokhov
 */
@WebServlet(name = "addPassengerOnTrip", urlPatterns = {"/addPassengerOnTrip"})
public class AddPassengerOnTrip extends HttpServlet {

    @EJB
    private PassengerService passengerService;
    
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
        
        //List of trips
        getServletContext().setAttribute("tripsList", tripService.get5Trips());
        //List of passengers
        getServletContext().setAttribute("passengersList", passengerService.get5Passengers());

        
        RequestDispatcher view = request.getRequestDispatcher("addPassengerOnTrip.jsp");   
        view.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Context attr 
        getServletContext().setAttribute("success", "Successfully added!");
        getServletContext().setAttribute("duplicate", "");
        
        //Requests param
        Integer tripId = Integer.valueOf(request.getParameter("trip"));
        Integer passengerId = Integer.valueOf(request.getParameter("passenger"));
        
        //Default values
        getServletContext().setAttribute("tripId", tripId);
        getServletContext().setAttribute("passengerId", passengerId);
        
        //Duplicate ID exception
        try{
            passengerService.addPassengerOnTrip(passengerId, tripId);
        } catch (Exception e) {
            getServletContext().setAttribute("success", "");
            getServletContext().setAttribute("duplicate", "This trip for the passenger is already exists!");
        }

        response.sendRedirect(request.getContextPath() + "/addOn.jsp");
    }
}
