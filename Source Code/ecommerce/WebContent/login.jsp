<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- saved from url=(0040)http://getbootstrap.com/examples/signin/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Signin</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]
    <script src="./boostrap/Signin Template for Bootstrap_files/ie-emulation-modes-warning.js"></script>
    -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
      
    <script src="http://connect.facebook.net/en_US/all.js#appId=338865516480455&xfbml=1"></script>

    <script>
    $(document).ready(function(){ 
    	 $.get("https://ipinfo.io", function(response) {
    	     $("#address").val(response.city + ", " + response.region);
    	     }, "jsonp");
    });
        </script>
    
    
   
  </head>
 
  <body>

    <div class="container">
    <div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
      <form class="form-signin" action="signinservlet"  method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <%String text="invalid username/password. Please try again"; %>
         <% if((request.getParameter("status")!=null))
         {
        	// System.out.println("status"+request.getParameter("status"));
         %>
         <p style="color:red;font-size:"6px"><%= text%></p>
        	 
         <% }%>
        	
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="useremail" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="userpassword" class="form-control" placeholder="Password" required="" vk_1855e="subscribed">
        <input type="hidden" name="userlocation" id="address" value="First Time Login" >
        <div class="checkbox">
          
		  <a href="forgot.jsp" style="float:right">forgot password?</a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="signin" type="submit">Sign in</button>
		</form>
		<center>OR</center>
        <form class="form-signin" action="Test.jsp" onSubmit="return buttoncheck();" method="post">
		<button class="btn btn-lg btn-primary btn-block" id="googlesignin"  type="submit">Sign in with Facebook</button>
		</form>



	  
        <a href="newuser.jsp">  <h4 style="text-align:center">Don't have an account??</h4></a>
        <p class="listprice"></p>
		
		
</div>
<div class="col-sm-4"></div>
</div>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug 
        <script src="./Signin Template for Bootstrap_files/ie10-viewport-bug-workaround.js"></script>
    -->
  

</body>
</html>