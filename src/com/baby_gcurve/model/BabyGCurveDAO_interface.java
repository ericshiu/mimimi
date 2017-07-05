package com.baby_gcurve.model;

import java.util.*;

public interface BabyGCurveDAO_interface {
//	      public void insert(BabyGCurveVO babyGCurveVO);
//          public void update(BabyGCurveVO babyGCurveVO);
//          public void delete(String bgc_no);
          public BabyGCurveVO findByPrimaryKey(String bgc_no);
	      public List<BabyGCurveVO> getAll();

	
}