/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.validators;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Roman
 */
@Stateless
@LocalBean
public class DriverValidator {

   public String checkFNameInput(String param_fname, HttpServletRequest request) {
 
       if(param_fname.length() == 0) {        
            request.setAttribute("errors", true);
            request.setAttribute("fname_error", true);
            request.setAttribute("fname", "");
        } else {
           
           try {
                String fname = param_fname;
                request.setAttribute("fname", fname);
            
                return fname;
                
           }catch(NumberFormatException e){
                request.setAttribute("errors", true);
                request.setAttribute("fname_error", true);
                request.setAttribute("fname", "");
           }
        } 
       
       return null;
    }
     
    public String checkLNameInput(String param_lname, HttpServletRequest request) {
 
       if(param_lname.length() == 0) {        
            request.setAttribute("errors", true);
            request.setAttribute("lname_error", true);
            request.setAttribute("lname", "");
        } else {
           
           try {
                String lname = param_lname;
                request.setAttribute("lname", lname);
            
                return lname;
                
           }catch(NumberFormatException e){
                request.setAttribute("errors", true);
                request.setAttribute("lname_error", true);
                request.setAttribute("lname", "");
           }
        } 
       
       return null;
    }
    
    public String setTripAtt(String currentTrip, HttpServletRequest request) {
        
        request.setAttribute("tripName", currentTrip);
        
        return currentTrip;
    }

    public String checkLicenseInput(String param_license, HttpServletRequest request) {
       
        if(param_license.length() == 0) {        
            request.setAttribute("errors", true);
            request.setAttribute("license_error", true);
            request.setAttribute("license", "");
        } else {
           
           try {
                String license = param_license;
                request.setAttribute("license", license);
            
                return license;
                
           }catch(NumberFormatException e){
                request.setAttribute("errors", true);
                request.setAttribute("license_error", true);
                request.setAttribute("license", "");
           }
        } 
       
       return null;
    }
    
}
