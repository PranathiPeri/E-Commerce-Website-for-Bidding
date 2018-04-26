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
 * Servlet implementation class requestbids
 */
@WebServlet("/requestbids")
public class requestbids extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestbids() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Logger log=Logger.getLogger(requestbids.class.getName());
		log.info("In Request Bids servlet");
			
		 System.out.println("I am in request bid servlet");
		 HttpSession session=request.getSession();
		 Client c=Client.create();
		 log.info("calling Request Bids web service");
		 WebResource wr=c.resource("https://localhost:9443/ecommerceserver/displaybids/bids");
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
    
		      ArrayList bids = new ArrayList();
			  Map row;
			
			for(int i=0;i<jsonObject.length();i++){
				JSONObject pr =jsonObject.getJSONObject(i);
				String postid=request.getParameter("upid");
				String bidpostid=pr.getString("postid");
				if(postid.equals(bidpostid))
				{
				
				row= new HashMap();
				row.put("postid", pr.get("postid"));
			    row.put("cid", pr.get("cid"));
				row.put("bidderemail", pr.get("bidderemail"));
				row.put("biddesc", pr.get("biddesc"));
				row.put("quantity", pr.get("quantity"));
				row.put("price", pr.get("price"));
				row.put("createdat", pr.get("createdat"));
					bids.add(row);
				}
					}
			
			
			session.setAttribute("bids",bids);
			log.info("Retrieved Bids,so redirecting to viewbids page");
			
			RequestDispatcher rd=request.getRequestDispatcher("viewbids.jsp");
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
