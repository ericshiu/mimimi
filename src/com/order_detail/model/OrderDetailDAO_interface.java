package com.order_detail.model;

import java.util.List;
import java.util.Set;


public interface OrderDetailDAO_interface {

	public void update(OrderDetailVO od);
	public void deleteWhole(String ord_no);
	public void deleteOnePro(String ord_no,String pro_no);
	public OrderDetailVO findByPrimaryKey(String ord_no);
	public List<OrderDetailVO> getAll();
	public Set<OrderDetailVO> getODByProNo(String pro_no);
	
	public void insert2 (OrderDetailVO od , java.sql.Connection con);

}
