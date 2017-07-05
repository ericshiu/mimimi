package com.baby_sleep.model;

import java.sql.Timestamp;

public class BabySleepVO implements java.io.Serializable{

	private String bs_no;
	private String bd_no;
	private Timestamp bs_start;
	private Timestamp bs_end;
	private String bs_time;
	
	public String getBs_no() {
		return bs_no;
	}
	public void setBs_no(String bs_no) {
		this.bs_no = bs_no;
	}
	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}

	public Timestamp getBs_start() {
		return bs_start;
	}
	public void setBs_start(Timestamp bs_start) {
		this.bs_start = bs_start;
	}
	public Timestamp getBs_end() {
		return bs_end;
	}
	public void setBs_end(Timestamp bs_end) {
		this.bs_end = bs_end;
	}
	public String getBs_time() {
		return bs_time;
	}
	public void setBs_time(String bs_time) {
		this.bs_time = bs_time;
	}

	
	
}