package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthorityDAO implements AuthorityDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO authority(aut_no, aut_name, aut_content)" + "VALUES('AUT'||LPAD(to_char(authority_seq.NEXTVAL),7,'0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM authority";
	private static final String GET_ONE_STMT = "SELECT * FROM authority WHERE aut_no = ?";
	private static final String DELETE_STMT = "DELETE FROM authority WHERE aut_no = ?";
	private static final String UPDATE = "UPDATE authority SET  aut_name=? , aut_content=? "
			+ "WHERE aut_no = ?";
	
	
	
	@Override
	public void insert(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authority.getAut_name());
			pstmt.setString(2, authority.getAut_content());

			pstmt.executeUpdate();

			// Handle any driver errors
		
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
	public void update(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, authority.getAut_name());
			pstmt.setString(2, authority.getAut_content());
			pstmt.setString(3, authority.getAut_no());
			pstmt.executeUpdate();

			// Handle any driver errors
	
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
	public void delete(String Aut_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, Aut_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		
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
	public AuthorityVO findByPK(String Aut_no) {
		AuthorityVO aut = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Aut_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aut = new AuthorityVO();
				aut.setAut_no(rs.getString("aut_no"));
				aut.setAut_name(rs.getString("aut_no"));
				aut.setAut_content(rs.getString("aut_content"));
			}

	
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

		return aut;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> autList = new ArrayList<>();
		AuthorityVO aut = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aut = new AuthorityVO();
				aut.setAut_no(rs.getString("aut_no"));
				aut.setAut_name(rs.getString("aut_name"));
				aut.setAut_content(rs.getString("aut_content"));

				autList.add(aut);
			}

	
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
		return autList;
	}
	
	
}
