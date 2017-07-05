package com.inspection.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class InspectionService {

	
	private InspectionDAO_interface dao;
	
	public InspectionService(){
		dao = new InspectionDAO();
	}
	
	public List<InspectionVO> getAll() {
		return dao.getAll();
	}

	public InspectionVO getOneIns_no(String ins_no) {
		return dao.findByPrimaryKey(ins_no);
	}

	public void deleteIns_no(String ins_no) {
		dao.delete(ins_no);
	}
	public InspectionVO addINS(String mem_no,  String ins_hospital, String ins_outoatuent, Integer ins_resetvation_no, Timestamp ins_date, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
		InspectionVO inspectionVO = new InspectionVO();
		
		
		inspectionVO.setMem_no(mem_no);
		inspectionVO.setIns_hospital(ins_hospital);
		inspectionVO.setIns_outoatuent(ins_outoatuent);
		inspectionVO.setIns_resetvation_no(ins_resetvation_no);
		inspectionVO.setIns_date(ins_date);
		dao.insert(inspectionVO,pointRecordVO,memberVO);
		return inspectionVO;
	}

	public InspectionVO updateINS(String ins_no,String mem_no,String ins_hospital, String ins_outoatuent, Integer ins_resetvation_no, Timestamp ins_date) {
		// TODO Auto-generated method stub
		
		InspectionVO inspectionVO = new InspectionVO();
		
		inspectionVO.setIns_no(ins_no);
		inspectionVO.setMem_no(mem_no);
		inspectionVO.setIns_hospital(ins_hospital);
		inspectionVO.setIns_outoatuent(ins_outoatuent);
		inspectionVO.setIns_resetvation_no(ins_resetvation_no);
		inspectionVO.setIns_date(ins_date);
		dao.update(inspectionVO);
		return inspectionVO;
	
	}
	
	
}
