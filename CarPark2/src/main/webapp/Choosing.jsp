<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Waiting List and Choosing Parking Spot</title>
</head>
<body>
	<h1>Choose your Parking Spot</h1>
	<% String Loc = request.getParameter("Location"); %>
	<% String CiD = request.getParameter("DateIn"); %>
	<% String CiT = request.getParameter("TimeIn"); %>
	<% String CoD = request.getParameter("DateOut"); %>
	<% String CoT = request.getParameter("TimeOut"); %>
	<h3>Your Current Details:</h3>
	<ul style="list-style-type:none;">
		<li>Location: <%=Loc %></li>
		<li>Check-in Date: <%= CiD %></li>
		<li>Check-in Time: <%= CiT %></li>
		<li>Check-out Date: <%= CoD %></li>
		<li>Check-out Time: <%= CoT %></li>
	</ul>

</body>
</html>