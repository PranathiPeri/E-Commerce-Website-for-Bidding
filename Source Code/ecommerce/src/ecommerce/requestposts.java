package ecommerce;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class requestposts
 */
@WebServlet("/requestposts")
public class requestposts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestposts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger log=Logger.getLogger(requestposts.class.getName());
		log.info("In Request Posts servlet");
		
		 System.out.println("I am in request post servlet");
		 
		 HttpSession session=request.getSession();
		 Client c=Client.create();
		 log.info("calling Request Posts web service");
		 WebResource wr=c.resource("https://localhost:9443/ecommerceserver/display/posts");
		 ClientResponse cliresponse=wr.accept("application/json").get(ClientResponse.class);
		 if (cliresponse.getStatus() != 200) {
			log.info("status!=200");
			
			throw new RuntimeException("Failed : HTTP error code : " + cliresponse.getStatus());
		 }
		 else{
				log.info("status=200");
				
			try{
			String statusString = cliresponse.getEntity(String.class);
			System.out.println(statusString);
			
			JSONArray jsonObject = new JSONArray(statusString);
    
		      ArrayList posts = new ArrayList();
			  Map row;
			
			for(int i=0;i<jsonObject.length();i++){
				JSONObject pr =jsonObject.getJSONObject(i);
				row= new HashMap();
				row.put("postid", pr.get("postid"));
				row.put("cid", pr.get("cid"));
				row.put("about", pr.get("about"));
				row.put("postdesc", pr.get("postdesc"));
				row.put("quantity", pr.get("quantity"));
				row.put("price", pr.get("price"));
				row.put("createdat", pr.get("createdat"));
				
						posts.add(row);
				
					}
			
			
			session.setAttribute("posts",posts);
			log.info("Retrieved Posts,so redirecting to viewposts page");
						RequestDispatcher rd=request.getRequestDispatcher("viewposts.jsp");
			rd.forward(request, response);
			}
			catch(Exception e){
				log.info("Caught an Exception");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
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
