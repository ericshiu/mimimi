package com.baby_sleep.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.baby_eating.model.BabyEatingVO;
import com.baby_eating.model.jdbcUtil_CompositeQuery_BE;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class BabySleepDAO implements BabySleepDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO BABY_SLEEP(bs_no,bd_no,bs_start,bs_end,bs_time) VALUES ('BS'||LPAD(to_char(baby_sleep_seq.NEXTVAL),8,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP order BY bs_start DESC";
	private static final String GET_ONE_STMT = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP where bs_no = ?";
	private static final String GET_ONE_BD = "SELECT bs_no,bd_no,bs_start,bs_end,bs_time FROM BABY_SLEEP where bd_no = ?";
	private static final String DELETE = "DELETE FROM BABY_SLEEP where bs_no = ?";
	private static final String UPDATE = "UPDATE BABY_SLEEP set bd_no=?, bs_start=?,bs_end=?,bs_time=? where bs_no = ?";

	@Override
	public void insert(BabySleepVO babySleepVO, PointRecordVO pointRecordVO, MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babySleepVO.getBd_no());
			pstmt.setTimestamp(2, babySleepVO.getBs_start());
			pstmt.setTimestamp(3, babySleepVO.getBs_end());
			pstmt.setString(4, babySleepVO.getBs_time());
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
	public void update(BabySleepVO babySleepVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babySleepVO.getBd_no());
			pstmt.setTimestamp(2, babySleepVO.getBs_start());
			pstmt.setTimestamp(3, babySleepVO.getBs_end());
			pstmt.setString(4, babySleepVO.getBs_time());
			pstmt.setString(5, babySleepVO.getBs_no());

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
	public void delete(String bs_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bs_no);

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
	public BabySleepVO findByPrimaryKey(String bs_no) {

		BabySleepVO babySleepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bs_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));

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
		return babySleepVO;
	}

	@Override
	public List<BabySleepVO> getOneBD(String bd_no) {

		List<BabySleepVO> list = new ArrayList<BabySleepVO>();
		BabySleepVO babySleepVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BD);

			pstmt.setString(1, bd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));
				list.add(babySleepVO); // Store the row in the list

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
	public List<BabySleepVO> getAll() {
		List<BabySleepVO> list = new ArrayList<BabySleepVO>();
		BabySleepVO babySleepVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babySleepVO = new BabySleepVO();
				babySleepVO.setBs_no(rs.getString("bs_no"));
				babySleepVO.setBd_no(rs.getString("bd_no"));
				babySleepVO.setBs_start(rs.getTimestamp("bs_start"));
				babySleepVO.setBs_end(rs.getTimestamp("bs_end"));
				babySleepVO.setBs_time(rs.getString("bs_time"));
				list.add(babySleepVO); // Store the row in the list

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
	public List<BabySleepVO> getAll(Map<String, String[]> map) {
		List<BabySleepVO> list = new ArrayList<BabySleepVO>();
		BabySleepVO BSVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Baby_Sleep "
		          + jdbcUtil_CompositeQuery_BE.get_WhereCondition(map)
		          +"order BY bs_start DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			System.out.println("DAO1");
			while (rs.next()) {
				BSVO = new BabySleepVO();
				BSVO.setBs_no(rs.getString("bs_no"));
				BSVO.setBd_no(rs.getString("bd_no"));
				BSVO.setBs_start(rs.getTimestamp("bs_start"));
				BSVO.setBs_end(rs.getTimestamp("bs_end"));
				BSVO.setBs_time(rs.getString("bs_time"));
				list.add(BSVO); // Store the row in the List
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
