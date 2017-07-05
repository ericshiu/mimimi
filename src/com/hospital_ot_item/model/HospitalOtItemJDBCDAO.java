package com.hospital_ot_item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.model.HospitalJDBCDAO;
import com.hospital.model.HospitalVO;

public class HospitalOtItemJDBCDAO implements HospitalOtItemDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 
		"INSERT INTO hospital_ot_item (hos_no,ot_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT hos_no,ot_no FROM hospital_ot_item order by hos_no, ot_no";
//	private static final String GET_ONE_STMT = 
//		"SELECT hos_no,ot_no FROM hospital_ot_item where hos_no = ? and ot_no = ?";
	private static final String DELETE = 
		"DELETE FROM hospital_ot_item where hos_no = ? and ot_no = ?";
//	private static final String UPDATE = 
//		"UPDATE hospital_ot_item SET pcp_no=?, pc_no=?, pcp_picture=?, pcp_summary=? where pcp_no = ?";

	@Override
	public void insert(HospitalOtItemVO hospitalOtItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hospitalOtItemVO.getHos_no());
			pstmt.setString(2, hospitalOtItemVO.getOt_no());

		
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

//	@Override
//	public void update(HospitalOtItemVO hospitalOtItemVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, hospitalOtItemVO.getHos_no());
//			pstmt.setString(2, hospitalOtItemVO.getOt_no());
//
//			pstmt.executeUpdate();
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
//
//		
//	}

	@Override
	public void delete(String hos_no, String ot_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, hos_no);
			pstmt.setString(2, ot_no);

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

//	@Override
//	public HospitalOtItemVO findByPrimaryKey(String hos_no, String ot_no) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<HospitalOtItemVO> getAll() {
		List<HospitalOtItemVO> list = new ArrayList<HospitalOtItemVO>();
		HospitalOtItemVO hospitalOtItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				hospitalOtItemVO = new HospitalOtItemVO();
				hospitalOtItemVO.setHos_no(rs.getString("hos_no"));
				hospitalOtItemVO.setOt_no(rs.getString("ot_no"));
				list.add(hospitalOtItemVO);// Store the row in the list
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
	public void insert2(HospitalOtItemVO hospitalOtItemVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
	
	 		pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setString(1, hospitalOtItemVO.getHos_no());
			pstmt.setString(2, hospitalOtItemVO.getOt_no());
	
	
			pstmt.executeUpdate();
	
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-hos-item");
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

	public static void main(String[] args) {
		HospitalOtItemJDBCDAO dao = new HospitalOtItemJDBCDAO();
		// 新增
//		HospitalOtItemVO hospitalOtItemVO = new HospitalOtItemVO();
//		hospitalOtItemVO.setHos_no("HOS0000008");
//		hospitalOtItemVO.setOt_no("OT00000001");	
//		dao.insert(hospitalOtItemVO);	
		
		// 刪除
		dao.delete("HOS0000008","OT00000001");
		
		// 查詢
		List<HospitalOtItemVO> list = dao.getAll();
		for (HospitalOtItemVO aItem : list) {
			System.out.print(aItem.getHos_no() + ",");
			System.out.print(aItem.getOt_no() + ",");
			System.out.println();
		}		
	}

}
