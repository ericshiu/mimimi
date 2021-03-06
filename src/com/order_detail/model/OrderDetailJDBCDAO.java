package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//public class OrderDetailJDBCDAO implements OrderDetailDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String user = "aa107g3";
//	String password = "aa107g3";
//	
//	private static final String INSERT_PSTMT = 
//			"INSERT INTO order_detail (ord_no, pro_no,pro_quantity) VALUES (?, ?, ?)";
//	private static final String DELETE_WHOLE = 
//			"DELETE FROM order_detail where ord_no = ?";
//	private static final String DELETE_ONE_PRO =
//			"DELETE FROM order_detail where ord_no = ? and pro_no = ?";
//	private static final String UPDATE = 
//			"UPDATE order_detail set pro_no=? ,pro_quantity=? where ord_no = ?";
//	private static final String GET_ALL_PSTMT = 
//			"SELECT * FROM order_detail";
//	private static final String FIND_BY_PK = 
//			"SELECT * FROM order_detail where ord_no = ?";
//	
//	@Override
//	public void insert(OrderDetailVO od) {
//		Connection con= null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(INSERT_PSTMT);
//			
//			pstmt.setString(1, od.getOrd_no());
//			pstmt.setString(2, od.getPro_no());
//			pstmt.setInt(3, od.getPro_quantity());
//			
//			pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException(se);
//			// Clean up JDBC resources
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
//	}
//
//	@Override
//	public void update(OrderDetailVO od) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(1, od.getPro_no());
//			pstmt.setInt(2, od.getPro_quantity());
//			pstmt.setString(3, od.getOrd_no());
//
//			pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
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
//	}
//	@Override
//	public void deleteWhole(String ord_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(DELETE_WHOLE);
//			
//			pstmt.setString(1, ord_no);
//			
//			pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
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
//	}
//	
//	@Override
//	public void deleteOnePro(String ord_no,String pro_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(DELETE_ONE_PRO);
//			
//			pstmt.setString(1, ord_no);
//			pstmt.setString(2, pro_no);
//			
//			pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
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
//	}
//	@Override
//	public OrderDetailVO findByPrimaryKey(String ord_no) {
//		OrderDetailVO odvo = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setString(1, ord_no);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				odvo = new OrderDetailVO();
//				odvo.setPro_no(rs.getString("pro_no"));
//				odvo.setPro_quantity(rs.getInt("pro_quantity"));
//				
//			}
//
//		} catch (ClassNotFoundException ce) {
//			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return odvo;
//	}
//	@Override
//	public List<OrderDetailVO> getAll() {
//		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
//		OrderDetailVO orderDetailVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(GET_ALL_PSTMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				orderDetailVO = new OrderDetailVO();
//				orderDetailVO.setOrd_no(rs.getString("ord_no"));
//				orderDetailVO.setPro_no(rs.getString("pro_no"));
//				orderDetailVO.setPro_quantity(rs.getInt("pro_quantity"));
//
//				list.add(orderDetailVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return list;
//	}
//
//}
