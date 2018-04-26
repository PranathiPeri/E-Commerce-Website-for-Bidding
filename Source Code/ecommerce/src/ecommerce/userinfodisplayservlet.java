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
 * Servlet implementation class userinfodisplayservlet
 */
@WebServlet("/userinfodisplayservlet")
public class userinfodisplayservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userinfodisplayservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger log=Logger.getLogger(userinfodisplayservlet.class.getName());
		log.info("In userInfo display servlet");
				
		
		HttpSession session=request.getSession();
		String email=session.getAttribute("useremail").toString();		
		if(session.getAttribute("useremail")==null)
		{
			response.sendRedirect("login.jsp");
		}
		
		MultivaluedMap m=new MultivaluedMapImpl();
		m.add("mailaddress", email);
		
		System.out.println("the emil is!!!!!!!!!!!!"+email);
		
		try{
			Client c= new Client();
			log.info("calling UserInfo Display web service");
			
			WebResource wb=c.resource("https://localhost:9443/ecommerceserver/userinfodisplayservice/getinfo");
			ClientResponse restResponse = wb.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class,m);
		
			if (restResponse.getStatus() != 200) {
				log.info("status!=200");
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			else{
				log.info("status=200");
				String statusString = restResponse.getEntity(String.class);
				JSONArray jsonObject = new JSONArray(statusString);
				ArrayList userinfo = new ArrayList();
				Map row;
					row = new HashMap();
					JSONObject pr =jsonObject.getJSONObject(0);
							
				session.setAttribute("firstname",pr.getString("firstname"));
				session.setAttribute("lastname",pr.getString("lastname"));
				session.setAttribute("email",pr.getString("email") );
				RequestDispatcher rd=request.getRequestDispatcher("userinfodisplay.jsp");
				rd.forward(request, response);
                
				}
		
		
		
		
		
		
		}
		catch(Exception e)
		{
		        System.out.println("i caught an exception"+e.getMessage());	
		}

		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
