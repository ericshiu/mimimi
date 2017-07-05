package com.forum_article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;
import com.forum_article.*;


public class ForumArticleService {

	private ForumArticleDAO_interface dao;

	public ForumArticleService() {
		dao = new ForumArticleDAO();
	}

	public ForumArticleVO addFa( String mem_no, String fa_title, String fa_content, Integer fa_like,
			Integer fa_dislike,  Timestamp fa_modify_date) {

		ForumArticleVO faVO = new ForumArticleVO();

		faVO.setMem_no(mem_no);
		faVO.setFa_title(fa_title);
		faVO.setFa_content(fa_content);
		faVO.setFa_like(fa_like);
		faVO.setFa_dislike(fa_dislike);
		faVO.setFa_modify_date(fa_modify_date);
		
		
		dao.insert(faVO);

		return faVO;
	}

	
	public void addFaWithPoint( ForumArticleVO FAVO,PointRecordVO pointRecordVO,MemberVO memberVO) {
		
		dao.insertWithPoint(FAVO,pointRecordVO,memberVO);
		
	}

	
	
	
	
	public ForumArticleVO updateFa(String fa_no,String mem_no, String fa_title, String fa_content, Integer fa_like,
			Integer fa_dislike, Timestamp fa_publish_date, Timestamp fa_modify_date) {

		ForumArticleVO faVO = new ForumArticleVO();
		
		faVO.setFa_no(fa_no);
		faVO.setMem_no(mem_no);
		faVO.setFa_title(fa_title);
		faVO.setFa_content(fa_content);
		faVO.setFa_like(fa_like);
		faVO.setFa_dislike(fa_dislike);
		faVO.setFa_publish_date(fa_publish_date);
		faVO.setFa_modify_date(fa_modify_date);
		
		System.out.println("有近service");
		dao.update(faVO);

		return faVO;
	}
	
	public void update_dislike(String fa_no,Integer fa_dislike) {

		ForumArticleVO faVO = new ForumArticleVO();
		
		faVO.setFa_no(fa_no);
		faVO.setFa_dislike(fa_dislike);

		dao.update_dislike(faVO);

	;
	}

	public void deleteFa(String fa_no) {
		dao.delete(fa_no);
	}

	public ForumArticleVO getOneFa(String fa_no) {
		return dao.findByPK(fa_no);
	}
	
	public ForumArticleVO getOneMem(String mem_no) {
		return dao.findByPKMem(mem_no);
	}

	public List<ForumArticleVO> getAllFa() {
		return dao.getAll();
	}
	
	public List<ForumArticleVO>getHot(){
		return dao.getHot();
	}
	public List<ForumArticleVO>getDesc(){
		return dao.getDesc();
	}
	
	public List<ForumArticleVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
}
