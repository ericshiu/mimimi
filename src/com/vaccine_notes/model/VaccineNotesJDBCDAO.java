package com.vaccine_notes.model;

import java.util.*;

import com.member.model.MemberVO;

import com.point_record.model.PointRecordVO;

import java.sql.*;

public class VaccineNotesJDBCDAO implements VaccineNotesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO VACCINE_NOTES(vn_no,bd_no,vc_no,vn_date) VALUES ('VN'||LPAD(to_char(vaccine_notes_seq.NEXTVAL),8,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES order by vn_no";
	private static final String GET_ONE_STMT = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES where vn_no = ?";
	private static final String GET_ONE_BD = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES where bd_no = ?";
	private static final String DELETE = "DELETE FROM VACCINE_NOTES where vn_no = ?";
	private static final String UPDATE = "UPDATE VACCINE_NOTES set bd_no=?,vc_no=?,vn_date=? where vn_no = ?";

//	@Override
//	public void insert(VaccineNotesVO vaccineNotesVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, vaccineNotesVO.getBd_no());
//			pstmt.setString(2, vaccineNotesVO.getVc_no());
//			pstmt.setDate(3, vaccineNotesVO.getVn_date());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	}

	@Override
	public void update(VaccineNotesVO vaccineNotesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, vaccineNotesVO.getBd_no());
			pstmt.setString(2, vaccineNotesVO.getVc_no());
			pstmt.setDate(3, vaccineNotesVO.getVn_date());
			pstmt.setString(4, vaccineNotesVO.getVn_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String vn_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, vn_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public VaccineNotesVO findByPrimaryKey(String vn_no) {

		VaccineNotesVO vaccineNotesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, vn_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				vaccineNotesVO = new VaccineNotesVO();
				vaccineNotesVO.setVn_no(rs.getString("vn_no"));
				vaccineNotesVO.setBd_no(rs.getString("bd_no"));
				vaccineNotesVO.setVc_no(rs.getString("vc_no"));
				vaccineNotesVO.setVn_date(rs.getDate("vn_date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return vaccineNotesVO;
	}

	
	@Override
	public List<VaccineNotesVO> getOneBD(String bd_no) {
		
		List<VaccineNotesVO> list = new ArrayList<VaccineNotesVO>();
		VaccineNotesVO vaccineNotesVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BD);	
			
			pstmt.setString(1, bd_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vaccineNotesVO = new VaccineNotesVO();
				vaccineNotesVO.setVn_no(rs.getString("vn_no"));
				vaccineNotesVO.setBd_no(rs.getString("bd_no"));
				vaccineNotesVO.setVc_no(rs.getString("vc_no"));
				vaccineNotesVO.setVn_date(rs.getDate("vn_date"));

				list.add(vaccineNotesVO); // Store the row in the list

			}
			// Handle any driver errors
					} catch (ClassNotFoundException e) {
						throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
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
		return list;
	}
	
	@Override
	public List<VaccineNotesVO> getAll() {
		List<VaccineNotesVO> list = new ArrayList<VaccineNotesVO>();
		VaccineNotesVO vaccineNotesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				vaccineNotesVO = new VaccineNotesVO();
				vaccineNotesVO.setVn_no(rs.getString("vn_no"));
				vaccineNotesVO.setBd_no(rs.getString("bd_no"));
				vaccineNotesVO.setVc_no(rs.getString("vc_no"));
				vaccineNotesVO.setVn_date(rs.getDate("vn_date"));

				list.add(vaccineNotesVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return list;
	}

	public static void main(String[] args) {

		VaccineNotesJDBCDAO dao = new VaccineNotesJDBCDAO();

		// 新增
//		VaccineNotesVO vaccineNotesVO1 = new VaccineNotesVO();
//		vaccineNotesVO1.setBd_no("BD00000001");
//		vaccineNotesVO1.setVc_no("VC00000001");
//		vaccineNotesVO1.setVn_date(java.sql.Date.valueOf("2017-4-10"));
//		dao.insert(vaccineNotesVO1);

//		// 修改
//		VaccineNotesVO vaccineNotesVO2 = new VaccineNotesVO();
//		vaccineNotesVO2.setBd_no("BD00000001");
//		vaccineNotesVO2.setVc_no("VC00000001");
//		vaccineNotesVO2.setVn_date(java.sql.Date.valueOf("2017-4-01"));
//		vaccineNotesVO2.setVn_no("VN00000002");
//		dao.update(vaccineNotesVO2);
//
//		// 刪除
//		dao.delete("VN00000005");

//		List<VaccineNotesVO> listbd = dao.getOneBD("BD00000001");
//			for (VaccineNotesVO vaccineNotesVO : listbd) {
//				System.out.print(vaccineNotesVO.getVn_no() + ",");
//				System.out.print(vaccineNotesVO.getBd_no() + ",");
//				System.out.print(vaccineNotesVO.getVc_no() + ",");
//				System.out.print(vaccineNotesVO.getVn_date() + ",");
//				System.out.println();
//		}
		
		
//		VaccineNotesVO vaccineNotesVO3 = dao.findByPrimaryKey("VN00000001");
//		System.out.print(vaccineNotesVO3.getVn_no() + ",");
//		System.out.print(vaccineNotesVO3.getBd_no() + ",");
//		System.out.print(vaccineNotesVO3.getVc_no() + ",");
//		System.out.print(vaccineNotesVO3.getVn_date() + ",");
//		System.out.println();
//
//		List<VaccineNotesVO> list = dao.getAll();
//		for (VaccineNotesVO vaccineNotesVO : list) {
//			System.out.print(vaccineNotesVO.getVn_no() + ",");
//			System.out.print(vaccineNotesVO.getBd_no() + ",");
//			System.out.print(vaccineNotesVO.getVc_no() + ",");
//			System.out.print(vaccineNotesVO.getVn_date() + ",");
//			System.out.println();
//
//		}
	}

	@Override
	public void insert(VaccineNotesVO vaccineNotesVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}
}