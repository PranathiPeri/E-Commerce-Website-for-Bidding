<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %> --%>
<!DOCTYPE html>
<html>
<head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <meta name="viewport" content="width=device-width, initial-scale=1">
       <meta name="description" content="">
       <meta name="author" content="">
   
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    
       <!-- Latest compiled and minified CSS -->
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >

       <!-- Optional theme -->
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >

        <!-- Latest compiled and minified JavaScript -->
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
  
	
<title>Home page</title>
</head>
<body>
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
	
	<div class="container-fluid">
	<%String UserName=new String(); 
	       UserName=" ";%>
	<%String timedisplay=new String();
	       timedisplay=" ";%>
	      <%String locationdisplay=new String();
	                locationdisplay=" ";%>
	<%if(session.getAttribute("useremail")!=null)
		{
		    UserName=session.getAttribute( "fname" ).toString()+" " +session.getAttribute( "lname" ).toString();
		    timedisplay=session.getAttribute("lastlogin").toString();
		    locationdisplay=session.getAttribute("location").toString();
		}%>
	<pre> Welcome User : <%=UserName %>     Last login :  <%=timedisplay %>      Last Login Location : <%=locationdisplay %> </pre>
	
</div>		
	
  	<div class="container-fluid">
  	<div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <a href="Postpage.jsp">
          <img class="first-slide" src="./Pics/post.png" alt="First slide">  
           </a>
        </div>
        <div class="item">        
          <a href="requestposts">
          <img class="second-slide" src="./Pics/viewposts.png" alt="Second slide">
          </a>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
</div>
</body>

</html>