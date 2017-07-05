package com.firm.model;

import java.util.*;

public interface FirmDAO_interface {
	public void insert(FirmVO firm);
	public void update(FirmVO firm);
	public void updatePsw(String fir_password,String fir_no);
	public void delete(String fir_no);
	public FirmVO findByPrimaryKey(String fir_no);
	public FirmVO findByID(String fir_id);
	public List<FirmVO> getAll();
	
	//更新評價
	public void updateEvaluation(FirmVO firmVO, java.sql.Connection con);
	
}
