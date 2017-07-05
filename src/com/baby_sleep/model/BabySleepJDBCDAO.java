package com.baby_sleep.model;

import java.util.*;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

import java.sql.*;

public class BabySleepJDBCDAO implements BabySleepDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO BABY_SLEEP(bs_no,bd_no,bs_start,bs_end,bs_time) VALUES ('BS'||LPAD(to_char(baby_sleep_seq.NEXTVAL),8,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP order by bs_no";
	private static final String GET_ONE_STMT = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP where bs_no = ?";
	private static final String GET_ONE_BD = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_SLEEP where bs_no = ?";
	private static final String UPDATE = "UPDATE BABY_SLEEP set bd_no=?, bs_start=?,bs_end=?,bs_time=? where bs_no = ?";

//	@Override
//	public void insert(BabySleepVO babySleepVO) {
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
//			pstmt.setString(1, babySleepVO.getBd_no());
//			pstmt.setTimestamp(2, babySleepVO.getBs_start());
//			pstmt.setTimestamp(3, babySleepVO.getBs_end());
//			pstmt.setString(4, babySleepVO.getBs_time());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(BabySleepVO babySleepVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babySleepVO.getBd_no());
			pstmt.setTimestamp(2, babySleepVO.getBs_start());
			pstmt.setTimestamp(3, babySleepVO.getBs_end());
			pstmt.setString(4, babySleepVO.getBs_time());
			pstmt.setString(5, babySleepVO.getBs_no());

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
	public void delete(String bs_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bs_no);

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
	public BabySleepVO findByPrimaryKey(String bs_no) {

		BabySleepVO babySleepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));

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
		return babySleepVO;
	}

	@Override
	public List<BabySleepVO> getOneBD(String bd_no) {

		List<BabySleepVO> list = new ArrayList<BabySleepVO>();
		BabySleepVO babySleepVO = null;

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
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));
				list.add(babySleepVO); // Store the row in the list

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
	public List<BabySleepVO> getAll() {
		List<BabySleepVO> list = new ArrayList<BabySleepVO>();
		BabySleepVO babySleepVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));
				list.add(babySleepVO); // Store the row in the list

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

		BabySleepJDBCDAO dao = new BabySleepJDBCDAO();

		// 新增
		BabySleepVO babySleepVO1 = new BabySleepVO();
//		babySleepVO1.setBd_no("BD00000001");
//		
//		babySleepVO1.setBs_start(java.sql.Timestamp.valueOf("2017-4-18 10:10"));
//		babySleepVO1.setBs_end(java.sql.Timestamp.valueOf("2017-4-19 18:10:00"));
//		babySleepVO1.setBs_time("3小時");
//		dao.insert(babySleepVO1);

//		// 修改
//		BabySleepVO babySleepVO2 = new BabySleepVO();
//		babySleepVO2.setBd_no("BD00000001");
//		babySleepVO2.setBs_start(java.sql.Timestamp.valueOf("2017-4-19 18:10:00"));
//		babySleepVO2.setBs_end(java.sql.Timestamp.valueOf("2017-4-19 21:10:00"));
//		babySleepVO2.setBs_time("3小時");
//		babySleepVO2.setBs_no("BS00000002");
//		dao.update(babySleepVO2);
//
//		// 刪除
//		dao.delete("BS00000005");
//
//		BabySleepVO babySleepVO3 = dao.findByPrimaryKey("BS00000002");
//		System.out.print(babySleepVO3.getBd_no() + ",");
//		System.out.print(babySleepVO3.getBs_start() + ",");
//		System.out.print(babySleepVO3.getBs_end() + ",");
//		System.out.print(babySleepVO3.getBs_time());
//		System.out.println();

		
//		List<BabySleepVO> listbd = dao.getOneBD("BD00000001");
//		for (BabySleepVO babySleepVO : listbd) {
//		System.out.print(babySleepVO.getBs_no() + ",");
//		System.out.print(babySleepVO.getBd_no() + ",");
//		System.out.print(babySleepVO.getBs_start() + ",");
//		System.out.print(babySleepVO.getBs_end() + ",");
//		System.out.print(babySleepVO.getBs_time());
//		System.out.println();
//		}
		
//		List<BabySleepVO> list = dao.getAll();
//		for (BabySleepVO babySleepVO : list) {
//			System.out.print(babySleepVO.getBs_no() + ",");
//			System.out.print(babySleepVO.getBd_no() + ",");
//			System.out.print(babySleepVO.getBs_start() + ",");
//			System.out.print(babySleepVO.getBs_end() + ",");
//			System.out.print(babySleepVO.getBs_time());
//			System.out.println();
//
//		}
	}

	@Override
	public void insert(BabySleepVO babySleepVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BabySleepVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}