package com.article_favorite.model;

public class ArticleFavoriteVO {

	private String mem_no;
	private String fa_no;
	public ArticleFavoriteVO() {
		super();
	}
	public ArticleFavoriteVO(String mem_no, String fa_no) {
		super();
		this.mem_no = mem_no;
		this.fa_no = fa_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFa_no() {
		return fa_no;
	}
	public void setFa_no(String fa_no) {
		this.fa_no = fa_no;
	}
	
	
	
	
}
