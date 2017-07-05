package com.baby_excretion.model;


import java.sql.Timestamp;

public class BabyExcretionVO implements java.io.Serializable{
	
	private String bex_no;
	private String bd_no;
	private Timestamp bex_date;
	private String bex_details;
	
	
	public String getBex_no() {
		return bex_no;
	}
	public void setBex_no(String bex_no) {
		this.bex_no = bex_no;
	}
	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}

	public Timestamp getBex_date() {
		return bex_date;
	}
	public void setBex_date(Timestamp bex_date) {
		this.bex_date = bex_date;
	}
	public String getBex_details() {
		return bex_details;
	}
	public void setBex_details(String bex_details) {
		this.bex_details = bex_details;
	}
	
	

}
