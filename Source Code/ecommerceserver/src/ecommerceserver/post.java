package ecommerceserver;

import java.io.Serializable;
import java.util.Date;


public class post implements Serializable {
     private static final long serialVersionUID = 1L;
	
	 private int postid;
	 private int cid;
	 private String about;
	 private String postdesc;
	 private int quantity;
	 private float price;
	 private Date createdat;
	  
	 
	 
	 public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getAbout() {
			return about;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		
	public String getPostDesc() {
		return postdesc;
	}
	public void setPostDesc(String postdesc) {
		this.postdesc= postdesc;
	}
	
	
	
}

