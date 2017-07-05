package com.baby_gcurve.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BabyGCurveDAO implements BabyGCurveDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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

			con = ds.getConnection();
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


			con = ds.getConnection();
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
}
