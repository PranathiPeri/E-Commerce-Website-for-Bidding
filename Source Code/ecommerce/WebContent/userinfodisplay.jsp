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

    <title>Display User Info</title>
     <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">


    

    <!-- Bootstrap core CSS -->
    <link href="./bootstrap/Signin Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./bootstrap/Signin Template for Bootstrap_files/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./bootstrap/Signin Template for Bootstrap_files/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./bootstrap/Signin Template for Bootstrap_files/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script>
	function passwordcheck()
	{
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
   
  <%String s=new String();
     if(request.getParameter("saved")!=null)
     {
    	 s="your changes have been saved!";
     }
   %>
   
 <%String FirstName=new String();     FirstName=" ";%>
	<%String LastName=new String();     LastName=" ";%>
	    <%String timedisplay=new String(); timedisplay=" ";%>
	      <%String emaildisplay=new String();
	                emaildisplay=" ";%>
	<%if(session.getAttribute("useremail")!=null)
		{
		      FirstName=session.getAttribute( "fname" ).toString();
		      LastName=session.getAttribute( "lname" ).toString();
		    timedisplay=session.getAttribute("lastlogin").toString();
		    emaildisplay=session.getAttribute("useremail").toString();
		}%>
	 
	<div class="container-fluid">
	<div class="navbar-wrapper">
      <div class="container-fluid">
        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container-fluid color">
            <div class="navbar-header">
            </div>
            <div class="navbar-collapse collapse">

				
			  <br/>
			  <br/>
			  	<ul class="nav navbar-nav">
			  	<li  class="active"><a href="index1.jsp">Home</a></li>
				<li><a href="newuser.jsp">Sign Up</a></li>
				<li class="dropdown">
                <a href="#"class="dropdown-toggle" type="button" data-toggle="dropdown">
                <span class="glyphicon glyphicon-pencil"></span>Posts<span class="caret"></span></a>
                <ul class="dropdown-menu">
                <li><a href="Postpage.jsp">Make a new Post</a></li>
                <li><a href="requestmyposts">View My posts</a></li>
                <li><a href="requestposts">View All posts</a></li>
                </ul>
               </li>
				
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
				<li class="dropdown">
                <a href="#"class="dropdown-toggle" type="button" data-toggle="dropdown">
                <span class="glyphicon glyphicon-user"></span>Login<span class="caret"></span></a>
                <ul class="dropdown-menu">
                <li><a href="login.jsp">Login</a></li>
                <li><a href="requestmyposts">View Orders</a></li>
                <li><a href="userinfodisplay.jsp">Edit profile</a></li>
                <li><a href="logout.jsp">Logout</a></li>  
                </ul>
               </li>
				
<!--                 <li class="right"><a href="#"><span class="glyphicon glyphicon-user"></span>Login</a></li> -->
                
                <li class="right"><a href="cartdisplay"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
				<li class="right"><a href="contactus.jsp"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
</div>
    	<br/>
    	<br/>
    		<br/>
		<div class="container">
<form  action="edituserservlet" onSubmit="return passwordcheck();" method="post">
       <div class="form-group"> 
         <h4><p><%=s %></p><h4> 
         <h3 class="form-signin-heading">You can edit your details here</h3>
       </div>
       <div class="form-group row">	
		 <h5>First Name<sup style="color:red">*</sup></h5>
		 <input type="text" name="firstname" class="form-control" placeholder="First Name" value="<%=FirstName %>" required autofocus>
        </div>
        <div class="form-group row">
          <h5>Last Name<sup style="color:red">*</sup></h5>
		  <input type="text" name="lastname" class="form-control" placeholder="Last Name" value="<%=LastName %>" required autofocus>
		</div>
		<div class="form-group row">
		   <h5>Email<sup style="color:red">*</sup></h5>
		   <input type="email" name="inputemail" class="form-control" placeholder="Email address" value="<%=emaildisplay %>"readonly autofocus>
        </div>
        <div class="form-group row">
            <h5>Create Password</h5>
		    <input type="password" name="inputpassword" class="form-control" placeholder="Password">
        </div>
        <div class="form-group row">
             <h5>Re-type Password</h5>
		     <input type="password" name="retypepassword" class="form-control" placeholder="Password">
        </div> 		
        <div style="display:inline-block">
		<button class="btn btn-lg btn-primary btn-block" id="regularsign" type="submit">Save</button>
		<button class="btn btn-lg btn-primary btn-block" id="regularsign" type="reset" style="float:right">Cancel</button>
		</div>

	 </form>

</div>
</body>
</html>
