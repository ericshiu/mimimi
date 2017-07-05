package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityJDBCDAO implements AuthorityDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "aa107g3";
	String password = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO authority(aut_no, aut_name, aut_content)" + "VALUES('AUT'||LPAD(to_char(authority_seq.NEXTVAL),7,'0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM authority";
	private static final String GET_ONE_STMT = "SELECT * FROM authority WHERE aut_no = ?";
	private static final String DELETE_STMT = "DELETE FROM authority WHERE aut_no = ?";
	private static final String UPDATE = "UPDATE authority SET  aut_name=? , aut_content=? "
			+ "WHERE aut_no = ?";

	@Override
	public void insert(AuthorityVO authority) {
		System.out.println("1");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authority.getAut_name());
			pstmt.setString(2, authority.getAut_content());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public void update(AuthorityVO authority) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, authority.getAut_name());
			pstmt.setString(2, authority.getAut_content());
			pstmt.setString(3, authority.getAut_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public void delete(String Aut_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, Aut_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	public AuthorityVO findByPK(String Aut_no) {
		AuthorityVO aut = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Aut_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aut = new AuthorityVO();
				aut.setAut_no(rs.getString("aut_no"));
				aut.setAut_name(rs.getString("aut_no"));
				aut.setAut_content(rs.getString("aut_content"));
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aut = new AuthorityVO();
				aut.setAut_no(rs.getString("aut_no"));
				aut.setAut_name(rs.getString("aut_no"));
				aut.setAut_content(rs.getString("aut_content"));

				autList.add(aut);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
		return autList;
	}

	public static void main(String[] args) {
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();

		// 新增
		 AuthorityVO aut1 = new AuthorityVO();
		 aut1.setAut_name("老闆管理");
		 aut1.setAut_content("1.炒魷魚功能");
		
		 dao.insert(aut1);
		
		
		
		
		 // 修改
//		 AuthorityVO aut2 = new AuthorityVO();
//		 aut2.setAut_no("AUT0000050");
//		 aut2.setAut_name("老闆管你");
//		 aut2.setAut_content("1.必殺炒魷魚");
//		 dao.update(aut2);
//		
//		 // 刪除
//		 dao.delete("AUT0000050");
//
//		// 查詢
//		 AuthorityVO aut3 = dao.findByPK("AUT0000010");
//		 System.out.print(aut3.getAut_no() + ",");
//		 System.out.print(aut3.getAut_name() + ",");
//		 System.out.print(aut3.getAut_content() + ",");
//		 System.out.println("---------------------");
//
//		 // 查詢
//		List<AuthorityVO> list = dao.getAll();
//		for (AuthorityVO man : list) {
//			System.out.print(man.getAut_no() + ",");
//			System.out.print(man.getAut_name() + ",");
//			System.out.print(man.getAut_content() + ",");	
//			System.out.println();
//		}
	}
}
