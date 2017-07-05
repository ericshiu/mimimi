package com.baby_gcurve.model;

import java.util.List;


public class BabyGCurveService {
	
	private BabyGCurveDAO_interface dao;

	public BabyGCurveService() {
		dao = new BabyGCurveDAO();
	}

	public BabyGCurveVO getOneBGC(String bgc_no) {
		return dao.findByPrimaryKey(bgc_no);
	}

	public List<BabyGCurveVO> getAll() {
		return dao.getAll();
	}


}
