package com.point_record.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PointRecordDAO implements PointRecordDAO_interface{

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
		"INSERT INTO point_record (pr_no,mem_no,pr_type,pr_content,pr_date,pr_point) VALUES ('PR' || LPAD(to_char(pr_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pr_no,mem_no,pr_type,pr_content,to_char(pr_date,'yyyy-mm-dd'),pr_point FROM point_record order by pr_no desc";
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pointRecordVO.getMem_no());
			pstmt.setString(2, pointRecordVO.getPr_type());
			pstmt.setString(3, pointRecordVO.getPr_content());
			pstmt.setDate(4, pointRecordVO.getPr_date());
			pstmt.setInt(5, pointRecordVO.getPr_point());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pr");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}

	@Override
	public void update(PointRecordVO pointRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pointRecordVO.getMem_no());
			pstmt.setString(2, pointRecordVO.getPr_type());
			pstmt.setString(3, pointRecordVO.getPr_content());
			pstmt.setDate(4, pointRecordVO.getPr_date());
			pstmt.setInt(5, pointRecordVO.getPr_point());
			pstmt.setString(6, pointRecordVO.getPr_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pr_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
				list.add(pointRecordVO); // Store the row in the list
			}

			// Handle any driver errors
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
