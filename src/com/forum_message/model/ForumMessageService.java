package com.forum_message.model;

import java.sql.Timestamp;
import java.util.List;

import com.forum_message.model.ForumMessageVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public class ForumMessageService {

	private ForumMessageDAO_interface dao;

	public ForumMessageService() {
		dao = new ForumMessageDAO();
	}

	public ForumMessageVO addFm(  String fa_no, String mem_no, String fm_content) {

		ForumMessageVO fmVO = new ForumMessageVO();

		fmVO.setFa_no(fa_no);
		fmVO.setMem_no(mem_no);
		fmVO.setFm_content(fm_content);
		
		
		
		dao.insert(fmVO);

		return fmVO;
	}

	
	
	public void addFmWithPoint(ForumMessageVO FMVO,PointRecordVO pointRecordVO,MemberVO memberVO) {
		
		dao.insertWithPoint(FMVO,pointRecordVO,memberVO);
	}
	
	
	public ForumMessageVO updateFm(String fm_no, String fa_no, String mem_no, String fm_content, Timestamp fm_publish_date) {

		ForumMessageVO fmVO = new ForumMessageVO();
		
		fmVO.setFm_no(fm_no);
		fmVO.setFa_no(fa_no);
		fmVO.setMem_no(mem_no);
		fmVO.setFm_content(fm_content);
		fmVO.setFm_publish_date(fm_publish_date);
		
		dao.update(fmVO);

		return fmVO;
	}

	public void deleteFm(String fm_no) {
		dao.delete(fm_no);
	}

	public ForumMessageVO getOneFm(String fm_no) {
		return dao.findByPK(fm_no);
	}
	
	public List<ForumMessageVO> getAllFm(String fa_no) {
		return dao.getAllFm(fa_no);
	}

	public List<ForumMessageVO> getAll() {
		return dao.getAll();
	}
	

	
	
}
