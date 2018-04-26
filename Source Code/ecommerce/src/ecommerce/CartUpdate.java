package ecommerce;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@WebServlet("/cartupdate")
public class CartUpdate extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log=Logger.getLogger(CartUpdate.class.getName());
		HttpSession session=request.getSession();
		String email=session.getAttribute("useremail").toString();
		
		log.info("email="+email);
		try {
			String id= request.getParameter("id");
			String quantity= request.getParameter("quantity");
			Client c=Client.create();
			log.info("calling Cart Update webservice");
			WebResource webresource=c.resource("http://localhost:9443/ecommerceserver/cart/update");
			//System.out.println(webresource.toString());
			ClientResponse restResponse = webresource.path(id).path(email).path(quantity).accept("application/text").get(ClientResponse.class);
			System.out.println(restResponse.toString());
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				//System.out.println("uri="+restResponse.getLocation());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200 redirecting to cartdisplay");
				System.out.println("Quantity update Success");
				RequestDispatcher rd=request.getRequestDispatcher("/cartdisplay");
				rd.forward(request, response);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Logger log=Logger.getLogger(CartUpdate.class.getName());
		
		try {
			String pid=request.getParameter("id").toString();
			log.info("product id"+pid);
			System.out.println("id is: "+pid);
			Client c=Client.create();
			HttpSession session=request.getSession();
			String email=session.getAttribute("useremail").toString();
			log.info("email="+email);
			log.info("caalling web service");
			WebResource webresource=c.resource("http://localhost:9080/ecommerceserver/cart/delete");
			ClientResponse restResponse = webresource.path(pid).path(email).accept("application/text").get(ClientResponse.class);
			System.out.println(restResponse.toString());
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				//System.out.println("uri="+restResponse.getLocation());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status 200");
				System.out.println("Deletion Success");
				RequestDispatcher rd=request.getRequestDispatcher("/cartdisplay");
				rd.forward(request, response);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
