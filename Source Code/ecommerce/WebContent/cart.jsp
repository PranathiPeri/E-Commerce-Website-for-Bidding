<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/cartStyle.css" />
<title>Cart</title>
<script>
	function incrementValue(x, id) {
		var value = parseFloat(x.nextSibling.nextSibling.innerHTML);
		//     document.write(value);
		value = isNaN(value) ? 0 : value;
		value++;
		//     document.write(value);
		x.nextSibling.nextSibling.innerHTML = value;
		quantity(value, id);
	}
	function decrementValue(y, id) {
		var value = parseFloat(y.previousSibling.previousSibling.innerHTML);
		value = isNaN(value) ? 0 : value;
		if (value > 1)
			value--;
		else
			value = 1;
		y.previousSibling.previousSibling.innerHTML = value;
		quantity(value, id);
	}

	function quantity(value, id) {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
			}
		}
		xhr.open('POST', 'cartupdate?quantity=' + value + '&id=' + id, true);
		xhr.send(null);
	}

	function Delete(d, id) {
		d.parentElement.parentElement.style.display = 'none';
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
			}
		}
		xhr.open('GET', 'cartupdate?id=' + id, true);
		xhr.send(null);
	}
</script>

</head>
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
    <div class="container" id="display">
		<c:forEach items="${cart}" var="cart">
			<div class="row" id="margin">
			
				<div class="col-md-2" id="textWrap">
							<c:out value='${cart.name}' />
							<br />
							<span class="red"><fmt:formatNumber value="${cart.price}"
									type="currency" /></span>
				</div>
				<div class="col-md-2 add">
					<p>
						<span class="btn btn-sm btn-info glyphicon glyphicon-plus"
							onclick="incrementValue(this,'${cart.product_id}')"></span> <span
							class="text"><fmt:formatNumber value='${cart.quantity}' /></span>
						<span class="btn btn-sm btn-info glyphicon glyphicon-minus"
							onclick="decrementValue(this,'${cart.product_id}')"></span>
					</p>
				</div>
				<div class="col-md-2 add">
					<button class="btn btn-sm btn-danger"
						onclick="Delete(this,'${cart.product_id}')">Delete</button>
				</div>
					</div>
		</c:forEach>
		<div class="row" id="margin">
			<div class="col-md-4 add" id="div"></div>
			<div class="col-md-4 add">
				<form action="cart" method="POST">
					<input type="submit" value="Check Out"
						class="btn btn-lg btn-success" />
				</form>
			</div>
			<div class="col-md-4 add"></div>
		</div>
	</div>	
		
</body>
</html>