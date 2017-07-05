package com.forum_report.model;

import java.sql.Timestamp;

public class ForumReportVO {

	private String fr_no;
	private String mem_no;
	private String fr_am_no;
	private String fr_type;
	private String fr_title;
	private String fr_content;
	private Timestamp fr_publish_date;
	private String fr_status;
	private Timestamp fr_review_date;
	public ForumReportVO() {
		super();
	}
	public ForumReportVO(String fr_no, String mem_no, String fr_am_no, String fr_type, String fr_title,
			String fr_content, Timestamp fr_publish_date, String fr_status, Timestamp fr_review_date) {
		super();
		this.fr_no = fr_no;
		this.mem_no = mem_no;
		this.fr_am_no = fr_am_no;
		this.fr_type = fr_type;
		this.fr_title = fr_title;
		this.fr_content = fr_content;
		this.fr_publish_date = fr_publish_date;
		this.fr_status = fr_status;
		this.fr_review_date = fr_review_date;
	}
	public String getFr_no() {
		return fr_no;
	}
	public void setFr_no(String fr_no) {
		this.fr_no = fr_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFr_am_no() {
		return fr_am_no;
	}
	public void setFr_am_no(String fr_am_no) {
		this.fr_am_no = fr_am_no;
	}
	public String getFr_type() {
		return fr_type;
	}
	public void setFr_type(String fr_type) {
		this.fr_type = fr_type;
	}
	public String getFr_title() {
		return fr_title;
	}
	public void setFr_title(String fr_title) {
		this.fr_title = fr_title;
	}
	public String getFr_content() {
		return fr_content;
	}
	public void setFr_content(String fr_content) {
		this.fr_content = fr_content;
	}
	public Timestamp getFr_publish_date() {
		return fr_publish_date;
	}
	public void setFr_publish_date(Timestamp fr_publish_date) {
		this.fr_publish_date = fr_publish_date;
	}
	public String getFr_status() {
		return fr_status;
	}
	public void setFr_status(String fr_status) {
		this.fr_status = fr_status;
	}
	public Timestamp getFr_review_date() {
		return fr_review_date;
	}
	public void setFr_review_date(Timestamp fr_review_date) {
		this.fr_review_date = fr_review_date;
	}
	

}