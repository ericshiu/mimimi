package com.advertise.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.advertise.model.*;

public class AdvertiseService {

	private AdvertiseDAO_interface dao;

	public AdvertiseService() {
		dao = new AdvertiseDAO();
	}

	public AdvertiseVO addAdv( String fir_no, Timestamp adv_start, Timestamp adv_end,
			String adv_review, Timestamp adv_review_date, String adv_status, byte[] adv_picture, String adv_title) {

		AdvertiseVO advVO = new AdvertiseVO();

		advVO.setFir_no(fir_no);
		advVO.setAdv_start(adv_start);
		advVO.setAdv_end(adv_end);
		advVO.setAdv_review(adv_review);
		advVO.setAdv_review_date(adv_review_date);
		advVO.setAdv_status(adv_status);
		advVO.setAdv_picture(adv_picture);
		advVO.setAdv_title(adv_title);
		dao.insert(advVO);

		return advVO;
	}

	public AdvertiseVO updateAdv(String adv_no,String fir_no, Timestamp adv_propose_date, Timestamp adv_start, Timestamp adv_end,
			String adv_review, Timestamp adv_review_date, String adv_status, byte[] adv_picture, String adv_title) {

		AdvertiseVO advVO = new AdvertiseVO();

		advVO.setAdv_no(adv_no);
		advVO.setFir_no(fir_no);
		advVO.setAdv_propose_date(adv_propose_date);
		advVO.setAdv_start(adv_start);
		advVO.setAdv_end(adv_end);
		advVO.setAdv_review(adv_review);
		advVO.setAdv_review_date(adv_review_date);
		advVO.setAdv_status(adv_status);
		advVO.setAdv_picture(adv_picture);
		advVO.setAdv_title(adv_title);
		dao.update(advVO);

		return advVO;
	}

	public void deleteAdv(String adv_no) {
		dao.delete(adv_no);
	}

	public AdvertiseVO getOneAdv(String adv_no) {
		return dao.findByPK(adv_no);
	}

	public List<AdvertiseVO> getAllAdv() {
		return dao.getAll();
	}
	
	public List<AdvertiseVO> getStatus(String adv_status) {
		return dao.getStatus(adv_status);
	}
	
	
	public List<AdvertiseVO> getDesc() {
		return dao.getDesc();
	}
	
	public List<AdvertiseVO> getAllAdv(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
