package com.hospital.model;

import java.util.*;

import com.hospital_ot_item.model.HospitalOtItemVO;
import com.optional_test.model.OptionalTestVO;
import com.postpartum_care.model.PostpartumCareVO;

public interface HospitalDAO_interface {
	public void insert(HospitalVO hospitalVO);
	public void update(HospitalVO hospitalVO);
	public void delete(String hos_no);
	public HospitalVO findByPrimaryKey(String hos_no);
	public List<HospitalVO> getAll();
	public Set<OptionalTestVO> getOTsByHos_no(String hos_no);
	
	
    //同時新增醫院與項目 
    public void insertWithOTs(HospitalVO hospitalVO, List<HospitalOtItemVO> list);	
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<HospitalVO> getAll(Map<String, String[]> map);       
}
