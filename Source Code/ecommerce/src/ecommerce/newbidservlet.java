
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
 * Servlet implementation class newbidservlet
 */
@WebServlet("/newbidservlet")
public class newbidservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newbidservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Logger log=Logger.getLogger(signinservlet.class.getName());
		log.info("In Bid Servlet");
		System.out.println("In Bid servlet");
		String biddesc=request.getParameter("biddesc").toString();
		//System.out.println(biddesc);
		String quantity=request.getParameter("quantity").toString();
		//System.out.println(quantity);
		String price=request.getParameter("price").toString();
		//System.out.println(price);
	
		HttpSession session= request.getSession();
		String postid=session.getAttribute("bidpid").toString();
		System.out.println(postid);
		String cid=session.getAttribute("userid").toString();
		System.out.println(cid);
		
		String bidderemail=session.getAttribute("useremail").toString();
		System.out.println(bidderemail);
		
		
		MultivaluedMap m=new MultivaluedMapImpl();
		
		m.add("biddesc", biddesc);
		m.add("quantity", quantity);
		m.add("price", price);
		m.add("bidderemail", bidderemail);
		m.add("pid", postid);
		m.add("cid", cid);
		
		
		try{
			
			Client c= new Client();
			log.info("Calling Bid web service");
			System.out.println("Calling Web Services");

			WebResource wb=c.resource("https://localhost:9443/ecommerceserver/bidservice/bid");
			ClientResponse restResponse = wb.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,m);
			//System.out.println("hello: " + restResponse.getStatus() + " , entity: "+restResponse.getEntity(String.class));
			if (restResponse.getStatus() != 200) {
				//log.info("status!=200");
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200");
				String statusString = restResponse.getEntity(String.class);
                System.out.println("in else"+ statusString); 
                
                if(statusString.contentEquals("fail"))
                {
                	log.info("Bid Not Successful. So redirecting  User to Login page  ");
                    
                	response.sendRedirect("/ecommerce/login.jsp");
          
                }
                else
                {
                	log.info("Bid Successful. So redirecting  User to Bid page  ");
                    
                	response.sendRedirect("/ecommerce/bids.jsp");
                	
                }
                
				}
		
		
		
		
		
		
		}
		catch(Exception e)
		{
			log.info("Caught an exception ");
            
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
