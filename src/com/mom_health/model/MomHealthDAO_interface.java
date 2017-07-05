package com.mom_health.model;

import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;



public interface MomHealthDAO_interface {
	
	public void insert(MomHealthVO mom_healthVO, PointRecordVO pointRecordVO, MemberVO memberVO);
    public void update(MomHealthVO mom_healthVO);
    public void delete(String mh_no);
    public MomHealthVO findByPrimaryKey(String mh_no);
    public List<MomHealthVO> getAll();


}
