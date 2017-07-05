package com.pc_picture.model;

public class PcPictureVO implements java.io.Serializable {
	private String pcp_no;
	private String pc_no;
	private byte[] pcp_picture;
	private String pcp_summary;
	
	
	public String getPcp_no() {
		return pcp_no;
	}
	public void setPcp_no(String pcp_no) {
		this.pcp_no = pcp_no;
	}
	public String getPc_no() {
		return pc_no;
	}
	public void setPc_no(String pc_no) {
		this.pc_no = pc_no;
	}
	public byte[] getPcp_picture() {
		return pcp_picture;
	}
	public void setPcp_picture(byte[] pcp_picture) {
		this.pcp_picture = pcp_picture;
	}
	public String getPcp_summary() {
		return pcp_summary;
	}
	public void setPcp_summary(String pcp_summary) {
		this.pcp_summary = pcp_summary;
	}

}
