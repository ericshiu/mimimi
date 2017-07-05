package com.firm_evaluation.model;

import java.util.List;
import java.util.Set;

import com.firm.model.FirmVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;
import com.postpartum_care.model.PostpartumCareVO;

public interface FirmEvaluationDAO_interface {
	public void insertToFirm(FirmEvaluationVO firmEvaluationVO, PointRecordVO pointRecordVO, FirmVO firmVO, MemberVO memberVO);
	public void insertToPC(FirmEvaluationVO firmEvaluationVO, PointRecordVO pointRecordVO, PostpartumCareVO postpartumCareVO, MemberVO memberVO);
	public void insertFirmSimple(FirmEvaluationVO firmEvaluationVO,FirmVO firmVO);
	
	//廠商找評價
	public Set<FirmEvaluationVO> getAllByFirmAndType(String fp_no, String fe_type);
	
	//會員看評價
	public Set<FirmEvaluationVO> getAllByMem_no(String mem_no);
	
	//會員看評價
	public Set<FirmEvaluationVO> getAllByEvaMem_no(String mem_no);
	
	//會員看有沒有評價過
	public FirmEvaluationVO getEvaByFp_no(String fp_no, String mem_no);	

}
