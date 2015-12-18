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
        .inn_trip {
            display: none;
        }
    </style>
    <script>
        $( document ).ready(function() {
            $(".trip").click(function() { 
                var atr = $(this).attr('id');
                $("#id_"+atr).toggle();
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
                  <th>First name</th>
                  <th>Last name</th>
                  <th>Date of birth</th>
                  <th>Gender</th>
                </tr> 
              </thead>
            <%   
               List<Passengers> pList = (List<Passengers>)request.getAttribute("passengersList");
               for(Integer i=0; i<pList.size(); i++){
            %> 
                <tr id="<%= pList.get(i).getId() %>" class="trip">
                <td><%= pList.get(i).getId() %></td>
                <td><%= pList.get(i).getFisrtname()%></td>
                <td><%= pList.get(i).getLastname()%></td>
                <td><%= pList.get(i).getDate()%></td>
                <td><%= pList.get(i).getGender()%></td>
              </tr>
            <tr>
                <td colspan="5" class="inn_trip alert-info" id="id_<%= pList.get(i).getId() %>">
                <%
                    if(pList.get(i).getTrips().size() > 0) { %>
                    <i>Available only 10 latest trips: </i><br /><br />
                 <%      
                        List<Trip> tList = pList.get(i).getTrips();
                        for(Integer k=0; k < tList.size(); k++){
                        if(k>=10){
                            break;
                        }
                %>
                <ul>
                    <li >
                        <b>Trip: </b><%=tList.get(k).getTripFrom()+ "-" + tList.get(k).getTripTo()+ " - <b>Time: </b> " + tList.get(k).getTripTime() + " - <b>Price: </b> " + tList.get(k).getPrice()+"eur"%><br />
                    </li>
                </ul>
                <%
                 }}else {
                %>
                <ul>
                    <li>
                    No trips yet!
                     </li>
                </ul>
                <%
                     }
                %>
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