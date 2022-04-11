<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Worker Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
</head>
<body>

<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="WrkLogin" method="post">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">Worker Log-in</h4>
              <div class="form-outline mb-3">
              <input type="text" id="typeuname2" class="form-control form-control-lg" name="uname" required autofocus" />
              <label class="form-label" for="typeuname2">Username</label>
            </div>

            <div class="form-outline mb-3">
              <input type="password" id="typePass2" class="form-control form-control-lg" name="pass" required/>
              <label class="form-label" for="typePass2">Password</label>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit" value="LOGIN">Login</button>
            </form>
            <hr class="my-4">

	<div><%
		String inval = (String) session.getAttribute("invalid-wrklogin");
		if(inval!=null){
			out.println(inval);
			session.removeAttribute("invalid-wrklogin");
		}
	%></div>
	   </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>