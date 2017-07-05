package com.pc_application.model;

import java.util.List;

import com.member.model.MemberVO;
import com.pc_report.model.PcReportVO;
import com.point_record.model.*;

public class PcApplicationService {
	
	private PcApplicationDAO_interface dao;
	
	public PcApplicationService(){
		dao = new PcApplicationDAO();
	}

	public PcApplicationVO addPCA(String pc_no, String mem_no, java.sql.Timestamp pca_appdate, java.sql.Date pca_date, String pca_memo, String pca_status, PointRecordVO pointRecordVO, MemberVO memberVO) {

		PcApplicationVO pcApplicationVO = new PcApplicationVO();
		
		pcApplicationVO.setPc_no(pc_no);
		pcApplicationVO.setMem_no(mem_no);
		pcApplicationVO.setPca_appdate(pca_appdate);
		pcApplicationVO.setPca_date(pca_date);
		pcApplicationVO.setPca_memo(pca_memo);
		pcApplicationVO.setPca_status(pca_status);


		dao.insert(pcApplicationVO,pointRecordVO,memberVO);
		
		return pcApplicationVO;
	}

	public PcApplicationVO updatePCA(String pca_no, String pca_status, java.sql.Date pca_review_date) {
		
		PcApplicationVO pcApplicationVO = new PcApplicationVO();

		pcApplicationVO.setPca_no(pca_no);
		pcApplicationVO.setPca_status(pca_status);
		pcApplicationVO.setPca_review_date(pca_review_date);
		
		dao.update(pcApplicationVO);
		
		return pcApplicationVO;
	}
	
	
	public void updatePCAWithPoint(String pca_no, String pca_status, java.sql.Date pca_review_date, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
		PcApplicationVO pcApplicationVO = new PcApplicationVO();

		pcApplicationVO.setPca_no(pca_no);
		pcApplicationVO.setPca_status(pca_status);
		pcApplicationVO.setPca_review_date(pca_review_date);
		
		dao.updateWithPoint(pcApplicationVO, pointRecordVO, memberVO);
	}
	
	public void updatePCAWithReport(String pca_no, String pca_status, java.sql.Date pca_review_date,PcReportVO pcReportVO) {
		
		PcApplicationVO pcApplicationVO = new PcApplicationVO();

		pcApplicationVO.setPca_no(pca_no);
		pcApplicationVO.setPca_status(pca_status);
		pcApplicationVO.setPca_review_date(pca_review_date);
		
		dao.updateWithReport(pcApplicationVO, pcReportVO);
	}		
	
	public void deletePCA(String pca_no) {
		dao.delete(pca_no);
	}

	public PcApplicationVO getOnePCA(String pca_no) {
		return dao.findByPrimaryKey(pca_no);
	}

	public List<PcApplicationVO> getAll(String pca_status) {
		return dao.getAll(pca_status);
	}		
}
