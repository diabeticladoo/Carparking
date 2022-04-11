<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpark.model.Worker" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<title>Add Worker</title>
</head>
<body>
<%! String fn=""; String ln=""; String un=""; String pw=""; String ad=""; String em=""; String ph=""; String cr=""; %>

<%
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Expires","0");
	
		Worker a = (Worker) session.getAttribute("Worker");
		if(a!=null){
			response.sendRedirect("admlogin.jsp");
			return;
		}
		
		if(session.getAttribute("locList")==null){
			session.setAttribute("redirLoc","addwork.jsp");
			System.out.println(session.getAttribute("redirLoc"));
			response.sendRedirect("LocLoad");
			return;
		}
		
		%>
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="addingworker" method="post">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">Add worker</h4>
              <div class="form-outline mb-3">
              <input type="text" id="typeuname" class="form-control form-control-lg" name="fname" maxlength="128" required  />
              <label class="form-label" for="typeuname">First name</label>
            </div>

            <div class="form-outline mb-3">
              <input type="text" id="typeuname" class="form-control form-control-lg" name="lname" maxlength="128" required/>
              <label class="form-label" for="typemeuna">Last name</label>
            </div>
            <div class="form-outline mb-3">
              <input type="email" id="typeuname" class="form-control form-control-lg" name="email" maxlength="64" required/>
              <label class="form-label" for="typeuname">Email</label>
            </div>
            <div class="form-outline mb-3">
              <input type="tel" id="typeuname" class="form-control form-control-lg" name="phno" maxlength="10" required />
              <label class="form-label" for="typeuname">Mobile no.</label>
            </div> 
            
            <select class="form-select form-select-lg mb-3 btn btn-lg btn-block" aria-label=".form-select-lg example" name="Location" required >
			<c:forEach items="${locList}" var="location">
				<option value="${location}" > ${location} </option>
			</c:forEach>
            </select>
            <label class="form-label" for="typeuname">Location</label>
            
            <div class="form-outline mb-3">
              <input type="date" id="typeuname" class="form-control form-control-lg" name="DateTimeIn" required/>
              <label class="form-label" for="typeuname">Date of Joining</label>
            </div>
            
            
            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Submit">

            </form>
            <hr class="my-4">
 
        </div>
        </div>
      </div>
    </div>
  </div>
</section>
	
</body>
</html>