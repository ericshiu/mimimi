package com.postpartum_care.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pc_application.model.PcApplicationVO;
import com.pc_picture.model.*;
import com.pc_report.model.PcReportVO;

public class PostpartumCareService {
	
	private PostpartumCareDAO_interface dao;
	
	public PostpartumCareService(){
		dao = new PostpartumCareDAO();
	}

	public PostpartumCareVO addPC(String pc_id, String pc_password, String pc_name,
			String pc_address, String pc_phone, String pc_email, String pc_type, Integer pc_eva_good, 
			Integer pc_eva_normal, Integer pc_eva_bad, String pc_summary, String pc_bonus, String pc_gift,	
			Integer pc_point, String pc_authority) {
		
		PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
		
		postpartumCareVO.setPc_id(pc_id);
		postpartumCareVO.setPc_password(pc_password);
		postpartumCareVO.setPc_name(pc_name);
		postpartumCareVO.setPc_type(pc_type);
		postpartumCareVO.setPc_address(pc_address);
		postpartumCareVO.setPc_phone(pc_phone);
		postpartumCareVO.setPc_email(pc_email);
		postpartumCareVO.setPc_summary(pc_summary);
		postpartumCareVO.setPc_bonus(pc_bonus);
		postpartumCareVO.setPc_gift(pc_gift);
		postpartumCareVO.setPc_authority(pc_authority);
		postpartumCareVO.setPc_point(pc_point);
		postpartumCareVO.setPc_eva_good(pc_eva_good);
		postpartumCareVO.setPc_eva_normal(pc_eva_normal);
		postpartumCareVO.setPc_eva_bad(pc_eva_bad);
		
		dao.insert(postpartumCareVO);
		
		return postpartumCareVO;
	}

	public PostpartumCareVO updatePC(String pc_no, String pc_id, String pc_password, String pc_name,
			String pc_address, String pc_phone, String pc_email, String pc_type, Integer pc_eva_good, 
			Integer pc_eva_normal, Integer pc_eva_bad, String pc_summary, String pc_bonus, String pc_gift,	
			Integer pc_point, String pc_authority) {
		
		PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
		
		postpartumCareVO.setPc_no(pc_no);
		postpartumCareVO.setPc_id(pc_id);
		postpartumCareVO.setPc_password(pc_password);
		postpartumCareVO.setPc_name(pc_name);
		postpartumCareVO.setPc_type(pc_type);
		postpartumCareVO.setPc_address(pc_address);
		postpartumCareVO.setPc_phone(pc_phone);
		postpartumCareVO.setPc_email(pc_email);
		postpartumCareVO.setPc_summary(pc_summary);
		postpartumCareVO.setPc_bonus(pc_bonus);
		postpartumCareVO.setPc_gift(pc_gift);
		postpartumCareVO.setPc_authority(pc_authority);
		postpartumCareVO.setPc_point(pc_point);
		postpartumCareVO.setPc_eva_good(pc_eva_good);
		postpartumCareVO.setPc_eva_normal(pc_eva_normal);
		postpartumCareVO.setPc_eva_bad(pc_eva_bad);
		
		dao.update(postpartumCareVO);
		
		return postpartumCareVO;
	}
	
	public void deletePC(String pc_no) {
		dao.delete(pc_no);
	}

	public PostpartumCareVO getOnePC(String pc_no) {
		return dao.findByPrimaryKey(pc_no);
	}
	
	public PostpartumCareVO getOnePCById(String pc_id) {
		return dao.findById(pc_id);
	}	

	public List<PostpartumCareVO> getAll() {
		return dao.getAll();
	}
	
	public Set<PcPictureVO> getPCPsByPc_no(String pc_no) {
		return dao.getPCPsByPc_no(pc_no);
	}		

	public void insertWithPCPs(PostpartumCareVO postpartumCareVO, List<PcPictureVO> list){
		dao.insertWithPCPs(postpartumCareVO, list);
	}
	
	public List<PostpartumCareVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public Set<PcApplicationVO> getAppsByPc_no(String pc_no, String pca_status) {
		return dao.getAppsByPc_no(pc_no,pca_status);
	}	
	
	public Set<PcReportVO> getReportsByPc_no(String pc_no, String pcr_status) {
		return dao.getReportsByPc_no(pc_no,pcr_status);
	}	

}
