package com.forum_report.model;

import java.sql.*;
import java.util.*;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class ForumReportJDBCDAO implements ForumReportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "aa107g3";
	String password = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO forum_report VALUES('FR'||LPAD(to_char(FORUM_REPORT_seq.NEXTVAL),8,'0'), ?, ?,?, ?, ?,sysdate,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_report ORDER BY fr_no";

	private static final String GET_ONE_STMT = "SELECT * FROM forum_report WHERE fr_no = ?";

	private static final String DELETE_STMT = "DELETE FROM foru_report WHERE fr_no = ?";
	private static final String UPDATE = "UPDATE forum_report SET mem_no=?, fr_am_no=?, "
			+ " fr_type=?,fr_title=?,fr_content=?,fr_publish_date=?,  fr_status=?, fr_review_date=? WHERE fr_no = ?";
	private static final String GET_DESC = "SELECT * FROM forum_report ORDER BY fr_no desc";
	private static final String GET_ALL_STATUS = "SELECT * FROM forum_report where fr_status=? ORDER BY fr_no";
	@Override
	public void insert(ForumReportVO forumreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumreportVO.getMem_no());
			pstmt.setString(2, forumreportVO.getFr_am_no());
			pstmt.setString(3, forumreportVO.getFr_type());
			pstmt.setString(4, forumreportVO.getFr_title());
			pstmt.setString(5, forumreportVO.getFr_content());
			// pstmt.setTimestamp(5, forumreportVO.getFr_publish_date());
			pstmt.setString(6, forumreportVO.getFr_status());
			pstmt.setTimestamp(7, forumreportVO.getFr_review_date());

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
	public void update(ForumReportVO forumreportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumreportVO.getMem_no());
			pstmt.setString(2, forumreportVO.getFr_am_no());
			pstmt.setString(3, forumreportVO.getFr_type());
			pstmt.setString(4, forumreportVO.getFr_title());
			pstmt.setString(5, forumreportVO.getFr_content());
			pstmt.setTimestamp(6, forumreportVO.getFr_publish_date());
			pstmt.setString(7, forumreportVO.getFr_status());
			pstmt.setTimestamp(8, forumreportVO.getFr_review_date());
			pstmt.setString(9, forumreportVO.getFr_no());

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
	public void updateWithPoint(ForumReportVO forumreportVO,PointRecordVO pointrecordVO,MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
						
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumreportVO.getMem_no());
			pstmt.setString(2, forumreportVO.getFr_am_no());
			pstmt.setString(3, forumreportVO.getFr_type());
			pstmt.setString(4, forumreportVO.getFr_title());
			pstmt.setString(5, forumreportVO.getFr_content());
			pstmt.setTimestamp(6, forumreportVO.getFr_publish_date());
			pstmt.setString(7, forumreportVO.getFr_status());
			pstmt.setTimestamp(8, forumreportVO.getFr_review_date());
			pstmt.setString(9, forumreportVO.getFr_no());

			pstmt.executeUpdate();

	
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointrecordVO, con);

			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());	
			
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
	public void delete(String fr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fr_no);

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
	public ForumReportVO findByPK(String fr_no) {
		ForumReportVO fr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fr = new ForumReportVO();
				fr.setFr_no(rs.getString("fr_no"));
				fr.setMem_no(rs.getString("mem_no"));
				fr.setFr_am_no(rs.getString("fr_am_no"));
				fr.setFr_type(rs.getString("fr_type"));
				fr.setFr_title(rs.getString("fr_title"));
				fr.setFr_content(rs.getString("fr_content"));
				fr.setFr_publish_date(rs.getTimestamp("fr_publish_date"));
				fr.setFr_status(rs.getString("fr_status"));
				fr.setFr_review_date(rs.getTimestamp("fr_review_date"));

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

		return fr;
	}

	@Override
	public List<ForumReportVO> getAll() {
		List<ForumReportVO> frList = new ArrayList<>();
		ForumReportVO fr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fr = new ForumReportVO();
				fr.setFr_no(rs.getString("fr_no"));
				fr.setMem_no(rs.getString("mem_no"));
				fr.setFr_am_no(rs.getString("fr_am_no"));
				fr.setFr_type(rs.getString("fr_type"));
				fr.setFr_title(rs.getString("fr_title"));
				fr.setFr_content(rs.getString("fr_content"));
				fr.setFr_publish_date(rs.getTimestamp("fr_publish_date"));
				fr.setFr_status(rs.getString("fr_status"));
				fr.setFr_review_date(rs.getTimestamp("fr_review_date"));
				frList.add(fr);
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
		return frList;
	}

	
	
	@Override
	public List<ForumReportVO> getAllStatus(String fr_status) {
		List<ForumReportVO> frList = new ArrayList<>();
		ForumReportVO fr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STATUS);
			pstmt.setString(1, fr_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fr = new ForumReportVO();
				fr.setFr_no(rs.getString("fr_no"));
				fr.setMem_no(rs.getString("mem_no"));
				fr.setFr_am_no(rs.getString("fr_am_no"));
				fr.setFr_type(rs.getString("fr_type"));
				fr.setFr_title(rs.getString("fr_title"));
				fr.setFr_content(rs.getString("fr_content"));
				fr.setFr_publish_date(rs.getTimestamp("fr_publish_date"));
				fr.setFr_status(rs.getString("fr_status"));
				fr.setFr_review_date(rs.getTimestamp("fr_review_date"));
				frList.add(fr);
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
		return frList;
	}
	
	
	@Override
	public List<ForumReportVO> getDesc() {
		List<ForumReportVO> frList = new ArrayList<>();
		ForumReportVO fr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_DESC);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fr = new ForumReportVO();
				fr.setFr_no(rs.getString("fr_no"));
				fr.setMem_no(rs.getString("mem_no"));
				fr.setFr_am_no(rs.getString("fr_am_no"));
				fr.setFr_type(rs.getString("fr_type"));
				fr.setFr_title(rs.getString("fr_title"));
				fr.setFr_content(rs.getString("fr_content"));
				fr.setFr_publish_date(rs.getTimestamp("fr_publish_date"));
				fr.setFr_status(rs.getString("fr_status"));
				fr.setFr_review_date(rs.getTimestamp("fr_review_date"));
				frList.add(fr);
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
		return frList;
	}
	
	@Override
	public List<ForumReportVO> getAll(Map<String, String[]> map) {
		List<ForumReportVO> list = new ArrayList<ForumReportVO>();
		ForumReportVO FRVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			String finalSQL = "select * from forum_report "
		          + jdbcUtil_CompositeQuery_ForumReport2.get_WhereCondition(map)
		          + "order by fr_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				FRVO = new ForumReportVO();
				FRVO.setFr_no(rs.getString("fr_no"));
				FRVO.setMem_no(rs.getString("mem_no"));
				FRVO.setFr_am_no(rs.getString("fr_am_no"));
				FRVO.setFr_type(rs.getString("fr_type"));
				FRVO.setFr_title(rs.getString("fr_title"));
				FRVO.setFr_content(rs.getString("fr_content"));
				FRVO.setFr_publish_date(rs.getTimestamp("fr_publish_date"));
				FRVO.setFr_status(rs.getString("fr_status"));
				FRVO.setFr_review_date(rs.getTimestamp("fr_review_date"));
				list.add(FRVO); // Store the row in the List
			}
	
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


	public static void main(String[] args) {
		ForumReportJDBCDAO dao = new ForumReportJDBCDAO();

		// 新增
//		 ForumReportVO fr = new ForumReportVO();
//		 fr.setMem_no("MEM0000003");
//		 fr.setFr_am_no("FA00000002");
//		 fr.setFr_type("1");
//		 fr.setFr_title("測是新增");
//		 fr.setFr_content("這是新增測試");
//		 fr.setFr_status("0");
//		 fr.setFr_review_date(null);
//		 dao.insert(fr);
		//
		//
		//
		//
		// // 修改
//		 ForumReportVO fr = new ForumReportVO();
//		 fr.setFr_no("FR00000002");
//		 fr.setMem_no("MEM0000002");
//		 fr.setFr_am_no("FM00000002");
//		 fr.setFr_type("1");
//		 fr.setFr_title("修改");
//		 fr.setFr_content("這是新增測試修改13132132131321321321321321321321321");
//		 fr.setFr_publish_date(Timestamp.valueOf("2027-07-07 07:07:00"));
//		 fr.setFr_status("0");
//		 fr.setFr_review_date(Timestamp.valueOf("2017-04-14 12:11:00"));
//		////
//		 dao.update(fr);
		//
		// // 刪除
		// dao.delete("MAN0000005");

		// 查詢
		ForumReportVO fr3 = dao.findByPK("FR00000003");
		System.out.print(fr3.getFr_no() + ",");
		System.out.print(fr3.getMem_no() + ",");
		System.out.print(fr3.getFr_am_no() + ",");
		System.out.print(fr3.getFr_type() + ",");
		System.out.println(fr3.getFr_title() + ",");
		System.out.println(fr3.getFr_content() + ",");
		System.out.println(fr3.getFr_publish_date() + ",");
		System.out.println(fr3.getFr_status() + ",");
		System.out.println(fr3.getFr_review_date() + ",");
		System.out.println("---------------------");

		// 查詢
		// ForumReportVO fr3 = dao.findById("red");
		// System.out.print(fr3.getFr_no() + ",");
		// System.out.print(fr3.getFr_id() + ",");
		// System.out.print(fr3.getFr_name() + ",");
		// System.out.print(fr3.getFr_password() + ",");
		// System.out.println(fr3.getFr_email() + ",");
		// System.out.println("---------------------");

		//// // 查詢
//		 List<ForumReportVO> list = dao.getAll();
//		 for (ForumReportVO fr : list) {
//		 System.out.print(fr.getFr_no() + ",");
//		 System.out.print(fr.getMem_no() + ",");
//		 System.out.print(fr.getFr_am_no() + ",");
//		 System.out.print(fr.getFr_type() + ",");
//		 System.out.println(fr.getFr_title() + ",");
//		 System.out.println(fr.getFr_content() + ",");
//		 System.out.println(fr.getFr_publish_date() + ",");
//		 System.out.println(fr.getFr_status() + ",");
//		 System.out.println(fr.getFr_review_date() + ",");
//		 System.out.println();
//		 }
		//

		// 查詢
//		List<ForumReportVO> list = dao.getDesc();
//		for (ForumReportVO fr : list) {
//			System.out.print(fr.getFr_no() + ",");
//			System.out.print(fr.getMem_no() + ",");
//			System.out.print(fr.getFr_am_no() + ",");
//			System.out.print(fr.getFr_type() + ",");
//			System.out.println(fr.getFr_title() + ",");
//			System.out.println(fr.getFr_content() + ",");
//			System.out.println(fr.getFr_publish_date() + ",");
//			System.out.println(fr.getFr_status() + ",");
//			System.out.println(fr.getFr_review_date() + ",");
//			System.out.println();
//		}
		
		
		
//		List<ForumReportVO> list = dao.getAllStatus("2");
//		for (ForumReportVO fr : list) {
//			System.out.print(fr.getFr_no() + ",");
//			System.out.print(fr.getMem_no() + ",");
//			System.out.print(fr.getFr_am_no() + ",");
//			System.out.print(fr.getFr_type() + ",");
//			System.out.println(fr.getFr_title() + ",");
//			System.out.println(fr.getFr_content() + ",");
//			System.out.println(fr.getFr_publish_date() + ",");
//			System.out.println(fr.getFr_status() + ",");
//			System.out.println(fr.getFr_review_date() + ",");
//			System.out.println();
//		}
	}
}
