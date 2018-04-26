package ecommerce;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.*;
import javax.mail.*;
 
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.activation.*;
import org.apache.log4j.Logger;
/**
 * Servlet implementation class contactusservlet
 */
@SuppressWarnings("unused")
@WebServlet("/contactusservlet")
public class email extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public email() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger log=Logger.getLogger(email.class.getName());
		HttpSession session=request.getSession();
		String email=session.getAttribute("useremail").toString();
		String bidderemail="parikshith.janagama@gmail.com";
		String bidderemail1="abhiram098@gmail.com";
		log.info("emailId is"+email);
		 ServletContext context = getServletContext();
	     String   host = context.getInitParameter("host");
	     String       port = context.getInitParameter("port");
	     final String    user = context.getInitParameter("user");
	     final String     pass = context.getInitParameter("password");
	     
	     System.out.println("details:"+host+":"+port+":"+user+":"+pass);
	     String messagebody_Bidder = "Your Bid  has been Selected by the user.The User is willing to buy product from you. We will get back to you as soon as possible.";
	     String messagebody = "A email has beed sent to the Bidder regarding your interest in the Bid. We will get back to you as soon as possible.";
	     
	     try
	     {
	    	 Properties properties = new Properties();
	         properties.put("mail.smtp.host", host);
	         properties.put("mail.smtp.port", port);
	         properties.put("mail.smtp.auth", "true");
	         properties.put("mail.smtp.starttls.enable", "true");
	  
	         // creates a new session with an authenticator
	         Authenticator auth = new Authenticator() {
	             public PasswordAuthentication getPasswordAuthentication() {
	                 return new PasswordAuthentication(user, pass);
	             }
	         };
	    	 
	         Session session1 = Session.getInstance(properties, auth);
	       
	         Message msg = new MimeMessage(session1);
	         
	         msg.setFrom(new InternetAddress(user));
	         InternetAddress[] toAddresses = { new InternetAddress(email) };
	         msg.setRecipients(Message.RecipientType.TO, toAddresses);
	         msg.setSubject("reply from ecommerce");
	         msg.setSentDate(new Date());
	         msg.setText(messagebody);
	  
	         // sends the e-mail
	         Transport.send(msg);
	         log.info("Email is sent to the User");
	         
            Message msg1 = new MimeMessage(session1);
	         
	         msg1.setFrom(new InternetAddress(user));
	         InternetAddress[] toAddresses1 = { new InternetAddress(bidderemail) };
	         msg1.setRecipients(Message.RecipientType.TO, toAddresses1);
	         msg1.setSubject("Reply from ecommerce");
	         msg1.setSentDate(new Date());
	         msg1.setText(messagebody_Bidder );
	  
	         // sends the e-mail
	         Transport.send(msg1);
	         
	         
	            Message msg2 = new MimeMessage(session1);
		         
		         msg2.setFrom(new InternetAddress(user));
		         InternetAddress[] toAddresses2 = { new InternetAddress(bidderemail) };
		         msg1.setRecipients(Message.RecipientType.TO, toAddresses2);
		         msg1.setSubject("Reply from ecommerce");
		         msg1.setSentDate(new Date());
		         msg1.setText(messagebody_Bidder );
		  
		         // sends the e-mail
		         Transport.send(msg2);
	         log.info("Email is sent to the Bidder");
	  
	         RequestDispatcher rd=request.getRequestDispatcher("/checkOut.jsp");
	         rd.forward(request, response);
	     }
	     catch(Exception e)
	     {
	    	 log.info("Caught an Exception");
	    	 System.out.println("hi i caught an exception in the email:"+e.getMessage());
	     }
}
}