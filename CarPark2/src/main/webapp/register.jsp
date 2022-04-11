<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register | Car Parking Solutions</title>
</head>
<body>
	<form action="CredentialRegister" method="post">
		Enter first name: <input type="text" name="fname" required>*<br>
		Enter last name: <input type="text" name="lname"><br>
		Enter username: <input type="text" name="uname" required>*<br>
		Enter password: <input type="password" name="pass" required>*<br>
		Confirm password: <input type="password" name="passCon" required>*<br>
		Enter residential address: <input type="text" name="address"><br>
		Enter email ID: <input type="text" name="email" required>*<br>
		Enter mobile no.: <input type="text" name="mobno"><br>
		Enter car registration no.: <input type="text" name="carno" required>*<br>
		<input type="submit" value="EMAIL OTP"><input type="submit" value="SMS OTP">
	</form><br>
	<form action="otp" method=post>
		Enter OTP: 
	</form>
	----OR----<br>
	//Register with social media
	<!-- Register with Facebook -->
	<!-- Register with Gmail -->
	<!-- Register with Instagram -->
	<!-- Register with LinkedIn -->
</body>
</html>