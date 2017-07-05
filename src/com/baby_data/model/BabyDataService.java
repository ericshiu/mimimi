package com.baby_data.model;

import java.io.IOException;
import java.util.List;


public class BabyDataService {
	
	private BabyDataDAO_interface dao;

	public BabyDataService() {
		dao = new BabyDataDAO();
	}

	public BabyDataVO addBD(String mem_no, Integer bd_order, String bd_name,
			String bd_sex,java.sql.Date bd_pre,java.sql.Date bd_birthday,byte[] bd_pictures) throws IOException{
	

		BabyDataVO babyDataVO = new BabyDataVO();
	
		//新增
		babyDataVO.setMem_no(mem_no);
		babyDataVO.setBd_order(bd_order);
		babyDataVO.setBd_name(bd_name);
		babyDataVO.setBd_sex(bd_sex);
		babyDataVO.setBd_pre(bd_pre);
		babyDataVO.setBd_birthday(bd_birthday);
		babyDataVO.setBd_pictures(bd_pictures);
		
		dao.insert(babyDataVO);		
		return babyDataVO;
	}

	public BabyDataVO updateBD(String bd_no,String mem_no, Integer bd_order, String bd_name,
			String bd_sex,java.sql.Date bd_pre,java.sql.Date bd_birthday,byte[] bd_pictures) throws IOException {

		BabyDataVO babyDataVO = new BabyDataVO();

		//修改
		babyDataVO.setBd_no(bd_no);
		babyDataVO.setMem_no(mem_no);
		babyDataVO.setBd_order(bd_order);
		babyDataVO.setBd_name(bd_name);
		babyDataVO.setBd_sex(bd_sex);
		babyDataVO.setBd_pre(bd_pre);
		babyDataVO.setBd_birthday(bd_birthday);
		babyDataVO.setBd_pictures(bd_pictures);
		dao.update(babyDataVO);
		return babyDataVO;
	}

	public void deleteBD(String bd_no) {
		dao.delete(bd_no);
	}

	public BabyDataVO getOneBD(String bd_no) {
		return dao.findByPrimaryKey(bd_no);
	}
	public List<BabyDataVO> getOneMEM(String mem_no) {
		return dao.getOneMEM(mem_no);
	}

	public List<BabyDataVO> getAll() {
		return dao.getAll();
	}

}
