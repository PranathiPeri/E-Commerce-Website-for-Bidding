# E-Commerce-Website-for-Bidding
Designed and implemented a responsive website and a scalable web application based on the Service-oriented architecture (SOA). Implemented using RESTful Services, Hibernate, Memcached, MySQL.


Instructions:
Tomcat:
1. Download and install (unzip) Tomcat.
2. We need to have two Tomcat servers ,So change the ports in the other tomcat from 8xxx to 9xxx.
3. Create a keystore which is required  for the HTTPS .
4. Enable in the server.xml the necessary changes needed for HTTPS. 
   <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
         maxThreads="150" scheme="https" secure="true" clientAuth="false" sslProtocol="TLS" SSLEnabled="true"
         keystoreFile="C:\Users\perip\Documents\Project WPL\.keystore" keystorePass="changeit"
         compression="on" compressionMinSize="0" noCompressionUserAgents="gozilla, traviata" 
	compressableMimeType="text/html,text/xml,text/plain,text/css,text/javascript,text/json,application/x-         javascript,application/javascript,application/json">
    </Connector>
5.Make these changes in the server.xml of oth instances of Tomcat.
Eclipse:   
1. Download and install Eclipse IDE for Java EE Developers (version: Mars or Neon)
2. Download and install Java Developer Kit (JDK) 1.8
3. Associate Java 1.8 with Eclipse
4. create an Dynamic web project and import the war files
                   OR
    Copy the folders ecommerce and ecommerce server into eclipse workspace and open them in JavaEE perspective.

5. Add a Tomcat Server(with runs on port 8080,8443) in Eclipse and add the resource  ecommerce to the server and start the server.
6. Add a Tomcat Server(with runs on port 9080,9443) in Eclipse and add the resource  ecommerceserver to the server and start the server .


MySQl:
We have used the MySQL DB for the project.Make necessary changes in properties of username and password in the conn.cfg.xml 

Memcached :
Download memcached-win64-1.4.4-14 and install it(unzip) .Run the memcached application before running the project.

Log Files:
We have used the log4j.To have log files change the log4j.appender.file.File property in log4j.properties  to an appropriate file path. 

To run the project run the index.jsp  which is present in the web content of the ecommerce resource which will lead to home page of website and after  login it directs to the home page of the User.
