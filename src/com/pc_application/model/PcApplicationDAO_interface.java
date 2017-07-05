package com.pc_application.model;

import java.util.List;

import com.member.model.MemberVO;
import com.pc_report.model.PcReportVO;
import com.point_record.model.PointRecordVO;

public interface PcApplicationDAO_interface {
	public void insert(PcApplicationVO pcApplicationVO, PointRecordVO pointRecordVO, MemberVO memberVO);
	public void update(PcApplicationVO pcApplicationVO);
	public void delete(String pca_no);
	public PcApplicationVO findByPrimaryKey(String pca_no);
	public List<PcApplicationVO> getAll(String pca_status);
	
	//審核未過退還積分
	public void updateWithPoint(PcApplicationVO pcApplicationVO, PointRecordVO pointRecordVO, MemberVO memberVO);

	//更新狀態合併檢舉
	public void updateWithReport(PcApplicationVO pcApplicationVO, PcReportVO pcReportVO);	
	
	
}
