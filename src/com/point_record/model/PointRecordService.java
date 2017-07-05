package com.point_record.model;

import java.util.List;

public class PointRecordService {
	
	private PointRecordDAO_interface dao;
	
	public PointRecordService(){
		dao = new PointRecordDAO();
	}
	
//	public PointRecordVO addPR(String mem_no, String pr_type, String pr_content, java.sql.Date pr_date, Integer pr_point) {
//
//		PointRecordVO pointRecordVO = new PointRecordVO();
//		
//		pointRecordVO.setMem_no(mem_no);
//		pointRecordVO.setPr_type(pr_type);
//		pointRecordVO.setPr_content(pr_content);
//		pointRecordVO.setPr_date(pr_date);
//		pointRecordVO.setPr_point(pr_point);
//
//		dao.insert(pointRecordVO);
//		
//		return pointRecordVO;
//	}

	public PointRecordVO updatePR(String pr_no, String mem_no, String pr_type, String pr_content, java.sql.Date pr_date, Integer pr_point) {
		
		PointRecordVO pointRecordVO = new PointRecordVO();
		
		pointRecordVO.setPr_no(pr_no);
		pointRecordVO.setMem_no(mem_no);
		pointRecordVO.setPr_type(pr_type);
		pointRecordVO.setPr_content(pr_content);
		pointRecordVO.setPr_date(pr_date);
		pointRecordVO.setPr_point(pr_point);
		
		dao.update(pointRecordVO);
		
		return pointRecordVO;
	}
	
	public void deletePR(String pr_no) {
		dao.delete(pr_no);
	}

	public PointRecordVO getOnePR(String pr_no) {
		return dao.findByPrimaryKey(pr_no);
	}

	public List<PointRecordVO> getAll() {
		return dao.getAll();
	}		
}
