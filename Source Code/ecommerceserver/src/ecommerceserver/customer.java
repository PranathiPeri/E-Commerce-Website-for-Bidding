package ecommerceserver;

import java.io.Serializable;
import java.util.Date;

public class customer implements Serializable{
	private int cid;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Date lastlogin;
	private int failedlogin;
	private String location="firsttime";
	private static final long serialVersionUID = 1L;
	
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public int getFailedlogin() {
		return failedlogin;
	}
	public void setFailedlogin(int faillogin) {
		this.failedlogin = faillogin;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}

