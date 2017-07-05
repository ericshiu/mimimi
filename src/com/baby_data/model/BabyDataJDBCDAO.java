package com.baby_data.model;

import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class BabyDataJDBCDAO implements BabyDataDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

	private static final String INSERT_STMT = "INSERT INTO BABY_DATA(bd_no,mem_no,bd_order,bd_name,bd_sex,bd_pre,bd_birthday,bd_pictures) VALUES ('BD'||LPAD(to_char(babydata_seq.NEXTVAL),8,'0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT bd_no,mem_no,bd_order,bd_name,bd_sex,bd_pre,bd_birthday,bd_pictures FROM BABY_DATA order by BD_NO";
	private static final String GET_ONE_STMT = "SELECT bd_no,mem_no,bd_order,bd_name,bd_sex,bd_pre,bd_birthday,bd_pictures FROM BABY_DATA where BD_NO = ?";
	private static final String GET_ONE_MEM = "SELECT bd_no,mem_no,bd_order,bd_name,bd_sex,bd_pre,bd_birthday,bd_pictures FROM BABY_DATA where mem_no = ?";
	private static final String DELETE = "DELETE FROM BABY_DATA where bd_no = ?";
	private static final String UPDATE = "UPDATE BABY_DATA set mem_no=?,bd_order=?,bd_name=?,bd_sex=?,bd_pre=?,bd_birthday=?,bd_pictures=? where bd_no = ?";

	@Override
	public void insert(BabyDataVO babyDataVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babyDataVO.getMem_no());
			pstmt.setInt(2, babyDataVO.getBd_order());
			pstmt.setString(3, babyDataVO.getBd_name());
			pstmt.setString(4, babyDataVO.getBd_sex());
			pstmt.setDate(5, babyDataVO.getBd_pre());
			pstmt.setDate(6, babyDataVO.getBd_birthday());
			pstmt.setBytes(7, babyDataVO.getBd_pictures());

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
	public void update(BabyDataVO babyDataVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, babyDataVO.getMem_no());
			pstmt.setInt(2, babyDataVO.getBd_order());
			pstmt.setString(3, babyDataVO.getBd_name());
			pstmt.setString(4, babyDataVO.getBd_sex());
			pstmt.setDate(5, babyDataVO.getBd_pre());
			pstmt.setDate(6, babyDataVO.getBd_birthday());
			pstmt.setBytes(7, babyDataVO.getBd_pictures());
			pstmt.setString(8, babyDataVO.getBd_no());

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
	public void delete(String bd_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bd_no);

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
	public BabyDataVO findByPrimaryKey(String bd_no) {

		BabyDataVO babyDataVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyDataVO = new BabyDataVO();
				babyDataVO.setBd_no(rs.getString("bd_no"));
				babyDataVO.setMem_no(rs.getString("mem_no"));
				babyDataVO.setBd_order(rs.getInt("bd_order"));
				babyDataVO.setBd_name(rs.getString("bd_name"));
				babyDataVO.setBd_sex(rs.getString("bd_sex"));
				babyDataVO.setBd_pre(rs.getDate("bd_pre"));
				babyDataVO.setBd_birthday(rs.getDate("bd_birthday"));
				babyDataVO.setBd_pictures(rs.getBytes("bd_pictures"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return babyDataVO;
	}
	
	
	@Override
	public List<BabyDataVO> getOneMEM(String mem_no) {
		
		List<BabyDataVO> list = new ArrayList<BabyDataVO>();
		BabyDataVO babyDataVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM);	
			
			pstmt.setString(1, mem_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				babyDataVO = new BabyDataVO();
				babyDataVO.setBd_no(rs.getString("bd_no"));
				babyDataVO.setMem_no(rs.getString("mem_no"));
				babyDataVO.setBd_order(rs.getInt("bd_order"));
				babyDataVO.setBd_name(rs.getString("bd_name"));
				babyDataVO.setBd_sex(rs.getString("bd_sex"));
				babyDataVO.setBd_pre(rs.getDate("bd_pre"));
				babyDataVO.setBd_birthday(rs.getDate("bd_birthday"));
				babyDataVO.setBd_pictures(rs.getBytes("bd_pictures"));

				list.add(babyDataVO); // Store the row in the list
				
							
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<BabyDataVO> getAll() {
		List<BabyDataVO> list = new ArrayList<BabyDataVO>();
		BabyDataVO babyDataVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				babyDataVO = new BabyDataVO();
				babyDataVO.setBd_no(rs.getString("bd_no"));
				babyDataVO.setMem_no(rs.getString("mem_no"));
				babyDataVO.setBd_order(rs.getInt("bd_order"));
				babyDataVO.setBd_name(rs.getString("bd_name"));
				babyDataVO.setBd_sex(rs.getString("bd_sex"));
				babyDataVO.setBd_pre(rs.getDate("bd_pre"));
				babyDataVO.setBd_birthday(rs.getDate("bd_birthday"));
				babyDataVO.setBd_pictures(rs.getBytes("bd_pictures"));

				list.add(babyDataVO); // Store the row in the list

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) throws IOException {

		BabyDataJDBCDAO dao = new BabyDataJDBCDAO();

		// 新增
		BabyDataVO babyDataVO1 = new BabyDataVO();
		babyDataVO1.setMem_no("MEM0000001");
		babyDataVO1.setBd_order(10);
		babyDataVO1.setBd_name("正妹");
		babyDataVO1.setBd_sex("女");
		babyDataVO1.setBd_pre(java.sql.Date.valueOf("2014-10-01"));
		babyDataVO1.setBd_birthday(java.sql.Date.valueOf("2014-10-01"));
		Path path = Paths.get("src/images/001.jpg");
		byte[] Pic1 = Files.readAllBytes(path);
		babyDataVO1.setBd_pictures(Pic1);
		dao.insert(babyDataVO1);

		// 修改

		// BabyDataVO babyDataVO2 = new BabyDataVO();
		// babyDataVO2.setBd_no("BD00000001");
		// babyDataVO2.setMem_no("MEM0000001");
		// babyDataVO2.setBd_order(10);
		// babyDataVO2.setBd_name("小屁孩");
		// babyDataVO2.setBd_sex("男");
		// babyDataVO2.setBd_pre(java.sql.Date.valueOf("2005-10-01"));
		// babyDataVO2.setBd_birthday(java.sql.Date.valueOf("2005-10-01"));
		// Path path2=Paths.get("src/images/baby0101.jpg");
		// byte[] Pic2 = Files.readAllBytes(path2);
		// babyDataVO2.setBd_pictures(Pic2);
		//
		// dao.update(babyDataVO2);
		//
		// // 刪除
		// dao.delete("BD00000005");
		//

		
//		 //查詢
//		 BabyDataVO babyDataVO4 = dao.findByPrimaryKey("BD00000001");
//		 System.out.print(babyDataVO4.getBd_no() + ",");
//		 System.out.print(babyDataVO4.getMem_no() + ",");
//		 System.out.print(babyDataVO4.getBd_order() + ",");
//		 System.out.print(babyDataVO4.getBd_name() + ",");
//		 System.out.print(babyDataVO4.getBd_sex() + ",");
//		 System.out.print(babyDataVO4.getBd_pre() + ",");
//		 System.out.println(babyDataVO4.getBd_birthday());
//		 System.out.println(babyDataVO4.getBd_pictures());
//		 System.out.println("---------------------");
//		 
		

		 List<BabyDataVO> list = dao.getOneMEM("MEM0000001");
		 for (BabyDataVO babyDataVO : list) {
			 System.out.print(babyDataVO.getBd_no() + ",");
			 System.out.print(babyDataVO.getMem_no() + ",");
			 System.out.print(babyDataVO.getBd_order() + ",");
			 System.out.print(babyDataVO.getBd_name() + ",");
			 System.out.print(babyDataVO.getBd_sex() + ",");
			 System.out.print(babyDataVO.getBd_pre() + ",");
			 System.out.print(babyDataVO.getBd_birthday() + ",");
			 System.out.print(babyDataVO.getBd_pictures());
			 System.out.println();
		 }
//		List<BabyDataVO> list = dao.getAll();
//		for (BabyDataVO babyDataVO : list) {
//			System.out.print(babyDataVO.getBd_no() + ",");
//			System.out.print(babyDataVO.getMem_no() + ",");
//			System.out.print(babyDataVO.getBd_order() + ",");
//			System.out.print(babyDataVO.getBd_name() + ",");
//			System.out.print(babyDataVO.getBd_sex() + ",");
//			System.out.print(babyDataVO.getBd_pre() + ",");
//			System.out.print(babyDataVO.getBd_birthday() + ",");
//			System.out.print(babyDataVO.getBd_pictures());
//			System.out.println();
//		}

	}
}