package com.product_picture.model;

public class ProductPictureVO {
	private String prp_no;
	private String pro_no;
	private byte[] prp_picture;
	
	
	public String getPrp_no() {
		return prp_no;
	}
	public void setPrp_no(String prp_no) {
		this.prp_no = prp_no;
	}
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public byte[] getPrp_picture() {
		return prp_picture;
	}
	public void setPrp_picture(byte[] prp_picture) {
		this.prp_picture = prp_picture;
	}
}