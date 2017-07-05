package com.baby_sleep.model;

import java.util.*;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public interface BabySleepDAO_interface {
    public void insert(BabySleepVO babySleepVO, PointRecordVO pointRecordVO, MemberVO memberVO);
    public void update(BabySleepVO babySleepVO);
    public void delete(String bs_no);
    public BabySleepVO findByPrimaryKey(String bs_no);
    public List<BabySleepVO> getOneBD(String bd_no);
    public List<BabySleepVO> getAll();
    public List<BabySleepVO> getAll(Map<String, String[]> map);   
}