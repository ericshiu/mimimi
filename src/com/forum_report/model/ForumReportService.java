package com.forum_report.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


import com.forum_report.model.ForumReportVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public class ForumReportService {
	private ForumReportDAO_interface dao;

	public ForumReportService() {
		dao = new ForumReportDAO();
	}

	public ForumReportVO addFr( String mem_no, String fr_am_no, String fr_type,String fr_title, String fr_content,
			 String fr_status, Timestamp fr_review_date) {

		ForumReportVO frVO = new ForumReportVO();

		frVO.setMem_no(mem_no);
		frVO.setFr_am_no(fr_am_no);
		frVO.setFr_type(fr_type);
		frVO.setFr_title(fr_title);
		frVO.setFr_content(fr_content);
		frVO.setFr_status(fr_status);
		frVO.setFr_review_date(fr_review_date);
	
		dao.insert(frVO);

		return frVO;
	}

	public ForumReportVO updateFr(String fr_no, String mem_no, String fr_am_no, String fr_type,String fr_title, String fr_content,
			Timestamp fr_publish_date, String fr_status, Timestamp fr_review_date) {

		ForumReportVO frVO = new ForumReportVO();
		
		frVO.setFr_no(fr_no);
		frVO.setMem_no(mem_no);
		frVO.setFr_am_no(fr_am_no);
		frVO.setFr_type(fr_type);
		frVO.setFr_title(fr_title);
		frVO.setFr_content(fr_content);
		frVO.setFr_status(fr_status);
		frVO.setFr_publish_date(fr_publish_date);
		frVO.setFr_review_date(fr_review_date);
		
		dao.update(frVO);

		return frVO;
	}
	
	
	public void updateWithPoint(ForumReportVO forumReportVO,PointRecordVO pointrecordVO,MemberVO memberVO) {

		
		dao.updateWithPoint(forumReportVO,pointrecordVO,memberVO);

	}
	
	

	public void deleteFr(String fr_no) {
		dao.delete(fr_no);
	}

	public ForumReportVO getOneFr(String fr_no) {
		return dao.findByPK(fr_no);
	}

	public List<ForumReportVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumReportVO> getDesc() {
		return dao.getDesc();
	}
	
	public List<ForumReportVO> getAllStatus(String fr_status) {
		return dao.getAllStatus(fr_status);
	}
	
	public List<ForumReportVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
