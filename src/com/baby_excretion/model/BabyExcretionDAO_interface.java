package com.baby_excretion.model;



import java.util.*;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface BabyExcretionDAO_interface {
	      public void insert(BabyExcretionVO babyExcretionVO, PointRecordVO pointRecordVO, MemberVO memberVO);
          public void update(BabyExcretionVO babyExcretionVO);
          public void delete(String bex_no);
          public BabyExcretionVO findByPrimaryKey(String bex_no);
          public List<BabyExcretionVO> getOneBD(String bd_no);
	      public List<BabyExcretionVO> getAll();
	      public List<BabyExcretionVO> getAll(Map<String, String[]> map);  

}
