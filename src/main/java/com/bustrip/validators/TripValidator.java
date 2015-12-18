/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.validators;

import com.bustrip.enums.Gender;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author roman.gelokhov
 */
@Stateless
@LocalBean
public class TripValidator {

     public Integer checkPriceInput(String param_price, HttpServletRequest request) {
 
       if(param_price.length() == 0) {
           
            request.setAttribute("errors", true);
            request.setAttribute("price_error", true);
            request.setAttribute("price", "");
            
        } else {
           
           try {
                Integer price = Integer.valueOf(param_price);
                request.setAttribute("price", price);
            
                return price;
                
           }catch(NumberFormatException e){
               request.setAttribute("errors", true);
               request.setAttribute("price_error", true);
               request.setAttribute("price", "");
           }
        } 
       
       return null;
    }
     
    public Integer checkDayInput(String param_day, HttpServletRequest request) {
 
       if(param_day.length() == 0) {
           
            request.setAttribute("errors", true);
            request.setAttribute("day_error", true);
            request.setAttribute("day", "");
            
        } else {
           
           try {
                Integer day = Integer.valueOf(param_day);
                request.setAttribute("day", day);
            
                return day;
           } catch (NumberFormatException e) {
               request.setAttribute("errors", true);
               request.setAttribute("day_error", true);
               request.setAttribute("day", "");
           }
        } 
       
        return 0;
    }
    
    public Integer checkHourInput(String param_hour, HttpServletRequest request) {
 
       if(param_hour.length() == 0) {
            request.setAttribute("errors", true);
            request.setAttribute("hour_error", true);
            request.setAttribute("hour", "");
        } else {
           
           try {
                Integer hour = Integer.valueOf(param_hour);
                request.setAttribute("hour", hour);

                return hour;
           } catch (NumberFormatException e) {
                request.setAttribute("errors", true);
                request.setAttribute("hour_error", true);
                request.setAttribute("hour", "");  
           }      
        } 
            return 0;
    }
    
    public Integer checkMinutehInput(String param_minute, HttpServletRequest request) {
 
       if(param_minute.length() == 0) {
            request.setAttribute("errors", true);
            request.setAttribute("minute_error", true);
            request.setAttribute("minute", "");
        } else {

           try {
                Integer minute = Integer.valueOf(param_minute);
                request.setAttribute("minute", minute);

                return minute;
           } catch (NumberFormatException e) {
               request.setAttribute("errors", true);
               request.setAttribute("minute_error", true);
               request.setAttribute("minute", "");
           }
        } 
            return 0;
    }
    
    public String setFromAtt(String param_from, HttpServletRequest request) {
        request.setAttribute("from", param_from);
        
        return param_from;
    }
    
    public String setToAtt(String param_to, HttpServletRequest request) {
        request.setAttribute("to", param_to);
        
        return param_to;
    }
}
