package com.authority_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorityListJDBCDAO implements AuthorityListDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 

			"INSERT INTO authority_list (man_no,aut_no) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM authority_list order by man_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM authority_list where man_no = ?";
//		"SELECT * FROM authority_list where aut_no = ?";
		private static final String DELETE = 
			"DELETE FROM authority_list where man_no = ?";
		private static final String UPDATE = 
			"UPDATE authority_list set aut_no=? where man_no = ?";
	
		@Override
		public void insert(AuthorityListVO authorityListVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, authorityListVO.getMan_no());
				pstmt.setString(2, authorityListVO.getAut_no());
				
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
		public void update(AuthorityListVO authorityListVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setString(1, authorityListVO.getAut_no());
				pstmt.setString(2, authorityListVO.getMan_no());
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
		public void delete(String man_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, man_no);

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
		public AuthorityListVO findByPK(String man_no) {

			AuthorityListVO authorityListVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, man_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					authorityListVO = new AuthorityListVO();
					authorityListVO.setMan_no(rs.getString("man_no"));
					authorityListVO.setAut_no(rs.getString("aut_no"));
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
			return authorityListVO;
		}

		@Override
		public List<AuthorityListVO> getAll() {
			List<AuthorityListVO> list = new ArrayList<AuthorityListVO>();
			AuthorityListVO authorityListVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// authorityListVO 也稱為 Domain objects
					authorityListVO = new AuthorityListVO();
					authorityListVO.setMan_no(rs.getString("man_no"));
					authorityListVO.setAut_no(rs.getString("aut_no"));
					list.add(authorityListVO); // Store the row in the list
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

			AuthorityListJDBCDAO dao = new AuthorityListJDBCDAO();

			// 新增
//			AuthorityListVO autlist = new AuthorityListVO();
//			autlist.setMan_no("MAN0000001");
//			autlist.setAut_no("AUT0000020");
//			
//			dao.insert(autlist);

			// 無修改
//			AuthorityListVO autlist1 = new AuthorityListVO();
//			
//			autlist1.setAut_no("MAN0000001");
//			autlist1.setMan_no("AUT0000020");
//			dao.update(autlist1);
//	//
////			// 刪除
			dao.delete("MAN0000001");
//	//
////			// 怪怪低查詢
//			AuthorityListVO autlist2 = dao.findByPK("MAN0000001");
////			AuthorityListVO autlist2 = dao.findByPK("AUT0000010");
//			System.out.print(autlist2.getMan_no() + ",");
//			System.out.print(autlist2.getAut_no() + ",");
//			System.out.println("---------------------");
//	//
////			// 查詢
//			List<AuthorityListVO> list = dao.getAll();
//			for (AuthorityListVO autlist3: list) {
//				System.out.print(autlist3.getMan_no() + ",");
//				System.out.print(autlist3.getAut_no() + ",");
//				System.out.println();
//			}
		}
	}
