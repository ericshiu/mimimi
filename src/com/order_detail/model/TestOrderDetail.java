package com.order_detail.model;

import java.util.List;

public class TestOrderDetail {

//	public static void main(String[] args) {
//		
//		OrderDetailJDBCDAO oddao = new OrderDetailJDBCDAO();
//		
//		//新增
//		OrderDetailVO od1 = new OrderDetailVO();
//		od1.setOrd_no("20170425-000001");
//		od1.setPro_no("FIR0000004");
//		od1.setPro_quantity(1);
//		
//		oddao.insert(od1);	
//		
//		//修改
//		OrderDetailVO od2 = new OrderDetailVO();
//		od2.setPro_no("20170421-000005");
//		od2.setPro_quantity(20);
//		od2.setOrd_no("PRO0000004");
//		
//		
//		oddao.update(od2);
//
//		// 刪除整張訂單
//		oddao.deleteWhole("20170422-000001");
//		
//		//刪除訂單中的商品
//		oddao.deleteOnePro("20170422-000002", "PRO0000001");
//
//		// 查詢
//		OrderDetailVO od3 = oddao.findByPrimaryKey("20170422-000003");
//		System.out.println(od3.getPro_no() + ",");
//		System.out.println(od3.getPro_quantity());
//
//
//		System.out.println("---------------------");
//
//		// 查詢
//		List<OrderDetailVO> list = oddao.getAll();
//		for (OrderDetailVO od4 : list) {
//			System.out.print(od4.getOrd_no() + ",");
//			System.out.print(od4.getPro_no() + ",");
//			System.out.print(od4.getPro_quantity());
//
//			System.out.println();
//		}
//	}
}
