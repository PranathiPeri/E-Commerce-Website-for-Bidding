package ecommerceserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

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

import net.spy.memcached.MemcachedClient;

@Path("/edituserservice")
public class edituserservice {
	@Path("/edit")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response edituser(MultivaluedMap m)
	{
		Logger log=Logger.getLogger(edituserservice.class.getName());
		log.info("In EditUserInfo  Service");
			
		System.out.println("im in edituser service");
		try{
			Configuration cfg=new Configuration();
		    cfg.configure("conn.cfg.xml");
		    @SuppressWarnings("deprecation")
			SessionFactory sf=cfg.buildSessionFactory();
		    Session s=sf.openSession();
		    Transaction tx1=s.beginTransaction();
		    customer c=new customer();
	        String firstname=m.get("firstname").toString().replace("[", "").replace("]", "");
	        String lastname=m.get("lastname").toString().replace("[", "").replace("]", "");
	        String password=m.get("password").toString().replace("[", "").replace("]", "");
	        String email=m.get("email").toString().replace("[", "").replace("]", "");
	        
	        
	        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			log.info("Connection to MemCache Successful");
	        System.out.println("Connection to server sucessful.");
	        if(mcc.get(email)!=null)
	        {
	        	log.info("Hit");
	        	System.out.println("hit");
	        	Future fo = mcc.set(email, 1800,password);
	        	log.info("Cache Updated");
	        }
	        
	        
	        
	        
	    	String hql1="update customer  set firstname = :firstname,lastname = :lastname,password = :password where email ="+"'"+email+"'";
	    	Query query1=s.createQuery(hql1);
	    	query1.setParameter("firstname",firstname);
	    	query1.setParameter("lastname",lastname);
	    	query1.setParameter("password", password);
	    	query1.executeUpdate();
	    	tx1.commit();
            
	        //s.flush();
		    s.close();
                  log.info("User Details updated");
		}
		catch(Exception e)
		{
			log.info("caught an exception");
			System.out.println("i caught an exception in hibernate"+e.getMessage());
			return Response.ok().entity("fail").build();
		}
		
		return Response.ok().entity("success").build();
	}

	

}
