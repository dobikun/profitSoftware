/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.controllers;

import com.bustrip.services.TripService;
import java.io.IOException;
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
@WebServlet(name = "Trips", urlPatterns = {"/Trips"})
public class Trips extends HttpServlet {

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
        
        //List of passengers
        request.setAttribute("tripsList", tripService.getAllTrips());
        request.setAttribute("driversList", tripService.getDrivers());
        
        RequestDispatcher view = request.getRequestDispatcher("tripsList.jsp");
        view.forward(request, response);
    }
}
