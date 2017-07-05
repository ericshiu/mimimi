package com.hospital.model;


public class HospitalVO implements java.io.Serializable {
	private String hos_no;
	private String hos_name;
	private String hos_address;
	private String hos_phone;
	
	
	
	public String getHos_no() {
		return hos_no;
	}
	public void setHos_no(String hos_no) {
		this.hos_no = hos_no;
	}
	public String getHos_name() {
		return hos_name;
	}
	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}
	public String getHos_address() {
		return hos_address;
	}
	public void setHos_address(String hos_address) {
		this.hos_address = hos_address;
	}
	public String getHos_phone() {
		return hos_phone;
	}
	public void setHos_phone(String hos_phone) {
		this.hos_phone = hos_phone;
	}
	
}
