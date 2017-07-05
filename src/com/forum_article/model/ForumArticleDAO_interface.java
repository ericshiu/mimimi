package com.forum_article.model;

import java.util.List;
import java.util.Map;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface ForumArticleDAO_interface {

	public void insert(ForumArticleVO forumArticleVO);
	public void insertWithPoint(ForumArticleVO forumArticleVO,PointRecordVO pointRecordVO, MemberVO memberVO);
	public void update(ForumArticleVO forumArticleVO);
	public void update_dislike(ForumArticleVO forumarticleVO);
	public void delete(String fa_no);
	public ForumArticleVO findByPK(String fa_no);
	public ForumArticleVO findByPKMem(String mem_no);
	public List<ForumArticleVO>getAll();
	public List<ForumArticleVO>getHot();
	public List<ForumArticleVO>getDesc();
	public List<ForumArticleVO> getAll(Map<String, String[]> map); 
}
