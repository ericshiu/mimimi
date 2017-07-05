package com.firm.model;

import java.util.List;


public class TestFirm {

	public static void main(String[] args) {
	
		FirmJDBCDAO dao = new FirmJDBCDAO();
		//新增
		FirmVO firm1 = new FirmVO();
		firm1.setFir_id("LaLaLand");
		firm1.setFir_password("AA107G3");
		firm1.setFir_name("LaLaLand");
		firm1.setFir_type("3");
		firm1.setFir_phone("0955888999");
		firm1.setFir_address("TaiwanNo1");
		firm1.setFir_email("22222@gg.com");
		firm1.setFir_account("04147856");
		firm1.setFir_authority("Y");
		firm1.setFir_eva_good(1);
		firm1.setFir_eva_normal(0);
		firm1.setFir_eva_bad(0);
		
		dao.insert(firm1);
		
		//修改
		FirmVO firm2 = new FirmVO();
		
		firm2.setFir_id("TaTa");
		firm2.setFir_password("AAAA");
		firm2.setFir_name("Lin");
		firm2.setFir_type("10");
		firm2.setFir_phone("098888888");
		firm2.setFir_address("TaiwanNo1");
		firm2.setFir_email("33333@gg.com");
		firm2.setFir_account("04147856");
		firm2.setFir_authority("N");
		firm2.setFir_eva_good(1);
		firm2.setFir_eva_normal(0);
		firm2.setFir_eva_bad(0);
		firm2.setFir_no("FIR0000015");
		
		dao.update(firm2);
		
		//刪除
		dao.delete("FIR0000016");
		
		//查詢
		FirmVO firm3 = dao.findByPrimaryKey("FIR0000015");
		
		System.out.print(firm3.getFir_id()+ ",");
		System.out.print(firm3.getFir_password()+ ",");
		System.out.print(firm3.getFir_name()+ ",");
		System.out.print(firm3.getFir_type()+ ",");
		System.out.print(firm3.getFir_phone()+ ",");
		System.out.print(firm3.getFir_address()+ ",");
		System.out.println(firm3.getFir_email());
		System.out.println(firm3.getFir_account());
		System.out.println("---------------------");
		
		// 查詢
		List<FirmVO> list = dao.getAll();
		for (FirmVO firm : list) {
			System.out.print(firm.getFir_id() + ",");
			System.out.print(firm.getFir_password()+ ",");
			System.out.print(firm.getFir_name());
			
			System.out.println();

		}
	}
}
