<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://connect.facebook.net/en_US/all.js#appId=338865516480455&xfbml=1"></script>

<script>

 
 FB.init({
  appId   : '338865516480455',
  status  : true, 
  cookie  : true, 
  xfbml   : true 
});
   
   
   
   function callFBLogin(){
  FB.login(function(response) {
    if (response.authResponse) {
	
	FB.api('/me', {fields: 'email'}, function(response) {
		//response.sendRedirect("signinservlet?useremail=pari@gmail.com &userpassword=12345&userlocation=dallas");
		 alert('Success:ful login for: ' + response.email + JSON.stringify(response));
			location.href="https://localhost:8443/ecommerce/index1.jsp"	   
		
    // alert('Successful login for: ' + response.email + JSON.stringify(response));
         	
});
     /* FB.api('/me', function(response) {
        alert("Recieved information: "+JSON.stringify(response));
        // You can send the required information to the server side for your custom needs
        // (like creating a local user for the site when somebody logs into the system using 
        // facebook first time etc) from here.
       }); */
    }else{
                alert("Access not authorized.");
        }
  },{scope: 'email'});
}
   
   
   
   
</script>

</head>
<body>
<a href="#" class="btn btn-lg btn-block omb_btn-facebook">
			        <i class="fa fa-facebook visible-xs"></i>
			        <span onclick="callFBLogin()" class ="hidden-xs"> Click Here to Login with Facebook</span>
		        </a>
<p id="hidden"> </p>
</body>
</html>