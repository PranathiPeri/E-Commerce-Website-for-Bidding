package ecommerceserver;

import java.io.Serializable;
import java.util.Date;

public class bid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bidid;
	private int postid;
	private int cid;
	private String	bidderemail;
	private String biddesc;
	private int quantity;
	 private float price;
	 private Date createdat;
	 
	 
	 
	  
	
	public String getBidderemail() {
		return bidderemail;
	}
	public void setBidderemail(String bidderemail) {
		this.bidderemail = bidderemail;
	}
	public int getBidid() {
		return bidid;
	}
	public void setBidid(int bidid) {
		this.bidid = bidid;
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
	public String getBidDesc() {
		return biddesc;
	}
	public void setBidDesc(String biddesc) {
		this.biddesc= biddesc;
	}
}
