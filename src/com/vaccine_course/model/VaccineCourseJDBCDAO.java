package com.vaccine_course.model;

import java.util.*;


import java.sql.*;


public class VaccineCourseJDBCDAO implements VaccineCourseDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = 
		"INSERT INTO VACCINE_COURSE(vc_no,vc_name,vc_sort,vc_age) VALUES ('VC'||LPAD(to_char(vaccine_course_seq.NEXTVAL),8,'0'),?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT vc_no,vc_name,vc_sort,vc_age FROM VACCINE_COURSE order by vc_no";
	private static final String GET_ONE_STMT = 
		"SELECT vc_no,vc_name,vc_sort,vc_age FROM VACCINE_COURSE where vc_no= ?";
	private static final String DELETE = 
		"DELETE FROM VACCINE_COURSE where vc_no= ?";
	private static final String UPDATE = 
		"UPDATE VACCINE_COURSE set vc_name=?, vc_sort=?,vc_age=? where vc_no= ?";
								
	@Override
	public void insert(VaccineCourseVO vaccineCourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,vaccineCourseVO.getVc_name());
			pstmt.setString(2,vaccineCourseVO.getVc_sort());
			pstmt.setInt(3,vaccineCourseVO.getVc_age());				
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(VaccineCourseVO vaccineCourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1,vaccineCourseVO.getVc_name());
			pstmt.setString(2,vaccineCourseVO.getVc_sort());
			pstmt.setInt(3,vaccineCourseVO.getVc_age());	
			pstmt.setString(4,vaccineCourseVO.getVc_no());
			
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
	public void delete(String vc_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,vc_no);

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
	public VaccineCourseVO findByPrimaryKey(String vc_no) {

		VaccineCourseVO vaccineCourseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, vc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				vaccineCourseVO = new VaccineCourseVO();
				vaccineCourseVO.setVc_no(rs.getString("vc_no"));
				vaccineCourseVO.setVc_name(rs.getString("vc_name"));
				vaccineCourseVO.setVc_sort(rs.getString("vc_sort"));
				vaccineCourseVO.setVc_age(rs.getInt("vc_age"));
				
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
		return vaccineCourseVO;
	}

	@Override
	public List<VaccineCourseVO> getAll() {
		List<VaccineCourseVO> list = new ArrayList<VaccineCourseVO>();
		VaccineCourseVO vaccineCourseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				vaccineCourseVO = new VaccineCourseVO();
				vaccineCourseVO.setVc_no(rs.getString("vc_no"));
				vaccineCourseVO.setVc_name(rs.getString("vc_name"));
				vaccineCourseVO.setVc_sort(rs.getString("vc_sort"));
				vaccineCourseVO.setVc_age(rs.getInt("vc_age"));
				list.add(vaccineCourseVO); // Store the row in the list
				
				
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

		VaccineCourseJDBCDAO dao = new VaccineCourseJDBCDAO();
		
		//新增
		VaccineCourseVO	vaccineCourseVO1 = new VaccineCourseVO();
		vaccineCourseVO1.setVc_name("卡介苗");
		vaccineCourseVO1.setVc_sort("dff");
		vaccineCourseVO1.setVc_age(1);
		dao.insert(vaccineCourseVO1);
		
		
		// 修改
		VaccineCourseVO	vaccineCourseVO2 = new VaccineCourseVO();
		vaccineCourseVO2.setVc_name("牛豆苗");
		vaccineCourseVO2.setVc_sort("df");
		vaccineCourseVO2.setVc_age(1);
		vaccineCourseVO2.setVc_no("VC00000001");
		dao.update(vaccineCourseVO2);
		
		// 刪除
		dao.delete("VC00000005");
		
		
		VaccineCourseVO vaccineCourseVO3 =  dao.findByPrimaryKey("VC00000001");
		System.out.print(vaccineCourseVO3.getVc_name() + ",");
		System.out.print(vaccineCourseVO3.getVc_sort() + ",");
		System.out.print(vaccineCourseVO3.getVc_age() );
		System.out.println();
		
		

		List<VaccineCourseVO> list = dao.getAll();
		for (VaccineCourseVO vaccineCourseVO : list) {
			System.out.print(vaccineCourseVO.getVc_no() + ",");
			System.out.print(vaccineCourseVO.getVc_name() + ",");
			System.out.print(vaccineCourseVO.getVc_sort() + ",");
			System.out.print(vaccineCourseVO.getVc_age() );
			System.out.println();
			

		}
	}
}