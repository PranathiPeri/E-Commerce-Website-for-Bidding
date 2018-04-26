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

@Path("/postservice")
public class postservice {
	@Path("/post")
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response newpost (MultivaluedMap m)
	{
		Logger log=Logger.getLogger(postservice.class.getName());
		log.info("In new Post Service");
			
		System.out.println("im in new Post service");
		try{
		    	Configuration cfg=new Configuration();
			    cfg.configure("conn.cfg.xml");
			    @SuppressWarnings("deprecation")
			    SessionFactory sf=cfg.buildSessionFactory();
		        Session s=sf.openSession();
		         String useremail=m.get("email").toString().replace("[", "").replace("]", "");
		      
		        Transaction tx=s.beginTransaction();
			    String hql = "SELECT cid from customer where email="+"'"+useremail+"'";
			    System.out.println("the query is:"+hql);
			    Query query = s.createQuery(hql);
			    List results1 = query.list();
			    String id=results1.get(0).toString();
			    int cid=Integer.parseInt(id);
			    System.out.println(cid);
		        tx.commit();	    
		      
		      
		        Transaction tx1=s.beginTransaction();
		        java.util.Date date= new java.util.Date();
				Timestamp flogin=new Timestamp(date.getTime());
		        post p=new post();
               // System.out.println("after post object creation"); 
              p.setCid(cid);
	          p.setAbout(m.get("about").toString().replace("[", "").replace("]", ""));
	          p.setPostDesc(m.get("postdesc").toString().replace("[", "").replace("]", ""));
	          p.setQuantity(Integer.parseInt(m.get("quantity").toString().replace("[", "").replace("]", "")));
	          p.setPrice(Float.parseFloat(m.get("price").toString().replace("[", "").replace("]", "")));
	          p.setCreatedat(flogin);
	                        
	           s.save(p);
	           s.flush();
			   tx1.commit();
	           s.close();
		     log.info("After a new post is made ");
                  
		}
		catch(Exception e)
		{
			log.info("Caught an Exception ");
            System.out.println("i caught an exception in hibernate" );
			e.printStackTrace();
			return Response.ok().entity(e.getMessage()).build();
		}
		
		return Response.ok().entity("success").build();
	}

}

