package com.fetal_movement.model;

import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;



public interface FetalMovementDAO_interface {
	public void insert(FetalMovementVO fetal_movementVO, PointRecordVO pointRecordVO, MemberVO memberVO);
    public void update(FetalMovementVO fetal_movementVO);
    public void delete(String fm_no);
    public FetalMovementVO findByPrimaryKey(String fm_no);
    public List<FetalMovementVO> getAll();


}
