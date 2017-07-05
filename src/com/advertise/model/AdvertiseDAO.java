package com.advertise.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdvertiseDAO implements AdvertiseDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = "INSERT INTO ADVERTISE(adv_no, fir_no, adv_propose_date, adv_start,"
			+ " adv_end, adv_review,adv_review_date,  adv_status, adv_picture, adv_title)"
			+ "VALUES('ADV'||LPAD(to_char(advertise_seq.NEXTVAL),7,'0'), ?, sysdate, ?, ?,?, ?, ?, ?,?)";						
	private static final String GET_ALL = "SELECT * FROM ADVERTISE";
	private static final String GET_ALL_DESC = "SELECT * FROM ADVERTISE order by adv_no desc";
	private static final String GET_ALL_STATUS = "SELECT * FROM ADVERTISE where adv_status=? order by adv_no desc";
	private static final String GET_ONE_STMT = "SELECT * FROM ADVERTISE WHERE adv_no = ?";
	private static final String DELETE = "DELETE FROM ADVERTISE WHERE adv_no = ?";
	private static final String UPDATE = "UPDATE ADVERTISE SET  fir_no=? , adv_propose_date=?, "
			+ "adv_start=?,adv_end=?,adv_review=?,adv_review_date=?,adv_status=?,adv_picture=?,adv_title=? WHERE adv_no = ?";
	@Override
	public void insert(AdvertiseVO advertise) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

	
			pstmt.setString(1, advertise.getFir_no());
