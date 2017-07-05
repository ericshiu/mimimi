package com.baby_eating.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.forum_report.model.ForumReportVO;
import com.member.model.MemberVO;
import com.point_record.model.*;


public class BabyEatingService {

	private BabyEatingDAO_interface dao;

	public BabyEatingService() {
		dao = new BabyEatingDAO();
	}

	public BabyEatingVO addBE( Timestamp be_date, String be_sort, Integer be_mete,String bd_no, PointRecordVO pointRecordVO, MemberVO memberVO) {

		BabyEatingVO babyEatingVO = new BabyEatingVO();
		// 新增
		babyEatingVO.setBe_date(be_date);
		babyEatingVO.setBe_sort(be_sort);
		babyEatingVO.setBe_mete(be_mete);
		babyEatingVO.setBd_no(bd_no);
		
		dao.insert(babyEatingVO,pointRecordVO,memberVO);
		
		return babyEatingVO;

	}

	public BabyEatingVO updateBE(String be_no, Timestamp be_date, String be_sort, Integer be_mete, String bd_no) {

		BabyEatingVO babyEatingVO = new BabyEatingVO();
		// 修改
		babyEatingVO.setBe_no(be_no);
		babyEatingVO.setBe_date(be_date);
		babyEatingVO.setBe_sort(be_sort);
		babyEatingVO.setBe_mete(be_mete);
		babyEatingVO.setBd_no(bd_no);

		dao.update(babyEatingVO);
		
		return dao.findByPrimaryKey(be_no);

	}
	
//	//預留給 Struts 2 用的
//		public void updateBE(BabyEatingVO babyEatingVO) {
//			dao.update(babyEatingVO);
//		}

	public void deleteBE(String be_no) {
		dao.delete(be_no);
	}

	public BabyEatingVO getOneBE(String be_no) {
		return dao.findByPrimaryKey(be_no);
	}
	
	public BabyEatingVO getOneBDD(String bd_no) {
		return dao.findByBD(bd_no);
	}
	
	public List<BabyEatingVO> getOneBD(String bd_no) {
		return dao.getOneBD(bd_no);
	}

	public List<BabyEatingVO> getAll() {
		return dao.getAll();
	}
	public List<BabyEatingVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
