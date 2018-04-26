package ecommerce;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Servlet implementation class newuserservlet
 */
@WebServlet("/newuserservlet")
public class newuserservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newuserservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		Logger log=Logger.getLogger(newuserservlet.class.getName());
		String firstname=request.getParameter("firstname").toString();
		String lastname=request.getParameter("lastname").toString();
		String email=request.getParameter("inputemail").toString();
		String password=request.getParameter("inputpassword").toString();
        log.info("user details:"+firstname+" "+lastname+" "+email+" "+password);
		MultivaluedMap m=new MultivaluedMapImpl();
		m.add("firstname", firstname);
		m.add("lastname", lastname);
		m.add("email", email);
		m.add("password", password);
		
		try{
			Client c= new Client();
			log.info("calling New User web service");
			WebResource wb=c.resource("https://localhost:9443/ecommerceserver/newuserservice/register");
			ClientResponse restResponse = wb.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,m);
			
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200");
				String statusString = restResponse.getEntity(String.class);
                //System.out.println("in else"+statusString); 
                
                if(statusString.contentEquals("fail"))
                {
                	log.info("Registration failed. So redirecting to New User Registration ");
                	response.sendRedirect("/ecommerce/newuser.jsp?register=false");
          
                }
                else
                {
                	log.info("Registration Successful. So redirecting to Login Form ");
            	
                	response.sendRedirect("/ecommerce/login.jsp");
                	
                }
                
				}
		
		
		
		
		
		
		}
		catch(Exception e)
		{
		        System.out.println("i caught an exception"+e.getMessage());	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
