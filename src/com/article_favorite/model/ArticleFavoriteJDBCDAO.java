package com.article_favorite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ArticleFavoriteJDBCDAO implements ArticleFavoriteDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 

			"INSERT INTO article_favorite   VALUES ( ?, ?)";
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, articleFavoriteVO.getMem_no());
				pstmt.setString(2, articleFavoriteVO.getFa_no());
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setString(1, articleFavoriteVO.getFa_no());
				pstmt.setString(2, articleFavoriteVO.getMem_no());
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, mem_no);
				pstmt.setString(2, fa_no);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_FA);

				pstmt.setString(1, fa_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public List<ArticleFavoriteVO> getAll() {
			List<ArticleFavoriteVO> list = new ArrayList<ArticleFavoriteVO>();
			ArticleFavoriteVO articleFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// articleFavoriteVO 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setMem_no(rs.getString("mem_no"));
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
					list.add(articleFavoriteVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public List<ArticleFavoriteVO> getAllfa(String fa_no) {
			List<ArticleFavoriteVO> list = new ArrayList<ArticleFavoriteVO>();
			ArticleFavoriteVO articleFavoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_FAV);
				rs = pstmt.executeQuery();
				
		
				while (rs.next()) {
					// articleFavoriteVO 也稱為 Domain objects
					articleFavoriteVO = new ArticleFavoriteVO();
					articleFavoriteVO.setFa_no(rs.getString("fa_no"));
					articleFavoriteVO.setMem_no(rs.getString("count(*)"));
					
					list.add(articleFavoriteVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		
		public static void main(String[] args) {

			ArticleFavoriteJDBCDAO dao = new ArticleFavoriteJDBCDAO();

			 //新增
//			ArticleFavoriteVO artfav = new ArticleFavoriteVO();
//			 artfav.setMem_no("MEM0000002");
//			 artfav.setFa_no("FA00000007");
//			
//			dao.insert(artfav);

			// 無修改
//			ArticleFavoriteVO autlist1 = new ArticleFavoriteVO();
//			
//			autlist1.setFa_no("MAN0000001");
//			autlist1.setMem_no("AUT0000020");
//			dao.update(autlist1);
//	//
////			// 刪除
//			dao.delete("MEM0000001","FA00000012");
//	//
//			// 怪怪低查詢
//			ArticleFavoriteVO autlist2 = dao.findByPK("MEM0000001");
//			ArticleFavoriteVO autlist2 = dao.findByPKfa("FA00000001");
//			ArticleFavoriteVO autlist2 = dao.getmemfa("MEM0000001","FA00000001");
//			System.out.print(autlist2.getMem_no() + ",");
//			System.out.print(autlist2.getFa_no() + ",");
//			System.out.println("---------------------");
	//
////			// 查詢
//			List<ArticleFavoriteVO> list = dao.getAll();
//			for (ArticleFavoriteVO autlist3: list) {
//				System.out.print(autlist3.getMem_no() + ",");
//				System.out.print(autlist3.getFa_no() + ",");
//				System.out.println();
//			}
			
			
////			查詢
//			List<ArticleFavoriteVO> list = dao.getAllfa("FA00000001");
//			for (ArticleFavoriteVO autlist3: list) {
//				System.out.print(autlist3.getMem_no() + ",");
//				System.out.print(autlist3.getFa_no() + ",");
//				System.out.println();
//			}
			
			
//			查詢
			List<ArticleFavoriteVO> list = dao.getAll_FAV();
			for (ArticleFavoriteVO autlist3: list) {
				System.out.print(autlist3.getMem_no() + ",");
				System.out.print(autlist3.getFa_no() + ",");
				System.out.println();
			}
		}
	}

