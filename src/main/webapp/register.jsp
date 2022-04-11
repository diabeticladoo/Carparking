<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpark.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register | Car Parking Solutions</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
	<%! String fn=""; String ln=""; String un=""; String pw=""; String ad=""; String em=""; String ph=""; String cr=""; %>
	<%
	User u = (User) session.getAttribute("new-user");
	if(u!=null){
		if(u.getFn()!=null) fn = u.getFn();
		if(u.getLn()!=null) ln = u.getLn();
		if(u.getUn()!=null) un = u.getUn();
		if(u.getPw()!=null) pw = u.getPw();
		if(u.getAddr()!=null) ad = u.getAddr();
		if(u.getEmail()!=null) em = u.getEmail();
		if(u.getPhno()!=null) ph = u.getPhno();
		if(u.getCarno()!=null) cr = u.getCarno();
	}
	%>
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="RegCred" method="post">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">Register</h4>
              <div class="form-outline mb-3">
              <input type="text" id="typeuname" class="form-control form-control-lg" name="fname" value='<%= fn %>' maxlength="128" required autofocus />
              <label class="form-label" for="typeuname">First Name</label>
            </div>
            <div class="form-outline mb-3">
            <input type="text" id="typeuname" class="form-control form-control-lg" name="lname" value='<%= ln %>' maxlength="128"  />
              <label class="form-label" for="typeuname">Last Name</label>
            </div>
            <div class="form-outline mb-3">
            <input type="text" id="typeuname" class="form-control form-control-lg" name="uname" value='<%= un %>' maxlength="128"  required/>
              <label class="form-label" for="typeuname">Username</label>
            </div>
            <div class="form-outline mb-3">
            <input type="password" id="typeuname" class="form-control form-control-lg" name="pass" value='<%= pw %>' maxlength="64" required/>
              <label class="form-label" for="typeuname">Password</label>
            </div>
            <div class="form-outline mb-3">
            <input type="password" id="typeuname" class="form-control form-control-lg" name="passCon" value='' maxlength="64" required/>
            <label class="form-label" for="typeuname">Confirm Password</label>
            </div>
            <div class="form-outline mb-3">
            <input type="text" id="typeuname" class="form-control form-control-lg" name="address" value='<%= ad %>' maxlength="1024" required/>
              <label class="form-label" for="typeuname">Address</label>
            </div>
            <div class="form-outline mb-3">
              <input type="email" id="typeuname" class="form-control form-control-lg" name="email" value='<%= em %>' maxlength="64" required/>
              <label class="form-label" for="typeuname">Email</label>
            </div>
            <div class="form-outline mb-3">
              <input type="tel" id="typeuname" class="form-control form-control-lg" name="phno" value='<%= ph %>' maxlength="10" required />
              <label class="form-label" for="typeuname">Mobile no.</label>
            </div>
            <div class="form-outline mb-3">
            <input type="text" id="typeuname" class="form-control form-control-lg" name="carno" value='<%= cr %>' maxlength="16"  required/>
              <label class="form-label" for="typeuname">Car Registration number</label>
            </div>
            
            <c:choose>
		    <c:when test="${OTP!=null}">
		    </c:when>    
		    <c:otherwise>
			    <c:choose>
				    <c:when test="${validated!=null}">
				        <input class="btn btn-primary btn-lg btn-block" name="emlOTP" type="submit" value="Email OTP">
						<input class="btn btn-primary btn-lg btn-block" name="smsOTP" type="submit" value="SMS OTP">
				    </c:when>    
				    <c:otherwise>
						<input class="btn btn-primary btn-lg btn-block" name="val" type="submit" value="Validate">
				    </c:otherwise>
				</c:choose>	
		    </c:otherwise>
		</c:choose>		
	</form>
	<c:choose>
		    <c:when test="${OTP!=null}">
		    	<br>OTP has been sent!<br>
		    	<form action="VerifyOTP" method="post">
		    		<div class="form-outline mb-3">
                    <input type="text" id="typeuname" class="form-control form-control-lg" name="uOTP" required/>
                    <label class="form-label" for="typeuname">Enter OTP</label>
                    </div>
		    		<input class="btn btn-primary btn-lg btn-block" type="submit" value="Verify OTP">
		    	</form>
		    </c:when>
		</c:choose>
       <div><%
		String inval = (String) session.getAttribute("invalid-reg");
		if(inval!=null){
			out.println(inval);
			session.removeAttribute("invalid-reg");
		}
	%></div><br>   


      </div>
        </div>
      </div>
    </div>
  </div>
</section>

</body>
</html>