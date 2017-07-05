/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.baby_growth.model;

import java.util.*;



public class jdbcUtil_CompositeQuery_BG {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		
		if ("bg_no".equals(columnName) || "bd_no".equals(columnName) ||
				"bg_height".equals(columnName) || "bg_weight".equals(columnName) ||
				"bg_head".equals(columnName) || "bg_date".equals(columnName) ) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("be_no", new String[] { "BE00000001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "10" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from BABY_Growth "
				          + jdbcUtil_CompositeQuery_BG.get_WhereCondition(map)
				          ;
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
