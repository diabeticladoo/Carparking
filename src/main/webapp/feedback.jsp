<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/6cfbb251e2.js" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Feedback</title>

<style>
body {
    background-color: #eee
}

div.stars {
    width: 290px;
    display: inline-flex;
    flex-direction: row-reverse;
   
}

.mt-200 {
    margin-top: 100px;
}

input.star {
    display: none
}

label.star {
    float: right;
    padding: 10px;
    font-size: 36px;
    color: #4A148C;
    transition: all .2s
}

input.star:checked~label.star:before {
    content: '\f005';
    color: #FD4;
    transition: all .25s
}

input.star-1:checked~label.star:before {
    color: #F62
}

label.star:hover {
    transform: rotate(-15deg) scale(1.3)
}

label.star:before {
    content: '\f006';
    font-family: FontAwesome
}
</style>
	
</head>
<body>
	<%
	if(session.getAttribute("Wid")==null){
		response.sendRedirect("home.jsp");
	}
	%>


	
 <section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="UpdateRating" method="post">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">Feedback</h4>
            <hr color="black">
	        <h4 class="mb-3">Please rate your overall experience:</h4>
	        
	                                

            <div class="stars align-center" >
                
                <input class="star star-5" id="star-5" type="radio" value="5" name="Rating" /> 
                <label class="star star-5" for="star-5"></label> 
                <input class="star star-4" id="star-4" type="radio" value="4" name="Rating" /> 
                <label class="star star-4" for="star-4"></label> 
                <input class="star star-3" id="star-3" type="radio" value="3" name="Rating" /> 
                <label class="star star-3" for="star-3"></label> 
                <input class="star star-2" id="star-2" type="radio" value="2" name="Rating" /> 
                <label class="star star-2" for="star-2"></label> 
                <input class="star star-1" id="star-1" type="radio" value="1" name="Rating" /> 
                <label class="star star-1" for="star-1"></label> 
              
           </div>


   <h4 class="mb-3">Additional feedback:</h4>
    <div class="form-outline mb-3">
   <textarea style="resize: none;" cols=50 rows=4 name="Addnl"></textarea>
   </div>
   <input class="btn btn-primary btn-lg btn-block" type="submit" value="Submit"> 
	</form>
	
      </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>