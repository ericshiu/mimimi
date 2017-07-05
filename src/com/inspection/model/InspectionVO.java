package com.inspection.model;

import java.sql.Date;
import java.sql.Timestamp;

public class InspectionVO implements java.io.Serializable{
	
	private String ins_no;
	private String mem_no;
	private String ins_hospital;
	private String ins_outoatuent;
	private Integer ins_resetvation_no;
	private Timestamp ins_date;
	
	
	public String getIns_no() {
		return ins_no;
	}
	public void setIns_no(String ins_no) {
		this.ins_no = ins_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getIns_hospital() {
		return ins_hospital;
	}
	public void setIns_hospital(String ins_hospital) {
		this.ins_hospital = ins_hospital;
	}
	public String getIns_outoatuent() {
		return ins_outoatuent;
	}
	public void setIns_outoatuent(String ins_outoatuent) {
		this.ins_outoatuent = ins_outoatuent;
	}
	public Integer getIns_resetvation_no() {
		return ins_resetvation_no;
	}
	public void setIns_resetvation_no(Integer ins_resetvation_no) {
		this.ins_resetvation_no = ins_resetvation_no;
	}
	public Timestamp getIns_date() {
		return ins_date;
	}
	public void setIns_date(Timestamp ins_date) {
		this.ins_date = ins_date;
	}

	
	

}
