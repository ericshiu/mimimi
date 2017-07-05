package com.pc_report.model;

import java.sql.Date;

public class PcReportVO implements java.io.Serializable {
	private String pcr_no;
	private String pc_no;
	private String mem_no;
	private String pcr_type;
	private String pcr_content;
	private Date pcr_date;
	private String pcr_status;
	private Date pcr_review_date;
	
	
	public String getPcr_no() {
		return pcr_no;
	}
	public void setPcr_no(String pcr_no) {
		this.pcr_no = pcr_no;
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
	public String getPcr_type() {
		return pcr_type;
	}
	public void setPcr_type(String pcr_type) {
		this.pcr_type = pcr_type;
	}
	public String getPcr_content() {
		return pcr_content;
	}
	public void setPcr_content(String pcr_content) {
		this.pcr_content = pcr_content;
	}
	public Date getPcr_date() {
		return pcr_date;
	}
	public void setPcr_date(Date pcr_date) {
		this.pcr_date = pcr_date;
	}
	public String getPcr_status() {
		return pcr_status;
	}
	public void setPcr_status(String pcr_status) {
		this.pcr_status = pcr_status;
	}
	public Date getPcr_review_date() {
		return pcr_review_date;
	}
	public void setPcr_review_date(Date pcr_review_date) {
		this.pcr_review_date = pcr_review_date;
	}

}
