/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.controllers;

import com.bustrip.enums.Gender;
import com.bustrip.models.Passengers;
import com.bustrip.services.PassengerService;
import com.bustrip.validators.passengerValidator;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author roman.gelokhov
 */
@WebServlet(name = "AddPassenger", urlPatterns = {"/AddPassenger"})
public class AddPassenger extends HttpServlet {

    @EJB
    private passengerValidator passengerValidator;

    @EJB
    private PassengerService passengerService;

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Default values
        request.setAttribute("fname", "");
        request.setAttribute("lname", "");
        request.setAttribute("date", "");
        request.setAttribute("gender", "Male");

        
        RequestDispatcher view = request.getRequestDispatcher("addPassenger.jsp");   
        view.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Error status by default 
        request.setAttribute("errors", false);
        
        //Get requests param
        String fname =  passengerValidator.checkFNameInput(request.getParameter("fname"), request);
        String lname =  passengerValidator.checkLNameInput(request.getParameter("lname"), request);
        Date date =     passengerValidator.checkDateInput(request.getParameter("date"), request);
        Gender gender = passengerValidator.setGenderAtt(request.getParameter("gender"), request);
        //End Get requests param
        
        //Check errors
        if((Boolean) request.getAttribute("errors")) {
            
            RequestDispatcher view_error = request.getRequestDispatcher("addPassenger.jsp");
            view_error.forward(request, response);
            
        } else {
            
            //Context session 
            getServletContext().setAttribute("success", "Passenger successfully added!");
        
            //Passenger requests
            Passengers passenger = new Passengers();
            
            passenger.setFisrtname(fname);
            passenger.setLastname(lname);
            passenger.setGender(gender);
            passenger.setDate(date);
            
            
            /* DEBUG entity
            System.out.println(trip);
            */
            
            //EM actions
            passengerService.addPassenger(passenger);
            
            response.sendRedirect(request.getContextPath() + "/addPassengers.jsp");
        }
    }
}
