package com.baby_growth.model;

import java.sql.Date;

public class BabyGrowthVO implements java.io.Serializable {

	private String bg_no;
	private String bd_no;
	private Integer bg_height;
	private Integer bg_weight;
	private Integer bg_head;
	private Date bg_date;
	
	public Date getBg_date() {
		return bg_date;
	}
	public void setBg_date(Date bg_date) {
		this.bg_date = bg_date;
	}
	public String getBg_no() {
		return bg_no;
	}
	public void setBg_no(String bg_no) {
		this.bg_no = bg_no;
	}
	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}
	public Integer getBg_height() {
		return bg_height;
	}
	public void setBg_height(Integer bg_height) {
		this.bg_height = bg_height;
	}
	public Integer getBg_weight() {
		return bg_weight;
	}
	public void setBg_weight(Integer bg_weight) {
		this.bg_weight = bg_weight;
	}
	public Integer getBg_head() {
		return bg_head;
	}
	public void setBg_head(Integer bg_head) {
		this.bg_head = bg_head;
	}



}