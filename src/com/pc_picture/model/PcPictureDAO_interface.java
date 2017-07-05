package com.pc_picture.model;

import java.util.List;

public interface PcPictureDAO_interface {
	public void insert(PcPictureVO pcPictureVO);
	public void update(PcPictureVO pcPictureVO);
	public void delete(String pcp_no);
	public PcPictureVO findByPrimaryKey(String pcp_no);
	public List<PcPictureVO> getAll();
	
    //新增廠商時順便加照片 
    public void insert2 (PcPictureVO pcPictureVO , java.sql.Connection con);	
}
