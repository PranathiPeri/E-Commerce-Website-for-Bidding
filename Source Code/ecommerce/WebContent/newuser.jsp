<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>New User</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"></script>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug
    <link href="./ie10-viewport-bug-workaround.css" rel="stylesheet">
     -->
    <!-- Custom styles for this template 
    <link href="./signin.css" rel="stylesheet">
     -->
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./bootstrap/ie8-responsive-file-warning.js"></script><![endif]
    <script src="./ie-emulation-modes-warning.js"></script>
    -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script>
	function passwordcheck()
	{
	   if(!document.getElementById("termsagreement").checked)
	   {
	      document.getElementById("inputpassword").value="";
		  document.getElementById("retypepassword").value="";
	      document.getElementById("errormessage").innerHTML="please agree to the terms and conditions";
		  return false;
	   }
	  if(document.getElementById("inputpassword").value==document.getElementById("retypepassword").value)
	    {
		  return true;
		}
		else
         {
		 document.getElementById("inputpassword").value="";
		 document.getElementById("retypepassword").value="";
		 document.getElementById("errormessage").innerHTML="The passwords donot match";
		  return false;
		 }		
		 
	}
	</script>
  </head>
  <body> 
    <div class="container">
<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
    
      <form class="form-signin" action="newuserservlet" onSubmit="return passwordcheck();" method="post">
        <h3 class="form-signin-heading">Please enter your details</h3>
        <%if(request.getParameter("register")!=null){%>
        	<p>email id already exists! </p>
        	<%} %>
		<sub id="errormessage" style="color:red; font-size:5=10px"></sub>
		<h5>First Name<sup style="color:red">*</sup></h5>
		<label for="firstname" class="sr-only">First Name</label>
        <input type="text" name="firstname" class="form-control" placeholder="First Name" required autofocus>
		<h5>Last Name<sup style="color:red">*</sup></h5>
		<label for="lastname" class="sr-only">Last Name</label>
        <input type="text" name="lastname" class="form-control" placeholder="Last Name" required autofocus>
		<h5>Email<sup style="color:red">*</sup></h5>
		<label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="inputemail" class="form-control" placeholder="Email address" required autofocus>
        <h5>Create Password<sup style="color:red">*</sup></h5>
		<label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="inputpassword" class="form-control" placeholder="Password" required>
        <h5>Re-type Password<sup style="color:red">*</sup></h5>
		<label for="retypepassword" class="sr-only">Password</label>
        <input type="password" name="retypepassword" class="form-control" placeholder="Password" required>
        		
		<div class="checkbox">
          <label>
            <input type="checkbox" id="termsagreement"><sup style="color:red">*</sup> I agree to the Ecommerce <a href="terms.html" target="_blank">terms of service and privacy policy</a>
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="regularsign" type="submit">Sign in</button>
		<p><center>OR</center></p>
		<button class="btn btn-lg btn-primary btn-block" id="googlesign" type="submit">Sign in with google</button>
      </form>


</div>
<div class="col-sm-4"></div>
</div>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
    <script src="./ie10-viewport-bug-workaround.js"></script>
    -->
  </body>
</html>
