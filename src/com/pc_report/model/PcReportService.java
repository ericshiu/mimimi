package com.pc_report.model;

import java.sql.*;
import java.util.*;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class PcReportService {
	
	private PcReportDAO_interface dao;
	
	public PcReportService(){
		dao = new PcReportDAO();
	}
	
	public PcReportVO addPCR(String pc_no, String mem_no, String pcr_type, String pcr_content, java.sql.Date pcr_date, String pcr_status, java.sql.Date pcr_review_date, java.sql.Connection con) {

		PcReportVO pcReportVO = new PcReportVO();
		
		pcReportVO.setPc_no(pc_no);
		pcReportVO.setMem_no(mem_no);
		pcReportVO.setPcr_type(pcr_type);
		pcReportVO.setPcr_content(pcr_content);
		pcReportVO.setPcr_date(pcr_date);
		pcReportVO.setPcr_status(pcr_status);
		pcReportVO.setPcr_review_date(pcr_review_date);

		dao.insert(pcReportVO,con);
		
		return pcReportVO;
	}

	public void updatePCR(String pcr_no,String pcr_status, java.sql.Date pcr_review_date) {
		
		PcReportVO pcReportVO = new PcReportVO();
		
		pcReportVO.setPcr_no(pcr_no);
//		pcReportVO.setPc_no(pc_no);
//		pcReportVO.setMem_no(mem_no);
//		pcReportVO.setPcr_type(pcr_type);
//		pcReportVO.setPcr_content(pcr_content);
//		pcReportVO.setPcr_date(pcr_date);
		pcReportVO.setPcr_status(pcr_status);
		pcReportVO.setPcr_review_date(pcr_review_date);
		
		dao.update(pcReportVO);

	}
	
	public void deletePCR(String pcr_no) {
		dao.delete(pcr_no);
	}

	public PcReportVO getOnePCR(String pcr_no) {
		return dao.findByPrimaryKey(pcr_no);
	}

	public List<PcReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<PcReportVO> getAllStatus(String pcr_status) {
		return dao.getAllStatus(pcr_status);
	}
	
	public void updatePCRWithPoint(String pcr_no,String pcr_status, java.sql.Date pcr_review_date, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
		PcReportVO pcReportVO = new PcReportVO();
		
		pcReportVO.setPcr_no(pcr_no);
		pcReportVO.setPcr_status(pcr_status);
		pcReportVO.setPcr_review_date(pcr_review_date);
		
		dao.updateWithPoint(pcReportVO,pointRecordVO,memberVO);

	}	
	
}
