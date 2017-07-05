package com.vaccine_course.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class VaccineCourseDAO implements VaccineCourseDAO_interface{
	
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
	
	private static final String INSERT_STMT = 
			"INSERT INTO VACCINE_COURSE(vc_no,vc_name,vc_sort,vc_age) VALUES ('VC'||LPAD(to_char(vaccine_course_seq.NEXTVAL),8,'0'),?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT vc_no,vc_name,vc_sort,vc_age,vc_introduction,vc_detailed FROM VACCINE_COURSE order by vc_no";
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1,vaccineCourseVO.getVc_name());
				pstmt.setString(2,vaccineCourseVO.getVc_sort());
				pstmt.setInt(3,vaccineCourseVO.getVc_age());				
				pstmt.executeUpdate();

			
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				

				pstmt.setString(1,vaccineCourseVO.getVc_name());
				pstmt.setString(2,vaccineCourseVO.getVc_sort());
				pstmt.setInt(3,vaccineCourseVO.getVc_age());	
				pstmt.setString(4,vaccineCourseVO.getVc_no());
				
				pstmt.executeUpdate();

				
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1,vc_no);

				pstmt.executeUpdate();

				
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);


				pstmt.setString(1, vc_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					vaccineCourseVO = new VaccineCourseVO();
					vaccineCourseVO.setVc_no(rs.getString("vc_no"));
					vaccineCourseVO.setVc_name(rs.getString("vc_name"));
					vaccineCourseVO.setVc_sort(rs.getString("vc_sort"));
					vaccineCourseVO.setVc_age(rs.getInt("vc_age"));
					vaccineCourseVO.setVc_introduction(rs.getString("vc_introduction"));
					vaccineCourseVO.setVc_detailed(rs.getString("vc_detailed"));
					
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					vaccineCourseVO = new VaccineCourseVO();
					vaccineCourseVO.setVc_no(rs.getString("vc_no"));
					vaccineCourseVO.setVc_name(rs.getString("vc_name"));
					vaccineCourseVO.setVc_sort(rs.getString("vc_sort"));
					vaccineCourseVO.setVc_age(rs.getInt("vc_age"));
					vaccineCourseVO.setVc_introduction(rs.getString("vc_introduction"));
					vaccineCourseVO.setVc_detailed(rs.getString("vc_detailed"));
					list.add(vaccineCourseVO); // Store the row in the list
					
					
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
