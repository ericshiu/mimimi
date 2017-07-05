package com.advertise.model;

import java.sql.Timestamp;

public class AdvertiseVO {
	
	public String getAdv_no() {
		return adv_no;
	}

	public void setAdv_no(String adv_no) {
		this.adv_no = adv_no;
	}

	public String getFir_no() {
		return fir_no;
	}

	public void setFir_no(String fir_no) {
		this.fir_no = fir_no;
	}

	public Timestamp getAdv_propose_date() {
		return adv_propose_date;
	}

	public void setAdv_propose_date(Timestamp adv_propose_date) {
		this.adv_propose_date = adv_propose_date;
	}

	public Timestamp getAdv_start() {
		return adv_start;
	}

	public void setAdv_start(Timestamp adv_start) {
		this.adv_start = adv_start;
	}

	public Timestamp getAdv_end() {
		return adv_end;
	}

	public void setAdv_end(Timestamp adv_end) {
		this.adv_end = adv_end;
	}

	public String getAdv_review() {
		return adv_review;
	}

	public void setAdv_review(String adv_review) {
		this.adv_review = adv_review;
	}

	public Timestamp getAdv_review_date() {
		return adv_review_date;
	}

	public void setAdv_review_date(Timestamp adv_review_date) {
		this.adv_review_date = adv_review_date;
	}

	public String getAdv_status() {
		return adv_status;
	}

	public void setAdv_status(String adv_status) {
		this.adv_status = adv_status;
	}

	public byte[] getAdv_picture() {
		return adv_picture;
	}

	public void setAdv_picture(byte[] adv_picture) {
		this.adv_picture = adv_picture;
	}

	public String getAdv_title() {
		return adv_title;
	}

	public void setAdv_title(String adv_title) {
		this.adv_title = adv_title;
	}

	private String adv_no;
	private String fir_no;
	private Timestamp adv_propose_date;
	private Timestamp adv_start;
	private Timestamp adv_end;
	private String adv_review;
	private Timestamp adv_review_date;
	private String adv_status;
	private byte[] adv_picture;
	private String adv_title;
	
	public AdvertiseVO() {
		super();
	}

	public AdvertiseVO(String adv_no, String fir_no, Timestamp adv_propose_date, Timestamp adv_start, Timestamp adv_end,
			String adv_review, Timestamp adv_review_date, String adv_status, byte[] adv_picture, String adv_title) {
		super();
		this.adv_no = adv_no;
		this.fir_no = fir_no;
		this.adv_propose_date = adv_propose_date;
		this.adv_start = adv_start;
		this.adv_end = adv_end;
		this.adv_review = adv_review;
		this.adv_review_date = adv_review_date;
		this.adv_status = adv_status;
		this.adv_picture = adv_picture;
		this.adv_title = adv_title;
	}
	
	
}
