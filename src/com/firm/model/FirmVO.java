package com.firm.model;

import java.io.Serializable;

public class FirmVO implements Serializable{
	private String  fir_no;
	private String  fir_id;
	private String  fir_password;
	private String  fir_name;
	private String  fir_type;
	private String  fir_phone;
	private String  fir_address;
	private String  fir_email;
	private Integer fir_eva_good;
	private Integer fir_eva_normal;
	private Integer fir_eva_bad;
	private String  fir_account;
	private String  fir_authority;
	
	public FirmVO(){
		
	}
	
	public FirmVO(String fir_no, String fir_id, String fir_password, String fir_name, String fir_type,
			String fir_phone, String fir_address, String fir_email, Integer fir_eva_good,
			Integer fir_eva_normal, Integer fir_eva_bad, String fir_account, String fir_authority) {
		super();
		this.fir_no = fir_no;
		this.fir_id = fir_id;
		this.fir_password = fir_password;
		this.fir_name = fir_name;
		this.fir_type = fir_type;
		this.fir_phone = fir_phone;
		this.fir_address = fir_address;
		this.fir_email = fir_email;
		this.fir_eva_good = fir_eva_good;
		this.fir_eva_normal = fir_eva_normal;
		this.fir_eva_bad = fir_eva_bad;
		this.fir_account = fir_account;
		this.fir_authority = fir_authority;
	}

	public String getFir_no() {
		return fir_no;
	}

	public void setFir_no(String fir_no) {
		this.fir_no = fir_no;
	}

	public String getFir_id() {
		return fir_id;
	}

	public void setFir_id(String fir_id) {
		this.fir_id = fir_id;
	}

	public String getFir_password() {
		return fir_password;
	}

	public void setFir_password(String fir_password) {
		this.fir_password = fir_password;
	}

	public String getFir_name() {
		return fir_name;
	}

	public void setFir_name(String fir_name) {
		this.fir_name = fir_name;
	}

	public String getFir_type() {
		return fir_type;
	}

	public void setFir_type(String fir_type) {
		this.fir_type = fir_type;
	}

	public String getFir_phone() {
		return fir_phone;
	}

	public void setFir_phone(String fir_phone) {
		this.fir_phone = fir_phone;
	}

	public String getFir_address() {
		return fir_address;
	}

	public void setFir_address(String fir_address) {
		this.fir_address = fir_address;
	}

	public String getFir_email() {
		return fir_email;
	}

	public void setFir_email(String fir_email) {
		this.fir_email = fir_email;
	}

	public Integer getFir_eva_good() {
		return fir_eva_good;
	}

	public void setFir_eva_good(Integer fir_eva_good) {
		this.fir_eva_good = fir_eva_good;
	}

	public Integer getFir_eva_normal() {
		return fir_eva_normal;
	}

	public void setFir_eva_normal(Integer fir_eva_normal) {
		this.fir_eva_normal = fir_eva_normal;
	}

	public Integer getFir_eva_bad() {
		return fir_eva_bad;
	}

	public void setFir_eva_bad(Integer fir_eva_bad) {
		this.fir_eva_bad = fir_eva_bad;
	}

	public String getFir_account() {
		return fir_account;
	}

	public void setFir_account(String fir_account) {
		this.fir_account = fir_account;
	}

	public String getFir_authority() {
		return fir_authority;
	}

	public void setFir_authority(String fir_authority) {
		this.fir_authority = fir_authority;
	}

}
