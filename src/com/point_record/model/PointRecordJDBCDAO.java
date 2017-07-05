package com.point_record.model;

import java.util.*;

import java.sql.*;

public class PointRecordJDBCDAO implements PointRecordDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 
		"INSERT INTO point_record (pr_no,mem_no,pr_type,pr_content,pr_date,pr_point) VALUES ('PR' || LPAD(to_char(pr_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pr_no,mem_no,pr_type,pr_content,to_char(pr_date,'yyyy-mm-dd'),pr_point FROM point_record order by pr_no";
	private static final String GET_ONE_STMT = 
		"SELECT pr_no,mem_no,pr_type,pr_content,to_char(pr_date,'yyyy-mm-dd'),pr_point FROM point_record where pr_no = ?";
	private static final String DELETE = 
		"DELETE FROM point_record where pr_no = ?";
	private static final String UPDATE = 
		"UPDATE point_record set mem_no=?, pr_type=?, pr_content=?, pr_date=?, pr_point=? where pr_no = ?";	

	@Override
	public void insert(PointRecordVO pointRecordVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pointRecordVO.getMem_no());
			pstmt.setString(2, pointRecordVO.getPr_type());
			pstmt.setString(3, pointRecordVO.getPr_content());
			pstmt.setDate(4, pointRecordVO.getPr_date());
			pstmt.setInt(5, pointRecordVO.getPr_point());
			
			pstmt.executeUpdate();

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
	public void update(PointRecordVO pointRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pointRecordVO.getMem_no());
			pstmt.setString(2, pointRecordVO.getPr_type());
			pstmt.setString(3, pointRecordVO.getPr_content());
			pstmt.setDate(4, pointRecordVO.getPr_date());
			pstmt.setInt(5, pointRecordVO.getPr_point());
			pstmt.setString(6, pointRecordVO.getPr_no());

			pstmt.executeUpdate();

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
	public void delete(String pr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pr_no);

			pstmt.executeUpdate();

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
	public PointRecordVO findByPrimaryKey(String pr_no) {
		PointRecordVO pointRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pointRecordVO = new PointRecordVO();
				pointRecordVO.setPr_no(rs.getString("pr_no"));
				pointRecordVO.setMem_no(rs.getString("mem_no"));
				pointRecordVO.setPr_type(rs.getString("pr_type"));
				pointRecordVO.setPr_content(rs.getString("pr_content"));
				pointRecordVO.setPr_date(rs.getDate("to_char(pr_date,'yyyy-mm-dd')"));
				pointRecordVO.setPr_point(rs.getInt("pr_point"));
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
		return pointRecordVO;
	}

	@Override
	public List<PointRecordVO> getAll() {
		List<PointRecordVO> list = new ArrayList<PointRecordVO>();
		PointRecordVO pointRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pointRecordVO = new PointRecordVO();
				pointRecordVO.setPr_no(rs.getString("pr_no"));
				pointRecordVO.setMem_no(rs.getString("mem_no"));
				pointRecordVO.setPr_type(rs.getString("pr_type"));
				pointRecordVO.setPr_content(rs.getString("pr_content"));
				pointRecordVO.setPr_date(rs.getDate("to_char(pr_date,'yyyy-mm-dd')"));
				pointRecordVO.setPr_point(rs.getInt("pr_point"));
				list.add(pointRecordVO);// Store the row in the list
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
		PointRecordJDBCDAO dao = new PointRecordJDBCDAO();

		// 新增
//		PointRecordVO pointRecordVO = new PointRecordVO();
//		pointRecordVO.setMem_no("MEM0000001");
//		pointRecordVO.setPr_type("預約參觀");
//		pointRecordVO.setPr_content("馨月產後護理之家");
//		pointRecordVO.setPr_date(java.sql.Date.valueOf("2017-04-20"));
//		pointRecordVO.setPr_point(100);
//		dao.insert(pointRecordVO);

		// 修改
//		PointRecordVO pointRecordVO2 = new PointRecordVO();
//		pointRecordVO2.setPr_no(990006);
//		pointRecordVO2.setMem_no(200001);
//		pointRecordVO2.setPr_type("預約參觀");
//		pointRecordVO2.setPr_content("馨月產後護理之家22");
//		pointRecordVO2.setPr_date(java.sql.Date.valueOf("2017-04-20"));
//		pointRecordVO2.setPr_point(-100);
//		dao.update(pointRecordVO2);

		// 刪除
//		dao.delete(990006);

		// 查詢
		PointRecordVO pointRecordVO3 = dao.findByPrimaryKey("PR00000001");
		System.out.print(pointRecordVO3.getPr_no() + ",");
		System.out.print(pointRecordVO3.getMem_no() + ",");
		System.out.print(pointRecordVO3.getPr_type() + ",");
		System.out.print(pointRecordVO3.getPr_content() + ",");
		System.out.print(pointRecordVO3.getPr_date() + ",");
		System.out.println(pointRecordVO3.getPr_point());
		System.out.println("---------------------");

		// 查詢
		List<PointRecordVO> list = dao.getAll();
		for (PointRecordVO aPR : list) {
			System.out.print(aPR.getPr_no() + ",");
			System.out.print(aPR.getMem_no() + ",");
			System.out.print(aPR.getPr_type() + ",");
			System.out.print(aPR.getPr_content() + ",");
			System.out.print(aPR.getPr_date() + ",");
			System.out.println(aPR.getPr_point());
			System.out.println();
		}
	
	}

}
