package com.baby_excretion.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class BabyExcretionService {
	
	private BabyExcretionDAO_interface dao;

	public BabyExcretionService() {
		dao = new BabyExcretionDAO();
	}

	

	public BabyExcretionVO addBEX(Timestamp bex_date,String bex_details,String bd_no, PointRecordVO pointRecordVO, MemberVO memberVO) {
	

		BabyExcretionVO babyExcretionVO = new BabyExcretionVO();
	
		//新增
		babyExcretionVO.setBd_no(bd_no);
		babyExcretionVO.setBex_date(bex_date);
		babyExcretionVO.setBex_details(bex_details);
		dao.insert(babyExcretionVO,pointRecordVO,memberVO);		
		return babyExcretionVO;
		
	}

	public BabyExcretionVO updateBEX(String bex_no,Timestamp bex_date,String bex_details,String bd_no) {
		
		BabyExcretionVO babyExcretionVO = new BabyExcretionVO();
		//修改
		babyExcretionVO.setBex_no(bex_no);
		babyExcretionVO.setBex_date(bex_date);
		babyExcretionVO.setBex_details(bex_details);
		babyExcretionVO.setBd_no(bd_no);
		dao.update(babyExcretionVO);
		return babyExcretionVO;
		
	}

	public void deleteBEX(String bex_no) {
		dao.delete(bex_no);
	}

	public BabyExcretionVO getOneBEX(String bex_no) {
		return dao.findByPrimaryKey(bex_no);
	}

	public List<BabyExcretionVO> getOneBD(String bd_no) {
		return dao.getOneBD(bd_no);
	}

	public List<BabyExcretionVO> getAll() {
		return dao.getAll();
	}
	public List<BabyExcretionVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
