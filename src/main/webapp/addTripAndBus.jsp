<%@page import="java.util.List"%>
<%@page import="com.bustrip.models.Trip"%>
<%@page import="com.bustrip.enums.TripDestinations"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Adding cargo</title>

    
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
   
    <!-- Bootstrap core CSS -->
    <link href="css/dashboard.css" rel="stylesheet"/>

  </head>

    <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      
        <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Profit Software</a>
        </div>
      </div>
    </nav>
        
      
    </div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" id="navbar">
          <ul class="nav nav-sidebar">
            <li class=""><a href="${pageContext.request.contextPath}/trips.jsp">Overview the trips</a></li>
            <li><a href="${pageContext.request.contextPath}/passengers.jsp">Overview the passengers</a></li>
            <hr />
            <li><a href="${pageContext.request.contextPath}/addTrip.jsp">Add trip and bus</a></li>
            <li><a href="${pageContext.request.contextPath}/addDriver.jsp">Add driver</a></li>
            <li><a href="${pageContext.request.contextPath}/driverOnTrip.jsp">Add driver on trip</a></li>
            <li><a href="${pageContext.request.contextPath}/addPassengers.jsp">Add passenger</a></li>
            <li><a href="${pageContext.request.contextPath}/addOn.jsp">Add passenger on trip</a></li>
          </ul>
        </div>
        <div class="col-md-8 col-md-offset-2 main">
         
  
        <form class="form-horizontal" style="overflow-x: hidden;" method="POST" action="${pageContext.request.contextPath}/addTrip.jsp">
        <fieldset>

        <!-- Success message -->  
        <div class="alert-success">
            <strong> 
                <%
                    if(getServletContext().getAttribute("success") != null){
                        out.print(getServletContext().getAttribute("success"));
                        getServletContext().setAttribute("success", "");
                    }
                %>
            </strong>
        </div>
        

        <h3><i>Add trip</i></h3>
        <hr />

        <!-- Select and remember value - From -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="from">From: </label>
                <div class="col-md-3">
                    <select name="from" class="form-control">
                      <% 
                      String att_from = (String)request.getAttribute("from");
                      TripDestinations fromValue = TripDestinations.valueOf(att_from);

                      for (TripDestinations trip : TripDestinations.values()) {
                      %>
                          <option value="<%= trip %>" <%= fromValue == trip ? "selected" : "" %>> <%= trip %>  </option>
                      <% 
                      }
                      %>
                    </select>
                </div>
        </div>  
                    
                    
        <!-- Select and remember value - To -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="to">To: </label>
            <div class="col-md-3">
                <select name="to" class="form-control">
                  <% 
                  String att_to = (String)request.getAttribute("to");
                  TripDestinations toValue = TripDestinations.valueOf(att_to);

                  for (TripDestinations trip : TripDestinations.values()) {
                  %>
                      <option value="<%= trip %>" <%= toValue == trip ? "selected" : "" %>> <%= trip %>  </option>
                  <% 
                  }
                  %>
                </select>
            </div>
        </div>  
        
        <br />
        
        <h4><i>Time of trip</i></h4>
        <br />
        
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="year">Year: </label>
            <div class="col-md-3">
              <select id="selectbasic" name="year" class="form-control">
                <option value="2015">2015</option>
                <option value="2016">2016</option>
              </select>
            </div>
        </div>
        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="month">Month: </label>
            <div class="col-md-3">
              <select id="selectbasic" name="month" class="form-control">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
              </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="day">Day: </label>  
          
          <% if(request.getAttribute("day_error") != null) {%> 
          <label for="day"><i>Day is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
              <input name="day" placeholder="31" class="form-control input-md" type="text" value="<%= request.getAttribute("day") %>">
          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="hour">Hour: </label>
          
          <% if(request.getAttribute("hour_error") != null) {%> 
          <label for="hour"><i>Hour is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
              <input  name="hour" placeholder="13" class="form-control input-md" type="text" value="<%= request.getAttribute("hour") %>">
          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="minute">Minute: </label>
          
           <% if(request.getAttribute("minute_error") != null) {%> 
          <label for="hour"><i>Minute is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
            <input  name="minute" placeholder="00" class="form-control input-md" type="text" value="<%= request.getAttribute("minute") %>">
          </div>
        </div>
        
        <!-- Text input-->
        <div class="form-group control-group error">
          <label class="col-md-2 control-label" for="price">Price: </label>
          
          <% if(request.getAttribute("price_error") != null) {%> 
            <label for="price"><i>Price is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
            <input  name="price" placeholder="120.00" class="form-control input-md" type="text" value="<%= request.getAttribute("price") %>">
          </div>
        </div>

        <br />
        <h3><i>Add bus</i></h3>
        <hr />
        
        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="busMake">Bus make: </label>
          
          <% if(request.getAttribute("busMake_error") != null) {%> 
            <label for="busMake"><i>Bus make is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
            <input name="busMake" placeholder="Ilizar" class="form-control input-md" type="text" value="<%= request.getAttribute("busMake") %>">
          </div>
        </div>
        
        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="busModel">Model name: </label>
          
          <% if(request.getAttribute("busModel_error") != null) {%> 
            <label for="busModel"><i>Model name is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
            <input name="busModel" placeholder="Volvo" class="form-control input-md" type="text" value="<%= request.getAttribute("busModel") %>">
          </div>
        </div>
        
        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="busSeating">Seatings: </label>
          
          <% if(request.getAttribute("busSeating_error") != null) {%> 
            <label for="busSeating"><i>Seating is missing!</i></label>
          <% } %> 
          
          <div class="col-md-3">
            <input name="busSeating" placeholder="60" class="form-control input-md" type="text" value="<%= request.getAttribute("busSeating") %>">
          </div>
        </div>
        
        <br /> <br />
        
        <!-- Button -->
        <div class="form-group">
          <label class="col-md-2 control-label" for="singlebutton">Press button</label>
          <div class="col-md-3">
            <button id="singlebutton" name="singlebutton" class="btn btn-primary" >Add</button>
          </div>
        </div>

        </fieldset>
          
          
          <%
            if(getServletContext().getAttribute("success") != null){
                out.print(getServletContext().getAttribute("success"));
                getServletContext().setAttribute("success", "");
            }
          %>
          
          
          <% if(request.getAttribute("success") == "lucky") {%> 
            <label for="price"><i><div class="success">Trip successfully added!</div>   </i></label>
          <% } %> 
        </form>
              

      </div>
    </div>
  </body>
</html>