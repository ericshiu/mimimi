package com.firm_evaluation.model;

import java.sql.Date;

public class FirmEvaluationVO implements java.io.Serializable  {
	private String fe_no;
	private String mem_no;
	private String fp_no;
	private String fe_type;
	private String fe_content;
	private Date fe_date;
	
	public Date getFe_date() {
		return fe_date;
	}
	public void setFe_date(Date fe_date) {
		this.fe_date = fe_date;
	}
	public String getFe_no() {
		return fe_no;
	}
	public void setFe_no(String fe_no) {
		this.fe_no = fe_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getFp_no() {
		return fp_no;
	}
	public void setFp_no(String fp_no) {
		this.fp_no = fp_no;
	}
	public String getFe_type() {
		return fe_type;
	}
	public void setFe_type(String fe_type) {
		this.fe_type = fe_type;
	}
	public String getFe_content() {
		return fe_content;
	}
	public void setFe_content(String fe_content) {
		this.fe_content = fe_content;
	}
	
}
