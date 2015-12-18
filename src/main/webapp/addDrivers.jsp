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
            
            
        <form class="form-horizontal" style="overflow-x: hidden;" method="POST" action="${pageContext.request.contextPath}/addDriver.jsp">
        <fieldset>
        
        <h3><i>Add the driver on the bus trip</i></h3>
        <hr />
        
        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="fname">First name</label>

          <% if(request.getAttribute("fname_error") != null) {%> 
            <label for="fname"><i>First name is missing!</i></label>
          <% } %> 

          <div class="col-md-3">
            <input name="fname" placeholder="Margus" class="form-control input-md" type="text" value="<%= request.getAttribute("fname") %>">
          </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="searchinput">Last name</label>

          <% if(request.getAttribute("lname_error") != null) {%> 
            <label for="lname"><i>Last name is missing!</i></label>
          <% } %>

          <div class="col-md-3">
            <input id="lname" name="lname" placeholder="Ainsalu" class="form-control input-md" type="text" value="<%= request.getAttribute("lname") %>">
          </div>
        </div>
        
        <!-- Text input-->
        <div class="form-group">
          <label class="col-md-2 control-label" for="license">License: </label>
          
          <% if(request.getAttribute("license_error") != null) {%> 
            <label for="license"><i>License is missing!</i></label>
          <% } %>
          
          <div class="col-md-3">
            <input name="license" placeholder="4F5RT-5T" class="form-control input-md" type="text" value="<%= request.getAttribute("license") %>">
          </div>
        </div>


        <br /> <br />
        
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