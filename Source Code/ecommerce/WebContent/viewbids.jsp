<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
   <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.css"/>
 
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>

<script>
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>
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
</div>    	<br/>
    	<br/>
    		<br/>
		

	<div class="container" id="display">
		<div class="row" id="margin">
		<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">	
		<thead>
		<tr>
		<th>Post ID	</th>
		<th>Bidder ID	</th>
		<th>Bidder EmailID</th>
		<th>Bid Description</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Created Time </th>
		
		</tr>
		</thead>
		<c:forEach items="${bids}" var="bids">
			<tr>
				<td><c:out value="${bids.postid}"></c:out></td>
			    <td><c:out value='${bids.cid}'></c:out></td>
			    <td><c:out value="${bids.bidderemail}"></c:out></td>
			    <td><c:out value="${bids.biddesc}"></c:out></td>
			    <td><c:out value="${bids.quantity}"></c:out></td>
			    <td><c:out value="${bids.price}"></c:out></td>
			    <td><c:out value="${bids.createdat}"></c:out>
			    
			   <a href="cart?cartpid=${bids.postid}&?bidemail=${bids.bidderemail}&quant=${bids.quantity}&price=${bids.price}&name=${bids.biddesc}" alt="Bid">Add to cart</a>  
			   </td>
			</tr>
		</c:forEach>
		</table>
		</div>
	</div>

</body>
</html>