package com.pc_report.model;

import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public interface PcReportDAO_interface {
	public void insert(PcReportVO pcReportVO, java.sql.Connection con);
	public void update(PcReportVO pcReportVO);
	public void delete(String pcr_no);
	public PcReportVO findByPrimaryKey(String pcr_no);
	public List<PcReportVO> getAll();
	public List<PcReportVO> getAllStatus(String pcr_status);
	
	//審核通過扣積分
	public void updateWithPoint(PcReportVO pcReportVO, PointRecordVO pointRecordVO, MemberVO memberVO);
	
	
	
}
