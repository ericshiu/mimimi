package com.optional_test.model;

import java.util.List;

public class OptionalTestService {
	
	private OptionalTestDAO_interface dao;
	
	public OptionalTestService(){
		dao = new OptionalTestDAO();
	}

	public OptionalTestVO addOT(String ot_name, Integer ot_week_start, Integer ot_week_end) {

		OptionalTestVO optionalTestVO = new OptionalTestVO();
		
		optionalTestVO.setOt_name(ot_name);
		optionalTestVO.setOt_week_start(ot_week_start);
		optionalTestVO.setOt_week_end(ot_week_end);

		dao.insert(optionalTestVO);
		
		return optionalTestVO;
	}

	public OptionalTestVO updateOT(String ot_no, String ot_name, Integer ot_week_start, Integer ot_week_end) {
		
		OptionalTestVO optionalTestVO = new OptionalTestVO();
		
		optionalTestVO.setOt_no(ot_no);
		optionalTestVO.setOt_name(ot_name);
		optionalTestVO.setOt_week_start(ot_week_start);
		optionalTestVO.setOt_week_end(ot_week_end);
		
		dao.update(optionalTestVO);
		
		return optionalTestVO;
	}
	
	public void deleteOT(String ot_no) {
		dao.delete(ot_no);
	}

	public OptionalTestVO getOneOT(String ot_no) {
		return dao.findByPrimaryKey(ot_no);
	}

	public List<OptionalTestVO> getAll() {
		return dao.getAll();
	}			
	
}
