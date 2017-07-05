package com.education_music.model;

import java.sql.Date;
import java.util.List;



public class EducationMusicService {
	
	
	
	private EducationMusicDAO_interface dao;
	
	public EducationMusicService(){
		dao = new EducationMusicDAO();
	}
	
	public List<EducationMusicVO> getAll() {
		return dao.getAll();
	}

	public EducationMusicVO getOneEm_no(String em_no) {
		return dao.findByPrimaryKey(em_no);
	}

	public void deletedd_no(String em_no) {
		dao.delete(em_no);
	}
	
	

	

}
