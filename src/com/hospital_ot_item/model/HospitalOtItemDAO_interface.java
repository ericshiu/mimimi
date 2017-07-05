package com.hospital_ot_item.model;

import java.util.List;





public interface HospitalOtItemDAO_interface {
    public void insert(HospitalOtItemVO hospitalOtItemVO);
   // public void update(HospitalOtItemVO hospitalOtItemVO);
    public void delete(String hos_no, String ot_no);
   // public HospitalOtItemVO findByPrimaryKey(String hos_no, String ot_no);
    public List<HospitalOtItemVO> getAll();		
    //新增醫院時順便加自費項目 
    public void insert2 (HospitalOtItemVO hospitalOtItemVO , java.sql.Connection con);	
}
