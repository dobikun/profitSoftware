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
@WebServlet(name = "AddDriver", urlPatterns = {"/AddDriver"})
public class AddDriver extends HttpServlet {
    
    @EJB
    private DriverService driverService;
    
    @EJB
    private DriverValidator driverValidator;
    
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
        
        request.setAttribute("fname", "");
        request.setAttribute("lname", "");
        request.setAttribute("license", "");
        

        RequestDispatcher view = request.getRequestDispatcher("addDrivers.jsp");   
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //Error status by default 
        request.setAttribute("errors", false);
        
        
        //Get requests param
        String fname   =  driverValidator.checkFNameInput(request.getParameter("fname"), request);
        String lname   =  driverValidator.checkLNameInput(request.getParameter("lname"), request);
        String license =  driverValidator.checkLicenseInput(request.getParameter("license"), request);
        

        //Check errors
        if((Boolean) request.getAttribute("errors")) {
            
            RequestDispatcher view_error = request.getRequestDispatcher("addDrivers.jsp");
            view_error.forward(request, response);
            
        } else {
            
            //Context session 
            getServletContext().setAttribute("success", "Driver successfully added!");
            
            //Add driver
            Driver driver = new Driver();
            driver.setFirstName(fname);
            driver.setLastName(lname);
            driver.setDriverLicense(license);
            
            /* DEBUG entity 
            System.out.println(tripService.getOneTrip(driver));
            */
            
            //EM actions
            driverService.addDriverOnTrip(driver);
            
            response.sendRedirect(request.getContextPath() + "/addDriver.jsp");
        }
    }
}
