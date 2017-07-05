package com.fetal_movement.model;

import java.sql.*;

public class FetalMovementVO implements java.io.Serializable{
	
	private String fm_no;
	private String mem_no;
	private Timestamp fm_date ;
	private String fm_lv;
	
	
	public String getFm_no() {
		return fm_no;
	}
	public void setFm_no(String fm_no) {
		this.fm_no = fm_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getFm_lv() {
		return fm_lv;
	}
	public void setFm_lv(String fm_lv) {
		this.fm_lv = fm_lv;
	}
	public Timestamp getFm_date() {
		return fm_date;
	}
	public void setFm_date(Timestamp fm_date) {
		this.fm_date = fm_date;
	}
	

	
	
}


