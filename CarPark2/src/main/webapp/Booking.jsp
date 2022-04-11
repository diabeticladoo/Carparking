<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
</head>
<body>
<% String userid = request.getParameter("uname"); %>
	<h1>Welcome, <%=userid %>!</h1>
	<form action = "Choosing.jsp">
		<ul style="list-style-type:none;">
			<li>Location 
			<input type="range" id="Location" name="Location" min="1" max="5" step="1"></input>
	        </li><br>
    	    <li>Check-in Date 
			<input type ="date" name="DateIn" required></input></li><br>
			<li>Check-in Time
			<input type ="time" name="TimeIn" required></input></li><br>
			<li>Check-out Date 
			<input type ="date" name="DateOut" required></input></li><br>
			<li>Check-out Time 
			<input type ="time" name="TimeOut" required></input></li><br>
		</ul>
	</form type = "submit" >
</body>
</html>