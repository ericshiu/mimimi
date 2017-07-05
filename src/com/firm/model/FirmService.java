package com.firm.model;

import java.util.List;

public class FirmService {
	private FirmDAO_interface dao;
	
	public FirmService(){
		dao = new FirmDAO();
	}
	
	public FirmVO addFir(String fir_id,String fir_password,String fir_name,String fir_type,
		String fir_phone,String fir_address,String fir_email,String fir_account,String fir_authority,
		Integer fir_eva_good,Integer fir_eva_normal,Integer fir_eva_bad){
		
		FirmVO firmVO = new FirmVO();
		firmVO.setFir_id(fir_id);
		firmVO.setFir_password(fir_password);
		firmVO.setFir_name(fir_name);
		firmVO.setFir_type(fir_type);
		firmVO.setFir_phone(fir_phone);
		firmVO.setFir_address(fir_address);
		firmVO.setFir_email(fir_email);
		firmVO.setFir_account(fir_account);
		firmVO.setFir_authority(fir_authority);
		firmVO.setFir_eva_good(fir_eva_good);
		firmVO.setFir_eva_normal(fir_eva_normal);
		firmVO.setFir_eva_bad(fir_eva_bad);
		
		dao.insert(firmVO);
		return firmVO;
	}
	
	public FirmVO updateFir(String fir_id,String fir_password,String fir_name,String fir_type,
		String fir_phone,String fir_address,String fir_email,String fir_account,
		String fir_authority,Integer fir_eva_good,Integer fir_eva_normal,Integer fir_eva_bad,
		String fir_no){
		
		FirmVO firmVO = new FirmVO();
		firmVO.setFir_id(fir_id);
		firmVO.setFir_password(fir_password);
		firmVO.setFir_name(fir_name);
		firmVO.setFir_type(fir_type);
		firmVO.setFir_phone(fir_phone);
		firmVO.setFir_address(fir_address);
		firmVO.setFir_email(fir_email);
		firmVO.setFir_account(fir_account);
		firmVO.setFir_authority(fir_authority);
		firmVO.setFir_eva_good(fir_eva_good);
		firmVO.setFir_eva_normal(fir_eva_normal);
		firmVO.setFir_eva_bad(fir_eva_bad);
		firmVO.setFir_no(fir_no);
		
		
		dao.update(firmVO);
		return firmVO;
	}
	
	public void deleteFir(String fir_no){
		dao.delete(fir_no);
	}
	
	public FirmVO getOneFir(String firm_no){
		return dao.findByPrimaryKey(firm_no);
	}
	
	
	public FirmVO getOneID(String firm_id){
		return dao.findByID(firm_id);
	}
	
	public List<FirmVO> getAllFir(){
		return dao.getAll();
	}
	
	public void updatePsw(String fir_password,String fir_no){
		dao.updatePsw(fir_password,fir_no);
	}

}
