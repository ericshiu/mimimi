package com.baby_eating.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.forum_report.model.jdbcUtil_CompositeQuery_ForumReport2;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;



public class BabyEatingDAO implements BabyEatingDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO BABY_EATING(be_no,bd_no,be_date,be_sort,be_mete) VALUES ('BE'||LPAD(to_char(baby_eating_seq.NEXTVAL),8,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING ORDER BY be_date DESC";
	private static final String GET_ONE_STMT = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING where be_no = ?";
	private static final String GET_ONE_BDD = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING where bd_no = ?";
	private static final String GET_ONE_BD = "SELECT be_no,bd_no,be_date,be_sort,be_mete FROM BABY_EATING where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_EATING where be_no = ?";
	private static final String UPDATE = "UPDATE BABY_EATING set bd_no=?, be_date=?,be_sort=?,be_mete=? where be_no = ?";

	@Override
	public void insert(BabyEatingVO babyEatingVO, PointRecordVO pointRecordVO, MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babyEatingVO.getBd_no());
			pstmt.setTimestamp(2, babyEatingVO.getBe_date());
			pstmt.setString(3, babyEatingVO.getBe_sort());
			pstmt.setInt(4, babyEatingVO.getBe_mete());

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
	public void update(BabyEatingVO babyEatingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyEatingVO.getBd_no());
			pstmt.setTimestamp(2, babyEatingVO.getBe_date());
			pstmt.setString(3, babyEatingVO.getBe_sort());
			pstmt.setInt(4, babyEatingVO.getBe_mete());
			pstmt.setString(5, babyEatingVO.getBe_no());

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
	public void delete(String be_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, be_no);

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
	public BabyEatingVO findByPrimaryKey(String be_no) {

		BabyEatingVO babyEatingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, be_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));

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
		return babyEatingVO;
	}
	
	
	@Override
	public BabyEatingVO findByBD(String bd_no) {

		BabyEatingVO babyEatingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BDD);

			pstmt.setString(1, bd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));

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
		return babyEatingVO;
	}
	
	
	
	@Override
	public List<BabyEatingVO> getOneBD(String bd_no) {
		
		List<BabyEatingVO> list = new ArrayList<BabyEatingVO>();
		BabyEatingVO babyEatingVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BD);	
			
			pstmt.setString(1, bd_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));
				list.add(babyEatingVO); // Store the row in the list
				
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
	public List<BabyEatingVO> getAll() {
		List<BabyEatingVO> list = new ArrayList<BabyEatingVO>();
		BabyEatingVO babyEatingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyEatingVO = new BabyEatingVO();
				babyEatingVO.setBe_no(rs.getString("be_no"));
				babyEatingVO.setBd_no(rs.getString("bd_no"));
				babyEatingVO.setBe_date(rs.getTimestamp("be_date"));
				babyEatingVO.setBe_sort(rs.getString("be_sort"));
				babyEatingVO.setBe_mete(rs.getInt("be_mete"));
				list.add(babyEatingVO); // Store the row in the list

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
	public List<BabyEatingVO> getAll(Map<String, String[]> map) {
		List<BabyEatingVO> list = new ArrayList<BabyEatingVO>();
		BabyEatingVO BEVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from BABY_EATING "
		          + jdbcUtil_CompositeQuery_BE.get_WhereCondition(map)
		          +"ORDER BY be_date DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			System.out.println("DAO1");
			while (rs.next()) {
				BEVO = new BabyEatingVO();
				BEVO.setBe_no(rs.getString("be_no"));
				BEVO.setBd_no(rs.getString("bd_no"));
				BEVO.setBe_date(rs.getTimestamp("be_date"));
				BEVO.setBe_sort(rs.getString("be_sort"));
				BEVO.setBe_mete(rs.getInt("be_mete"));
				list.add(BEVO); // Store the row in the List
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



