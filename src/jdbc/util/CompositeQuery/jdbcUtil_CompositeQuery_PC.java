/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_PC {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("pc_point".equals(columnName)) // 用於積分小於?
			aCondition = columnName + "<=" + value;
		else if ( "pc_id".equals(columnName) || "pc_name".equals(columnName) || "pc_type".equals(columnName) || "pc_address".equals(columnName) || "pc_bonus".equals(columnName) || "pc_gift".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		String order = null;
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"order".equals(key) && !"area".equals(key) && !"city".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			} else if (value != null && "order".equals(key)){
				order = value.trim();
			}
		}
		
		
			if (order.equals("pc_no")){
				whereCondition.append("order by pc_no DESC");				
			} else if (order.equals("pc_eva_good")){
				whereCondition.append("order by pc_eva_good DESC");
			} else if (order.equals("pc_point")){
				if (count < 1){
					whereCondition.append("where pc_point > 0 order by pc_point ASC");
				} else {
					whereCondition.append("and pc_point > 0 order by pc_point ASC");
				}
			}		
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("pc_no", new String[] { "PC" });
		map.put("pc_type", new String[] { "月子餐廠商" });
		map.put("pc_point", new String[] { "100" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from postpartum_care "
				          + jdbcUtil_CompositeQuery_PC.get_WhereCondition(map)
				          + "order by pc_no";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
