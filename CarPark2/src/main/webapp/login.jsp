<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login | Car Parking Solutions</title>
</head>
<body>
	<form action="Booking.jsp" method="post">
		Enter username: <input type="text" name="uname"><br>
		Enter password: <input type="password" name="pass"><br>
		<input type="submit" value="LOGIN">
	</form><br>
	<%
		if(session.getAttribute("invalid-login")!=null){
			out.println("Invalid login credentials!");
			session.removeAttribute("invalid-login");
		}
	%>
	----OR----<br>
	//Login with social media
	<!-- Login with Facebook -->
	<!-- Login with Gmail -->
	<!-- Login with Instagram -->
	<!-- Login with LinkedIn -->
</body>
</html>