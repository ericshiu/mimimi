package com.education_music.model;

import java.sql.*;

public class EducationMusicVO implements java.io.Serializable{
	
	private String em_no;
	private String em_name;
	private String em_introduction ;
	private byte[] em_content;
	
	
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getEm_name() {
		return em_name;
	}
	public void setEm_name(String em_name) {
		this.em_name = em_name;
	}
	public String getEm_introduction() {
		return em_introduction;
	}
	public void setEm_introduction(String em_introduction) {
		this.em_introduction = em_introduction;
	}
	public byte[] getEm_content() {
		return em_content;
	}
	public void setEm_content(byte[] em_content) {
		this.em_content = em_content;
	}
	
	
	

	
	
}


