package com.baby_growth.model;

import java.util.List;
import java.util.Map;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class BabyGrowthService {

	private BabyGrowthDAO_interface dao;

	public BabyGrowthService() {
		dao = new BabyGrowthDAO();
	}

	public BabyGrowthVO addBG( Integer bg_height, Integer bg_weight, Integer bg_head,String bd_no,java.sql.Date bg_date, PointRecordVO pointRecordVO, MemberVO memberVO) {

		BabyGrowthVO babyGrowthVO = new BabyGrowthVO();

		// 新增

		babyGrowthVO.setBg_height(bg_height);
		babyGrowthVO.setBg_weight(bg_weight);
		babyGrowthVO.setBg_head(bg_head);
		babyGrowthVO.setBd_no(bd_no);
		babyGrowthVO.setBg_date(bg_date);
		dao.insert(babyGrowthVO,pointRecordVO,memberVO);
		return babyGrowthVO;

	}

	public BabyGrowthVO updateBG(String bg_no, Integer bg_height, Integer bg_weight, Integer bg_head,String bd_no,java.sql.Date bg_date) {

		BabyGrowthVO babyGrowthVO = new BabyGrowthVO();
		
		// 修改
		babyGrowthVO.setBg_no(bg_no);
		babyGrowthVO.setBd_no(bd_no);
		babyGrowthVO.setBg_height(bg_height);
		babyGrowthVO.setBg_weight(bg_weight);
		babyGrowthVO.setBg_head(bg_head);
		babyGrowthVO.setBg_date(bg_date);
		dao.update(babyGrowthVO);
		return babyGrowthVO;

	}

	public void deleteBG(String bg_no) {
		dao.delete(bg_no);
	}

	public BabyGrowthVO getOneBG(String bg_no) {
		return dao.findByPrimaryKey(bg_no);
	}
	
	public List<BabyGrowthVO> getOneBD(String bd_no) {
		return dao.getOneBD(bd_no);
	}


	public List<BabyGrowthVO> getAll() {
		return dao.getAll();
	}
	public List<BabyGrowthVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
