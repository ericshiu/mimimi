package com.baby_sleep.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;



public class BabySleepService {
	
	private BabySleepDAO_interface dao;

	public BabySleepService() {
		dao = new BabySleepDAO();
	}

	public BabySleepVO addBS(String bd_no,Timestamp bs_start,Timestamp bs_end,String bs_time, PointRecordVO pointRecordVO, MemberVO memberVO) {
		
		BabySleepVO babySleepVO = new BabySleepVO();
		
		//新增
		babySleepVO.setBd_no(bd_no);
		babySleepVO.setBs_start(bs_start);
		babySleepVO.setBs_end(bs_end);
		babySleepVO.setBs_time(bs_time);
				
		dao.insert(babySleepVO,pointRecordVO,memberVO);
		return babySleepVO;
		

	}

	public BabySleepVO updateBS(String bs_no,String bd_no,Timestamp bs_start,Timestamp bs_end,String bs_time) {
		
		BabySleepVO babySleepVO = new BabySleepVO();
		
		// 修改
		babySleepVO.setBs_no(bs_no);
		babySleepVO.setBd_no(bd_no);
		babySleepVO.setBs_start(bs_start);
		babySleepVO.setBs_end(bs_end);
		babySleepVO.setBs_time(bs_time);

		dao.update(babySleepVO);
		return babySleepVO;

	}

	public void deleteBS(String bs_no) {
		dao.delete(bs_no);
	}

	public BabySleepVO getOneBS(String bs_no) {
		return dao.findByPrimaryKey(bs_no);
	}
	
	public List<BabySleepVO> getOneBD(String bd_no) {
		return dao.getOneBD(bd_no);
	}


	public List<BabySleepVO> getAll() {
		return dao.getAll();
	}
	
	public List<BabySleepVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}



}
