package com.baby_growth.model;

import java.util.*;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface BabyGrowthDAO_interface {
	      public void insert(BabyGrowthVO babyGrowthVO, PointRecordVO pointRecordVO, MemberVO memberVO);
          public void update(BabyGrowthVO babyGrowthVO);
          public void delete(String bg_no);
          public BabyGrowthVO findByPrimaryKey(String bg_no);
          public List<BabyGrowthVO> getOneBD(String bd_no);
	      public List<BabyGrowthVO> getAll();
	      public List<BabyGrowthVO> getAll(Map<String, String[]> map);  
}
