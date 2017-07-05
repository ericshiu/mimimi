package com.orders.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;


public class TestOrders {

	public static void main(String[] args) {
		OrdersJDBCDAO odao = new OrdersJDBCDAO();
//		
//		OrdersVO orders1 = new OrdersVO();
//		
//		orders1.setMem_no("MEM0000001");
//		orders1.setFir_no("FIR0000001");
//		orders1.setOrd_sum(600);
//		orders1.setOrd_status("3");
//		orders1.setOrd_rec_name("TATA");
//		orders1.setOrd_rec_address("中央大學資策會");
//		orders1.setOrd_rec_phone("0933456789");
//		orders1.setMem_account("0204123456");
//				
//		odao.insert(orders1);	
		
		//修改
//		OrdersVO orders2 = new OrdersVO();
//		
//		orders2.setMem_no("MEM0000002");
//		orders2.setFir_no("FIR0000003");
//		orders2.setOrd_sum(7788);
//		orders2.setOrd_status("3");
//		orders2.setOrd_ship_no("XD1234567");
//		orders2.setOrd_rec_name("TATA");
//		orders2.setOrd_rec_address("中央大學資策會");
//		orders2.setOrd_rec_phone("0933456789");
//		orders2.setMem_account("0204123456");
//		orders2.setOrd_no("20170422-000002");
//		
//		odao.update(orders2);
//		
//		//修改運送日期
//		OrdersVO orders4 = new OrdersVO();
//		orders4.setOrd_no("20170422-000006");
//		odao.updateShipDate(orders4);
//		
//		//修改收到貨物日期
//		OrdersVO orders5 = new OrdersVO();
//		orders5.setOrd_no("20170422-000006");
//		odao.updateRecDate(orders5);

		// 查詢
//		List<ProductVO> list = odao.findByFirNo("FIR0000004");
//		for (ProductVO pro4 : list) {
//			System.out.println(pro4.getPro_no());
//			System.out.println(pro4.getFir_no());
//			System.out.println(pro4.getPro_name());
//			System.out.println(pro4.getPro_price());
//			System.out.println(pro4.getOrd_no());
//			System.out.println(pro4.getPro_quantity());
//		}
//		System.out.println("---------------------");

		List<ProductVO> list2 = odao.findOneOrdByFriNo("FIR0000004");
		for (ProductVO pro5 : list2) {
			System.out.println(pro5.getOrd_no());
			
		}
		System.out.println("---------------------");
		// 查詢
//		List<OrdersVO> list = odao.getAll();
//		for (OrdersVO order4 : list) {
//			System.out.print(order4.getOrd_no() + ",");
//			System.out.print(order4.getOrd_date() + ",");
//			System.out.print(order4.getMem_no() + ",");
//			System.out.print(order4.getFir_no() + ",");
//			System.out.print(order4.getOrd_ship_date() + ",");
//			System.out.print(order4.getOrd_rec_date() + ",");
//			System.out.print(order4.getOrd_sum());
//			System.out.print(order4.getOrd_status());
//			System.out.print(order4.getOrd_ship_no());
//			System.out.print(order4.getOrd_rec_name());
//			System.out.print(order4.getOrd_rec_address());
//			System.out.print(order4.getOrd_rec_phone());
//			System.out.print(order4.getMem_account());
//			
//			System.out.println();
//		}
//	}
}
}
