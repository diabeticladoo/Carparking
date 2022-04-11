<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login | Car Parking Solutions</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="304993750427-lhfj72u0qff5qfjg7hhiqh4v2n32ud83.apps.googleusercontent.com">

  <script src="https://apis.google.com/js/api:client.js"></script>
  <script>
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init();
      attachSignin(document.getElementById('customBtn'));
    });
};

  function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
		  	  var profile = googleUser.getBasicProfile();
			  document.FormNonCr.loginw.value = "G";
			  document.FormNonCr.fname.value = profile.getGivenName();
			  document.FormNonCr.lname.value = profile.getFamilyName();
			  document.FormNonCr.email.value = profile.getEmail();
			  document.FormNonCr.submit();
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
  }
  </script>
  <style type="text/css">
      
    #fbbtn {
      padding-top:30px;
    }
    
</style>
</head>
<body>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v12.0&appId=300478212005443&autoLogAppEvents=1" nonce="yWJJh4tB"></script>
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
          <form class="form-sigin" action="LoginCred" method="post">
            <img class="mb-1 img-fluid" src="images/kasa-Logo.png" alt="" width="150px" height="150px">
            <h3 class="mb-3 font-weight-bolder">Car Parking Solutions</h3>
            <hr color="black">
            <h4 class="mb-3">Sign in</h4>
              <div class="form-outline mb-3">
              <input type="text" id="typeuname" class="form-control form-control-lg" name="uname" required autofocus />
              <label class="form-label" for="typeuname">Username</label>
            </div>

            <div class="form-outline mb-3">
              <input type="password" id="typePass" class="form-control form-control-lg" name="pass" required/>
              <label class="form-label" for="typePass">Password</label>
            </div>
             
            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Login">
            <!-- <button class="btn btn-primary btn-lg btn-block" type="submit" value="LOGIN">Login</button> -->
            </form>
            
            <div> <h5 class="mb-3" style="font-weight:normal;"><%
		String inval = (String) session.getAttribute("invalid-login");
		if(inval!=null){
			out.println(inval);
			session.removeAttribute("invalid-login");
		}
	%></h5></div>
            
            <hr class="my-4">
   
    <form name="FormNonCr" action="LoginNonCred" method="post">
    	<input type="hidden" name="loginw">
    	<input type="hidden" name="fname">
    	<input type="hidden" name="lname">
    	<input type="hidden" name="email">
	</form>
    <div id="gSignInWrapper" class="row-justify-center">
    <div id="customBtn" class="customGPlusSignIn btn btn-outline-dark" >
      <img width="20px" style="margin-bottom:3px; margin-right:5px" alt="Google sign-in" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/512px-Google_%22G%22_Logo.svg.png" />
      Login with Google
    </div>
 
  </div>
 
  <div id="name"></div>
  <script>startApp();</script>
    
    <fb:login-button id="fbbtn" class="fb-login-button" data-width="" data-size="large" scope="public_profile,email" onlogin="checkLoginState()" data-button-type="continue_with" data-layout="default" data-auto-logout-link="false" data-use-continue-as="false"></fb:login-button>

   <script type="text/javascript">
      function onSignIn(googleUser) {
      	  //window.location.href='home.jsp';
    	  var profile = googleUser.getBasicProfile();
    	  document.FormNonCr.loginw.value = "G";
    	  document.FormNonCr.fname.value = profile.getGivenName();
    	  document.FormNonCr.lname.value = profile.getFamilyName();
    	  document.FormNonCr.email.value = profile.getEmail();
    	  document.FormNonCr.submit();
	}
   </script>
   <script>
      function myFunction() {
	      gapi.auth2.getAuthInstance().disconnect();
	      FB.logout(function(response){});
	      location.reload();
	   }
   </script><br>

<script>

  function statusChangeCallback(response) {  // Called with the results from FB.getLoginStatus().
    //console.log('statusChangeCallback');
    //console.log(response);                   // The current login status of the person.
    if (response.status === 'connected') {   // Logged into your webpage and Facebook. 
      //window.location.href='home.jsp?access_token='+response.authResponse.accessToken; 
  	  testAPI();
    }
  }


  function checkLoginState() {               // Called when a person is finished with the Login Button.
    FB.getLoginStatus(function(response) {   // See the onlogin handler
      statusChangeCallback(response);
    });
  }


  window.fbAsyncInit = function() {
    FB.init({
      appId      : '300478212005443',
      cookie     : true,                     // Enable cookies to allow the server to access the session.
      xfbml      : true,                     // Parse social plugins on this webpage.
      version    : 'v12.0'           // Use this Graph API version for this call.
    });


    FB.getLoginStatus(function(response) {   // Called after the JS SDK has been initialized.
      statusChangeCallback(response);        // Returns the login status.
    });
  };
  
  
 
  function testAPI() {                      // Testing Graph API after login.  See statusChangeCallback() for when this call is made.
    FB.api('/me/?fields=id,first_name,last_name,email', function(response) {
    	document.FormNonCr.loginw.value = "FB";
    	//console.log('Welcome!  Fetching your information.... ');
    	document.FormNonCr.fname.value = response.first_name;
    	document.FormNonCr.lname.value = response.last_name;
    	document.FormNonCr.email.value = response.email;
    	document.FormNonCr.submit();
    });
  }

</script>

<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js"></script>
 

          </div>
        </div>
      </div>
    </div>
  </div>
</section>

</body>
</html>