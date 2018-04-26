package ecommerceserver;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

@Path("/cart")
public class cartServ {

	@SuppressWarnings({ "unchecked" })
	private static List<Object> getData(String email) throws IOException  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();

		//Transaction
		Transaction tx = session.beginTransaction();
		List<Object> shoppingCart;
		shoppingCart =  session. createQuery("from cart where cust_email='"+email+"'").list();
		System.out.println("shoppingCart : "+ shoppingCart.toString());
		tx.commit();
		session.close();
		return shoppingCart;
	}
	@SuppressWarnings({ "unchecked" })
	private static boolean check(String pid,String email) throws IOException  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		boolean present=false;
		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();

		//Transaction
		Transaction tx = session.beginTransaction();
		List<Object> shoppingCart;
		shoppingCart =  session. createQuery("from cart where product_id='"+pid+"' and cust_email='"+email+"'").list();
		tx.commit();
		session.close();
		if(!shoppingCart.isEmpty()){
			present=true;
		}
		System.out.println("Present value: "+present);
		return present;
	}

	@SuppressWarnings({ "unchecked" })
	private static List<Object> getRow(String pid,String email) throws IOException  {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();

		//Transaction
		Transaction tx = session.beginTransaction();
		List<Object> shoppingCart;
		shoppingCart =  session. createQuery("from cart where product_id='"+pid+"' and cust_email='"+email+"'").list();
		tx.commit();
		session.close();
		return shoppingCart;
	}


	@Path("/display")
	@GET
	@Produces("application/text")
	public Response viewCart(@QueryParam("display") String display,@QueryParam("email") String email) throws IOException {
		
		List<Object> cartItems = getData(email);
		// create a new Gson instance
		Gson gson = new Gson();
		// convert your list to json
		String jsonCartList = gson.toJson(cartItems);
		System.out.println("cartlist : "+jsonCartList);
		return Response.status(200).entity(jsonCartList).build();
		//return jsonObject;
	}

	@SuppressWarnings("unused")
	@Path("/{productId}/{email}/{name}/{price}/{quantity}")
	@GET
	@Produces("application/text")
	public Response addtoCart(@PathParam("productId") String pid,@PathParam("email") String email,@PathParam("name") String name,@PathParam("price") String price,@PathParam("quantity") String quantity) throws IOException {

		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();
		//Transaction
		Transaction tx = session.beginTransaction();

		boolean present=check(pid,email);
		String en ="false";
		if(present){
			cartKey key = new cartKey();
			cart cart = new cart();
			key.setProductid(pid);
			key.setCustemail(email);
			cart.setKey(key);
			cart.setName(name);
			float p = Float.parseFloat(price);
			cart.setPrice(p);
			int q = Integer.parseInt(quantity);
			cart.setQuantity(q+1);
			session.saveOrUpdate(cart);
			tx.commit();
			session.close();
			en="false";
		}
		else{
			cartKey key = new cartKey();
			cart cart = new cart();
			key.setProductid(pid);
			key.setCustemail(email);
			cart.setKey(key);
			cart.setName(name);
			float p = Float.parseFloat(price);
			cart.setPrice(p);
			int q = Integer.parseInt(quantity);
			cart.setQuantity(q);
			//System.out.println("In cart server");
			//System.out.println("Cart Key is: "+cart.getKey().toString());
			//System.out.println("Cart Name is: "+cart.getName().toString());
			session.save(cart);
			//session.saveOrUpdate(cart);
			tx.commit();
			session.close();
			en="true";
		}
		List<Object> cartItems = getData(email);
		// create a new Gson instance
		Gson gson = new Gson();
		// convert your list to json
		String jsonCartList = gson.toJson(cartItems);
		return Response.status(200).entity(jsonCartList).build();
	}

	@SuppressWarnings({ "unused" })
	@Path("/update/{productId}/{email}/{quantity}")
	@GET
	@Produces("application/text")
	public Response updateCart(@PathParam("productId") String pid,@PathParam("email") String email,@PathParam("quantity") String quantity) throws IOException {

		try{
			Configuration cfg = new Configuration();
			cfg.configure("conn.cfg.xml");

			//Session
			SessionFactory factory = cfg.buildSessionFactory();
			org.hibernate.Session session = factory.openSession();
			//Transaction
			Transaction tx = session.beginTransaction();

			boolean present=check(pid,email);
			String en ="false";
			if(present){
				List<Object> cartList = getRow(pid,email);
				Gson gson1 = new Gson();
				// convert your list to json
				String jsonCartList1 = gson1.toJson(cartList);
				JSONArray jsonObject1 = new JSONArray(jsonCartList1);
				JSONObject pr =jsonObject1.getJSONObject(0);
				String name= pr.getString("name");
				String price = pr.getString("price");
				cartKey key = new cartKey();
				cart cart = new cart();
				key.setProductid(pid);
				key.setCustemail(email);
				cart.setKey(key);
				cart.setName(name);
				float p = Float.parseFloat(price);
				cart.setPrice(p);
				int q = Integer.parseInt(quantity);
				cart.setQuantity(q);
				session.saveOrUpdate(cart);
				tx.commit();
				session.close();
				en="false";
			}
			else{
			}
			List<Object> cartItems = getData(email);
			// create a new Gson instance
			Gson gson = new Gson();
			// convert your list to json
			String jsonCartList = gson.toJson(cartItems);
			return Response.status(200).entity(jsonCartList).build();

		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(200).entity("true").build();
		}
	}

	@SuppressWarnings("unused")
	@Path("/delete/{productId}/{email}")
	@GET
	@Produces("application/text")
	public Response deleteProduct(@PathParam("productId") String pid,@PathParam("email") String email) throws IOException {

		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();
		//Transaction
		Transaction tx = session.beginTransaction();
		boolean present=check(pid,email);
		String en ="false";
		if(present){
			cartKey key = new cartKey();
			cart cart = new cart();
			key.setProductid(pid);
			key.setCustemail(email);
			cart.setKey(key);
			session.delete(cart);
			tx.commit();
			session.close();
			en="false";
		}
		else{
			System.out.println("Nothing to delete");
			en="true";
		}
		List<Object> cartItems = getData(email);
		// create a new Gson instance
		Gson gson = new Gson();
		// convert your list to json
		String jsonCartList = gson.toJson(cartItems);
		return Response.status(200).entity(jsonCartList).build();
	}

	@SuppressWarnings("unused")
	@Path("/remove/{email}")
	@GET
	@Produces("application/text")
	public Response delete(@QueryParam("remove") String remove,@PathParam("email") String email) throws IOException {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		String en ="false";
		updateOrders(email);
		cartKey key = new cartKey();
		cart cart = new cart();
		List<Object> cartItems;
		cartItems = getData(email);
		System.out.println("in remove");
		// create a new Gson instance
		Gson gson = new Gson();
		// convert your list to json
		String jsonCartList = gson.toJson(cartItems);
		JSONArray jsonObject;
		try {
			jsonObject = new JSONArray(jsonCartList);		
			for(int i=0;i<jsonObject.length();i++){
				SessionFactory factory = cfg.buildSessionFactory();
				org.hibernate.Session session = factory.openSession();
				Transaction tx = session.beginTransaction();
				JSONObject pr =jsonObject.getJSONObject(i);
				String key1 = pr.getString("key");
				System.out.println("key is: "+key1);
				String[] k =key1.split("[:,]");
				String product_id = k[1].substring(1, k[1].length()-1);
				key.setCustemail(email);
				key.setProductid(product_id);
				cart.setKey(key);
				System.out.println("Product id is: "+product_id);
				session.delete(cart);
				System.out.println("in delete from cart");
				tx.commit();
				session.close();
			}
			
			en="false";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200).entity(jsonCartList).build();
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private void updateOrders(String email) {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");
		orders order = new orders();
		List<Object> cartItems;
		List<Object> customer;
		try {
			cartItems = getData(email);
			customer =getCustomer(email);

			Gson gson1 = new Gson();
			// convert your list to json
			String jsonCartList1 = gson1.toJson(customer);
			JSONArray jsonObject1 = new JSONArray(jsonCartList1);
			JSONObject cust =jsonObject1.getJSONObject(0);
			int cid= Integer.parseInt(cust.getString("cid"));
			// create a new Gson instance
			Gson gson = new Gson();
			String jsonCartList = gson.toJson(cartItems);
			JSONArray jsonObject = new JSONArray(jsonCartList);

			for(int i=0;i<jsonObject.length();i++){
				SessionFactory factory = cfg.buildSessionFactory();
				org.hibernate.Session session = factory.openSession();
				Transaction tx = session.beginTransaction();
				JSONObject pr =jsonObject.getJSONObject(i);
				String key = pr.getString("key");
				String[] k =key.split("[:,]");
				String product_id = k[1].substring(1, k[1].length()-1);
				order.setCid(cid);
				order.setProductid(product_id);
				order.setQuantity(Integer.parseInt(pr.getString("quantity")));
				order.setDeliverydate(null);
				order.setShippingaddress("7777 McCallum Blvd,Dallas,TX");
				order.setStatus("not delivered");
				System.out.println("in orders update");
				session.save(order);
				tx.commit();
				session.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@SuppressWarnings("unchecked")
	private List<Object> getCustomer(String email) {
		Configuration cfg = new Configuration();
		cfg.configure("conn.cfg.xml");

		//Session
		SessionFactory factory = cfg.buildSessionFactory();
		org.hibernate.Session session = factory.openSession();
		System.out.println("in get customer");
		//Transaction
		Transaction tx = session.beginTransaction();
		List<Object> customer;
		customer =  session. createQuery("from customer where email='"+email+"'").list();
		//System.out.println("shoppingCart : "+ shoppingCart.toString());
		tx.commit();
		session.close();
		return customer;
	}
}
