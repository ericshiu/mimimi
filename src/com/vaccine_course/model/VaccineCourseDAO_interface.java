package com.vaccine_course.model;

import java.util.*;

public interface VaccineCourseDAO_interface {
	      public void insert(VaccineCourseVO vaccineCourseVO);
          public void update(VaccineCourseVO vaccineCourseVO);
          public void delete(String vc_no);
          public VaccineCourseVO findByPrimaryKey(String vc_no);
	      public List<VaccineCourseVO> getAll();
	      
}