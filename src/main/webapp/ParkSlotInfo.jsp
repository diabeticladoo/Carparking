<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpark.model.Admin"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<title>Slots</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	Admin a = (Admin) session.getAttribute("admin");
	if (a == null) {
		response.sendRedirect("admlogin.jsp");
		return;
	}

	if (session.getAttribute("Dates") == null) {
		response.sendRedirect("viewpark.jsp");
		return;
	}
	%>
	
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="ViewSlots" method="get">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">View Slots</h4>
            <hr color="black">
	        <h4 class="mb-3">Parking ID : ${Pid}</h4>
	        
	        <select class="form-select form-select-lg mb-3 btn btn-lg btn-block" aria-label=".form-select-lg example" name="Dt" required >
			<c:forEach items="${Dates}" var="date">
				<c:choose>
					<c:when test="${date.equals(dt) }">
						<option value="${date}" selected>
					</c:when>
					<c:otherwise>
						<option value="${date}">
					</c:otherwise>
				</c:choose>
				${date}</option>
			</c:forEach>
		</select>
            <label class="form-label" for="typeuname">Date</label>
            
            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Load">
	        </form>
	<hr class="my-4">
	<c:choose>
		<c:when test="${Slots!=null && Slots.size()>0}">
		
		<table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
        <th scope="col">Start Time</th>
	    <th scope="col">End Time</th>
	    <th scope="col">Remove Slot</th>
        </tr>
		</thead>
		<c:forEach items="${Slots}" var="time">
					<tr>
						<td class="align-middle">${time.getDTin()}</td>
						<td class="align-middle">${time.getDTout()}</td>
						<td class="align-middle">
						<button class="btn btn-success btn-lg" onClick="TimeR('${time.getDTout()}',${Pid})" >Remove</button></td>
						</td>
					</tr>
				</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
		<h4 class="mb-3 mt-1">No slots loaded</h4>
		</c:otherwise>
	</c:choose>
		

	<form name="TimeRem" action="RemovingTime" method="post">
		<input name="pid" type="hidden"> <input name="time"
			type="hidden">
	</form>
	<script>
		function TimeR(time,pid){
				if(confirm("Remove This slot?")){
					document.TimeRem.pid.value = pid;
					document.TimeRem.time.value = time;
					document.TimeRem.submit();
				}
			}
		

	</script>
	<hr class="my-4">
    <form name="homeForm" action="admhome.jsp"></form>
    <button class="btn btn-primary btn-lg btn-block" onclick="document.homeForm.submit()" type="submit" value="Home">Home</button><br>
	<form name="AdmLogout" action="Logout" method="post">
	<input class="btn btn-primary btn-lg btn-block" type="submit" value="Sign Out"></form>
	</form>
<%
	session.removeAttribute("Slots");
	session.removeAttribute("dt");
%>

        </div>
        </div>
      </div>
    </div>
  </div>
</section>	
</body>
</html>