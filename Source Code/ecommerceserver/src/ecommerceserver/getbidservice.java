package ecommerceserver;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.google.gson.Gson;
@Path("/displaybids")
public class getbidservice {
	
	@Path("/bids")
	@GET
	@Produces("application/json")
	public Response getBidsFromDB() {
		System.out.println("In Get Bids service start");
		Logger log=Logger.getLogger(getbidservice.class.getName());
		log.info("In Get bids Service");
		
		
		JSONObject jsonObject = new JSONObject();
		List review = getData();
		// create a new Gson instance
		 Gson gson = new Gson();
		 // convert your list to json
		 String jsonCartList = gson.toJson(review);
		 System.out.println("in server:"+jsonCartList);
		 	log.info("Bids"+jsonCartList);
			
		return Response.status(200).entity((jsonCartList)).build();
		//return jsonObject;
	}
	private static List getData()  {
		
		 Configuration cfg=new Configuration();
	     cfg.configure("conn.cfg.xml");
	     @SuppressWarnings("deprecation")
		 SessionFactory sf=cfg.buildSessionFactory();
	     Session s=sf.openSession();
		 
	         Transaction tx=s.beginTransaction();
		     String hql = "from bid";
		     System.out.println("the query is:"+hql);
		     Query query = s.createQuery(hql);
		     System.out.println("query executed");
		     List results = query.list();
		     tx.commit();
		
		
		s.close();
		return results;
	}
}



