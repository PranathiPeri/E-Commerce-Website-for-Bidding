package ecommerceserver;

import java.io.Serializable;

public class cartKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productid;
	private String custemail;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCustemail() {
		return custemail;
	}
	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}

}
