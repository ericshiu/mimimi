package com.baby_data.model;

import java.util.*;




public interface BabyDataDAO_interface {
	      public void insert(BabyDataVO babydataVo);
          public void update(BabyDataVO babydataVo);
          public void delete(String bd_no);
          public BabyDataVO findByPrimaryKey(String bd_no);
	      public List<BabyDataVO> getAll();
	      public List<BabyDataVO> getOneMEM(String mem_no);
	      //查詢某部門的員工(一對多)(回傳 Set)
	     // public Set<BabyDataVO> getBabyDataByBDno(String bd_no);

}
