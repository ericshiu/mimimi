package com.authority_list.model;

public class AuthorityListVO {

	private String man_no;
	private String aut_no;
	
	public AuthorityListVO() {
		super();
	}
	
	public AuthorityListVO(String man_no, String aut_no) {
		super();
		this.man_no = man_no;
		this.aut_no = aut_no;
	}

	public String getMan_no() {
		return man_no;
	}

	public void setMan_no(String man_no) {
		this.man_no = man_no;
	}

	public String getAut_no() {
		return aut_no;
	}

	public void setAut_no(String aut_no) {
		this.aut_no = aut_no;
	}
	
}