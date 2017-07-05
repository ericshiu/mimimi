package com.order_detail.model;

import java.io.Serializable;

public class OrderDetailVO implements Serializable{
	private String  ord_no;
	private String  pro_no;
	private Integer pro_quantity;
	
	
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public Integer getPro_quantity() {
		return pro_quantity;
	}
	public void setPro_quantity(Integer pro_quantity) {
		this.pro_quantity = pro_quantity;
	}
	
}
