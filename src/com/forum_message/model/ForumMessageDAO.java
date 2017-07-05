package com.forum_message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class ForumMessageDAO  implements ForumMessageDAO_interface{

	

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setString(1, forummessageVO.getFa_no());
			pstmt.setString(2, forummessageVO.getMem_no());
			pstmt.setString(3, forummessageVO.getFm_content());
		

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forummessageVO.getFa_no());
			pstmt.setString(2, forummessageVO.getMem_no());
			pstmt.setString(3, forummessageVO.getFm_content());
			pstmt.setTimestamp(4, forummessageVO.getFm_publish_date());
			pstmt.setString(5, forummessageVO.getFm_no());
		
			pstmt.executeUpdate();
		
	
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fm_no);
			
			pstmt.executeUpdate();

	
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fm=new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));
				
			
			}

	
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

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_FA_NO);
			pstmt.setString(1, fa_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fm=new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));
				fmList.add(fm);
			
			}

		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
	
				fm=new ForumMessageVO();
				fm.setFm_no(rs.getString("fm_no"));
				fm.setMem_no(rs.getString("mem_no"));
				fm.setFa_no(rs.getString("fa_no"));
				fm.setFm_content(rs.getString("fm_content"));
				fm.setFm_publish_date(rs.getTimestamp("fm_publish_date"));
				
				fmList.add(fm);
			}

	
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
}
