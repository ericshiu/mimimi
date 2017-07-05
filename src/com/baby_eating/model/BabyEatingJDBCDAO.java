package com.baby_eating.model;

import java.util.*;

import com.forum_report.model.ForumReportVO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

import java.sql.*;

public class BabyEatingJDBCDAO implements BabyEatingDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

//	private static final String INSERT_STMT = "INSERT INTO BABY_EATING(be_no,bd_no,be_date,be_sort,be_mete) VALUES ('BE'||LPAD(to_char(baby_eating_seq.NEXTVAL),8,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING order by be_no";
	private static final String GET_ONE_STMT = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING where be_no = ?";
	private static final String GET_ONE_BD = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_EATING where be_no = ?";
	private static final String UPDATE = "UPDATE BABY_EATING set bd_no=?, be_date=?,be_sort=?,be_mete=? where be_no = ?";

//	@Override
//	public void insert(BabyEatingVO babyEatingVO) {
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
//			pstmt.setString(1, babyEatingVO.getBd_no());
//			pstmt.setTimestamp(2, babyEatingVO.getBe_date());
//			pstmt.setString(3, babyEatingVO.getBe_sort());
//			pstmt.setInt(4, babyEatingVO.getBe_mete());
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
	public void update(BabyEatingVO babyEatingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyEatingVO.getBd_no());
			pstmt.setTimestamp(2, babyEatingVO.getBe_date());
			pstmt.setString(3, babyEatingVO.getBe_sort());
			pstmt.setInt(4, babyEatingVO.getBe_mete());
			pstmt.setString(5, babyEatingVO.getBe_no());
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
	public void delete(String be_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, be_no);

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
	public BabyEatingVO findByPrimaryKey(String be_no) {

		BabyEatingVO babyEatingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, be_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));

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
		return babyEatingVO;
	}
	
	
	@Override
	public List<BabyEatingVO> getOneBD(String bd_no) {
		
		List<BabyEatingVO> list = new ArrayList<BabyEatingVO>();
		BabyEatingVO babyEatingVO = null;
		
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
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));
				list.add(babyEatingVO); // Store the row in the list
				
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
	public List<BabyEatingVO> getAll() {
		List<BabyEatingVO> list = new ArrayList<BabyEatingVO>();
		BabyEatingVO babyEatingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));
				list.add(babyEatingVO); // Store the row in the list

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

		BabyEatingJDBCDAO dao = new BabyEatingJDBCDAO();

		// 新增
//		BabyEatingVO BabyEatingVO1 = new BabyEatingVO();
//		BabyEatingVO1.setBd_no("BD00000001");
//		BabyEatingVO1.setBe_date(java.sql.Timestamp.valueOf("2017-4-18 10:10:00"));
//		BabyEatingVO1.setBe_sort("羊奶");
//		BabyEatingVO1.setBe_mete(10);
//		dao.insert(BabyEatingVO1);

		// 修改
		BabyEatingVO babyEatingVO2 = new BabyEatingVO();
		babyEatingVO2.setBd_no("BD00000001");
		babyEatingVO2.setBe_date(java.sql.Timestamp.valueOf("2017-4-18 18:10:00"));
		babyEatingVO2.setBe_sort("母乳");
		babyEatingVO2.setBe_mete(20);
		babyEatingVO2.setBe_no("BE00000003");
		dao.update(babyEatingVO2);

		// 刪除
		dao.delete("BE00000005");

//		// 查詢
//		BabyEatingVO babyEatingVO3 = dao.findByPrimaryKey("BE00000002");
//		System.out.print(babyEatingVO3.getBe_no() + ",");
//		System.out.print(babyEatingVO3.getBd_no() + ",");
//		System.out.print(babyEatingVO3.getBe_date() + ",");
//		System.out.print(babyEatingVO3.getBe_sort() + ",");
//		System.out.println(babyEatingVO3.getBe_mete() + ",");
//		System.out.println("---------------------");

		List<BabyEatingVO> listbd = dao.getOneBD("BD00000001");
		for (BabyEatingVO babyEatingVO : listbd) {
			System.out.print(babyEatingVO.getBe_no() + ",");
			System.out.print(babyEatingVO.getBd_no() + ",");
			System.out.print(babyEatingVO.getBe_date() + ",");
			System.out.print(babyEatingVO.getBe_sort() + ",");
			System.out.print(babyEatingVO.getBe_mete());
			System.out.println();
		}
		
//		List<BabyEatingVO> list = dao.getAll();
//		for (BabyEatingVO babyEatingVO : list) {
//			System.out.print(babyEatingVO.getBe_no() + ",");
//			System.out.print(babyEatingVO.getBd_no() + ",");
//			System.out.print(babyEatingVO.getBe_date() + ",");
//			System.out.print(babyEatingVO.getBe_sort() + ",");
//			System.out.print(babyEatingVO.getBe_mete());
//			System.out.println();
//		}

	}

	@Override
	public BabyEatingVO findByBD(String bd_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(BabyEatingVO babyEatingVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BabyEatingVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}