<%@page import="com.bustrip.models.Passengers"%>
<%@page import="java.util.List"%>
<%@page import="com.bustrip.models.Trip"%>
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
  
            
         <form class="form-horizontal" style="overflow-x: hidden;" method="POST" action="${pageContext.request.contextPath}/addOn.jsp">
         <fieldset>   
        <div class="alert-success">
            <strong> 
                <%
                   String duplicateMessage = "";

                   if(getServletContext().getAttribute("success") != null){
                       out.print(getServletContext().getAttribute("success"));
                       getServletContext().setAttribute("success", "");
                   }

                   if(getServletContext().getAttribute("duplicate") != null){
                       duplicateMessage = (String)getServletContext().getAttribute("duplicate");
                       getServletContext().setAttribute("duplicate", "");
                   }
               %>
           </strong>
        </div>  
             

        <h3><i>Add the passenger on the bus trip</i></h3>
        <hr />
        
         <!-- Select From list -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="passenger">Select the passenger: </label>
                <div class="col-md-3">
                    <select name="passenger" class="form-control">
                      <% 
                      //Need handler for empty List 
                      List<Passengers> att_passengersList = (List<Passengers>)getServletContext().getAttribute("passengersList");
                      Integer passengerId = (Integer)getServletContext().getAttribute("passengerId");   
                      if(passengerId == null){
                          passengerId = 0;
                      }

                      for (Passengers passengers : att_passengersList) {
                      %>
                          <option value="<%=passengers.getId()%>" <%=passengerId.equals(passengers.getId()) ? "selected" : "" %>><%=passengers.getFisrtname()%> <%=passengers.getLastname()%>  </option>
                      <% 
                      }
                      %>
                    </select>
                    <h6><i>(Available only 5 latest passengers)</i></h6>
                </div>
        </div>
        
                    
        <!-- Select From list -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="trip">Select the trip: </label>
                <div class="col-md-3">
                    <select name="trip" class="form-control">
                      <%   
                      //Need handler for empty List 
                      List<Trip> att_tripsList = (List<Trip>)getServletContext().getAttribute("tripsList");
                      Integer tripId = (Integer)getServletContext().getAttribute("tripId");   
                      if(tripId == null){
                          tripId = 0;
                      }

                      for (Trip trip : att_tripsList) {
                      %>
                      <option value="<%=trip.getId()%>" <%=tripId.equals(trip.getId()) ? "selected" : "" %>><%=trip.getTripFrom()%> - <%=trip.getTripTo()%>  </option>
                      <%}%>
                    </select>
                    <h6><i>(Available only 5 latest trips)</i></h6>
                </div>
                 <b><i><%=duplicateMessage%></i></b>
        </div>

        <br /><br />
        
        <!-- Button -->
        <div class="form-group">
          <label class="col-md-2 control-label" for="singlebutton">Press button</label>
          <div class="col-md-3">
            <button id="singlebutton" name="singlebutton" class="btn btn-primary">Add</button>
          </div>
        </div>

        </fieldset>
        </form>
              

      </div>
    </div>
  </body>
</html>