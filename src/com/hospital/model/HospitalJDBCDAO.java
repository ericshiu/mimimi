package com.hospital.model;

import java.util.*;

import com.hospital_ot_item.model.*;
import com.optional_test.model.*;
import com.pc_picture.model.PcPictureVO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class HospitalJDBCDAO implements HospitalDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = 
		"INSERT INTO hospital (hos_no,hos_name,hos_address,hos_phone) VALUES ('HOS' || LPAD(to_char(hos_seq.NEXTVAL),7,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT hos_no,hos_name,hos_address,hos_phone FROM hospital order by hos_no";
	private static final String GET_ONE_STMT = 
		"SELECT hos_no,hos_name,hos_address,hos_phone FROM hospital where hos_no = ?";
	private static final String DELETE = 
		"DELETE FROM hospital where hos_no = ?";
	private static final String UPDATE = 
		"UPDATE hospital set hos_name=?, hos_address=?, hos_phone=? where hos_no = ?";

	private static final String GET_OTs_ByHos_no_STMT = "SELECT ot_no, ot_name, ot_week_start, ot_week_end FROM optional_test WHERE ot_no IN (SELECT ot_no FROM hospital_ot_item WHERE hos_no = ? ) order by ot_no";
	
	@Override
	public void insert(HospitalVO hospitalVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hospitalVO.getHos_name());
			pstmt.setString(2, hospitalVO.getHos_address());
			pstmt.setString(3, hospitalVO.getHos_phone());

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
	public void update(HospitalVO hospitalVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hospitalVO.getHos_name());
			pstmt.setString(2, hospitalVO.getHos_address());
			pstmt.setString(3, hospitalVO.getHos_phone());
			pstmt.setString(4, hospitalVO.getHos_no());

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
	public void delete(String hos_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, hos_no);

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
	public HospitalVO findByPrimaryKey(String hos_no) {
		HospitalVO hospitalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, hos_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(rs.getString("hos_no"));
				hospitalVO.setHos_name(rs.getString("hos_name"));
				hospitalVO.setHos_address(rs.getString("hos_address"));
				hospitalVO.setHos_phone(rs.getString("hos_phone"));
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
		return hospitalVO;
	}
	@Override
	public List<HospitalVO> getAll() {
		List<HospitalVO> list = new ArrayList<HospitalVO>();
		HospitalVO hospitalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(rs.getString("hos_no"));
				hospitalVO.setHos_name(rs.getString("hos_name"));
				hospitalVO.setHos_address(rs.getString("hos_address"));
				hospitalVO.setHos_phone(rs.getString("hos_phone"));
				list.add(hospitalVO);// Store the row in the list
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
	public Set<OptionalTestVO> getOTsByHos_no(String hos_no) {
		Set<OptionalTestVO> set = new LinkedHashSet<OptionalTestVO>();
		OptionalTestVO optionalTestVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_OTs_ByHos_no_STMT);
			pstmt.setString(1, hos_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				optionalTestVO = new OptionalTestVO();
				optionalTestVO.setOt_no(rs.getString("ot_no"));
				optionalTestVO.setOt_name(rs.getString("ot_name"));
				optionalTestVO.setOt_week_start(rs.getInt("ot_week_start"));
				optionalTestVO.setOt_week_end(rs.getInt("ot_week_end"));
				set.add(optionalTestVO); // Store the row in the vector
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
	public void insertWithOTs(HospitalVO hospitalVO, List<HospitalOtItemVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先新增廠商
			String cols[] = {"hos_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, hospitalVO.getHos_name());
			pstmt.setString(2, hospitalVO.getHos_address());
			pstmt.setString(3, hospitalVO.getHos_phone());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_hos_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_hos_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_hos_no +"(剛新增成功的醫院編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增自費項目
			
			HospitalOtItemJDBCDAO dao = new HospitalOtItemJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (HospitalOtItemVO aOT : list) {
				aOT.setHos_no(new String(next_hos_no)) ;
				dao.insert2(aOT,con);
			}
	
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增醫院編號" + next_hos_no + "時,共有自費項目" + list.size()
					+ "項同時被新增");
			
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
					System.err.println("rolled back-由-hos");
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
	@Override
	public List<HospitalVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {

		HospitalJDBCDAO dao = new HospitalJDBCDAO();

		// 新增
//		HospitalVO hospitalVO = new HospitalVO();
//		hospitalVO.setHos_name("曾婦產科診所");
//		hospitalVO.setHos_address("台北市中山區長安東路二段11之1號1樓");
//		hospitalVO.setHos_phone("02 2537 6712");		
//		dao.insert(hospitalVO);

		// 修改
//		HospitalVO hospitalVO2 = new HospitalVO();
//		hospitalVO2.setHos_no("HOS0000006");
//		hospitalVO2.setHos_name("曾婦產科診所2");
//		hospitalVO2.setHos_address("台北市中山區長安東路二段11之1號1樓");
//		hospitalVO2.setHos_phone("02 2537 6712");	
//		dao.update(hospitalVO2);

		// 刪除
//		dao.delete("HOS0000006");

		// 查詢
//		HospitalVO hospitalVO3 = dao.findByPrimaryKey("HOS0000005");
//		System.out.print(hospitalVO3.getHos_no() + ",");
//		System.out.print(hospitalVO3.getHos_name() + ",");
//		System.out.print(hospitalVO3.getHos_address() + ",");
//		System.out.println(hospitalVO3.getHos_phone());
//		System.out.println("---------------------");

		// 查詢
//		List<HospitalVO> list = dao.getAll();
//		for (HospitalVO aHos : list) {
//			System.out.print(aHos.getHos_no() + ",");
//			System.out.print(aHos.getHos_name() + ",");
//			System.out.print(aHos.getHos_address() + ",");
//			System.out.print(aHos.getHos_phone() + ",");
//			System.out.println();
//		}
		

//		
//		HospitalVO hospitalVO = new HospitalVO();
//		hospitalVO.setHos_name("123婦產科診所");
//		hospitalVO.setHos_address("111台北市中山區長安東路二段11之1號1樓");
//		hospitalVO.setHos_phone("02 2537 6712");			
//		
//		List<HospitalOtItemVO> testList = new ArrayList<HospitalOtItemVO>(); // 準備置入自費項目
//		HospitalOtItemVO hospitalOtItemVO1 = new HospitalOtItemVO();
//		hospitalOtItemVO1.setOt_no("OT00000001");
//
//		HospitalOtItemVO hospitalOtItemVO2 = new HospitalOtItemVO();
//		hospitalOtItemVO2.setOt_no("OT00000002");
//		
//		HospitalOtItemVO hospitalOtItemVO3 = new HospitalOtItemVO();
//		hospitalOtItemVO3.setOt_no("OT00000003");
//
//		testList.add(hospitalOtItemVO1);
//		testList.add(hospitalOtItemVO2);
//		testList.add(hospitalOtItemVO3);
//
//		
//		dao.insertWithOTs(hospitalVO , testList);			
		
		
		// 查詢某醫院自費項目
		Set<OptionalTestVO> set = dao.getOTsByHos_no("HOS0000086");
		for (OptionalTestVO aOT : set) {
			System.out.print(aOT.getOt_no() + ",");
			System.out.print(aOT.getOt_name() + ",");
			System.out.print(aOT.getOt_week_start() + ",");
			System.out.print(aOT.getOt_week_end() );
			System.out.println();
		}			
		
		
	}	
	
}
