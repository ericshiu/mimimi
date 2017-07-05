package com.vaccine_notes.model;

import java.sql.Date;

public class VaccineNotesVO implements java.io.Serializable{

	private String vn_no;
	private String bd_no;
	private String vc_no;
	private Date vn_date;
	
	public String getVn_no() {
		return vn_no;
	}
	public void setVn_no(String vn_no) {
		this.vn_no = vn_no;
	}
	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}
	public String getVc_no() {
		return vc_no;
	}
	public void setVc_no(String vc_no) {
		this.vc_no = vc_no;
	}
	public Date getVn_date() {
		return vn_date;
	}
	public void setVn_date(Date vn_date) {
		this.vn_date = vn_date;
	}
	
	
}