package com.article_favorite.model;

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

public class ArticleFavoriteDAO implements ArticleFavoriteDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 

			"INSERT INTO article_favorite VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM article_favorite order by mem_no";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM article_favorite where mem_no = ?";
		
		private static final String GET_MEM_FA = 
				"SELECT * FROM article_favorite where mem_no =? and fa_no=?";
		private static final String GET_FA = 
				"SELECT * FROM article_favorite where fa_no = ?";

		private static final String DELETE = 
			"DELETE FROM article_favorite where mem_no = ? and fa_no=?";
		private static final String UPDATE = 
			"UPDATE article_favorite set fa_no=? where mem_no = ?";
	
		private static final String GET_ALL_FA = 
				"SELECT * FROM article_favorite where fa_no=? order by mem_no";
		private static final String GET_ALL_FAV = 
		"select fa_no,count(*) from article_favorite group by fa_no order by count(*)desc";
		
		@Override
		public void insert(ArticleFavoriteVO articleFavoriteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, articleFavoriteVO.getMem_no());
				pstmt.setString(2, articleFavoriteVO.getFa_no());
				
				pstmt.executeUpdate();

		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void update(ArticleFavoriteVO articleFavoriteVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setString(1, articleFavoriteVO.getFa_no());
				pstmt.setString(2, articleFavoriteVO.getMem_no());
				pstmt.executeUpdate();

			
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void delete(String mem_no,String fa_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mem_no);
				pstmt.setString(2, fa_no);
System.out.println("有近dao");
				pstmt.executeUpdate();

		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public ArticleFavoriteVO findByPK(String mem_no) {

			ArticleFavoriteVO articleFavoriteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
				}

	
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return articleFavoriteVO;
		}

		@Override
		public ArticleFavoriteVO getmemfa(String mem_no,String fa_no) {

			ArticleFavoriteVO articleFavoriteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_MEM_FA);
				
				pstmt.setString(1, mem_no);
				pstmt.setString(2, fa_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
				}

	
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return articleFavoriteVO;
		}
		
		
		@Override
		public ArticleFavoriteVO findByPKfa(String fa_no) {

			ArticleFavoriteVO articleFavoriteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_FA);

				pstmt.setString(1, fa_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
				}

	
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return articleFavoriteVO;
		}
		

		@Override
		public List<ArticleFavoriteVO> getAllfa(String fa_no) {
			List<ArticleFavoriteVO> list = new ArrayList<ArticleFavoriteVO>();
			ArticleFavoriteVO articleFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();

				pstmt = con.prepareStatement(GET_ALL_FA);

				pstmt.setString(1, fa_no);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					// articleFavoriteVO 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
					list.add(articleFavoriteVO); // Store the row in the list
				}

			
		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<ArticleFavoriteVO> getAll() {
			List<ArticleFavoriteVO> list = new ArrayList<ArticleFavoriteVO>();
			ArticleFavoriteVO articleFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// articleFavoriteVO 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
					list.add(articleFavoriteVO); // Store the row in the list
				}

		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<ArticleFavoriteVO> getAll_FAV() {
			List<ArticleFavoriteVO> list = new ArrayList<ArticleFavoriteVO>();
			ArticleFavoriteVO articleFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con=ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_FAV);
				rs = pstmt.executeQuery();
				
		
				while (rs.next()) {
					// articleFavoriteVO 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
					articleFavoriteVO.setMem_no(rs.getString("count(*)"));
					
					list.add(articleFavoriteVO); // Store the row in the list
				}

		
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
