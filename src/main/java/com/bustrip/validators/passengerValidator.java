/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bustrip.validators;

import com.bustrip.enums.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author roman.gelokhov
 */
@Stateless
@LocalBean
public class passengerValidator {

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
    
    public Date checkDateInput(String param_date, HttpServletRequest request) {
 
       Date getDate = null;
        
       if(param_date.length() == 0) {        
            request.setAttribute("errors", true);
            request.setAttribute("date_error", true);
            request.setAttribute("date", "");
        } else {
                String[] dateArray = param_date.split("\\/");
                
                String pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
                
                Pattern dateRule = Pattern.compile(pattern);
                Matcher dateMatch = dateRule.matcher(param_date);

                if(dateMatch.find()) {
                    String month = dateArray[0];
                    String day = dateArray[1];
                    String year = dateArray[2];

                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, Integer.parseInt(year));
                    cal.set(Calendar.MONTH, Integer.parseInt(month));
                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
                    getDate = cal.getTime();

                    
                    request.setAttribute("date", param_date);
                    
                    return getDate;

                } else {
                    request.setAttribute("errors", true);
                    request.setAttribute("date_format_error", true);
                    request.setAttribute("date", param_date);
                }  
        } // end else
       
       return getDate;
    }
    
    public Gender setGenderAtt(String param_gender, HttpServletRequest request) {
        
        request.setAttribute("gender", param_gender);
        
        Gender gender = Gender.valueOf(param_gender);
        
        return gender;
    }
}
