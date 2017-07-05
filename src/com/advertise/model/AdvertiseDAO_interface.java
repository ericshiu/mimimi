package com.advertise.model;

import java.util.List;
import java.util.Map;

import com.advertise.model.*;


public interface AdvertiseDAO_interface {

	public void insert(AdvertiseVO advertise);
	public void update(AdvertiseVO advertise);
	public void delete(String adv_no);
	public AdvertiseVO findByPK(String adv_no);
	public List<AdvertiseVO> getAll();
	public List<AdvertiseVO> getDesc();
	public List<AdvertiseVO> getStatus(String adv_status);
	public List<AdvertiseVO> getAll(Map<String, String[]> map);
}
