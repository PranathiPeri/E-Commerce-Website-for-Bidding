package ecommerceserver;

import java.sql.Timestamp;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.hibernate.persister.*;



import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Path("/newuserservice")
public class newuserservice {
	@Path("/register")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response newregister(MultivaluedMap m)
	{
	
		Logger log=Logger.getLogger(newuserservice.class.getName());		
		//System.out.println("im in newuser registration service");
		log.info("In New User Registration Service");
		try{
			 Configuration cfg=new Configuration();
			 cfg.configure("conn.cfg.xml");
			 @SuppressWarnings("deprecation")
			  SessionFactory sf=cfg.buildSessionFactory();
		      Session s=sf.openSession();
		      Transaction tx=s.beginTransaction();
		      java.util.Date date= new java.util.Date();
				Timestamp flogin=new Timestamp(date.getTime());
		      customer c=new customer();
             // System.out.println("after trans first Name: "+ m.get("firstname").toString()); 
	          c.setFirstname(m.get("firstname").toString().replace("[", "").replace("]", ""));
	          c.setLastname(m.get("lastname").toString().replace("[", "").replace("]", ""));
	          c.setEmail(m.get("email").toString().replace("[", "").replace("]", ""));
	          c.setPassword(m.get("password").toString().replace("[", "").replace("]", ""));
	          c.setFailedlogin(0);
	          c.setLastlogin(flogin);
	           s.save(c);
	           s.flush();
			   tx.commit();
	           s.close();
		     //System.out.println("after close: ");
	           log.info("After Creation of new customer");
                  
		}
		catch(Exception e)
		{
			log.info("I caught an Exception in hibernate");
			System.out.println("i caught an exception in hibernate" );
			e.printStackTrace();
			return Response.ok().entity(e.getMessage()).build();
		}
		
		return Response.ok().entity("success").build();
	}

}

