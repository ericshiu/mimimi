package com.fetal_movement.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class FetalMovementService {
	
private FetalMovementDAO_interface dao;
	
	public FetalMovementService(){
		dao = new FetalMovementDAO();
	}
	
	public List<FetalMovementVO> getAll() {
		return dao.getAll();
	}

	public FetalMovementVO getOneFd_no(String fm_no) {
		return dao.findByPrimaryKey(fm_no);
	}

	public void deleteFM_no(String fm_no) {
		dao.delete(fm_no);
	}
	
public FetalMovementVO addFM(String mem_no, String fm_lv, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
	FetalMovementVO fetalMovementVO = new FetalMovementVO();
		
		
	fetalMovementVO.setMem_no(mem_no);

	fetalMovementVO.setFm_lv(fm_lv);
		
		dao.insert(fetalMovementVO,pointRecordVO,memberVO);
		return fetalMovementVO;
	}

	public FetalMovementVO updateFM(String Fm_no, String mem_no, Timestamp fm_date, String fm_lv) {
		// TODO Auto-generated method stub
		FetalMovementVO fetalMovementVO = new FetalMovementVO();
		
		fetalMovementVO.setFm_no(Fm_no);
		fetalMovementVO.setMem_no(mem_no);
		fetalMovementVO.setFm_date(fm_date);
		fetalMovementVO.setFm_lv(fm_lv);
			
			dao.update(fetalMovementVO);
			return fetalMovementVO;
		
	}

}
