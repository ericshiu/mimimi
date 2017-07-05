package com.point_record.model;

import java.util.List;

public interface PointRecordDAO_interface {
	public void insert(PointRecordVO pointRecordVO, java.sql.Connection con);
	public void update(PointRecordVO pointRecordVO);
	public void delete(String pr_no);
	public PointRecordVO findByPrimaryKey(String pr_no);
	public List<PointRecordVO> getAll();	

}
