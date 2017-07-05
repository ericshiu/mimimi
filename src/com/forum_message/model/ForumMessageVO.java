package com.forum_message.model;

import java.sql.Timestamp;

public class ForumMessageVO {

	private String fm_no;
	private String fa_no;
	private String mem_no;
	private String fm_content;
	private Timestamp fm_publish_date;
	public ForumMessageVO() {
		super();
	}
	public ForumMessageVO(String fm_no, String fa_no, String mem_no, String fm_content, Timestamp fm_publish_date) {
		super();
		this.fm_no = fm_no;
		this.fa_no = fa_no;
		this.mem_no = mem_no;
		this.fm_content = fm_content;
		this.fm_publish_date = fm_publish_date;
	}
	public String getFm_no() {
		return fm_no;
	}
	public void setFm_no(String fm_no) {
		this.fm_no = fm_no;
	}
	public String getFa_no() {
		return fa_no;
	}
	public void setFa_no(String fa_no) {
		this.fa_no = fa_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFm_content() {
		return fm_content;
	}
	public void setFm_content(String fm_content) {
		this.fm_content = fm_content;
	}
	public Timestamp getFm_publish_date() {
		return fm_publish_date;
	}
	public void setFm_publish_date(Timestamp fm_publish_date) {
		this.fm_publish_date = fm_publish_date;
	}
	
}
