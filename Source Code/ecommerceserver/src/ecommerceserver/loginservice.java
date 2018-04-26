package ecommerceserver;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.google.gson.Gson;
import net.spy.memcached.MemcachedClient;

@Path("/loginservice")

public class loginservice {
@Path("/hi")
@Consumes("application/x-www-form-urlencoded")
@Produces(MediaType.TEXT_PLAIN)
@POST
public Response hello(MultivaluedMap <String,String> formParam)
{
	Logger log=Logger.getLogger(loginservice.class.getName());
	log.info("In User Login Service");
	String response = new String();
	String useremail=formParam.get("useremail").toString();
	String userpassword=formParam.get("userpassword").toString();
    String userlocation=formParam.get("userlocation").toString();
	    useremail = useremail.replace("[", "").replace("]", "");
	    userpassword=userpassword.replace("[", "").replace("]", "");
        userlocation=userlocation.replace("[", "").replace("]", "");
    String results=new String();
	
	try{
		Configuration cfg=new Configuration();
	    cfg.configure("conn.cfg.xml");
	    @SuppressWarnings("deprecation")
		SessionFactory sf=cfg.buildSessionFactory();
	    Session s=sf.openSession();
		
		MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		log.info("Connection to MemCache Successful");
        System.out.println("Connection to server sucessful.");
        if(mcc.get(useremail)!=null)
        {
        	log.info("Hit");
        	System.out.println("hit");
            results=mcc.get(useremail).toString();
        }
        else
        {
        	log.info("Miss");
        	System.out.println("miss");
        	    Transaction tx=s.beginTransaction();
        	    String hql = "SELECT password from customer where email="+"'"+useremail+"'";
        	    System.out.println("the query is:"+hql);
        	    Query query = s.createQuery(hql);
        	    List resultsa = query.list();
        	    results=resultsa.get(0).toString();
        	    tx.commit();        	
        	    Future fo = mcc.set(useremail, 1800,results);
        }
        Transaction tx1=s.beginTransaction();
        String hql1 = "SELECT lastlogin from customer where email="+"'"+useremail+"'";
        System.out.println("the query is:"+hql1);
        Query query1 = s.createQuery(hql1);
        List results1 = query1.list();
        tx1.commit();
        
        
        if(userpassword.contentEquals(results))
        {
        	log.info("Passwords Match");
        	System.out.println("the passwords match");
        	java.util.Date date= new java.util.Date();
    		Timestamp lastlogin=new Timestamp(date.getTime());
    		
        	
        	Transaction tx2=s.beginTransaction();
        	String hql2="update customer  set lastlogin = :lastlogin where email ="+"'"+useremail+"'";
        	Query query2=s.createQuery(hql2);
        	query2.setParameter("lastlogin", lastlogin);
        	query2.executeUpdate();
        	tx2.commit();
        	response=results1.get(0).toString();

             
	    	Transaction tx6=s.beginTransaction();
	    	String hql6="update customer  set  location = :userlocation where email ="+"'"+useremail+"'";
	    	Query query6=s.createQuery(hql6);
	    	query6.setParameter("userlocation", userlocation);
	    	query6.executeUpdate();
	    	tx6.commit();
	    	response="success";
	    	
        }
        else
        {
        	log.info("The passwords donot match:"+userpassword+" :"+results);
        	System.out.println("The passwords donot match:"+userpassword+" :"+results);
        	
        	Transaction tx3=s.beginTransaction();
        	String hql3 = "SELECT failedlogin from customer where email="+"'"+useremail+"'";
        	Query query3 = s.createQuery(hql3);
            List results3 = query3.list();
            tx3.commit();    	
        	
        	
        	Transaction tx4=s.beginTransaction();
        	String hql4="update customer  set failedlogin = :failedlogin where email ="+"'"+useremail+"'";
        	Query query4=s.createQuery(hql4);
        	int failedlogin=(int)results3.get(0)+1;
        	query4.setParameter("failedlogin", failedlogin);
        	query4.executeUpdate();
        	tx4.commit();
        	
        	
        	response="false";
        }
         System.out.println("password:"+results);
        s.close();

      //  System.out.println(mcc.flush().isDone());
    		
	}
	catch(Exception e)
	{
		log.info("Caught an exception");
		System.out.println("i caught exception:"+e.getMessage());
		response="false";
	}
	
	//return Response.ok().entity(String.valueOf(response)).build();
	
	JSONObject jsonObject = new JSONObject();
	   List review = getData(useremail);
	    // create a new Gson instance
	    Gson gson = new Gson();
	    // convert your list to json
	   String jsonCartList = gson.toJson(review);
	    System.out.println("in server:"+jsonCartList);
		
		return Response.status(200).entity((jsonCartList)).build();

}
private static List getData(String useremail)  {
	

	 Configuration cfg=new Configuration();
   cfg.configure("conn.cfg.xml");
   @SuppressWarnings("deprecation")
	 SessionFactory sf=cfg.buildSessionFactory();
   Session s=sf.openSession();
	 
       Transaction tx12=s.beginTransaction();
	     String hql12 = " from customer where email="+"'"+useremail+"'";
	     System.out.println("the query is:"+hql12);
	     Query query = s.createQuery(hql12);
	     System.out.println("query executed");
	     List results = query.list();
	     tx12.commit();
	
	
	s.close();
	return results;
}
}