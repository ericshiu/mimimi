package com.advertise.model;

import java.io.IOException;
import java.nio.file.*;

import java.sql.*;
import java.util.*;


public class AdvertiseJDBCDAO implements AdvertiseDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "aa107g3";
	String password = "aa107g3";
																		
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

	
			pstmt.setString(1, advertise.getFir_no());
			pstmt.setTimestamp(2, advertise.getAdv_propose_date());
//			pstmt.setTimestamp(3, advertise.getAdv_start());
			pstmt.setTimestamp(3, advertise.getAdv_end());
			pstmt.setString(4, advertise.getAdv_review());
			pstmt.setTimestamp(5, advertise.getAdv_review_date());
			pstmt.setString(6, advertise.getAdv_status());
			pstmt.setBytes(7, advertise.getAdv_picture());
			pstmt.setString(8, advertise.getAdv_title());

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
	public void update(AdvertiseVO advertise) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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
	public void delete(String adv_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adv_no);
			
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
	public AdvertiseVO findByPK(String adv_no) {
		AdvertiseVO adv = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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
				advVO.setAdv_propose_date(rs.getTimestamp("adv_proposadvVOate"));
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
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
	
	public static void main(String[] args) throws IOException{

		AdvertiseJDBCDAO dao = new AdvertiseJDBCDAO();

		// 新增
//		AdvertiseVO adv1 = new AdvertiseVO();
//		adv1.setAdv_no("ADV0000001");
//		adv1.setFir_no("FIR0000001");
////		adv1.setAdv_propose_date(sysdate);
//		adv1.setAdv_start(null);
//		adv1.setAdv_end(null);
//		adv1.setAdv_review("1");
//		adv1.setAdv_review_date(null);
//		adv1.setAdv_status("1");
//		Path path=Paths.get("src/image/elephant.jpg");
//		byte[]data=Files.readAllBytes(path);
//		adv1.setAdv_picture(data);
//		adv1.setAdv_title("『大』周年慶，買一箱送一箱。");
//		dao.insert(adv1);
		
		
		
		// 新增
//		
//AdvertiseVO adv1 = new AdvertiseVO();
////		adv1.setAdv_no("ADV0000001");
//		adv1.setFir_no("FIR0000001");
//		adv1.setAdv_propose_date(Timestamp.valueOf("2017-04-14 11:11:00"));
//		adv1.setAdv_start(null);
//		adv1.setAdv_end(null);
//		adv1.setAdv_review("1");
//		adv1.setAdv_review_date(null);
//		adv1.setAdv_status("1");
//		Path path=Paths.get("src/image/elephant.jpg");
//		byte[]data=Files.readAllBytes(path);
//		adv1.setAdv_picture(data);
//		adv1.setAdv_title("『大』周年慶，買一箱送一箱。");
//		dao.insert(adv1);
		// 修改
//		AdvertiseVO adv2 = new AdvertiseVO();
//		adv2.setAdv_no("ADV0000002");
//		adv2.setFir_no("FIR0000001");
//		adv2.setAdv_propose_date(Timestamp.valueOf("2000-04-15"));
//		adv2.setAdv_start(Timestamp.valueOf("2000-04-16"));
//		adv2.setAdv_end(Timestamp.valueOf("2000-04-29"));
//		adv2.setAdv_review("1");
//		adv2.setAdv_review_date(Timestamp.valueOf("2000-04-17"));
//		adv2.setAdv_status("1");
//		Path path=Paths.get("src/image/swan.jpg");
//		byte[] data=Files.readAllBytes(path);
//		adv2.setAdv_picture(data);
//		adv2.setAdv_title("『大修正幫』周年慶，買一箱送一箱。");
//		dao.update(adv2);
//
//		// 刪除
//		dao.delete("ADV0000001");
//
//		// 查詢
//		AdvertiseVO adv3 = dao.findByPK("ADV0000001");
//		System.out.print(adv3.getAdv_no() + ",");
//		System.out.print(adv3.getFir_no() + ",");
//		System.out.print(adv3.getAdv_propose_date() + ",");
//		System.out.print(adv3.getAdv_start() + ",");
//		System.out.print(adv3.getAdv_end() + ",");
//		System.out.print(adv3.getAdv_review() + ",");
//		System.out.print(adv3.getAdv_review_date() + ",");
//		System.out.print(adv3.getAdv_status() + ",");
//		System.out.print(adv3.getAdv_picture()+",");
//		System.out.print(adv3.getAdv_title() );
//		System.out.println("---------------------");
//
//		// 查詢
//		List<AdvertiseVO> list = dao.getAll();
//		for (AdvertiseVO adv : list) {
//			System.out.print(adv.getAdv_no() + ",");
//			System.out.print(adv.getFir_no() + ",");
//			System.out.print(adv.getAdv_propose_date() + ",");
//			System.out.print(adv.getAdv_start() + ",");
//			System.out.print(adv.getAdv_end() + ",");
//			System.out.print(adv.getAdv_review() + ",");
//			System.out.print(adv.getAdv_review_date() + ",");
//			System.out.print(adv.getAdv_status() + ",");
//			System.out.print(adv.getAdv_picture()+",");
//			System.out.print(adv.getAdv_title() + ",");
//			System.out.println("");
//		}
		
		
		// 查詢DESC
//				List<AdvertiseVO> list = dao.getDesc();
//				for (AdvertiseVO adv : list) {
//					System.out.print(adv.getAdv_no() + ",");
//					System.out.print(adv.getFir_no() + ",");
//					System.out.print(adv.getAdv_propose_date() + ",");
//					System.out.print(adv.getAdv_start() + ",");
//					System.out.print(adv.getAdv_end() + ",");
//					System.out.print(adv.getAdv_review() + ",");
//					System.out.print(adv.getAdv_review_date() + ",");
//					System.out.print(adv.getAdv_status() + ",");
//					System.out.print(adv.getAdv_picture()+",");
//					System.out.print(adv.getAdv_title() + ",");
//					System.out.println("");
//				}
		
//		List<AdvertiseVO> list = dao.getStatus("2");
//		for (AdvertiseVO adv : list) {
//			System.out.print(adv.getAdv_no() + ",");
//			System.out.print(adv.getFir_no() + ",");
//			System.out.print(adv.getAdv_propose_date() + ",");
//			System.out.print(adv.getAdv_start() + ",");
//			System.out.print(adv.getAdv_end() + ",");
//			System.out.print(adv.getAdv_review() + ",");
//			System.out.print(adv.getAdv_review_date() + ",");
//			System.out.print(adv.getAdv_status() + ",");
//			System.out.print(adv.getAdv_picture()+",");
//			System.out.print(adv.getAdv_title() + ",");
//			System.out.println("");
//		}
	}
}
