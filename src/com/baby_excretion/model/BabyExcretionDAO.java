package com.baby_excretion.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.baby_eating.model.jdbcUtil_CompositeQuery_BE;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class BabyExcretionDAO implements BabyExcretionDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO BABY_EXCRETION(bex_no,bd_no,bex_date,bex_details) VALUES ('BEX'||LPAD(to_char(baby_excretion_seq.NEXTVAL),7,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION order BY bex_date DESC";
	private static final String GET_ONE_STMT = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION where bex_no = ?";
	private static final String GET_ONE_BD = "SELECT bex_no,bd_no,bex_date,bex_details FROM BABY_EXCRETION where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_EXCRETION where bex_no = ?";
	private static final String UPDATE = "UPDATE BABY_EXCRETION set bd_no=?, bex_date=?,bex_details=? where bex_no = ?";

	@Override
	public void insert(BabyExcretionVO babyExcretionVO, PointRecordVO pointRecordVO, MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babyExcretionVO.getBd_no());
			pstmt.setTimestamp(2, babyExcretionVO.getBex_date());
			pstmt.setString(3, babyExcretionVO.getBex_details());
			pstmt.executeUpdate();

			// Handle any SQL errors
			
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);

			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);
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
	public void update(BabyExcretionVO babyExcretionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyExcretionVO.getBd_no());
			pstmt.setTimestamp(2, babyExcretionVO.getBex_date());
			pstmt.setString(3, babyExcretionVO.getBex_details());
			pstmt.setString(4, babyExcretionVO.getBex_no());

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
	public void delete(String bex_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bex_no);

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
	public BabyExcretionVO findByPrimaryKey(String bex_no) {

		BabyExcretionVO babyExcretionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bex_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));

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
		return babyExcretionVO;
	}
	
	
	
	@Override
	public List<BabyExcretionVO> getOneBD(String bd_no) {
		
		List<BabyExcretionVO> list = new ArrayList<BabyExcretionVO>();
		BabyExcretionVO babyExcretionVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BD);	
			
			pstmt.setString(1, bd_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));
				list.add(babyExcretionVO); // Store the row in the list
				
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
	public List<BabyExcretionVO> getAll() {
		List<BabyExcretionVO> list = new ArrayList<BabyExcretionVO>();
		BabyExcretionVO babyExcretionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyExcretionVO = new BabyExcretionVO();
				babyExcretionVO.setBex_no(rs.getString("bex_no"));
				babyExcretionVO.setBd_no(rs.getString("bd_no"));
				babyExcretionVO.setBex_date(rs.getTimestamp("bex_date"));
				babyExcretionVO.setBex_details(rs.getString("bex_details"));
				list.add(babyExcretionVO); // Store the row in the list

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
	
	public List<BabyExcretionVO> getAll(Map<String, String[]> map) {
		List<BabyExcretionVO> list = new ArrayList<BabyExcretionVO>();
		BabyExcretionVO BEXVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Baby_Excretion "
		          + jdbcUtil_CompositeQuery_BE.get_WhereCondition(map)
		          +"order BY bex_date DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			System.out.println("DAO1");
			while (rs.next()) {
				BEXVO = new BabyExcretionVO();
				BEXVO.setBex_no(rs.getString("bex_no"));
				BEXVO.setBd_no(rs.getString("bd_no"));
				BEXVO.setBex_date(rs.getTimestamp("bex_date"));
				BEXVO.setBex_details(rs.getString("bex_details"));
				list.add(BEXVO); // Store the row in the List
				System.out.println("DAO2");
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
		System.out.println("DAO3");
		return list;
	}

}
