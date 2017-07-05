package com.orders.model;

import java.sql.Timestamp;
import java.util.List;

import com.cart.model.CartVO;
import com.order_detail.model.OrderDetailDAO_interface;
import com.order_detail.model.OrderDetailVO;
import com.product.model.ProductVO;

public class OrdersService {
	private OrdersDAO_interface dao;
	public OrdersService(){
		dao = new OrdersDAO();
	}
	
	public void addOrd(OrdersVO ordVO,List<OrderDetailVO> list) {
		dao.insertOrderWithQty(ordVO, list);
	}
	
	public OrdersVO updateOrd(String mem_no,String fir_no,Integer ord_sum,String ord_status,
			String ord_ship_no,String ord_rec_name,String ord_rec_address,String ord_rec_phone,
			String mem_account,Timestamp ord_date,String ord_no){
		OrdersVO ordVO = new OrdersVO();
		ordVO.setMem_no(mem_no);
		ordVO.setFir_no(fir_no);
		ordVO.setOrd_sum(ord_sum);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_ship_no(ord_ship_no);
		ordVO.setOrd_rec_name(ord_rec_name);
		ordVO.setOrd_rec_address(ord_rec_address);
		ordVO.setOrd_rec_phone(ord_rec_phone);
		ordVO.setMem_account(mem_account);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_no(ord_no);
		
		dao.update(ordVO);
		return ordVO;
	}
	public void updateShipDate(String ord_no){
		dao.updateShipDate(ord_no);
	}
	public void updateRecDate(String ord_no){
		dao.updateRecDate(ord_no);
	}
	
	public void updateOrdStatus(String ord_status,String ord_no) {
		OrdersVO ordVO = new OrdersVO();
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_no(ord_no);
		
		dao.updateStatus(ordVO);
	}
	
	public void updateOrdShipNo(String ord_ship_no,String ord_status,String ord_no) {
		OrdersVO ordVO = new OrdersVO();
		ordVO.setOrd_ship_no(ord_ship_no);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_no(ord_no);
		
		dao.updateShipOrdNo(ordVO);	
	}
	public OrdersVO getOneOrd(String ord_no){
		return dao.findByPrimaryKey(ord_no); 
	}
	public List<OrdersVO> getAllOrd(){
		return dao.getAll();
	}
	public List<ProductVO> getOrdByFir(String fir_no){
		return dao.findByFirNo(fir_no);
	}	
	public List<ProductVO> getOneOrdByFir(String fir_no) {
		return dao.findOneOrdByFriNo(fir_no);
	}
	public List<ProductVO> getFirmByOrdNo(String ord_no) {
		return dao.findFirmsByOrdNo(ord_no);
	}

}
