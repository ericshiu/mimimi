package com.vaccine_notes.model;

import java.util.*;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface VaccineNotesDAO_interface {
	      public void insert(VaccineNotesVO vaccineNotesVO, PointRecordVO pointRecordVO, MemberVO memberVO);
          public void update(VaccineNotesVO vaccineNotesVO);
          public void delete(String vn_no);
          public VaccineNotesVO findByPrimaryKey(String vn_no);
          public List<VaccineNotesVO> getOneBD(String bd_no);
	      public List<VaccineNotesVO> getAll();
	   
}