package com.baby_eating.model;


import java.util.*;

import com.forum_report.model.ForumReportVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface BabyEatingDAO_interface {
    public void insert(BabyEatingVO babyEatingVO, PointRecordVO pointRecordVO, MemberVO memberVO);
    public void update(BabyEatingVO babyEatingVO);
    public void delete(String be_no);
    public BabyEatingVO findByPrimaryKey(String be_no);
    public BabyEatingVO findByBD(String bd_no);
    public List<BabyEatingVO> getOneBD(String bd_no);
    public List<BabyEatingVO> getAll();
    public List<BabyEatingVO> getAll(Map<String, String[]> map);     
}
