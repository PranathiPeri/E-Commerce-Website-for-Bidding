package ecommerceserver;

import java.io.Serializable;

public class cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	cartKey key;
	private String name;
	private int quantity;
	private float price;
	
	public cartKey getKey() {
		return key;
	}
	public void setKey(cartKey key) {
		this.key = key;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
