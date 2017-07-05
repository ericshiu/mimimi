package com.baby_excretion.model;

import java.util.*;

import com.baby_eating.model.BabyEatingVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

import java.sql.*;

public class BabyExcretionJDBCDAO implements BabyExcretionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO BABY_EXCRETION(bex_no,bd_no,bex_date,bex_details) VALUES ('BEX'||LPAD(to_char(baby_excretion_seq.NEXTVAL),7,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION order by bex_no";
	private static final String GET_ONE_STMT = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION where bex_no = ?";
	private static final String GET_ONE_BD = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_EXCRETION where bex_no = ?";
	private static final String UPDATE = "UPDATE BABY_EXCRETION set bd_no=?, bex_date=?,bex_details=? where bex_no = ?";

//	@Override
//	public void insert(BabyExcretionVO babyExcretionVO) {
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
//			pstmt.setString(1, babyExcretionVO.getBd_no());
//			pstmt.setTimestamp(2, babyExcretionVO.getBex_date());
//			pstmt.setString(3, babyExcretionVO.getBex_details());
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
	public void update(BabyExcretionVO babyExcretionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyExcretionVO.getBd_no());
			pstmt.setTimestamp(2, babyExcretionVO.getBex_date());
			pstmt.setString(3, babyExcretionVO.getBex_details());
			pstmt.setString(4, babyExcretionVO.getBex_no());

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
	public void delete(String bex_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bex_no);

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
	public BabyExcretionVO findByPrimaryKey(String bex_no) {

		BabyExcretionVO babyExcretionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bex_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));

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
		return babyExcretionVO;
	}

	@Override
	public List<BabyExcretionVO> getOneBD(String bd_no) {

		List<BabyExcretionVO> list = new ArrayList<BabyExcretionVO>();
		BabyExcretionVO babyExcretionVO = null;

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
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));
				list.add(babyExcretionVO); // Store the row in the list

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
	public List<BabyExcretionVO> getAll() {
		List<BabyExcretionVO> list = new ArrayList<BabyExcretionVO>();
		BabyExcretionVO babyExcretionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));
				list.add(babyExcretionVO); // Store the row in the list

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

		BabyExcretionJDBCDAO dao = new BabyExcretionJDBCDAO();

		// 新增
//		BabyExcretionVO babyExcretionVO1 = new BabyExcretionVO();
//		babyExcretionVO1.setBd_no("BD00000001");
//		babyExcretionVO1.setBex_date(java.sql.Timestamp.valueOf("2017-4-19 8:10:00"));
//		babyExcretionVO1.setBex_details("便便硬硬的");
//
//		dao.insert(babyExcretionVO1);

//		// 修改
//		BabyExcretionVO babyExcretionVO2 = new BabyExcretionVO();
//		babyExcretionVO2.setBd_no("BD00000001");
//		babyExcretionVO2.setBex_date(java.sql.Timestamp.valueOf("2017-4-19 10:10:00"));
//		babyExcretionVO2.setBex_details("落賽");
//		babyExcretionVO2.setBex_no("BEX0000002");
//
//		dao.update(babyExcretionVO2);
//
//		// 刪除
//		dao.delete("BEX0000005");
//
//		// 查詢
//		BabyExcretionVO babyExcretionVO3 = dao.findByPrimaryKey("BEX0000001");
//		System.out.print(babyExcretionVO3.getBex_no() + ",");
//		System.out.print(babyExcretionVO3.getBd_no() + ",");
//		System.out.print(babyExcretionVO3.getBex_date() + ",");
//		System.out.print(babyExcretionVO3.getBex_details() + ",");
//		System.out.println("---------------------");

		List<BabyExcretionVO> listbd = dao.getOneBD("BD00000001");
		for (BabyExcretionVO babyExcretionVO : listbd) {
			System.out.print(babyExcretionVO.getBex_no() + ",");
			System.out.print(babyExcretionVO.getBd_no() + ",");
			System.out.print(babyExcretionVO.getBex_date() + ",");
			System.out.print(babyExcretionVO.getBex_details() + ",");
			System.out.println();	
		}
		
//		List<BabyExcretionVO> list = dao.getAll();
//		for (BabyExcretionVO babyExcretionVO : list) {
//			System.out.print(babyExcretionVO.getBex_no() + ",");
//			System.out.print(babyExcretionVO.getBd_no() + ",");
//			System.out.print(babyExcretionVO.getBex_date() + ",");
//			System.out.print(babyExcretionVO.getBex_details() + ",");
//			System.out.println();
//
//		}
	}

	@Override
	public void insert(BabyExcretionVO babyExcretionVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BabyExcretionVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}