package com.authority.model;

public class AuthorityVO {
	private String aut_no;
	private String aut_name;
	private String aut_content;
	
	public AuthorityVO() {
		super();
	}

	public AuthorityVO(String aut_no, String aut_name, String aut_content) {
		super();
		this.aut_no = aut_no;
		this.aut_name = aut_name;
		this.aut_content = aut_content;
	}

	public String getAut_no() {
		return aut_no;
	}

	public void setAut_no(String aut_no) {
		this.aut_no = aut_no;
	}

	public String getAut_name() {
		return aut_name;
	}

	public void setAut_name(String aut_name) {
		this.aut_name = aut_name;
	}

	public String getAut_content() {
		return aut_content;
	}

	public void setAut_content(String aut_content) {
		this.aut_content = aut_content;
	}
	
}
