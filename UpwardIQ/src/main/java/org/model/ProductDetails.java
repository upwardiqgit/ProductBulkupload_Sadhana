package org.model;

public class ProductDetails {

	private int productid;
	private String productname;
	private String productype;
	private String productprice;
	public ProductDetails() {
		
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductype() {
		return productype;
	}
	public void setProductype(String productype) {
		this.productype = productype;
	}
	public String getProductprice() {
		return productprice;
	}
	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}
	@Override
	public String toString() {
		return "ProductDetails [productid=" + productid + ", productname=" + productname + ", productype=" + productype
				+ ", productprice=" + productprice + "]";
	}
	
	
	
}