//			pstmt.setTimestamp(2, advertise.getAdv_propose_date());
			pstmt.setTimestamp(2, advertise.getAdv_start());
			pstmt.setTimestamp(3, advertise.getAdv_end());
			pstmt.setString(4, advertise.getAdv_review());
			pstmt.setTimestamp(5, advertise.getAdv_review_date());
			pstmt.setString(6, advertise.getAdv_status());
			pstmt.setBytes(7, advertise.getAdv_picture());
			pstmt.setString(8, advertise.getAdv_title());

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
	public void update(AdvertiseVO advertise) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

		
			pstmt.setString(1, advertise.getFir_no());
			pstmt.setTimestamp(2, advertise.getAdv_propose_date());
			pstmt.setTimestamp(3, advertise.getAdv_start());
			pstmt.setTimestamp(4, advertise.getAdv_end());
			pstmt.setString(5, advertise.getAdv_review());
			pstmt.setTimestamp(6, advertise.getAdv_review_date());
			pstmt.setString(7, advertise.getAdv_status());
			pstmt.setBytes(8, advertise.getAdv_picture());
			pstmt.setString(9, advertise.getAdv_title());
			pstmt.setString(10, advertise.getAdv_no());
			
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
	public void delete(String adv_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adv_no);
			
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
	public AdvertiseVO findByPK(String adv_no) {
		AdvertiseVO adv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, adv_no);
			rs = pstmt.executeQuery();

		
			while (rs.next()) {
				adv=new AdvertiseVO();
				adv.setAdv_no(rs.getString("adv_no"));
				adv.setFir_no(rs.getString("Fir_no"));
				adv.setAdv_propose_date(rs.getTimestamp("adv_propose_date"));
				adv.setAdv_start(rs.getTimestamp("adv_start"));
				adv.setAdv_end(rs.getTimestamp("adv_end"));
				adv.setAdv_review(rs.getString("adv_review"));
				adv.setAdv_review_date(rs.getTimestamp("adv_review_date"));
				adv.setAdv_status(rs.getString("adv_status"));
				adv.setAdv_picture(rs.getBytes("adv_picture"));
				adv.setAdv_title(rs.getString("adv_title"));
				
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

		return adv;
	}

	@Override
	public List<AdvertiseVO> getAll() {
		List<AdvertiseVO> advList = new ArrayList<AdvertiseVO>();
		AdvertiseVO adv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adv=new AdvertiseVO();
				adv.setAdv_no(rs.getString("adv_no"));
				adv.setFir_no(rs.getString("fir_no"));
				adv.setAdv_propose_date(rs.getTimestamp("adv_propose_date"));
				adv.setAdv_start(rs.getTimestamp("adv_start"));
				adv.setAdv_end(rs.getTimestamp("adv_end"));
				adv.setAdv_review(rs.getString("adv_review"));
				adv.setAdv_review_date(rs.getTimestamp("adv_review_date"));
				adv.setAdv_status(rs.getString("adv_status"));
				adv.setAdv_picture(rs.getBytes("adv_picture"));
				adv.setAdv_title(rs.getString("adv_title"));
				advList.add(adv);
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
		return advList;
	}
	
	
	
	@Override
	public List<AdvertiseVO> getStatus(String adv_status) {
		List<AdvertiseVO> advList = new ArrayList<AdvertiseVO>();
		AdvertiseVO adv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STATUS);
			pstmt.setString(1, adv_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adv=new AdvertiseVO();
				adv.setAdv_no(rs.getString("adv_no"));
				adv.setFir_no(rs.getString("fir_no"));
				adv.setAdv_propose_date(rs.getTimestamp("adv_propose_date"));
				adv.setAdv_start(rs.getTimestamp("adv_start"));
				adv.setAdv_end(rs.getTimestamp("adv_end"));
				adv.setAdv_review(rs.getString("adv_review"));
				adv.setAdv_review_date(rs.getTimestamp("adv_review_date"));
				adv.setAdv_status(rs.getString("adv_status"));
				adv.setAdv_picture(rs.getBytes("adv_picture"));
				adv.setAdv_title(rs.getString("adv_title"));
				advList.add(adv);
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
		return advList;
	}
	
	
	
	@Override
	public List<AdvertiseVO> getDesc() {
		List<AdvertiseVO> advList = new ArrayList<AdvertiseVO>();
		AdvertiseVO adv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DESC);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adv=new AdvertiseVO();
				adv.setAdv_no(rs.getString("adv_no"));
				adv.setFir_no(rs.getString("fir_no"));
				adv.setAdv_propose_date(rs.getTimestamp("adv_propose_date"));
				adv.setAdv_start(rs.getTimestamp("adv_start"));
				adv.setAdv_end(rs.getTimestamp("adv_end"));
				adv.setAdv_review(rs.getString("adv_review"));
				adv.setAdv_review_date(rs.getTimestamp("adv_review_date"));
				adv.setAdv_status(rs.getString("adv_status"));
				adv.setAdv_picture(rs.getBytes("adv_picture"));
				adv.setAdv_title(rs.getString("adv_title"));
				advList.add(adv);
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
		return advList;
	}
	
	
	@Override
	public List<AdvertiseVO> getAll(Map<String, String[]> map) {
		List<AdvertiseVO> list = new ArrayList<AdvertiseVO>();
		AdvertiseVO advVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from advertise "
		          + CompositeQuery_Adv.get_WhereCondition(map)
		          + "order by adv_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				advVO=new AdvertiseVO();
				advVO.setAdv_no(rs.getString("adv_no"));
				advVO.setFir_no(rs.getString("fir_no"));
				advVO.setAdv_propose_date(rs.getTimestamp("adv_propose_date"));
				advVO.setAdv_start(rs.getTimestamp("adv_start"));
				advVO.setAdv_end(rs.getTimestamp("adv_end"));
				advVO.setAdv_review(rs.getString("adv_review"));
				advVO.setAdv_review_date(rs.getTimestamp("adv_review_date"));
				advVO.setAdv_status(rs.getString("adv_status"));
				advVO.setAdv_picture(rs.getBytes("adv_picture"));
				advVO.setAdv_title(rs.getString("adv_title"));
				list.add(advVO);
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
		return list;
	}
	
}
