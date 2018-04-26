package ecommerceserver;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.hibernate.persister.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Path("/bidservice")
public class bidservice {
	@Path("/bid")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response newpost (MultivaluedMap m)
	{
		Logger log=Logger.getLogger(loginservice.class.getName());
		log.info("In new Bid Service");
		
	
		System.out.println("im in new Bid service");
		try{
			Configuration cfg=new Configuration();
			cfg.configure("conn.cfg.xml");
			 @SuppressWarnings("deprecation")
			  SessionFactory sf=cfg.buildSessionFactory();
		      Session s=sf.openSession();
		     
		      
		      Transaction tx1=s.beginTransaction();
		      java.util.Date date= new java.util.Date();
				Timestamp flogin=new Timestamp(date.getTime());
		       bid  p=new bid();
              System.out.println("after Bid object creation"); 
              p.setPostid(Integer.parseInt(m.get("pid").toString().replace("[", "").replace("]", "")));
              p.setCid(Integer.parseInt(m.get("cid").toString().replace("[", "").replace("]", "")));
              p.setBidderemail(m.get("bidderemail").toString().replace("[", "").replace("]", "")); 
              p.setBidDesc(m.get("biddesc").toString().replace("[", "").replace("]", ""));
              p.setQuantity(Integer.parseInt(m.get("quantity").toString().replace("[", "").replace("]", "")));
	          p.setPrice(Float.parseFloat(m.get("price").toString().replace("[", "").replace("]", "")));
	          p.setCreatedat(flogin);
	          
               s.save(p);
	           s.flush();
	          tx1.commit();
	           s.close();
		 log.info("After a new bid is made");         
		}
		catch(Exception e)
		{
			log.info("Caught an Exception in hibernate");         
			System.out.println("i caught an exception in hibernate" );
			e.printStackTrace();
			return Response.ok().entity("fail").build();
		}
		
		return Response.ok().entity("success").build();
	}

}

