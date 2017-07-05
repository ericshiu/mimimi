package com.forum_report.model;

import java.util.List;
import java.util.Map;

import com.forum_report.model.ForumReportVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface ForumReportDAO_interface {

	public void insert(ForumReportVO forumReportVO);
	public void update(ForumReportVO forumReportVO);
	public void updateWithPoint(ForumReportVO forumReportVO,PointRecordVO pointrecordVO,MemberVO memberVO);
	public void delete(String fr_no);
	public ForumReportVO findByPK(String fr_no);
	public List<ForumReportVO>getAll();
	public List<ForumReportVO>getAllStatus(String fr_status);
	public List<ForumReportVO>getDesc();
	public List<ForumReportVO> getAll(Map<String, String[]> map); 
}
