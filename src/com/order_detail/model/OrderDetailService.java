package com.order_detail.model;

import java.util.List;
import java.util.Set;

public class OrderDetailService {
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService (){
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO updateOd(String ord_no,String pro_no,Integer pro_quantity){
		OrderDetailVO odvo = new OrderDetailVO();
		odvo.setOrd_no(ord_no);
		odvo.setPro_no(pro_no);
		odvo.setPro_quantity(pro_quantity);
		
		dao.update(odvo);
		return odvo;
	}
	public void deleteOnePro(String ord_no,String pro_no){
		dao.deleteOnePro(ord_no, pro_no);
	}
	public void deleteWholePro(String ord_no){
		dao.deleteWhole(ord_no);
	}
	public OrderDetailVO getOneOD(String pro_no){
		return dao.findByPrimaryKey(pro_no);
	}
	public Set<OrderDetailVO> getQTYByProNo(String pro_no) {
		return dao.getODByProNo(pro_no);
	}
	public List<OrderDetailVO> getAllOD(){
		return dao.getAll();
	}

}
