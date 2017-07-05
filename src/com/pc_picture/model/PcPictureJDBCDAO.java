package com.pc_picture.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PcPictureJDBCDAO implements PcPictureDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 
		"INSERT INTO pc_picture (pcp_no,pc_no,pcp_picture,pcp_summary) VALUES ('PCP' || LPAD(to_char(pcp_seq.NEXTVAL),7,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pcp_no,pc_no,pcp_picture,pcp_summary FROM pc_picture order by pcp_no";
	private static final String GET_ONE_STMT = 
		"SELECT pcp_no,pc_no,pcp_picture,pcp_summary FROM pc_picture where pcp_no = ?";
	private static final String DELETE = 
		"DELETE FROM pc_picture where pcp_no = ?";
	private static final String UPDATE = 
		"UPDATE pc_picture SET pc_no=?, pcp_picture=?, pcp_summary=? where pcp_no = ?";

	@Override
	public void insert(PcPictureVO pcPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pcPictureVO.getPc_no());
			pstmt.setBytes(2, pcPictureVO.getPcp_picture());
			pstmt.setString(3, pcPictureVO.getPcp_summary());


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
	public void update(PcPictureVO pcPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcPictureVO.getPc_no());
			pstmt.setBytes(2, pcPictureVO.getPcp_picture());
			pstmt.setString(3, pcPictureVO.getPcp_summary());
			pstmt.setString(4, pcPictureVO.getPcp_no());
			
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
	public void delete(String pcp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pcp_no);

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
	public PcPictureVO findByPrimaryKey(String pcp_no) {
		PcPictureVO pcPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pcp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcPictureVO = new PcPictureVO();
				pcPictureVO.setPcp_no(rs.getString("pcp_no"));
				pcPictureVO.setPc_no(rs.getString("pc_no"));
				pcPictureVO.setPcp_picture(rs.getBytes("pcp_picture"));
				pcPictureVO.setPcp_summary(rs.getString("pcp_summary"));
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
		return pcPictureVO;
	}

	@Override
	public List<PcPictureVO> getAll() {
		List<PcPictureVO> list = new ArrayList<PcPictureVO>();
		PcPictureVO pcPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcPictureVO = new PcPictureVO();
				pcPictureVO.setPcp_no(rs.getString("pcp_no"));
				pcPictureVO.setPc_no(rs.getString("pc_no"));
				pcPictureVO.setPcp_picture(rs.getBytes("pcp_picture"));
				pcPictureVO.setPcp_summary(rs.getString("pcp_summary"));
				list.add(pcPictureVO);// Store the row in the list
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
	public void insert2(PcPictureVO pcPictureVO, Connection con) {
		PreparedStatement pstmt = null;
	
		try {
	
	 		pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setString(1, pcPictureVO.getPc_no());
			pstmt.setBytes(2, pcPictureVO.getPcp_picture());
			pstmt.setString(3, pcPictureVO.getPcp_summary());
	
	
			pstmt.executeUpdate();
	
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pcp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}

	public static void main(String[] args) throws IOException {
		PcPictureJDBCDAO dao = new PcPictureJDBCDAO();

		// 新增
//		PcPictureVO pcPictureVO = new PcPictureVO();
//		pcPictureVO.setPc_no("PC00000002");
//		Path path = Paths.get("src/images/500001-3.jpg");
//		byte[] pic = Files.readAllBytes(path);
//		pcPictureVO.setPcp_picture(pic);
//		pcPictureVO.setPcp_summary("111235");	
//		dao.insert(pcPictureVO);

		// 修改
//		PcPictureVO pcPictureVO2 = new PcPictureVO();
//		pcPictureVO2.setPcp_no("PCP0000001");
//		pcPictureVO2.setPc_no("PC00000001");
//		Path path2 = Paths.get("src/images/500001-.jpg");
//		byte[] pic2 = Files.readAllBytes(path2);
//		pcPictureVO2.setPcp_picture(pic2);
//		pcPictureVO2.setPcp_summary("標頭照片222");	
//		dao.update(pcPictureVO2);

		// 刪除
		//dao.delete("PCP0000001");

		// 查詢
//		PcPictureVO pcPictureVO3 = dao.findByPrimaryKey("PCP0000001");
//		System.out.print(pcPictureVO3.getPcp_no() + ",");
//		System.out.print(pcPictureVO3.getPc_no() + ",");
//		System.out.print(pcPictureVO3.getPcp_picture() + ",");
//		System.out.println(pcPictureVO3.getPcp_summary());
//		System.out.println("---------------------");

		// 查詢
		List<PcPictureVO> list = dao.getAll();
		for (PcPictureVO aPcP : list) {
			System.out.print(aPcP.getPcp_no() + ",");
			System.out.print(aPcP.getPc_no() + ",");
			System.out.print(aPcP.getPcp_picture() + ",");
			System.out.println(aPcP.getPcp_summary());
			System.out.println();
		}
	
	}

}
