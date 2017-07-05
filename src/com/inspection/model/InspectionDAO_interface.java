package com.inspection.model;

import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;



public interface InspectionDAO_interface {
	
	public void insert(InspectionVO inspectionVO, PointRecordVO pointRecordVO, MemberVO memberVO);
    public void update(InspectionVO inspectionVO);
    public void delete(String ins_no);
    public InspectionVO findByPrimaryKey(String ins_no);
    public List<InspectionVO> getAll();


}
