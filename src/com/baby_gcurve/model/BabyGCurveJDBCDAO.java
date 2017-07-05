package com.baby_gcurve.model;


import java.util.*;



import java.sql.*;

public class BabyGCurveJDBCDAO implements BabyGCurveDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

		private static final String GET_ALL_STMT = 
			"SELECT bgc_no,BGC_SEX,BGC_AGE,BGC_HEIGHT_97,BGC_HEIGHT_85,BGC_HEIGHT_50,BGC_HEIGHT_15,BGC_HEIGHT_3,BGC_WEIGHT_97,BGC_WEIGHT_85,BGC_WEIGHT_50,BGC_WEIGHT_15,BGC_WEIGHT_3,BGC_HEAD_97,BGC_HEAD_85,BGC_HEAD_50,BGC_HEAD_15,BGC_HEAD_3 FROM BABYG_CURVE order by BGC_NO";
		private static final String GET_ONE_STMT = 
			"SELECT bgc_no,BGC_SEX,BGC_AGE,BGC_HEIGHT_97,BGC_HEIGHT_85,BGC_HEIGHT_50,BGC_HEIGHT_15,BGC_HEIGHT_3,BGC_WEIGHT_97,BGC_WEIGHT_85,BGC_WEIGHT_50,BGC_WEIGHT_15,BGC_WEIGHT_3,BGC_HEAD_97,BGC_HEAD_85,BGC_HEAD_50,BGC_HEAD_15,BGC_HEAD_3 FROM BABYG_CURVE where BGC_NO = ?";


	@Override
	public BabyGCurveVO findByPrimaryKey(String bgc_no) {

		BabyGCurveVO babyGCurveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bgc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGCurveVO = new BabyGCurveVO();
				babyGCurveVO.setBgc_no(rs.getString("bgc_no"));
				babyGCurveVO.setBgc_sex(rs.getString("bgc_sex"));
				babyGCurveVO.setBgc_age(rs.getInt("bgc_age"));
				babyGCurveVO.setBgc_height_97(rs.getDouble("bgc_height_97"));
				babyGCurveVO.setBgc_height_85(rs.getDouble("bgc_height_85"));
				babyGCurveVO.setBgc_height_50(rs.getDouble("bgc_height_50"));
				babyGCurveVO.setBgc_height_15(rs.getDouble("bgc_height_15"));
				babyGCurveVO.setBgc_height_3(rs.getDouble("bgc_height_3"));
				babyGCurveVO.setBgc_weight_97(rs.getDouble("bgc_weight_97"));
				babyGCurveVO.setBgc_weight_85(rs.getDouble("bgc_weight_85"));
				babyGCurveVO.setBgc_weight_50(rs.getDouble("bgc_weight_50"));
				babyGCurveVO.setBgc_weight_15(rs.getDouble("bgc_weight_15"));
				babyGCurveVO.setBgc_weight_3(rs.getDouble("bgc_weight_3"));
				babyGCurveVO.setBgc_head_97(rs.getDouble("bgc_head_97"));
				babyGCurveVO.setBgc_head_85(rs.getDouble("bgc_head_85"));
				babyGCurveVO.setBgc_head_50(rs.getDouble("bgc_head_50"));
				babyGCurveVO.setBgc_head_15(rs.getDouble("bgc_head_15"));
				babyGCurveVO.setBgc_head_3(rs.getDouble("bgc_head_3"));
	
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return babyGCurveVO;
	}

	@Override
	public List<BabyGCurveVO> getAll() {
		List<BabyGCurveVO> list = new ArrayList<BabyGCurveVO>();
		BabyGCurveVO babyGCurveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGCurveVO = new BabyGCurveVO();

				babyGCurveVO.setBgc_no(rs.getString("bgc_no"));
				babyGCurveVO.setBgc_sex(rs.getString("bgc_sex"));
				babyGCurveVO.setBgc_age(rs.getInt("bgc_age"));
				babyGCurveVO.setBgc_height_97(rs.getDouble("bgc_height_97"));
				babyGCurveVO.setBgc_height_85(rs.getDouble("bgc_height_85"));
				babyGCurveVO.setBgc_height_50(rs.getDouble("bgc_height_50"));
				babyGCurveVO.setBgc_height_15(rs.getDouble("bgc_height_15"));
				babyGCurveVO.setBgc_height_3(rs.getDouble("bgc_height_3"));
				babyGCurveVO.setBgc_weight_97(rs.getDouble("bgc_weight_97"));
				babyGCurveVO.setBgc_weight_85(rs.getDouble("bgc_weight_85"));
				babyGCurveVO.setBgc_weight_50(rs.getDouble("bgc_weight_50"));
				babyGCurveVO.setBgc_weight_15(rs.getDouble("bgc_weight_15"));
				babyGCurveVO.setBgc_weight_3(rs.getDouble("bgc_weight_3"));
				babyGCurveVO.setBgc_head_97(rs.getDouble("bgc_head_97"));
				babyGCurveVO.setBgc_head_85(rs.getDouble("bgc_head_85"));
				babyGCurveVO.setBgc_head_50(rs.getDouble("bgc_head_50"));
				babyGCurveVO.setBgc_head_15(rs.getDouble("bgc_head_15"));
				babyGCurveVO.setBgc_head_3(rs.getDouble("bgc_head_3"));
	
				list.add(babyGCurveVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		BabyGCurveJDBCDAO dao = new BabyGCurveJDBCDAO();
		

		
		
		// 查詢
		BabyGCurveVO babyGCurveVO3 = dao.findByPrimaryKey("BGC0000001");
		System.out.print(babyGCurveVO3.getBgc_no() + ",");
		System.out.print(babyGCurveVO3.getBgc_sex() + ",");
		System.out.print(babyGCurveVO3.getBgc_age() + ",");
		System.out.print(babyGCurveVO3.getBgc_height_97() + ",");
		System.out.print(babyGCurveVO3.getBgc_height_85() + ",");
		System.out.print(babyGCurveVO3.getBgc_height_50() + ",");
		System.out.print(babyGCurveVO3.getBgc_height_15() + ",");
		System.out.print(babyGCurveVO3.getBgc_height_15() + ",");
		System.out.print(babyGCurveVO3.getBgc_weight_97() + ",");
		System.out.print(babyGCurveVO3.getBgc_weight_85() + ",");
		System.out.print(babyGCurveVO3.getBgc_weight_50() + ",");
		System.out.print(babyGCurveVO3.getBgc_weight_15() + ",");
		System.out.print(babyGCurveVO3.getBgc_weight_3() + ",");
		System.out.print(babyGCurveVO3.getBgc_head_97() + ",");
		System.out.print(babyGCurveVO3.getBgc_head_85() + ",");
		System.out.print(babyGCurveVO3.getBgc_head_50() + ",");
		System.out.print(babyGCurveVO3.getBgc_head_15() + ",");
		System.out.print(babyGCurveVO3.getBgc_head_3());
		System.out.println("---------------------");
			
		List<BabyGCurveVO> list = dao.getAll();
		for (BabyGCurveVO babyGCurveVO : list) {
			System.out.print(babyGCurveVO.getBgc_no() + ",");
			System.out.print(babyGCurveVO.getBgc_sex() + ",");
			System.out.print(babyGCurveVO.getBgc_age() + ",");
			System.out.print(babyGCurveVO.getBgc_height_97() + ",");
			System.out.print(babyGCurveVO.getBgc_height_85() + ",");
			System.out.print(babyGCurveVO.getBgc_height_50() + ",");
			System.out.print(babyGCurveVO.getBgc_height_15() + ",");
			System.out.print(babyGCurveVO.getBgc_height_15() + ",");
			System.out.print(babyGCurveVO.getBgc_weight_97() + ",");
			System.out.print(babyGCurveVO.getBgc_weight_85() + ",");
			System.out.print(babyGCurveVO.getBgc_weight_50() + ",");
			System.out.print(babyGCurveVO.getBgc_weight_15() + ",");
			System.out.print(babyGCurveVO.getBgc_weight_3() + ",");
			System.out.print(babyGCurveVO.getBgc_head_97() + ",");
			System.out.print(babyGCurveVO.getBgc_head_85() + ",");
			System.out.print(babyGCurveVO.getBgc_head_50() + ",");
			System.out.print(babyGCurveVO.getBgc_head_15() + ",");
			System.out.print(babyGCurveVO.getBgc_head_3());
			System.out.println();
		}
	}
}