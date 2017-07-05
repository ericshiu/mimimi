package com.optional_test.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hospital.model.HospitalVO;

public class OptionalTestDAO implements OptionalTestDAO_interface{
	
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
		"INSERT INTO optional_test (ot_no,ot_name,ot_week_start,ot_week_end,ot_summary) VALUES ('OT' || LPAD(to_char(ot_seq.NEXTVAL),8,'0'), ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT ot_no,ot_name,ot_week_start,ot_week_end,ot_summary FROM optional_test order by ot_no";
	private static final String GET_ONE_STMT = 
		"SELECT ot_no,ot_name,ot_week_start,ot_week_end,ot_summary FROM optional_test where ot_no = ?";
	private static final String DELETE = 
		"DELETE FROM optional_test where ot_no = ?";
	private static final String UPDATE = 
		"UPDATE optional_test set ot_name=?, ot_week_start=?, ot_week_end=?, ot_summary=? where ot_no = ?";

	private static final String GET_Hos_ByOt_no_STMT = "SELECT hos_no, hos_name, hos_address, hos_phone FROM hospital WHERE hos_no IN (SELECT hos_no FROM hospital_ot_item WHERE ot_no = ? ) order by hos_no";
	
	
	@Override
	public void insert(OptionalTestVO optionalTestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, optionalTestVO.getOt_name());
			pstmt.setInt(2, optionalTestVO.getOt_week_start());
			pstmt.setInt(3, optionalTestVO.getOt_week_end());
			pstmt.setString(4, optionalTestVO.getOt_summary());		

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
	public void update(OptionalTestVO optionalTestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, optionalTestVO.getOt_name());
			pstmt.setInt(2, optionalTestVO.getOt_week_start());
			pstmt.setInt(3, optionalTestVO.getOt_week_end());
			pstmt.setString(4, optionalTestVO.getOt_summary());
			pstmt.setString(5, optionalTestVO.getOt_no());
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
	public void delete(String ot_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ot_no);

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
	public OptionalTestVO findByPrimaryKey(String ot_no) {
		OptionalTestVO optionalTestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ot_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				optionalTestVO = new OptionalTestVO();
				optionalTestVO.setOt_no(rs.getString("ot_no"));
				optionalTestVO.setOt_name(rs.getString("ot_name"));
				optionalTestVO.setOt_week_start(rs.getInt("ot_week_start"));
				optionalTestVO.setOt_week_end(rs.getInt("ot_week_end"));
				optionalTestVO.setOt_summary(rs.getString("ot_summary"));
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
		return optionalTestVO;
	}
	@Override
	public List<OptionalTestVO> getAll() {
		List<OptionalTestVO> list = new ArrayList<OptionalTestVO>();
		OptionalTestVO optionalTestVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				optionalTestVO = new OptionalTestVO();
				optionalTestVO.setOt_no(rs.getString("ot_no"));
				optionalTestVO.setOt_name(rs.getString("ot_name"));
				optionalTestVO.setOt_week_start(rs.getInt("ot_week_start"));
				optionalTestVO.setOt_week_end(rs.getInt("ot_week_end"));
				optionalTestVO.setOt_summary(rs.getString("ot_summary"));
				list.add(optionalTestVO); // Store the row in the list
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
	@Override
	public Set<HospitalVO> getHosByOt_no(String ot_no) {
		Set<HospitalVO> set = new LinkedHashSet<HospitalVO>();
		HospitalVO hospitalVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Hos_ByOt_no_STMT);
			pstmt.setString(1, ot_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(rs.getString("hos_no"));
				hospitalVO.setHos_name(rs.getString("hos_name"));
				hospitalVO.setHos_address(rs.getString("hos_address"));
				hospitalVO.setHos_phone(rs.getString("hos_phone"));
				set.add(hospitalVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	
	
}
