package com.forum_article.model;

import java.sql.Timestamp;

public class ForumArticleVO {

	private String fa_no;
	private String mem_no;
	private String fa_title;
	private String fa_content;
	private Integer fa_like;
	private Integer fa_dislike;
	private Timestamp fa_publish_date;
	private Timestamp fa_modify_date;
	public ForumArticleVO() {
		super();
	}
	public ForumArticleVO(String fa_no, String mem_no, String fa_title, String fa_content, Integer fa_like,
			Integer fa_dislike, Timestamp fa_publish_date, Timestamp fa_modify_date) {
		super();
		this.fa_no = fa_no;
		this.mem_no = mem_no;
		this.fa_title = fa_title;
		this.fa_content = fa_content;
		this.fa_like = fa_like;
		this.fa_dislike = fa_dislike;
		this.fa_publish_date = fa_publish_date;
		this.fa_modify_date = fa_modify_date;
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
	public String getFa_title() {
		return fa_title;
	}
	public void setFa_title(String fa_title) {
		this.fa_title = fa_title;
	}
	public String getFa_content() {
		return fa_content;
	}
	public void setFa_content(String fa_content) {
		this.fa_content = fa_content;
	}
	public Integer getFa_like() {
		return fa_like;
	}
	public void setFa_like(Integer fa_like) {
		this.fa_like = fa_like;
	}
	public Integer getFa_dislike() {
		return fa_dislike;
	}
	public void setFa_dislike(Integer fa_dislike) {
		this.fa_dislike = fa_dislike;
	}
	public Timestamp getFa_publish_date() {
		return fa_publish_date;
	}
	public void setFa_publish_date(Timestamp fa_publish_date) {
		this.fa_publish_date = fa_publish_date;
	}
	public Timestamp getFa_modify_date() {
		return fa_modify_date;
	}
	public void setFa_modify_date(Timestamp fa_modify_date) {
		this.fa_modify_date = fa_modify_date;
	}
	
	
}
