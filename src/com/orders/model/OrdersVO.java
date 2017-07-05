package com.orders.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdersVO implements Serializable{
	private String ord_no;
	private Timestamp ord_date;
	private String mem_no;
	private String fir_no;
	private Timestamp ord_ship_date;
	private Timestamp ord_rec_date;
	private Integer ord_sum;
	private String ord_status;
	private String ord_ship_no;
	private String ord_rec_name;
	private String ord_rec_address;
	private String ord_rec_phone;
	private String mem_account;
	
	
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}
	public Timestamp getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Timestamp ord_date) {
		this.ord_date = ord_date;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFir_no() {
		return fir_no;
	}
	public void setFir_no(String fir_no) {
		this.fir_no = fir_no;
	}
	public Timestamp getOrd_ship_date() {
		return ord_ship_date;
	}
	public void setOrd_ship_date(Timestamp ord_ship_date) {
		this.ord_ship_date = ord_ship_date;
	}
	public Timestamp getOrd_rec_date() {
		return ord_rec_date;
	}
	public void setOrd_rec_date(Timestamp ord_rec_date) {
		this.ord_rec_date = ord_rec_date;
	}
	public Integer getOrd_sum() {
		return ord_sum;
	}
	public void setOrd_sum(Integer ord_sum) {
		this.ord_sum = ord_sum;
	}
	public String getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}
	public String getOrd_ship_no() {
		return ord_ship_no;
	}
	public void setOrd_ship_no(String ord_ship_no) {
		this.ord_ship_no = ord_ship_no;
	}
	public String getOrd_rec_name() {
		return ord_rec_name;
	}
	public void setOrd_rec_name(String ord_rec_name) {
		this.ord_rec_name = ord_rec_name;
	}
	public String getOrd_rec_address() {
		return ord_rec_address;
	}
	public void setOrd_rec_address(String ord_rec_address) {
		this.ord_rec_address = ord_rec_address;
	}
	public String getOrd_rec_phone() {
		return ord_rec_phone;
	}
	public void setOrd_rec_phone(String ord_rec_phone) {
		this.ord_rec_phone = ord_rec_phone;
	}
	public String getMem_account() {
		return mem_account;
	}
	public void setMem_account(String mem_account) {
		this.mem_account = mem_account;
	}
	
}
