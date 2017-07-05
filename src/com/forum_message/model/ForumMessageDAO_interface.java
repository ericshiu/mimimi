package com.forum_message.model;

import java.util.List;

import com.forum_message.model.ForumMessageVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface ForumMessageDAO_interface {

	public void insert(ForumMessageVO forumMessageVO);
	public void insertWithPoint(ForumMessageVO forumMessageVO,PointRecordVO pointRecordVO, MemberVO memberVO);
	public void update(ForumMessageVO forumMessageVO);
	public void delete(String fm_no);
	public ForumMessageVO findByPK(String fm_no);
	public List<ForumMessageVO> getAllFm(String fm_no);
	public List<ForumMessageVO>getAll();
}
