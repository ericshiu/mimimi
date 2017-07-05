package com.orders.model;

import java.util.List;

import com.order_detail.model.OrderDetailVO;
import com.product.model.ProductVO;

public interface OrdersDAO_interface {

	public void update(OrdersVO orders);
	public void updateShipDate(String ord_no);
	public void updateRecDate(String ord_no);
	public void updateStatus(OrdersVO orders);
	public void updateShipOrdNo(OrdersVO orders);
	public OrdersVO findByPrimaryKey(String ord_no);
	public List<OrdersVO> getAll();
	public List<ProductVO> findByFirNo(String fir_no);
	public List<ProductVO> findOneOrdByFriNo(String fir_no);
	public List<ProductVO> findFirmsByOrdNo(String ord_no);
	
	public void insertOrderWithQty(OrdersVO orderVO , List<OrderDetailVO> list);

}
