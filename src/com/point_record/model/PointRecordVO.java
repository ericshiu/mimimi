package com.point_record.model;

import java.sql.Date;

public class PointRecordVO implements java.io.Serializable {
	private String pr_no;
	private String mem_no;
	private String pr_type;
	private String pr_content;
	private Date pr_date;
	private Integer pr_point;
	
	
	public String getPr_no() {
		return pr_no;
	}
	public void setPr_no(String pr_no) {
		this.pr_no = pr_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getPr_type() {
		return pr_type;
	}
	public void setPr_type(String pr_type) {
		this.pr_type = pr_type;
	}
	public String getPr_content() {
		return pr_content;
	}
	public void setPr_content(String pr_content) {
		this.pr_content = pr_content;
	}
	public Date getPr_date() {
		return pr_date;
	}
	public void setPr_date(Date pr_date) {
		this.pr_date = pr_date;
	}
	public Integer getPr_point() {
		return pr_point;
	}
	public void setPr_point(Integer pr_point) {
		this.pr_point = pr_point;
	}
	
}
