package com.vaccine_course.model;

import java.util.List;



public class VaccineCourseService {
	
	private VaccineCourseDAO_interface dao;

	public VaccineCourseService() {
		dao = new VaccineCourseDAO();
	}

	public VaccineCourseVO getOneVC(String vc_no) {
		return dao.findByPrimaryKey(vc_no);
	}

	public List<VaccineCourseVO> getAll() {
		return dao.getAll();
	}

}
