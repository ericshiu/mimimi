package com.optional_test.model;

import java.util.*;

import com.hospital.model.HospitalVO;

public interface OptionalTestDAO_interface {
    public void insert(OptionalTestVO optionalTestVO);
    public void update(OptionalTestVO optionalTestVO);
    public void delete(String ot_no);
    public OptionalTestVO findByPrimaryKey(String ot_no);
    public List<OptionalTestVO> getAll();	
	public Set<HospitalVO> getHosByOt_no(String ot_no);

}
