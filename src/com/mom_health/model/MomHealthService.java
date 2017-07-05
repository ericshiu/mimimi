package com.mom_health.model;

import java.sql.Date;
import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class MomHealthService {

	
	
private MomHealthDAO_interface dao;
	
	public MomHealthService(){
		dao = new MomHealthDAO();
	}
	
	public List<MomHealthVO> getAll() {
		return dao.getAll();
	}

	public MomHealthVO getOneMH_no(String mh_no) {
		return dao.findByPrimaryKey(mh_no);
	}

	public void deleteMH_no(String mh_no) {
		dao.delete(mh_no);
	}
public MomHealthVO addMH(String mem_no,Date mh_date,Integer mh_weight,Integer mh_ststolic,Integer mh_diastolic,Integer mh_heartbeat,String mh_protein,Integer mh_baby_heartbeat, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
	MomHealthVO momHealthVO = new MomHealthVO();
		
		
	momHealthVO.setMem_no(mem_no);
	momHealthVO.setMh_date(mh_date);
	momHealthVO.setMh_weight(mh_weight);
	momHealthVO.setMh_ststolic(mh_ststolic);
	momHealthVO.setMh_diastolic(mh_diastolic);
	momHealthVO.setMh_heartbeat(mh_heartbeat);
	momHealthVO.setMh_protein(mh_protein);
	momHealthVO.setMh_baby_heartbeat(mh_baby_heartbeat);
	
		dao.insert(momHealthVO,pointRecordVO,memberVO);
		return momHealthVO;
	}

	public MomHealthVO updateMH(String mh_no, String mem_no, Date mh_date, Integer mh_weight, Integer mh_ststolic,
			Integer mh_diastolic, Integer mh_heartbeat, String mh_protein, Integer mh_baby_heartbeat) {
		// TODO Auto-generated method stub
		
		MomHealthVO momHealthVO = new MomHealthVO();
		
		momHealthVO.setMh_no(mh_no);
		momHealthVO.setMem_no(mem_no);
		momHealthVO.setMh_date(mh_date);
		momHealthVO.setMh_weight(mh_weight);
		momHealthVO.setMh_ststolic(mh_ststolic);
		momHealthVO.setMh_diastolic(mh_diastolic);
		momHealthVO.setMh_heartbeat(mh_heartbeat);
		momHealthVO.setMh_protein(mh_protein);
		momHealthVO.setMh_baby_heartbeat(mh_baby_heartbeat);
		
		dao.update(momHealthVO);
		return momHealthVO;
	
	}
}
