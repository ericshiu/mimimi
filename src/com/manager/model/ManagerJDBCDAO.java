package com.manager.model;

import java.sql.*;
import java.util.*;

public class ManagerJDBCDAO implements ManagerDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "aa107g3";
	String password = "aa107g3";
	
	private static final String INSERT_STMT = "INSERT INTO 	MANAGER(man_no, man_id, man_name, man_password,man_email)"
			+ "VALUES('MAN'||LPAD(to_char(manager_seq.NEXTVAL),7,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MANAGER ORDER BY man_no";
	
	private static final String GET_ONE_STMT = "SELECT * FROM MANAGER WHERE man_no = ?";
	private static final String GET_ONE_ID = "select * from manager where man_id=?";
	private static final String DELETE_STMT = "DELETE FROM MANAGER WHERE man_no = ?";
	private static final String UPDATE = "UPDATE MANAGER SET  man_id=? , man_name=?, "
			+ "man_password=?,man_email=? WHERE man_no = ?";
	
	@Override
	public void insert(ManagerVO manager) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setInt(1, manager.getMan_no());
			pstmt.setString(1, manager.getMan_id());
			pstmt.setString(2, manager.getMan_name());
			pstmt.setString(3, manager.getMan_password());
			pstmt.setString(4, manager.getMan_email());

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
	public void update(ManagerVO manager) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, manager.getMan_id());
			pstmt.setString(2, manager.getMan_name());
			pstmt.setString(3, manager.getMan_password());
			pstmt.setString(4, manager.getMan_email());
			pstmt.setString(5, manager.getMan_no());
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
	public void delete(String man_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, man_no);
			
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
	public ManagerVO findByPrimaryKey(String man_no) {
		ManagerVO man = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, man_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				man=new ManagerVO();
				man.setMan_no(rs.getString("man_no"));
				man.setMan_id(rs.getString("man_id"));
				man.setMan_name(rs.getString("man_no"));
				man.setMan_password(rs.getString("man_password"));
				man.setMan_email(rs.getString("man_email"));
				
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

		return man;
	}

	
	@Override
	public ManagerVO findById(String man_id) {
		ManagerVO man = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_ID);
			pstmt.setString(1, man_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				man=new ManagerVO();
				man.setMan_no(rs.getString("man_no"));
				man.setMan_id(rs.getString("man_id"));
				man.setMan_name(rs.getString("man_no"));
				man.setMan_password(rs.getString("man_password"));
				man.setMan_email(rs.getString("man_email"));
				
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

		return man;
	}
	
	
	
	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> manList = new ArrayList<>();
		ManagerVO man = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				man=new ManagerVO();
				man.setMan_no(rs.getString("man_no"));
				man.setMan_id(rs.getString("man_id"));
				man.setMan_name(rs.getString("man_no"));
				man.setMan_password(rs.getString("man_password"));
				man.setMan_email(rs.getString("man_email"));
				manList.add(man);
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
		return manList;
	}
	public static void main(String[] args) {
		ManagerJDBCDAO dao = new ManagerJDBCDAO();

		// 新增
//		ManagerVO man1 = new ManagerVO();
//		man1.setMan_id("white");
//		man1.setMan_name("白博士白白");
//		man1.setMan_password("white123");
//		man1.setMan_email("white@gmail.com");
//		dao.insert(man1);
//
//		
//		
//		
//		// 修改
//		ManagerVO man2 = new ManagerVO();
//		man2.setMan_no("MAN0000005");
//		man2.setMan_id("whitewhite");
//		man2.setMan_name("白白博士005");
//		man2.setMan_password("white123123");
//		man2.setMan_email("white123@gmail.com");
//		dao.update(man2);
//		
//		// 刪除
//		dao.delete("MAN0000005");

		// 查詢
//		ManagerVO man3 = dao.findByPrimaryKey("MAN0000001");
//		System.out.print(man3.getMan_no() + ",");
//		System.out.print(man3.getMan_id() + ",");
//		System.out.print(man3.getMan_name() + ",");
//		System.out.print(man3.getMan_password() + ",");
//		System.out.println(man3.getMan_email() + ",");
//		System.out.println("---------------------");
		
		// 查詢
//		ManagerVO man3 = dao.findById("red");
//		System.out.print(man3.getMan_no() + ",");
//		System.out.print(man3.getMan_id() + ",");
//		System.out.print(man3.getMan_name() + ",");
//		System.out.print(man3.getMan_password() + ",");
//		System.out.println(man3.getMan_email() + ",");
//		System.out.println("---------------------");

//		// 查詢
//		List<ManagerVO> list = dao.getAll();
//		for (ManagerVO man : list) {
//			System.out.print(man.getMan_no() + ",");
//			System.out.print(man.getMan_id() + ",");
//			System.out.print(man.getMan_name() + ",");
//			System.out.print(man.getMan_password() + ",");
//			System.out.print(man.getMan_email() + ",");
//			System.out.println();
//		}
		}
	}

