<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.carpark.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires","0");
	
		User u = (User) session.getAttribute("user");
		if(u==null){
			response.sendRedirect("landing.jsp");
		}else{
			out.println("Hello " + u.getFn() + "!");
			out.println("Your registered car is: " + u.getCarno());
			
		}
	%><br>
	<form action="Logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>