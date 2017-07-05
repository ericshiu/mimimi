package com.pc_application.model;

import java.sql.*;

public class PcApplicationVO implements java.io.Serializable {
	private String pca_no;
	private String pc_no;
	private String mem_no;
	private Timestamp pca_appdate;
	private String pca_appdate_format;
	private Date pca_date;
	private String pca_status;
	private String pca_memo;
	private Date pca_review_date;
	
	
	public Date getPca_review_date() {
		return pca_review_date;
	}
	public void setPca_review_date(Date pca_review_date) {
		this.pca_review_date = pca_review_date;
	}
	public Date getPca_date() {
		return pca_date;
	}
	public void setPca_date(Date pca_date) {
		this.pca_date = pca_date;
	}
	public String getPca_no() {
		return pca_no;
	}
	public void setPca_no(String pca_no) {
		this.pca_no = pca_no;
	}
	public String getPc_no() {
		return pc_no;
	}
	public void setPc_no(String pc_no) {
		this.pc_no = pc_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Timestamp getPca_appdate() {
		return pca_appdate;
	}
	public void setPca_appdate(Timestamp pca_appdate) {
		this.pca_appdate = pca_appdate;
	}
	public String getPca_appdate_format() {
		return pca_appdate_format;
	}
	public void setPca_appdate_format(String pca_appdate_format) {
		this.pca_appdate_format = pca_appdate_format;
	}
	public String getPca_status() {
		return pca_status;
	}
	public void setPca_status(String pca_status) {
		this.pca_status = pca_status;
	}
	public String getPca_memo() {
		return pca_memo;
	}
	public void setPca_memo(String pca_memo) {
		this.pca_memo = pca_memo;
	}
	

	
}
