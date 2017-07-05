package com.baby_data.model;

import java.sql.Date;

public class BabyDataVO implements java.io.Serializable{
	private String bd_no;
	private String mem_no;
	private Integer bd_order;
	private String bd_name;
	private String bd_sex;
	private Date bd_pre;
	private Date bd_birthday;
	private byte[] bd_pictures;
	

	public String getBd_no() {
		return bd_no;
	}
	public void setBd_no(String bd_no) {
		this.bd_no = bd_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getBd_order() {
		return bd_order;
	}
	public void setBd_order(Integer bd_order) {
		this.bd_order = bd_order;
	}
	public String getBd_name() {
		return bd_name;
	}
	public void setBd_name(String bd_name) {
		this.bd_name = bd_name;
	}
	public String getBd_sex() {
		return bd_sex;
	}
	public void setBd_sex(String bd_sex) {
		this.bd_sex = bd_sex;
	}
	public Date getBd_pre() {
		return bd_pre;
	}
	public void setBd_pre(Date bd_pre) {
		this.bd_pre = bd_pre;
	}

	public Date getBd_birthday() {
		return bd_birthday;
	}
	public void setBd_birthday(Date bd_birthday) {
		this.bd_birthday = bd_birthday;
	}
	public byte[] getBd_pictures() {
		return bd_pictures;
	}
	public void setBd_pictures(byte[] bd_pictures) {
		this.bd_pictures = bd_pictures;
	}


	
	
	
}