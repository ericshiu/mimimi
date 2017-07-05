package com.member.model;

import java.io.*;
import java.sql.Date;

public class MemberVO implements Serializable{
	
	private String mem_no;
	private String mem_id;
	private String mem_password;
	private String mem_name;
	private String mem_nike;
	private String mem_sex;
	private Date mem_birthday;
	private String mem_phone;
	private String mem_email;
	private String mem_address;
	private Integer mem_point;
	private Integer mem_actual_point;
	private byte[] mem_picture;
	private String mem_authority;
	private String mem_facebook;
	private String mem_google;
	
	public MemberVO() {
		super();
	}

	public MemberVO(String mem_no, String mem_id, String mem_password, String mem_name, String mem_nike, String mem_sex,
			Date mem_birthday, String mem_phone, String mem_email, String mem_address, Integer mem_point,
			Integer mem_actual_point, byte[] mem_picture, String mem_authority, String mem_facebook,
			String mem_google) {
		super();
		this.mem_no = mem_no;
		this.mem_id = mem_id;
		this.mem_password = mem_password;
		this.mem_name = mem_name;
		this.mem_nike = mem_nike;
		this.mem_sex = mem_sex;
		this.mem_birthday = mem_birthday;
		this.mem_phone = mem_phone;
		this.mem_email = mem_email;
		this.mem_address = mem_address;
		this.mem_point = mem_point;
		this.mem_actual_point = mem_actual_point;
		this.mem_picture = mem_picture;
		this.mem_authority = mem_authority;
		this.mem_facebook = mem_facebook;
		this.mem_google = mem_google;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_password() {
		return mem_password;
	}

	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nike() {
		return mem_nike;
	}

	public void setMem_nike(String mem_nike) {
		this.mem_nike = mem_nike;
	}

	public String getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public Date getMem_birthday() {
		return mem_birthday;
	}

	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_address() {
		return mem_address;
	}

	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}

	public Integer getMem_point() {
		return mem_point;
	}

	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}

	public Integer getMem_actual_point() {
		return mem_actual_point;
	}

	public void setMem_actual_point(Integer mem_actual_point) {
		this.mem_actual_point = mem_actual_point;
	}

	public byte[] getMem_picture() {
		return mem_picture;
	}

	public void setMem_picture(byte[] mem_picture) {
		this.mem_picture = mem_picture;
	}

	public String getMem_authority() {
		return mem_authority;
	}

	public void setMem_authority(String mem_authority) {
		this.mem_authority = mem_authority;
	}

	public String getMem_facebook() {
		return mem_facebook;
	}

	public void setMem_facebook(String mem_facebook) {
		this.mem_facebook = mem_facebook;
	}

	public String getMem_google() {
		return mem_google;
	}

	public void setMem_google(String mem_google) {
		this.mem_google = mem_google;
	}
	

}
