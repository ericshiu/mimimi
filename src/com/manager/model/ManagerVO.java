package com.manager.model;

import java.io.Serializable;

public class ManagerVO implements Serializable {

	private String man_no;
	private String man_id;
	private String man_name;
	private String man_password;
	private String man_email;
	
	public ManagerVO() {
		super();
	}

	public ManagerVO(String man_no, String man_id, String man_name, String man_password, String man_email) {
		super();
		this.man_no = man_no;
		this.man_id = man_id;
		this.man_name = man_name;
		this.man_password = man_password;
		this.man_email = man_email;
	}

	public String getMan_no() {
		return man_no;
	}

	public void setMan_no(String man_no) {
		this.man_no = man_no;
	}

	public String getMan_id() {
		return man_id;
	}

	public void setMan_id(String man_id) {
		this.man_id = man_id;
	}

	public String getMan_name() {
		return man_name;
	}

	public void setMan_name(String man_name) {
		this.man_name = man_name;
	}

	public String getMan_password() {
		return man_password;
	}

	public void setMan_password(String man_password) {
		this.man_password = man_password;
	}

	public String getMan_email() {
		return man_email;
	}

	public void setMan_email(String man_email) {
		this.man_email = man_email;
	}
	
	
	
}
