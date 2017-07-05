package com.hospital.model;

import java.util.List;
import java.util.Map;

import com.hospital_ot_item.model.HospitalOtItemVO;


public class HospitalService {
	
	private HospitalDAO_interface dao;
	
	public HospitalService() {
		dao = new HospitalDAO();
	}
	
	public HospitalVO addHos(String hos_name, String hos_address, String hos_phone) {
		HospitalVO hospitalVO = new HospitalVO();
		
		hospitalVO.setHos_name(hos_name);
		hospitalVO.setHos_address(hos_address);
		hospitalVO.setHos_phone(hos_phone);
		dao.insert(hospitalVO);
		
		return hospitalVO;
	}

	public HospitalVO updateHos(String hos_no, String hos_name, String hos_address, String hos_phone) {
		
		HospitalVO hospitalVO = new HospitalVO();
		
		hospitalVO.setHos_name(hos_no);
		hospitalVO.setHos_name(hos_name);
		hospitalVO.setHos_address(hos_address);
		hospitalVO.setHos_phone(hos_phone);
		dao.update(hospitalVO);
		
		return hospitalVO;
	}
	
	public void deleteHos(String hos_no) {
		dao.delete(hos_no);
	}

	public HospitalVO getOneHos(String hos_no) {
		return dao.findByPrimaryKey(hos_no);
	}

	public List<HospitalVO> getAll() {
		return dao.getAll();
	}	
	
	public void insertWithOTs(HospitalVO hospitalVO, List<HospitalOtItemVO> list){
		dao.insertWithOTs(hospitalVO, list);
	}
	
	public List<HospitalVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}	
}
