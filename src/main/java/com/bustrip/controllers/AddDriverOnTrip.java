/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.controllers;
import com.bustrip.models.Driver;
import com.bustrip.services.DriverService;
import com.bustrip.services.TripService;
import com.bustrip.validators.DriverValidator;
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
 * @author Roman
 */
@WebServlet(name = "AddDriverOnTrip", urlPatterns = {"/AddDriverOnTrip"})
public class AddDriverOnTrip extends HttpServlet {
    
    @EJB
    private DriverService driverService;
    
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
        //List of drivers
        getServletContext().setAttribute("driversList", driverService.get5Drivers());

        request.setAttribute("tripName", "");
        

        RequestDispatcher view = request.getRequestDispatcher("addDriverOnTrip.jsp");   
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
            //Context attr 
            getServletContext().setAttribute("success", "Successfully added!");

            //Requests param
            Integer tripId = Integer.valueOf(request.getParameter("trip"));
            Integer driverId = Integer.valueOf(request.getParameter("driver"));
       
            //Default values
            getServletContext().setAttribute("tripId", tripId);
            getServletContext().setAttribute("driverId", driverId);
        
            /* DEBUG entity 
            System.out.println(tripService.getOneTrip(driver));
            */
    
            //EM actions
            driverService.addDriverOnTrip(driverId, tripId);

            response.sendRedirect(request.getContextPath() + "/driverOnTrip.jsp");
        }
}
