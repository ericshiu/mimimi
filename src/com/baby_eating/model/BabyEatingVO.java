package com.baby_eating.model;

import java.sql.Timestamp;

public class BabyEatingVO implements java.io.Serializable{
	
	private String be_no;
	private String bd_no;
	private Timestamp be_date;
	private String be_sort;
	private Integer be_mete;
	
	public String getBe_no() {
		return be_no;
	}
	public void setBe_no(String be_no) {
		this.be_no = be_no;
	}
	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}
	public Timestamp getBe_date() {
		return be_date;
	}
	public void setBe_date(Timestamp be_date) {
		this.be_date = be_date;
	}
	public String getBe_sort() {
		return be_sort;
	}
	public void setBe_sort(String be_sort) {
		this.be_sort = be_sort;
	}
	public Integer getBe_mete() {
		return be_mete;
	}
	public void setBe_mete(Integer be_mete) {
		this.be_mete = be_mete;
	}
	
	
}
