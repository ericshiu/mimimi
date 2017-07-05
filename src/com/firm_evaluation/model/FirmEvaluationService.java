package com.firm_evaluation.model;

import java.util.Set;

import com.firm.model.FirmVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;
import com.postpartum_care.model.PostpartumCareVO;

public class FirmEvaluationService {
	
	private FirmEvaluationDAO_interface dao;
	
	public FirmEvaluationService(){
		dao = new FirmEvaluationDAO();
	}
	
	public void insertToPC(String fp_no, String mem_no, String fe_type, String fe_content, java.sql.Date fe_date, PointRecordVO pointRecordVO, PostpartumCareVO postpartumCareVO, MemberVO memberVO){

		FirmEvaluationVO firmEvaluationVO = new FirmEvaluationVO();
		
		firmEvaluationVO.setFp_no(fp_no);
		firmEvaluationVO.setMem_no(mem_no);
		firmEvaluationVO.setFe_type(fe_type);
		firmEvaluationVO.setFe_content(fe_content);
		firmEvaluationVO.setFe_date(fe_date);


		dao.insertToPC(firmEvaluationVO,pointRecordVO,postpartumCareVO,memberVO);				
	}
	
	public void insertToFirm(String fp_no, String mem_no, String fe_type, String fe_content, java.sql.Date fe_date, PointRecordVO pointRecordVO, FirmVO firmVO, MemberVO memberVO){

		FirmEvaluationVO firmEvaluationVO = new FirmEvaluationVO();
		
		firmEvaluationVO.setFp_no(fp_no);
		firmEvaluationVO.setMem_no(mem_no);
		firmEvaluationVO.setFe_type(fe_type);
		firmEvaluationVO.setFe_content(fe_content);
		firmEvaluationVO.setFe_date(fe_date);


		dao.insertToFirm(firmEvaluationVO,pointRecordVO,firmVO,memberVO);		
	}
	
	public void insertEvaToFirm(String fp_no, String mem_no, String fe_type, String fe_content, java.sql.Date fe_date, FirmVO firmVO) {
		
		FirmEvaluationVO firmEvaluationVO = new FirmEvaluationVO();
		
		firmEvaluationVO.setFp_no(fp_no);
		firmEvaluationVO.setMem_no(mem_no);
		firmEvaluationVO.setFe_type(fe_type);
		firmEvaluationVO.setFe_content(fe_content);
		firmEvaluationVO.setFe_date(fe_date);
		
		dao.insertFirmSimple(firmEvaluationVO, firmVO);
	}
	
	
	public Set<FirmEvaluationVO> getAllByFirmAndType(String fp_no, String fe_type){
		return dao.getAllByFirmAndType(fp_no, fe_type);		
	}
	
	public Set<FirmEvaluationVO> getAllByMem_no(String mem_no){
		return dao.getAllByMem_no(mem_no);	
	}
	
	public Set<FirmEvaluationVO> getAllByEvaMem_no(String mem_no){
		return dao.getAllByEvaMem_no(mem_no);	
	}
	
	public FirmEvaluationVO getEvaByFp_no(String fp_no, String mem_no){
		System.out.println("有近service");
		return dao.getEvaByFp_no(fp_no, mem_no);	
	}

}
