/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Hos {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("hospital_ot_item.ot_no".equals(columnName)) // ot_no
			aCondition = columnName + "='" + value+"'";
		else if ( "hospital.hos_address".equals(columnName) || "hospital.hos_name".equals(columnName) ) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		whereCondition.append(" where hospital_ot_item.hos_no = hospital.hos_no ");
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key) ) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			
			}
		}
		

		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("hospital_ot_item.ot_no", new String[] { "OT00000001" });
		map.put("hospital.hos_address", new String[] { "桃園市" });
       // 注意Map裡面會含有action的key

		String finalSQL = "select hospital.hos_no, hospital.hos_name, hospital.hos_address, hospital.hos_phone, hospital_ot_item.ot_no from hospital, hospital_ot_item "
				          + jdbcUtil_CompositeQuery_Hos.get_WhereCondition(map)
				          + "order by hospital.hos_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
