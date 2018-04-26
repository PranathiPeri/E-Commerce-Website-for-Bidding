package ecommerce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@WebServlet("/cart")
public class Cart extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings({ })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log=Logger.getLogger(Cart.class.getName());
		try {
			Client c=Client.create();
			log.info("calling Cart webservice");
			HttpSession session=request.getSession();
			String email=session.getAttribute("useremail").toString();
			WebResource webresource=c.resource("https://localhost:9443/ecommerceserver/cart/remove");
			//System.out.println(webresource.toString());
			ClientResponse restResponse = webresource.path(email).queryParam("remove","remove").accept("application/text").get(ClientResponse.class);
			if (restResponse.getStatus() != 200) {
				log.info("exception: status!=200");
				//System.out.println("uri="+restResponse.getLocation());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("redirecting to contactusservlet");
				System.out.println("Successfull");
				HttpSession s=request.getSession();
				RequestDispatcher rd=request.getRequestDispatcher("contactusservlet");
				rd.forward(request, response);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log=Logger.getLogger(Cart.class.getName());
		try {
			log.info("Caart.java doGet()");
			String id= request.getParameter("cartpid");
			System.out.println(id);
			String price= request.getParameter("price");
			System.out.println(price);
			String name= request.getParameter("name");
			System.out.println(name);
			//String price= p.substring(1, p.length());
			//System.out.println(price);
			String quantity= request.getParameter("quant");
			System.out.println(quantity);
			Client c=Client.create();
			HttpSession session=request.getSession();
			String email=session.getAttribute("useremail").toString();
			if(session.getAttribute("useremail")==null)
			{
				response.sendRedirect("login.jsp");
			}
			
			log.info("Caart.java calling webservice");
			WebResource webresource=c.resource("http://localhost:9080/ecommerceserver/cart");
			//System.out.println(webresource.toString());
			ClientResponse restResponse = webresource.path(id).path(email).path(name).path(price).path(quantity).accept("application/text").get(ClientResponse.class);
			if (restResponse.getStatus() != 200) {
				log.info("Caart.java caught exceptio statuss !=200");
				//System.out.println("uri="+restResponse.getLocation());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("Caart.java success redirecting to page");
				System.out.println("Successfull");
				response.sendRedirect("/ecommerce/viewbids.jsp");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	
		
	}
}
