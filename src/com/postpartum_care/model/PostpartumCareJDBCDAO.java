package com.postpartum_care.model;

import java.util.*;

import com.pc_application.model.PcApplicationVO;
import com.pc_picture.model.*;
import com.pc_report.model.PcReportVO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class PostpartumCareJDBCDAO implements PostpartumCareDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 
		"INSERT INTO postpartum_care (pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad) VALUES ('PC' || LPAD(to_char(pc_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care order by pc_no";
	private static final String GET_ONE_STMT = 
		"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care where pc_no = ?";
	private static final String DELETE = 
		"DELETE FROM postpartum_care where pc_no = ?";
	private static final String UPDATE = 
		"UPDATE postpartum_care set pc_id=?, pc_password=?, pc_name=?, pc_type=?, pc_address=?, pc_phone=?, pc_email=?, pc_summary=?, pc_bonus=?, pc_gift=?, pc_authority=?, pc_point=?, pc_eva_good=?, pc_eva_normal=?, pc_eva_bad=? where pc_no = ?";

	private static final String GET_PCPs_ByPc_no_STMT = "SELECT pcp_no, pc_no, pcp_summary, pcp_picture FROM pc_picture where pc_no = ? order by pcp_no";
	private static final String GET_ONE_BYID = 
			"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care where pc_id = ?";

	
	@Override
	public void insert(PostpartumCareVO postpartumCareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());

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
	public void update(PostpartumCareVO postpartumCareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());
			pstmt.setString(16, postpartumCareVO.getPc_no());

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
	public void delete(String pc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pc_no);

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
	public PostpartumCareVO findByPrimaryKey(String pc_no) {
		PostpartumCareVO postpartumCareVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
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
		return postpartumCareVO;
	}

	@Override
	public List<PostpartumCareVO> getAll() {
		List<PostpartumCareVO> list = new ArrayList<PostpartumCareVO>();
		PostpartumCareVO postpartumCareVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
				list.add(postpartumCareVO);// Store the row in the list
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
	public Set<PcPictureVO> getPCPsByPc_no(String pc_no) {
		Set<PcPictureVO> set = new LinkedHashSet<PcPictureVO>();
		PcPictureVO pcPictureVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PCPs_ByPc_no_STMT);
			pstmt.setString(1, pc_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				pcPictureVO = new PcPictureVO();
				pcPictureVO.setPcp_no(rs.getString("pcp_no"));
				pcPictureVO.setPc_no(rs.getString("pc_no"));
				pcPictureVO.setPcp_summary(rs.getString("pcp_summary"));
				pcPictureVO.setPcp_picture(rs.getBytes("pcp_picture"));
				set.add(pcPictureVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return set;
	}

	@Override
	public PostpartumCareVO findById(String pc_id) {
		PostpartumCareVO postpartumCareVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BYID);

			pstmt.setString(1, pc_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
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
		return postpartumCareVO;
	}

	@Override
	public void insertWithPCPs(PostpartumCareVO postpartumCareVO, List<PcPictureVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先新增廠商
			String cols[] = {"pc_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_pc_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_pc_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_pc_no +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增照片
			PcPictureJDBCDAO dao = new PcPictureJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (PcPictureVO aPCP : list) {
				aPCP.setPc_no(new String(next_pc_no)) ;
				dao.insert2(aPCP,con);
			}
	
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增廠商編號" + next_pc_no + "時,共有照片" + list.size()
					+ "張同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pc");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
		
	}

	public static void main(String[] args) throws IOException {
		PostpartumCareJDBCDAO dao = new PostpartumCareJDBCDAO();

		// 新增
//		PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
//		postpartumCareVO.setPc_id("PC0004");
//		postpartumCareVO.setPc_password("PC0004");
//		postpartumCareVO.setPc_name("藍田產後護理之家");
//		postpartumCareVO.setPc_type("月子中心");
//		postpartumCareVO.setPc_address("台北市中正區新生南路一段170巷13號");
//		postpartumCareVO.setPc_phone("02-2332-2500");
//		postpartumCareVO.setPc_email("aaa@abc.com.tw");
//		postpartumCareVO.setPc_summary("總有一個溫暖的家，在您身邊");
//		postpartumCareVO.setPc_bonus("N");
//		postpartumCareVO.setPc_gift("Y");
//		postpartumCareVO.setPc_authority("Y");
//		postpartumCareVO.setPc_point(100);
//		postpartumCareVO.setPc_eva_good(0);
//		postpartumCareVO.setPc_eva_normal(0);
//		postpartumCareVO.setPc_eva_bad(0);	
//		dao.insert(postpartumCareVO);

		// 修改
//		PostpartumCareVO postpartumCareVO2 = new PostpartumCareVO();
//		postpartumCareVO2.setPc_no("PC00000004");
//		postpartumCareVO2.setPc_id("PC0004");
//		postpartumCareVO2.setPc_password("PC0004");
//		postpartumCareVO2.setPc_name("藍田產後護理之家222");
//		postpartumCareVO2.setPc_type("月子中心");
//		postpartumCareVO2.setPc_address("台北市中正區新生南路一段170巷13號");
//		postpartumCareVO2.setPc_phone("02-2332-2500");
//		postpartumCareVO2.setPc_email("aaa@abc.com.tw");
//		postpartumCareVO2.setPc_summary("總有一個溫暖的家，在您身邊");
//		postpartumCareVO2.setPc_bonus("N");
//		postpartumCareVO2.setPc_gift("Y");
//		postpartumCareVO2.setPc_authority("Y");
//		postpartumCareVO2.setPc_point(100);
//		postpartumCareVO2.setPc_eva_good(0);
//		postpartumCareVO2.setPc_eva_normal(0);
//		postpartumCareVO2.setPc_eva_bad(0);	
//		dao.update(postpartumCareVO2);

		// 刪除
//		dao.delete("PC00000004");

		// 查詢
//		PostpartumCareVO postpartumCareVO3 = dao.findByPrimaryKey("PC00000001");
//		System.out.print(postpartumCareVO3.getPc_no() + ",");
//		System.out.print(postpartumCareVO3.getPc_id() + ",");
//		System.out.print(postpartumCareVO3.getPc_password() + ",");
//		System.out.print(postpartumCareVO3.getPc_name() + ",");
//		System.out.print(postpartumCareVO3.getPc_type() + ",");
//		System.out.print(postpartumCareVO3.getPc_address() + ",");
//		System.out.print(postpartumCareVO3.getPc_phone() + ",");
//		System.out.print(postpartumCareVO3.getPc_email() + ",");
//		System.out.print(postpartumCareVO3.getPc_summary() + ",");
//		System.out.print(postpartumCareVO3.getPc_bonus() + ",");
//		System.out.print(postpartumCareVO3.getPc_gift() + ",");
//		System.out.print(postpartumCareVO3.getPc_authority() + ",");
//		System.out.print(postpartumCareVO3.getPc_point() + ",");
//		System.out.print(postpartumCareVO3.getPc_eva_good() + ",");
//		System.out.print(postpartumCareVO3.getPc_eva_normal() + ",");
//		System.out.println(postpartumCareVO3.getPc_eva_bad());
//		System.out.println("---------------------");
		
		// 查詢
//		PostpartumCareVO postpartumCareVO3 = dao.findById("PC0001");
//		System.out.print(postpartumCareVO3.getPc_no() + ",");
//		System.out.print(postpartumCareVO3.getPc_id() + ",");
//		System.out.print(postpartumCareVO3.getPc_password() + ",");
//		System.out.print(postpartumCareVO3.getPc_name() + ",");
//		System.out.print(postpartumCareVO3.getPc_type() + ",");
//		System.out.print(postpartumCareVO3.getPc_address() + ",");
//		System.out.print(postpartumCareVO3.getPc_phone() + ",");
//		System.out.print(postpartumCareVO3.getPc_email() + ",");
//		System.out.print(postpartumCareVO3.getPc_summary() + ",");
//		System.out.print(postpartumCareVO3.getPc_bonus() + ",");
//		System.out.print(postpartumCareVO3.getPc_gift() + ",");
//		System.out.print(postpartumCareVO3.getPc_authority() + ",");
//		System.out.print(postpartumCareVO3.getPc_point() + ",");
//		System.out.print(postpartumCareVO3.getPc_eva_good() + ",");
//		System.out.print(postpartumCareVO3.getPc_eva_normal() + ",");
//		System.out.println(postpartumCareVO3.getPc_eva_bad());
//		System.out.println("---------------------");		

//		// 查詢
//		List<PostpartumCareVO> list = dao.getAll();
//		for (PostpartumCareVO aPC : list) {
//			System.out.print(aPC.getPc_no() + ",");
//			System.out.print(aPC.getPc_id() + ",");
//			System.out.print(aPC.getPc_password() + ",");
//			System.out.print(aPC.getPc_name() + ",");
//			System.out.print(aPC.getPc_type() + ",");
//			System.out.print(aPC.getPc_address() + ",");
//			System.out.print(aPC.getPc_phone() + ",");
//			System.out.print(aPC.getPc_email() + ",");
//			System.out.print(aPC.getPc_summary() + ",");
//			System.out.print(aPC.getPc_bonus() + ",");
//			System.out.print(aPC.getPc_gift() + ",");
//			System.out.print(aPC.getPc_authority() + ",");
//			System.out.print(aPC.getPc_point() + ",");
//			System.out.print(aPC.getPc_eva_good() + ",");
//			System.out.print(aPC.getPc_eva_normal() + ",");
//			System.out.print(aPC.getPc_eva_bad());
//			System.out.println();
//		}	
//		
//		// 查詢某廠商照片
//		Set<PcPictureVO> set = dao.getPCPsByPc_no("PC00000001");
//		for (PcPictureVO aPCP : set) {
//			System.out.print(aPCP.getPcp_no() + ",");
//			System.out.print(aPCP.getPc_no() + ",");
//			System.out.print(aPCP.getPcp_summary() + ",");
//			System.out.print(aPCP.getPcp_picture() );
//			System.out.println();
//		}
		
		
		PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
		postpartumCareVO.setPc_id("PC0005");
		postpartumCareVO.setPc_password("PC0005");
		postpartumCareVO.setPc_name("藍田產後護理之家");
		postpartumCareVO.setPc_type("月子中心");
		postpartumCareVO.setPc_address("台北市中正區新生南路一段170巷13號");
		postpartumCareVO.setPc_phone("02-2332-2500");
		postpartumCareVO.setPc_email("aaa@abc.com.tw");
		postpartumCareVO.setPc_summary("總有一個溫暖的家，在您身邊");
		postpartumCareVO.setPc_bonus("N");
		postpartumCareVO.setPc_gift("Y");
		postpartumCareVO.setPc_authority("Y");
		postpartumCareVO.setPc_point(100);
		postpartumCareVO.setPc_eva_good(0);
		postpartumCareVO.setPc_eva_normal(0);
		postpartumCareVO.setPc_eva_bad(0);	
		
		List<PcPictureVO> testList = new ArrayList<PcPictureVO>(); // 準備置入照片數張
		PcPictureVO pcPictureVO = new PcPictureVO();
		Path path = Paths.get("src/images/500001-3.jpg");
		byte[] pic = Files.readAllBytes(path);
		pcPictureVO.setPcp_picture(pic);
		pcPictureVO.setPcp_summary("111235");	

		PcPictureVO pcPictureVO2 = new PcPictureVO();
		Path path2 = Paths.get("src/images/500001-1.jpg");
		byte[] pic2 = Files.readAllBytes(path2);
		pcPictureVO2.setPcp_picture(pic2);
		pcPictureVO2.setPcp_summary("111235");	

		testList.add(pcPictureVO);
		testList.add(pcPictureVO2);
		
		dao.insertWithPCPs(postpartumCareVO , testList);		
		
		
		
	}

	@Override
	public List<PostpartumCareVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PcApplicationVO> getAppsByPc_no(String pc_no, String pca_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PcReportVO> getReportsByPc_no(String pc_no, String pcr_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEvaluation(PostpartumCareVO postpartumCareVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

}
