package com.cart.model;

import java.io.Serializable;

public class CartVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String  pro_no;
	private Integer pro_quantity;
	private Integer sub_total;
	private String pro_name;
	private Integer pro_price;
	
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
	public Integer getSub_total() {
		return sub_total;
	}
	public void setSub_total(Integer sub_total) {
		this.sub_total = sub_total;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public Integer getPro_price() {
		return pro_price;
	}
	public void setPro_price(Integer pro_price) {
		this.pro_price = pro_price;
	}	
}
