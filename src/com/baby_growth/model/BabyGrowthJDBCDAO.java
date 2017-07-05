package com.baby_growth.model;

import java.util.*;

import com.baby_excretion.model.BabyExcretionVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

import java.sql.*;

public class BabyGrowthJDBCDAO implements BabyGrowthDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO BABY_GROWTH(bg_no,bd_no,bg_height,bg_weight,bg_head,bg_date) VALUES ('BG'||LPAD(to_char(baby_growth_seq.NEXTVAL),8,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH order by bg_no";
	private static final String GET_ONE_STMT = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH where bg_no = ?";
	private static final String GET_ONE_BD = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_GROWTH where bg_no = ?";
	private static final String UPDATE = "UPDATE BABY_GROWTH set bd_no=?,bg_height=?,bg_weight=?, bg_head=? ,bg_date=? where bg_no = ?";

//	@Override
//	public void insert(BabyGrowthVO babyGrowthVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, babyGrowthVO.getBd_no());
//			pstmt.setInt(2, babyGrowthVO.getBg_height());
//			pstmt.setInt(3, babyGrowthVO.getBg_weight());
//			pstmt.setInt(4, babyGrowthVO.getBg_head());
//			pstmt.setDate(5, babyGrowthVO.getBg_date());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void update(BabyGrowthVO babyGrowthVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyGrowthVO.getBd_no());
			pstmt.setInt(2, babyGrowthVO.getBg_height());
			pstmt.setInt(3, babyGrowthVO.getBg_weight());
			pstmt.setInt(4, babyGrowthVO.getBg_head());
			pstmt.setDate(5, babyGrowthVO.getBg_date());
			pstmt.setString(6, babyGrowthVO.getBg_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public void delete(String bg_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bg_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public BabyGrowthVO findByPrimaryKey(String bg_no) {

		BabyGrowthVO babyGrowthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return babyGrowthVO;
	}

	@Override
	public List<BabyGrowthVO> getOneBD(String bd_no) {

		List<BabyGrowthVO> list = new ArrayList<BabyGrowthVO>();
		BabyGrowthVO babyGrowthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BD);

			pstmt.setString(1, bd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
				list.add(babyGrowthVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<BabyGrowthVO> getAll() {
		List<BabyGrowthVO> list = new ArrayList<BabyGrowthVO>();
		BabyGrowthVO babyGrowthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
				list.add(babyGrowthVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		BabyGrowthJDBCDAO dao = new BabyGrowthJDBCDAO();

		// 新增
		BabyGrowthVO babyGrowthVO1 = new BabyGrowthVO();
//		babyGrowthVO1.setBd_no("BD00000001");
//		babyGrowthVO1.setBg_height(30);
//		babyGrowthVO1.setBg_weight(50);
//		babyGrowthVO1.setBg_head(15);
//		babyGrowthVO1.setBg_date(java.sql.Date.valueOf("2005-10-01"));
//
//		dao.insert(babyGrowthVO1);

		// 修改
		BabyGrowthVO babyGrowthVO2 = new BabyGrowthVO();
		babyGrowthVO1.setBg_no("BG00000001");
		babyGrowthVO2.setBd_no("BD00000002");
		babyGrowthVO2.setBg_height(45);
		babyGrowthVO2.setBg_weight(60);
		babyGrowthVO2.setBg_head(5);
		babyGrowthVO1.setBg_date(java.sql.Date.valueOf("2005-10-01"));
		dao.update(babyGrowthVO2);

		// // 刪除
		// dao.delete("BG00000005");

		// 查詢
		BabyGrowthVO babyGrowthVO3 = dao.findByPrimaryKey("BG00000002");
		System.out.print(babyGrowthVO3.getBg_no() + ",");
		System.out.print(babyGrowthVO3.getBd_no() + ",");
		System.out.print(babyGrowthVO3.getBg_height() + ",");
		System.out.print(babyGrowthVO3.getBg_weight() + ",");
		System.out.println(babyGrowthVO3.getBg_head() + ",");
		System.out.println(babyGrowthVO3.getBg_date() + ",");
		System.out.println("---------------------");

		List<BabyGrowthVO> listbd = dao.getOneBD("BD00000001");
		for (BabyGrowthVO babyGrowthVO : listbd) {
			System.out.print(babyGrowthVO.getBg_no() + ",");
			System.out.print(babyGrowthVO.getBd_no() + ",");
			System.out.print(babyGrowthVO.getBg_height() + ",");
			System.out.print(babyGrowthVO.getBg_weight() + ",");
			System.out.print(babyGrowthVO.getBg_head() + ",");
			System.out.print(babyGrowthVO.getBg_date());
			System.out.println();
		}
		// List<BabyGrowthVO> list = dao.getAll();
		// for (BabyGrowthVO babyGrowthVO : list) {
		// System.out.print(babyGrowthVO.getBg_no() + ",");
		// System.out.print(babyGrowthVO.getBd_no() + ",");
		// System.out.print(babyGrowthVO.getBg_height() + ",");
		// System.out.print(babyGrowthVO.getBg_weight() + ",");
		// System.out.print(babyGrowthVO.getBg_head());
		// System.out.println();
		// }
	}

	@Override
	public void insert(BabyGrowthVO babyGrowthVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BabyGrowthVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}