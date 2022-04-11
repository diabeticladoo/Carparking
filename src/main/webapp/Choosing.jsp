<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "com.carpark.model.Park" import = "java.util.ArrayList"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="com.carpark.model.User" %>
<%@ page import="com.carpark.model.Worker" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Waiting List and Choosing Parking Spot</title>
   <script src="JS/logoutScr.js"></script>
   <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
   <meta name="google-signin-client_id" content="304993750427-lhfj72u0qff5qfjg7hhiqh4v2n32ud83.apps.googleusercontent.com">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
   <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires","0");
	
		User u = (User) session.getAttribute("user");
		if(u==null){
			response.sendRedirect("login.jsp");
			return;
		}
		if(session.getAttribute("ParkingList")==null){
			response.sendRedirect("Booking.jsp");
			return;
		}
	%>
	<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
           <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h2 class="mb-3">Choose your Parking Spots</h2>
             <hr color="black">
            
	<% String Loc = (String)session.getAttribute("Location"); %>
	<% String CiDT = (String)session.getAttribute("DateTimeIn"); %>
	<% String CoDT = (String)session.getAttribute("DateTimeOut"); %>
	<h4 class="mb-3">Your Current Details:</h4>
	        <h5 class="mb-3">Location: <%= Loc %> </h5>
            <h5 class="mb-3">Check-in Date and Time: <%= CiDT %> </h5>
            <h5 class="mb-3">Check-out Date and Time: <%= CoDT%> </h5>

	<h4>Following parking spaces are available:</h4><br>
	
  <table class="table table-bordered">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Parking Space</th>
      <th scope="col">Cost</th>
      <th scope="col">Waiting Time (hrs)</th>
      <th scope="col">Rating</th>
      <th scope="col">Services</th>
      <th scope="col">Availability</th>
    </tr>
  </thead>
  <tbody>
	
		<c:forEach items="${ParkingList}" var="pspace">
			<c:choose>
		  	<c:when test="${pspace.getEmt()==0}" >
				<tr style = "background-color : #ff4d4d">
					<td class="align-middle" style="font-weight:bold;">${pspace.getPID()}</td>
					<td class="align-middle" style="font-weight:bold;">${pspace.getCost()}</td>
					<td class="align-middle" style="font-weight:bold;">${pspace.getWaitingTime()}</td>
					<td class="align-middle strong">
						<c:choose>
							<c:when test="${pspace.getRating()>=0 }">
								${pspace.getRating() }
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td class="align-middle strong">
						<c:forEach var="i" begin="1" end="3">
							<c:choose>
								<c:when test="${pspace.getServStr().charAt(i)=='1'}">
									<c:out value="${Worker.servNames[i]}"></c:out>
								</c:when>
							</c:choose>
						</c:forEach>
					</td>
					<td class="align-middle"><button class="btn btn-secondary btn-lg" type="submit" value="Unavailable" disabled>Unavailable</button></td>
				</tr>
		  	</c:when>
		 	<c:otherwise>
				<tr style = "background-color :#7fff00">
					<td class="align-middle" style="font-weight:bold;">${pspace.getPID()}</td>
					<td class="align-middle" style="font-weight:bold;">${pspace.getCost()}</td>
					<td class="align-middle" style="font-weight:bold;">0</td>
					<td class="align-middle">
						<c:choose>
							<c:when test="${pspace.getRating()>=0 }">
								${pspace.getRating() }
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td class="align-middle">
						<c:forEach var="i" begin="0" end="3">
							<c:choose>
								<c:when test="${pspace.getServStr().charAt(i)-'0'>48}">
									<c:out value="${Worker.servNames[i]}"></c:out>
									<br>
								</c:when>
							</c:choose>
						</c:forEach>
					</td>
					<td class="align-middle"><button class="btn btn-danger btn-lg" type="submit" onclick="clickAvailable(${pspace.getPID()})">Available</button></td>
				</tr>
		  	</c:otherwise>
			</c:choose>
		</c:forEach>
	</tbody>
	</table>
	<%
		if(session.getAttribute("showWaiting")!=null){
			out.println("Join waiting list: <button onclick=\"toWaiting()\">JOIN</button>");
			session.removeAttribute("showWaiting");
		}
	%>
	<form name="payForm" action="GoToBook" method="post">
		<input type="hidden" name="Pid">
	</form>
	<form name="waitForm" action="AddWaiting" method="post">
	</form>
	<script>
		function clickAvailable(Psp){
			console.log(Psp);
			document.payForm.Pid.value = Psp;
			document.payForm.submit()
		}
		function toWaiting(){
			document.waitForm.submit()
		}
	</script>
	
	<form name="homeForm" action="home.jsp"></form>
    <button class="btn btn-primary btn-lg btn-block" onclick="document.homeForm.submit()" type="submit" value="Home">Home</button><br>
	<form name="logoutForm" action="Logout" method="post"></form>
	<button class="btn btn-primary btn-lg btn-block" onclick="myFunction()" type="submit" value="Sign Out">Sign Out</button>

        </div>
        </div>
      </div>
    </div>
  </div>
</section>	

</body>
</html>