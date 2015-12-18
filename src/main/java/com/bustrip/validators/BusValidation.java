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
public class BusValidation  {

    public String checkMakeInput(String param_busMake, HttpServletRequest request) {
 
       if(param_busMake.length() == 0) {
            request.setAttribute("errors", true);
            request.setAttribute("busMake_error", true);
            request.setAttribute("busMake", "");
        } else {
            String busMake = param_busMake;
            request.setAttribute("busMake", busMake);
            
            return busMake;
        } 
       
       return null;
    }
    
    public String checkModelInput(String param_busModel, HttpServletRequest request) {
 
       if(param_busModel.length() == 0) {
            request.setAttribute("errors", true);
            request.setAttribute("busModel_error", true);
            request.setAttribute("busModel", "");
        } else {
            String busModel = param_busModel;
            request.setAttribute("busModel", busModel);
            
            return busModel;
        } 
       
       return null;
    }
    
    public Integer checkSeatingInput(String param_busSeating, HttpServletRequest request) {
 
       if(param_busSeating.length() == 0) {
            request.setAttribute("errors", true);
            request.setAttribute("busSeating_error", true);
            request.setAttribute("busSeating", "");
        } else {
           try {
                Integer busSeating = Integer.valueOf(param_busSeating);
                request.setAttribute("busSeating", busSeating);

                return busSeating;
           } catch (NumberFormatException e) {
               request.setAttribute("errors", true);
               request.setAttribute("busSeating_error", true);
               request.setAttribute("busSeating", "");
           }
        } 
       
       return null;
    }
}
