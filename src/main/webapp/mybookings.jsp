<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpark.model.User" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Bookings</title>
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
		if(session.getAttribute("Bookings")==null){
			response.sendRedirect("GetBookings");
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
            <h4 class="mb-3">Your Bookings:</h4>
      
	
	<c:choose>
		<c:when test="${Bookings.size()==0 }">
		<h4 class="mb-3">No bookings yet!</h4>
		</c:when>
		<c:otherwise>
		
		<table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
        <th scope="col">Location</th>
        <th scope="col">Check-in Datetime</th>
        <th scope="col">Check-out Datetime</th>
        <th scope="col">Cost</th>
        <th scope="col">Checkout</th>
        </tr>
        </thead>
        <tbody>
			<c:forEach items="${Bookings }" var="booking">
				<tr>
					<td class="align-middle">${booking.getLoc()}</td>
					<td class="align-middle">${booking.getDTin()}</td>
					<td class="align-middle">${booking.getDTout()}</td>
					<td class="align-middle">${booking.getCost() }</td>
					<c:choose>
						<c:when test="${booking.getEmt()==0}">
							<td class="align-middle"><button class="btn btn-success btn-lg" onClick="payFor(${booking.getPID()},'${booking.getDTout()}',${booking.getCost()})">Pay</button></td>
						</c:when>
						<c:otherwise>
							<td class="align-middle">Paid</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			</tbody>
			</table>	
			<form name="payForm" action="Pay" method="post">
				<input type="hidden" name="pid">
				<input type="hidden" name="dtout">
				<input type="hidden" name="cost">
				<h4 class="mb-3" style="font-weight:normal;" >Promo code <input type="text" name="upromo" maxlength="4"></h4>
				
				<!-- <input type="text" name="upromo" maxlength="4"> -->
			</form>	
				
		</c:otherwise>
	</c:choose><br>
		<script>
		function payFor(Pid,DTout,Cost){
			console.log(Pid);
			document.payForm.pid.value = Pid;
			document.payForm.dtout.value = DTout;
			document.payForm.cost.value = Cost;
			document.payForm.submit();
		}
	</script>
	
	
	<c:choose>
		<c:when test="${Waits.size()==0 }">
		</c:when>
		<c:otherwise>
			<h4 class="mb-3">Waiting</h4>
			
		    <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
            <th scope="col">Location</th>
            <th scope="col">Check-in Datetime</th>
            <th scope="col">Check-out Datetime</th>
            </tr>
            </thead>
            <tbody>
			<c:forEach items="${Waits }" var="wait">
				<tr>
					<td class="align-middle">${wait.getLoc()}</td>
					<td class="align-middle">${wait.getDTin()}</td>
					<td class="align-middle">${wait.getDTout()}</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>		
		</c:otherwise>
	</c:choose><br>
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