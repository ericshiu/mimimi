package com.baby_growth.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.baby_eating.model.BabyEatingVO;
import com.baby_eating.model.jdbcUtil_CompositeQuery_BE;
import com.baby_excretion.model.BabyExcretionVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class BabyGrowthDAO implements BabyGrowthDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO BABY_GROWTH(bg_no,bd_no,bg_height,bg_weight,bg_head,bg_date) VALUES ('BG'||LPAD(to_char(baby_growth_seq.NEXTVAL),8,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH order BY bg_date DESC";
	private static final String GET_ONE_STMT = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH where bg_no = ?";
	private static final String GET_ONE_BD = "SELECT bg_no,bd_no,bg_height,bg_weight,bg_head ,bg_date FROM BABY_GROWTH where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_GROWTH where bg_no = ?";
	private static final String UPDATE = "UPDATE BABY_GROWTH set bd_no=?,bg_height=?,bg_weight=?, bg_head=? ,bg_date=? where bg_no = ?";

	@Override
	public void insert(BabyGrowthVO babyGrowthVO, PointRecordVO pointRecordVO, MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babyGrowthVO.getBd_no());
			pstmt.setInt(2, babyGrowthVO.getBg_height());
			pstmt.setInt(3, babyGrowthVO.getBg_weight());
			pstmt.setInt(4, babyGrowthVO.getBg_head());
			pstmt.setDate(5, babyGrowthVO.getBg_date());

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
	public void update(BabyGrowthVO babyGrowthVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyGrowthVO.getBd_no());
			pstmt.setInt(2, babyGrowthVO.getBg_height());
			pstmt.setInt(3, babyGrowthVO.getBg_weight());
			pstmt.setInt(4, babyGrowthVO.getBg_head());
			pstmt.setDate(5, babyGrowthVO.getBg_date());
			pstmt.setString(6, babyGrowthVO.getBg_no());
System.out.println("dao");
			pstmt.executeUpdate();
			System.out.println("dao22");
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
	public void delete(String bg_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bg_no);

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
	public BabyGrowthVO findByPrimaryKey(String bg_no) {

		BabyGrowthVO babyGrowthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
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
		return babyGrowthVO;
	}

	
	@Override
	public List<BabyGrowthVO> getOneBD(String bd_no) {
		
		List<BabyGrowthVO> list = new ArrayList<BabyGrowthVO>();
		BabyGrowthVO babyGrowthVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BD);	
			
			pstmt.setString(1, bd_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
				list.add(babyGrowthVO); // Store the row in the list
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
	public List<BabyGrowthVO> getAll() {
		List<BabyGrowthVO> list = new ArrayList<BabyGrowthVO>();
		BabyGrowthVO babyGrowthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				babyGrowthVO = new BabyGrowthVO();
				babyGrowthVO.setBg_no(rs.getString("bg_no"));
				babyGrowthVO.setBd_no(rs.getString("bd_no"));
				babyGrowthVO.setBg_height(rs.getInt("bg_height"));
				babyGrowthVO.setBg_weight(rs.getInt("bg_weight"));
				babyGrowthVO.setBg_head(rs.getInt("bg_head"));
				babyGrowthVO.setBg_date(rs.getDate("bg_date"));
				list.add(babyGrowthVO); // Store the row in the list


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
	public List<BabyGrowthVO> getAll(Map<String, String[]> map) {
		List<BabyGrowthVO> list = new ArrayList<BabyGrowthVO>();
		BabyGrowthVO BGVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Baby_Growth"
		          + jdbcUtil_CompositeQuery_BE.get_WhereCondition(map)
		          +"order BY bg_date DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			System.out.println("DAO1");
			while (rs.next()) {
				BGVO = new BabyGrowthVO();
				BGVO.setBg_no(rs.getString("bg_no"));
				BGVO.setBd_no(rs.getString("bd_no"));
				BGVO.setBg_height(rs.getInt("bg_height"));
				BGVO.setBg_weight(rs.getInt("bg_weight"));
				BGVO.setBg_head(rs.getInt("bg_head"));
				BGVO.setBg_date(rs.getDate("bg_date"));
				list.add(BGVO); // Store the row in the List
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
