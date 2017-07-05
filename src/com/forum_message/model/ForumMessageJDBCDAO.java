package com.forum_message.model;

import java.sql.*;
import java.util.*;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class ForumMessageJDBCDAO implements ForumMessageDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "aa107g3";
	String password = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO forum_message VALUES('FM'||LPAD(to_char(forum_message_seq.NEXTVAL),8,'0'), ?, ?, ?,sysdate)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_message ORDER BY fm_no";

	private static final String GET_ONE_STMT = "SELECT * FROM forum_message WHERE fm_no = ?";

	private static final String DELETE_STMT = "DELETE FROM forum_message WHERE fm_no = ?";
	private static final String UPDATE = "UPDATE forum_message SET fa_no=?, mem_no=?, fm_content=?,fm_publish_date=? WHERE fm_no = ?";
	private static final String GET_FA_NO = "SELECT * FROM forum_message WHERE fa_no = ?";

	@Override
	public void insert(ForumMessageVO forummessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forummessageVO.getFa_no());
			pstmt.setString(2, forummessageVO.getMem_no());
			pstmt.setString(3, forummessageVO.getFm_content());

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
	
	
	
	public void insertWithPoint(ForumMessageVO forummessageVO,PointRecordVO pointRecordVO,MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			
			pstmt.setString(1, forummessageVO.getFa_no());
			pstmt.setString(2, forummessageVO.getMem_no());
			pstmt.setString(3, forummessageVO.getFm_content());
		

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
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());	
			
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
	public void update(ForumMessageVO forummessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forummessageVO.getFa_no());
			pstmt.setString(2, forummessageVO.getMem_no());
			pstmt.setString(3, forummessageVO.getFm_content());
			pstmt.setTimestamp(4, forummessageVO.getFm_publish_date());
			pstmt.setString(5, forummessageVO.getFm_no());

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
	public void delete(String fm_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fm_no);

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
	public ForumMessageVO findByPK(String fm_no) {
		ForumMessageVO fm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fm = new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));

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

		return fm;
	}

	@Override
	public List<ForumMessageVO> getAllFm(String fa_no) {
		List<ForumMessageVO> fmList = new ArrayList<>();
		ForumMessageVO fm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_FA_NO);
			pstmt.setString(1, fa_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fm = new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));
				fmList.add(fm);

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

		return fmList;
	}

	@Override
	public List<ForumMessageVO> getAll() {
		List<ForumMessageVO> fmList = new ArrayList<>();
		ForumMessageVO fm = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fm = new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));

				fmList.add(fm);
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
		return fmList;
	}

	public static void main(String[] args) {
		ForumMessageJDBCDAO dao = new ForumMessageJDBCDAO();

		// 新增
		// ForumMessageVO fm = new ForumMessageVO();
		//
		//
		// fm.setFa_no("FA00000002");
		// fm.setMem_no("MEM0000002");
		// fm.setFm_content("這邊也是測試新增");
		//
		// dao.insert(fm);
		//
		//
		//
		//
		// // 修改
		// ForumMessageVO fm = new ForumMessageVO();
		// fm.setFm_no("FM00000002");
		// fm.setFa_no("FA00000002");
		// fm.setMem_no("MEM0000003");
		// fm.setFm_content("這是新增測試修改13132132131321321321321321321321321");
		// fm.setFm_publish_date(Timestamp.valueOf("2017-04-14 12:11:00"));
		////
		// dao.update(fm);
		//
		// // 刪除
		// dao.delete("MAN0000005");

		// 查詢
		// ForumMessageVO fm3 = dao.findByPK("FM00000003");
		// System.out.print(fm3.getFm_no() + ",");
		// System.out.print(fm3.getFm_no() + ",");
		// System.out.print(fm3.getMem_no() + ",");
		// System.out.println(fm3.getFm_content() + ",");
		// System.out.println(fm3.getFm_publish_date() + ",");
		// System.out.println("---------------------");

		// 查詢
		// ForumMessageVO fm3 = dao.findById("red");
		// System.out.print(fm3.getFm_no() + ",");
		// System.out.print(fm3.getFm_id() + ",");
		// System.out.print(fm3.getFm_name() + ",");
		// System.out.print(fm3.getFm_password() + ",");
		// System.out.println(fm3.getFm_email() + ",");
		// System.out.println("---------------------");

		//// // 查詢
//		List<ForumMessageVO> list = dao.getAll();
//		for (ForumMessageVO fm3 : list) {
//			System.out.print(fm3.getFm_no() + ",");
//			System.out.print(fm3.getFm_no() + ",");
//			System.out.print(fm3.getMem_no() + ",");
//			System.out.println(fm3.getFm_content() + ",");
//			System.out.println(fm3.getFm_publish_date() + ",");
//			System.out.println();
//		}
		
		
		List<ForumMessageVO> list = dao.getAllFm("FA00000002");
		for (ForumMessageVO fm3 : list) {
			System.out.print(fm3.getFm_no() + ",");
			System.out.print(fm3.getFa_no() + ",");
			System.out.print(fm3.getMem_no() + ",");
			System.out.println(fm3.getFm_content() + ",");
			System.out.println(fm3.getFm_publish_date() + ",");
			System.out.println();
		}
	}
	
}
