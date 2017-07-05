package com.optional_test.model;

public class OptionalTestVO implements java.io.Serializable  {
	private String ot_no;
	private String ot_name;
	private Integer ot_week_start;
	private Integer ot_week_end;
	private String ot_summary;	
	
	public String getOt_summary() {
		return ot_summary;
	}
	public void setOt_summary(String ot_summary) {
		this.ot_summary = ot_summary;
	}
	public String getOt_no() {
		return ot_no;
	}
	public void setOt_no(String ot_no) {
		this.ot_no = ot_no;
	}
	public String getOt_name() {
		return ot_name;
	}
	public void setOt_name(String ot_name) {
		this.ot_name = ot_name;
	}
	public Integer getOt_week_start() {
		return ot_week_start;
	}
	public void setOt_week_start(Integer ot_week_start) {
		this.ot_week_start = ot_week_start;
	}
	public Integer getOt_week_end() {
		return ot_week_end;
	}
	public void setOt_week_end(Integer ot_week_end) {
		this.ot_week_end = ot_week_end;
	}

}
