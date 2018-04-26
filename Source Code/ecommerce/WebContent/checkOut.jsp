<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/carousel.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/cartStyle.css" />
<title>Cart</title>
</head>
<body>
<body>
	<div class="navbar-wrapper">
      <div class="container-fluid">
        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container-fluid color">
            <div class="navbar-header">
            </div>
            <div class="navbar-collapse collapse">
			  
				
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
                <li><a href="RequestOrders">View Orders</a></li>
                <li><a href="userinfodisplay.jsp">Edit profile</a></li>
                <li><a href="logout.jsp">Logout</a></li>  
                </ul>
               </li>
               <li class="right"><a href="cartdisplay"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
				<li class="right"><a href="contactus.jsp"><span class="glyphicon glyphicon-earphone"></span>Contact Us</a></li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
    	<br/>
    	<br/>
    		<br/>

<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">Your CheckOut is Successful !!!!</div>
		<div class="col-md-4"></div>
	</div>
</div>
</body>
</html>