
package ecommerce;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
/**
 * Servlet implementation class newpostservlet
 */
@WebServlet("/newpostservlet")
public class newpostservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newpostservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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

		Logger log=Logger.getLogger(newpostservlet.class.getName());
		log.info("In Post servlet");
		System.out.println("In Post servlet");
		String about=request.getParameter("about").toString();
		//System.out.println(about);
		String postdesc=request.getParameter("postdesc").toString();
		//System.out.println(postdesc);
		String quantity=request.getParameter("quantity").toString();
		//System.out.println(quantity);
		String price=request.getParameter("price").toString();
		//System.out.println(price);
	
		HttpSession session=request.getSession();
		String email=session.getAttribute("useremail").toString();
		//System.out.println(email);
		
		
		MultivaluedMap m=new MultivaluedMapImpl();
		m.add("about", about);
		m.add("postdesc", postdesc);
		m.add("quantity", quantity);
		m.add("price", price);
		m.add("email", email);
		
		
		try{
			
			Client c= new Client();
			log.info("calling Post web service");
			System.out.println("Calling Web Services");

			WebResource wb=c.resource("https://localhost:9443/ecommerceserver/postservice/post");
			ClientResponse restResponse = wb.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,m);
			//System.out.println("hello: " + restResponse.getStatus() + " , entity: "+restResponse.getEntity(String.class));
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200");
				String statusString = restResponse.getEntity(String.class);
                System.out.println("in else"+ statusString); 
                
                if(statusString.contentEquals("fail"))
                {
                	log.info("Failed to post the item. So redirecting  User to Login page  ");
                	
                	response.sendRedirect("/ecommerce/login.jsp");
          
                }
                else
                {
                	log.info("Post Successful. So redirecting  User to Post page  ");
            	
                	response.sendRedirect("/ecommerce/Postpage.jsp");
                	
                }
                
				}
		
		
		
		
		
		
		}
		catch(Exception e)
		{
			log.info("Caught an exception");
    	     System.out.println("i caught an exception"+e.getMessage());	
		}
		
		
		
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
