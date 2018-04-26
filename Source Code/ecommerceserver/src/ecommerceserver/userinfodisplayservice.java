package ecommerceserver;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;

@Path("/userinfodisplayservice")
public class userinfodisplayservice {
	@Path("/getinfo")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response newregister(MultivaluedMap m)
	{
		Logger log=Logger.getLogger(userinfodisplayservice.class.getName());
		log.info("In UserLoginDispaly Service");
		
		String emailid=m.get("mailaddress").toString().replace("[","" ).replace("]","");
		String output;
		System.out.println("im in userinfodisplayservice");
		System.out.println("email is"+emailid);
		try{
			Configuration cfg=new Configuration();
		    cfg.configure("conn.cfg.xml");
		    @SuppressWarnings("deprecation")
			SessionFactory sf=cfg.buildSessionFactory();
		    Session s=sf.openSession();
		    Transaction tx=s.beginTransaction();
		    customer c=new customer();
		    Query q1=s.createQuery("from customer where email='"+emailid+"'");
		    List results = q1.list();
		    
		    System.out.println("results are"+results);
		    Gson gson=new Gson();
		    output=gson.toJson(results);
		    
		    System.out.println("output is:"+output);
	        tx.commit();
		    s.flush();
		    s.close();
                  
		}
		catch(Exception e)
		{
			log.info("caught an exception");
			System.out.println("i caught an exception in hibernate"+e.getMessage());
			return Response.ok().entity("fail").build();
		}
		
		return Response.ok().entity(output).build();
	}

}
