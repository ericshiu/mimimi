package com.forum_article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.forum_article.model.ForumArticleVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Article;


public class ForumArticleDAO implements ForumArticleDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO forum_article VALUES('FA'||LPAD(to_char(forum_article_seq.NEXTVAL),8,'0'), ?, ?, ?,?,?,sysdate,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM forum_article ORDER BY fa_no";

	private static final String GET_ONE_STMT = "SELECT * FROM forum_article WHERE fa_no = ?";
	private static final String GET_ONE_MEM = "SELECT * FROM forum_article WHERE mem_no = ?";

	private static final String DELETE_STMT = "DELETE FROM forum_article WHERE fa_no = ?";
	private static final String UPDATE = "UPDATE forum_article SET  mem_no=?,fa_title=?,fa_content=?, "
			+ "fa_like=?, fa_dislike=?, fa_publish_date=?,fa_modify_date=? WHERE fa_no = ?";
	private static final String GET_HOT = "select * from forum_article order by fa_like desc";
	private static final String GET_DESC = "select * from forum_article order by fa_no desc";
	private static final String UPDATE_DISLIKE = "UPDATE forum_article SET fa_dislike=? WHERE fa_no = ?";
	
	@Override
	public void insert(ForumArticleVO forumarticleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumarticleVO.getMem_no());
			pstmt.setString(2, forumarticleVO.getFa_title());
			pstmt.setString(3, forumarticleVO.getFa_content());
			pstmt.setInt(4, forumarticleVO.getFa_like());
			pstmt.setInt(5, forumarticleVO.getFa_dislike());
			pstmt.setTimestamp(6, forumarticleVO.getFa_modify_date());

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
	public void insertWithPoint(ForumArticleVO forumarticleVO,PointRecordVO pointRecordVO,MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumarticleVO.getMem_no());
			pstmt.setString(2, forumarticleVO.getFa_title());
			pstmt.setString(3, forumarticleVO.getFa_content());
			pstmt.setInt(4, forumarticleVO.getFa_like());
			pstmt.setInt(5, forumarticleVO.getFa_dislike());
			pstmt.setTimestamp(6, forumarticleVO.getFa_modify_date());

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
	public void update_dislike(ForumArticleVO forumarticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_DISLIKE);
			
			pstmt.setInt(1, forumarticleVO.getFa_dislike());
			pstmt.setString(2, forumarticleVO.getFa_no());
			
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
	public void update(ForumArticleVO forumarticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumarticleVO.getMem_no());
			pstmt.setString(2, forumarticleVO.getFa_title());
			pstmt.setString(3, forumarticleVO.getFa_content());
			pstmt.setInt(4, forumarticleVO.getFa_like());
			pstmt.setInt(5, forumarticleVO.getFa_dislike());
			pstmt.setTimestamp(6, forumarticleVO.getFa_publish_date());
			pstmt.setTimestamp(7, forumarticleVO.getFa_modify_date());
			pstmt.setString(8, forumarticleVO.getFa_no());
			System.out.println("有近dao");
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
	public void delete(String fa_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fa_no);

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
	public ForumArticleVO findByPK(String fa_no) {
		ForumArticleVO fa = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fa_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fa = new ForumArticleVO();
				fa.setFa_no(rs.getString("fa_no"));
				fa.setMem_no(rs.getString("mem_no"));
				fa.setFa_title(rs.getString("fa_title"));
				fa.setFa_content(rs.getString("fa_content"));
				fa.setFa_like(rs.getInt("fa_like"));
				fa.setFa_dislike(rs.getInt("fa_dislike"));
				fa.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				fa.setFa_modify_date(rs.getTimestamp("fa_modify_date"));

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

		return fa;
	}

	
	
	@Override
	public ForumArticleVO findByPKMem(String mem_no) {
		ForumArticleVO fa = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fa = new ForumArticleVO();
				fa.setFa_no(rs.getString("fa_no"));
				fa.setMem_no(rs.getString("mem_no"));
				fa.setFa_title(rs.getString("fa_title"));
				fa.setFa_content(rs.getString("fa_content"));
				fa.setFa_like(rs.getInt("fa_like"));
				fa.setFa_dislike(rs.getInt("fa_dislike"));
				fa.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				fa.setFa_modify_date(rs.getTimestamp("fa_modify_date"));

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

		return fa;
	}
	
	
	
	
	@Override
	public List<ForumArticleVO> getAll() {
		List<ForumArticleVO> faList = new ArrayList<>();
		ForumArticleVO fa = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fa = new ForumArticleVO();
				fa.setFa_no(rs.getString("fa_no"));
				fa.setMem_no(rs.getString("mem_no"));
				fa.setFa_title(rs.getString("fa_title"));
				fa.setFa_content(rs.getString("fa_content"));
				fa.setFa_like(rs.getInt("fa_like"));
				fa.setFa_dislike(rs.getInt("fa_dislike"));
				fa.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				fa.setFa_modify_date(rs.getTimestamp("fa_modify_date"));

				faList.add(fa);
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
		return faList;
	}

	@Override
	public List<ForumArticleVO> getHot() {
		List<ForumArticleVO> faList = new ArrayList<>();
		ForumArticleVO fa = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HOT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fa = new ForumArticleVO();
				fa.setFa_no(rs.getString("fa_no"));
				fa.setMem_no(rs.getString("mem_no"));
				fa.setFa_title(rs.getString("fa_title"));
				fa.setFa_content(rs.getString("fa_content"));
				fa.setFa_like(rs.getInt("fa_like"));
				fa.setFa_dislike(rs.getInt("fa_dislike"));
				fa.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				fa.setFa_modify_date(rs.getTimestamp("fa_modify_date"));
				faList.add(fa);
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
		return faList;
	}
	
	
	@Override
	public List<ForumArticleVO> getDesc() {
		List<ForumArticleVO> faList = new ArrayList<>();
		ForumArticleVO fa = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DESC);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fa = new ForumArticleVO();
				fa.setFa_no(rs.getString("fa_no"));
				fa.setMem_no(rs.getString("mem_no"));
				fa.setFa_title(rs.getString("fa_title"));
				fa.setFa_content(rs.getString("fa_content"));
				fa.setFa_like(rs.getInt("fa_like"));
				fa.setFa_dislike(rs.getInt("fa_dislike"));
				fa.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				fa.setFa_modify_date(rs.getTimestamp("fa_modify_date"));

				faList.add(fa);
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
		return faList;
	}
	
	
	@Override
	public List<ForumArticleVO> getAll(Map<String, String[]> map) {
		List<ForumArticleVO> list = new ArrayList<ForumArticleVO>();
		ForumArticleVO FAVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from forum_article "
		          + jdbcUtil_CompositeQuery_Article.get_WhereCondition(map)
		          + "order by fa_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			
		
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FAVO = new ForumArticleVO();
				FAVO.setFa_no(rs.getString("fa_no"));
				FAVO.setMem_no(rs.getString("mem_no"));
				FAVO.setFa_title(rs.getString("fa_title"));
				FAVO.setFa_content(rs.getString("fa_content"));
				FAVO.setFa_like(rs.getInt("fa_like"));
				FAVO.setFa_dislike(rs.getInt("fa_dislike"));
				FAVO.setFa_publish_date(rs.getTimestamp("fa_publish_date"));
				FAVO.setFa_modify_date(rs.getTimestamp("fa_modify_date"));
				list.add(FAVO); // Store the row in the List
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
		return list;
	}
}
