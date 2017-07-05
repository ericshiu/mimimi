package com.vaccine_notes.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;

import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class VaccineNotesDAO implements VaccineNotesDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO VACCINE_NOTES(vn_no,bd_no,vc_no,vn_date) VALUES ('VN'||LPAD(to_char(vaccine_notes_seq.NEXTVAL),8,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES order by vn_date DESC";
	private static final String GET_ONE_STMT = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES where vn_no = ?";
	private static final String GET_ONE_BD = "SELECT vn_no,bd_no,vc_no,vn_date FROM VACCINE_NOTES where bd_no = ?";
	private static final String DELETE = "DELETE FROM VACCINE_NOTES where vn_no = ?";
	private static final String UPDATE = "UPDATE VACCINE_NOTES set bd_no=?,vc_no=?,vn_date=? where vn_no = ?";

	@Override
	public void insert(VaccineNotesVO vaccineNotesVO, PointRecordVO pointRecordVO, MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
						con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, vaccineNotesVO.getBd_no());
			pstmt.setString(2, vaccineNotesVO.getVc_no());
			pstmt.setDate(3, vaccineNotesVO.getVn_date());

			pstmt.executeUpdate();
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);

			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);

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
	public void update(VaccineNotesVO vaccineNotesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, vaccineNotesVO.getBd_no());
			pstmt.setString(2, vaccineNotesVO.getVc_no());
			pstmt.setDate(3, vaccineNotesVO.getVn_date());
			pstmt.setString(4, vaccineNotesVO.getVn_no());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, vn_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, vn_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vaccineNotesVO = new VaccineNotesVO();
				vaccineNotesVO.setVn_no(rs.getString("vn_no"));
				vaccineNotesVO.setBd_no(rs.getString("bd_no"));
				vaccineNotesVO.setVc_no(rs.getString("vc_no"));
				vaccineNotesVO.setVn_date(rs.getDate("vn_date"));
			}

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
			
			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vaccineNotesVO = new VaccineNotesVO();
				vaccineNotesVO.setVn_no(rs.getString("vn_no"));
				vaccineNotesVO.setBd_no(rs.getString("bd_no"));
				vaccineNotesVO.setVc_no(rs.getString("vc_no"));
				vaccineNotesVO.setVn_date(rs.getDate("vn_date"));

				list.add(vaccineNotesVO); // Store the row in the list

			}

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

}
