package com.postpartum_care.model;

import java.util.*;

import com.pc_application.model.PcApplicationVO;
import com.pc_picture.model.PcPictureVO;
import com.pc_report.model.PcReportVO;

public interface PostpartumCareDAO_interface {
	public void insert(PostpartumCareVO postpartumCareVO);
	public void update(PostpartumCareVO postpartumCareVO);
	public void delete(String pc_no);
	public PostpartumCareVO findByPrimaryKey(String pc_no);
	public PostpartumCareVO findById(String pc_id);
	public List<PostpartumCareVO> getAll();
	public Set<PcPictureVO> getPCPsByPc_no(String pc_no);
	
    //同時新增廠商與照片 
    public void insertWithPCPs(PostpartumCareVO postpartumCareVO, List<PcPictureVO> list);	
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<PostpartumCareVO> getAll(Map<String, String[]> map);    
    
    //預約
	public Set<PcApplicationVO> getAppsByPc_no(String pc_no, String pca_status);   
	
    //檢舉
	public Set<PcReportVO> getReportsByPc_no(String pc_no, String pcr_status); 
	
	//更新評價
	public void updateEvaluation(PostpartumCareVO postpartumCareVO, java.sql.Connection con);    
}
