package com.education_music.model;

import java.util.List;



public interface EducationMusicDAO_interface {
	public void insert(EducationMusicVO education_music);
    public void update(EducationMusicVO education_music);
    public void delete(String em_no);
    public EducationMusicVO findByPrimaryKey(String em_no);
    public List<EducationMusicVO> getAll();


}
