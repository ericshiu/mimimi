package com.vaccine_course.model;

public class VaccineCourseVO implements java.io.Serializable{

	private String vc_no;
	private String vc_name;
	private String vc_sort;
	private Integer vc_age;
	private String vc_introduction;
	private String vc_detailed;
	

	public String getVc_no() {
		return vc_no;
	}
	public void setVc_no(String vc_no) {
		this.vc_no = vc_no;
	}
	public String getVc_name() {
		return vc_name;
	}
	public void setVc_name(String vc_name) {
		this.vc_name = vc_name;
	}

	public String getVc_sort() {
		return vc_sort;
	}
	public void setVc_sort(String vc_sort) {
		this.vc_sort = vc_sort;
	}
	public Integer getVc_age() {
		return vc_age;
	}
	public void setVc_age(Integer vc_age) {
		this.vc_age = vc_age;
	}
	public String getVc_introduction() {
		return vc_introduction;
	}
	public void setVc_introduction(String vc_introduction) {
		this.vc_introduction = vc_introduction;
	}
	public String getVc_detailed() {
		return vc_detailed;
	}
	public void setVc_detailed(String vc_detailed) {
		this.vc_detailed = vc_detailed;
	}

	
}