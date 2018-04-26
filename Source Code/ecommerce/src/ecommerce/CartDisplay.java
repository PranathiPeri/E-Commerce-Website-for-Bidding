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

@WebServlet("/cartdisplay")
public class CartDisplay extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartDisplay() {
		super();
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log=Logger.getLogger(CartDisplay.class.getName());	
		try {
			System.out.println("in servlet");
			log.info("in CartDisplay");
			Client c=Client.create();
			log.info("calling webservice");
			WebResource webresource=c.resource("https://localhost:9443/ecommerceserver/cart/display");
			//System.out.println(webresource.toString());
			HttpSession session=request.getSession();
			
			String email=session.getAttribute("useremail").toString();
			log.info("email is"+email);
			
			ClientResponse restResponse = webresource.queryParam("display", "display").queryParam("email", email).accept("application/text").get(ClientResponse.class);
			if (restResponse.getStatus() != 200) {
				log.info("exception status!=200");
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status 200");
				String statusString = restResponse.getEntity(String.class);
				//System.out.println("In client JSOn="+statusString);
				JSONArray jsonObject = new JSONArray(statusString);
				ArrayList cart = new ArrayList();
				Map row;
				for(int i=0;i<jsonObject.length();i++){
					row = new HashMap();
					JSONObject pr =jsonObject.getJSONObject(i);
					System.out.println("JSON object "+pr.toString());
					String key = pr.getString("key");
				 	System.out.println("key is: "+key);
					String[] k =key.split("[:,]");
					String product_id = k[1].substring(1, k[1].length()-1);
					String bidemailer=k[3].substring(1, k[3].length()-2);
					row.put("product_id",product_id);
					row.put("bidemailer",bidemailer);
					row.put("name",pr.getString("name"));
					row.put("price",pr.getDouble("price"));
					row.put("quantity",pr.getDouble("quantity"));
					cart.add(row);
				}

				session.setAttribute("cart", cart);
				RequestDispatcher rd=request.getRequestDispatcher("cart.jsp");
				if(rd!=null){
				rd.forward(request, response);
				}
				else{
					
				}
				//response.getWriter().append("Served at: ").append(request.getContextPath());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger log=Logger.getLogger(CartDisplay.class.getName());
		try {
			Client c=Client.create();
			log.info("calling web service");
			WebResource webresource=c.resource("http://localhost:9080/ecommerceserver/cart/display");
			ClientResponse restResponse = webresource.queryParam("display", "delete").accept("application/text").get(ClientResponse.class);
			System.out.println(restResponse.toString());
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
				//System.out.println("uri="+restResponse.getLocation());
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status 200");
				String statusString = restResponse.getEntity(String.class);
				JSONArray jsonObject = new JSONArray(statusString);
				ArrayList cart = new ArrayList();
				Map row;
				for(int i=0;i<jsonObject.length();i++){
					row = new HashMap();
					JSONObject pr =jsonObject.getJSONObject(i);
					String key = pr.getString("key");
					String[] k =key.split("[:,]");
					String product_id = k[1].substring(1, k[1].length()-1);
				
					row.put("product_id",product_id);
					row.put("name",pr.getString("name"));
					row.put("price",pr.getDouble("price"));
					row.put("quantity",pr.getDouble("quantity"));
					cart.add(row);
				}
				System.out.println("After Object Creation");
				HttpSession session=request.getSession();
				session.setAttribute("cart", cart);
				System.out.println("after setting attributes");
				RequestDispatcher rd=request.getRequestDispatcher("/ecommerce/cart.jsp");
				rd.forward(request, response);
				//response.getWriter().append("Served at: ").append(request.getContextPath());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
