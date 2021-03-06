package com.orders.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.order_detail.model.OrderDetailVO;
import com.product.model.ProductVO;

public class OrdersJDBCDAO implements OrdersDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "aa107g3";
	String password = "aa107g3";
	
	private static final String INSERT_PSTMT = 
			"INSERT INTO orders (ord_no, ord_date, mem_no, fir_no, ord_sum,"
			+ "ord_status, ord_rec_name, ord_rec_address, ord_rec_phone, mem_account)"
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-'||lpad(to_char(orders_seq.NEXTVAL),6,'0'),"
			+ "sysdate, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = 
			"UPDATE orders set mem_no=?, fir_no=?, ord_sum=?, ord_status=?, ord_ship_no=?,"
			+ "ord_rec_name=?, ord_rec_address=?, ord_rec_phone=?, mem_account=?,ord_date=?"
			+ " where ord_no = ?";
	private static final String UPDATE_SHIP ="update orders set ord_ship_date = sysdate where ord_no=?";
			
	private static final String UPDATE_REC ="update orders set ord_rec_date = sysdate where ord_no=?";
	
	private static final String GET_ALL_PSTMT = 
			"SELECT * FROM orders";
	private static final String FIND_BY_PK = 
			"SELECT * FROM orders where ord_no = ?";
	
	private static final String FIND_BY_FIR = " SELECT order_detail.ord_no, product.pro_no, product.fir_no,"
			+ "product.pro_name,product.pro_price,order_detail.pro_quantity FROM order_detail JOIN product ON "
			+ "product.pro_no=order_detail.pro_no where product.fir_no= ? order by order_detail.ord_no";
	
//	@Override
//	public void insert(OrdersVO orders) {
//		Connection con= null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(INSERT_PSTMT);
//			
//			
//			pstmt.setString(1, orders.getMem_no());
//			pstmt.setString(2, orders.getFir_no());
//			pstmt.setInt(3, orders.getOrd_sum());
//			pstmt.setString(4, orders.getOrd_status());
//			pstmt.setString(5, orders.getOrd_rec_name());
//			pstmt.setString(6, orders.getOrd_rec_address());
//			pstmt.setString(7, orders.getOrd_rec_phone());
//			pstmt.setString(8, orders.getMem_account());
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

//	@Override
//	public void update(OrdersVO orders) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(1, orders.getMem_no());
//			pstmt.setString(2, orders.getFir_no());
//			pstmt.setInt(3, orders.getOrd_sum());
//			pstmt.setString(4, orders.getOrd_status());
//			pstmt.setString(5, orders.getOrd_ship_no());
//			pstmt.setString(6, orders.getOrd_rec_name());
//			pstmt.setString(7, orders.getOrd_rec_address());
//			pstmt.setString(8, orders.getOrd_rec_phone());
//			pstmt.setString(9, orders.getMem_account());
//			pstmt.setTimestamp(10, orders.getOrd_date());
//			pstmt.setString(11, orders.getOrd_no());
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
	
	@Override
	public void updateShipDate(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE_SHIP);
			
			pstmt.setString(1,ord_no);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void updateRecDate(String ord_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE_REC);
			
			pstmt.setString(1,ord_no);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public OrdersVO findByPrimaryKey(String ord_no) {
		OrdersVO ord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				ord = new OrdersVO();
				ord.setOrd_date(rs.getTimestamp("ord_date"));
				ord.setMem_no(rs.getString("mem_no"));
				ord.setFir_no(rs.getString("fir_no"));
				ord.setOrd_ship_date(rs.getTimestamp("ord_ship_date"));
				ord.setOrd_rec_date(rs.getTimestamp("ord_rec_date"));
				ord.setOrd_sum(rs.getInt("ord_sum"));
				ord.setOrd_status(rs.getString("ord_status"));
				ord.setOrd_ship_no(rs.getString("ord_ship_no"));
				ord.setOrd_rec_name(rs.getString("ord_rec_name"));
				ord.setOrd_rec_address(rs.getString("ord_rec_address"));
				ord.setOrd_rec_phone(rs.getString("ord_rec_phone"));
				ord.setMem_account(rs.getString("mem_account"));
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
		return ord;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ordersVO = new OrdersVO();
				ordersVO.setOrd_no(rs.getString("ord_no"));
				ordersVO.setOrd_date(rs.getTimestamp("ord_date"));
				ordersVO.setMem_no(rs.getString("mem_no"));
				ordersVO.setFir_no(rs.getString("fir_no"));
				
				list.add(ordersVO); // Store the row in the list
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

	@Override
	public void insertOrderWithQty(OrdersVO orderVO, List<OrderDetailVO> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> findByFirNo(String fir_no) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO pro = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(FIND_BY_FIR);
			pstmt.setString(1, fir_no);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				pro = new ProductVO();
				
				pro.setPro_no(rs.getString("pro_no"));
				pro.setFir_no(rs.getString("fir_no"));
				pro.setPro_name(rs.getString("pro_name"));
				pro.setPro_price(rs.getInt("pro_price"));
				pro.setOrd_no(rs.getString("ord_no"));
				pro.setPro_quantity(rs.getString("pro_quantity"));
				list.add(pro);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(OrdersVO orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> findOneOrdByFriNo(String fir_no) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO pro = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(FIND_BY_FIR);
			pstmt.setString(1, fir_no);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				pro = new ProductVO();
				
				pro.setOrd_no(rs.getString("ord_no"));
				
				list.add(pro);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void updateStatus(OrdersVO orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShipOrdNo(OrdersVO orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> findFirmsByOrdNo(String ord_no) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
