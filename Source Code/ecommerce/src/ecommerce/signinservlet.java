package ecommerce;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class signinservlet
 */
@WebServlet("/signinservlet")
public class signinservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signinservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger log=Logger.getLogger(signinservlet.class.getName());
		String useremail=request.getParameter("useremail");
		String userpassword=request.getParameter("userpassword");
		String userlocation=request.getParameter("userlocation");
		
		//System.out.println(userlocation);
		log.info("In user login servlet");
		log.info("usermail"+useremail);
		log.info("password"+userpassword);
		log.info("User Location "+userlocation);
		MultivaluedMap m=new MultivaluedMapImpl();
		m.add("useremail", useremail);
		m.add("userpassword", userpassword);
		m.add("userlocation", userlocation);
		HttpSession session= request.getSession();
	  	System.out.println("user email in signin servlett........"+useremail);
   	     session.setAttribute("useremail", useremail);
   	 
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			    new javax.net.ssl.HostnameVerifier(){

			        public boolean verify(String hostname,
			                javax.net.ssl.SSLSession sslSession) {
			            if (hostname.equals("localhost")) {
			                return true;
			            }
			            return false;
			        }
			    });
		try{
			
			Client c=Client.create();
			log.info("calling User Login web service");
			WebResource webresource=c.resource("https://localhost:9443/ecommerceserver/loginservice/hi");
			ClientResponse restResponse = webresource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,m);
			
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200");
				String statusString = restResponse.getEntity(String.class);
				System.out.println(statusString);
				System.out.println("After returning from app server");
                
				
				  JSONArray jsonObject = new JSONArray(statusString);
				 
			    JSONObject pr =jsonObject.getJSONObject(0);
			    session.setAttribute("userid", pr.get("cid"));
			    session.setAttribute("fname", pr.get("firstname"));
			    session.setAttribute("lname", pr.get("lastname"));
			    session.setAttribute("lastlogin", pr.get("lastlogin"));
			    session.setAttribute("location", pr.get("location"));
			    
			    System.out.println(session.getAttribute("userid"));
			    
                if(!statusString.contentEquals("false"))
                {
                	log.info("Login Successful. So redirecting to User  Home page  ");
                	
                	System.out.println("In redirection to index ");
                	 response.sendRedirect("index1.jsp");
                }
                else
                {
                	log.info("Login failed. So redirecting  User to Login page ");
                	
                	response.sendRedirect("/ecommerce/login.jsp?status=false");
                	
                }
                
				}
			
		}
		catch(Exception e)
		{
		  System.out.println("exception"+e.getMessage());	
		}
 }
}
