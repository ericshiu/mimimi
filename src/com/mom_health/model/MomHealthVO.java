package com.mom_health.model;

import java.sql.Date;

public class MomHealthVO implements java.io.Serializable{
	
	private String mh_no;
	private String mem_no;
	private Date mh_date;
	private Integer mh_weight;
	private Integer mh_ststolic;
	private Integer mh_diastolic;
	private Integer mh_heartbeat;
	private String mh_protein;
	private Integer mh_baby_heartbeat;
	public String getMh_no() {
		return mh_no;
	}
	public void setMh_no(String mh_no) {
		this.mh_no = mh_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Date getMh_date() {
		return mh_date;
	}
	public void setMh_date(Date mh_date) {
		this.mh_date = mh_date;
	}
	public Integer getMh_weight() {
		return mh_weight;
	}
	public void setMh_weight(Integer mh_weight) {
		this.mh_weight = mh_weight;
	}
	public Integer getMh_ststolic() {
		return mh_ststolic;
	}
	public void setMh_ststolic(Integer mh_ststolic) {
		this.mh_ststolic = mh_ststolic;
	}
	public Integer getMh_diastolic() {
		return mh_diastolic;
	}
	public void setMh_diastolic(Integer mh_diastolic) {
		this.mh_diastolic = mh_diastolic;
	}
	public Integer getMh_heartbeat() {
		return mh_heartbeat;
	}
	public void setMh_heartbeat(Integer mh_heartbeat) {
		this.mh_heartbeat = mh_heartbeat;
	}
	public String getMh_protein() {
		return mh_protein;
	}
	public void setMh_protein(String mh_protein) {
		this.mh_protein = mh_protein;
	}
	public Integer getMh_baby_heartbeat() {
		return mh_baby_heartbeat;
	}
	public void setMh_baby_heartbeat(Integer mh_baby_heartbeat) {
		this.mh_baby_heartbeat = mh_baby_heartbeat;
	}
	




}
