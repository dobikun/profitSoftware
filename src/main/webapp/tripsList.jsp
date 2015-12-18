<%@page import="com.bustrip.services.TripService"%>
<%@page import="com.bustrip.models.Driver"%>
<%@page import="com.bustrip.models.Trip"%>
<%@page import="java.util.List"%>
<%@page import="com.bustrip.models.Passengers"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Profit Software Project</title>

    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
   
    <!-- Bootstrap core CSS -->
    <link href="css/dashboard.css" rel="stylesheet">
    
    <style>
        .hideDriver {
            display: none;
            border: 1px solid #DBDBDB;
            margin-left: -18px;
            margin-top: 27px;
            width: auto;
            height: auto;
            padding: 12px;
            position: absolute;
            z-index: 9999;
            -moz-box-shadow: 0 0 15px #DBDBDB;
            -webkit-box-shadow: 0 0 15px #DBDBDB;
            box-shadow: 0 0 15px #DBDBDB;
            border-radius: 10px;
            color: #31708f;
            background-color: #d9edf7;
        }
        .hidePassenger {
            display: none;
            border: 1px solid #DBDBDB;
            margin-left: -9px;
            margin-top: 8px;
            width: auto;
            height: auto;
            padding: 12px;
            position: absolute;
            z-index: 9999;
            -moz-box-shadow: 0 0 15px #DBDBDB;
            -webkit-box-shadow: 0 0 15px #DBDBDB;
            box-shadow: 0 0 15px #DBDBDB;
            border-radius: 10px;
            color: #31708f;
            background-color: #d9edf7;
        }
    </style>
    <script>
        $( document ).ready(function() {
        $(".hoverme1").mouseover(function() {
            var sid = $(this).attr('tid');
            var tid = $("#show_drivers_"+sid).attr('tid');
            $("#d_id_"+tid).slideDown();
         });
         
        $(".hoverme1").mouseleave(function() {
            var sid = $(this).attr('tid');
            var tid = $("#show_drivers_"+sid).attr('tid');
            $("#d_id_"+tid).slideUp();
        });
        
        
        $(".hoverme2").mouseover(function() {
            var sid = $(this).attr('tid');
            var tid = $("#show_passenger_"+sid).attr('tid');
            $("#p_id_"+tid).slideDown();
         });
         
        $(".hoverme2").mouseleave(function() {
            var sid = $(this).attr('tid');
            var tid = $("#show_passenger_"+sid).attr('tid');
            $("#p_id_"+tid).slideUp();
        });
      });
    </script>
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
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                    <th>ID</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Time</th>
                    <th>Drivers</th>
                    <th>Price</th>
                    <th>Bus</th>
                    <th>Seating</th>
                    <th>Passengers </th>
                </tr> 
              </thead>
                <%   
                   List<Driver> driversList = (List<Driver>)request.getAttribute("driversList");
                   List<Trip> pList = (List<Trip>)request.getAttribute("tripsList");
                   
                   for(Integer i=0; i<pList.size(); i++){
                %> 
                
                <!--Common-->
                <td><%= pList.get(i).getId()%></td>
                <td><%= pList.get(i).getTripFrom() %></td>
                <td><%= pList.get(i).getTripTo() %></td>
                <td width="280px"><%= pList.get(i).getTripTime() %></td>
                <!--Drivers-->
                <td id="show_drivers_<%= pList.get(i).getId() %>" tid="<%= pList.get(i).getId() %>" class="hoverme1">
                   
                    <%
                        if(pList.get(i).getDrives().size() > 0) {
                    %>
                        <%= pList.get(i).getDrives().size() %>
                    <%
                        } else {    
                    %>
                        0
                    <%
                        }   
                    %>
                    
                    <span id="d_id_<%= pList.get(i).getId() %>" class="hideDriver">
                    <%
                        if(pList.get(i).getDrives().size() > 0) {
                        List<Driver> dList = (List<Driver>)pList.get(i).getDrives();
                        for(Integer j=0; j<dList.size(); j++){
                    %>
                        <%=
                            dList.get(j).getFirstName() + " " + dList.get(j).getLastName() + "<br />"
                        %>
                    <%
                        }
                         } else {
                    %>
                        No drivers on this trip yet!
                    <%
                        }
                    %>
                    </span>
                </td>
                <td><%= pList.get(i).getPrice() %></td>
                <td><%= pList.get(i).getBusDetails().getModelName() %></td>
                <td><%= pList.get(i).getBusDetails().getSeatingCapacity() %></td>

                 
                <!--Passengers-->
                <td id="show_passenger_<%= pList.get(i).getId() %>" tid="<%= pList.get(i).getId() %>" class="hoverme2">
                    
                    <%
                        if(pList.get(i).getPassengers().size() > 0) {
                    %>
                        <%= pList.get(i).getPassengers().size() %>
                    <%
                        } else {    
                    %>
                        0
                    <%
                        }   
                    %>
                    
                    <br />

                    <span id="p_id_<%= pList.get(i).getId() %>" class="hidePassenger">
                    <%
                        if(pList.get(i).getPassengers().size() > 0) {
                        List<Passengers> passengerList = pList.get(i).getPassengers();
                        for(Integer k=0; k<passengerList.size(); k++){
                    %>
                        <%=
                            passengerList.get(k).getFisrtname()+ "  " + passengerList.get(k).getLastname() + "<br />"
                        %>
                    <%
                        }
                      }   else {
                    %>
                        No passengers on this trip yet!
                    <%
                        }
                    %>
                    </span>
                </td>
              </tr>
            <%
              }
            %>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>