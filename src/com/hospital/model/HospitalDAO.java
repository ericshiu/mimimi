package com.hospital.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hospital_ot_item.model.*;
import com.optional_test.model.OptionalTestVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Hos;


public class HospitalDAO implements HospitalDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hospitalVO.getHos_name());
			pstmt.setString(2, hospitalVO.getHos_address());
			pstmt.setString(3, hospitalVO.getHos_phone());


			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hospitalVO.getHos_name());
			pstmt.setString(2, hospitalVO.getHos_address());
			pstmt.setString(3, hospitalVO.getHos_phone());
			pstmt.setString(4, hospitalVO.getHos_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, hos_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// hospitalVO 也稱為 Domain objects
				hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(rs.getString("hos_no"));
				hospitalVO.setHos_name(rs.getString("hos_name"));
				hospitalVO.setHos_address(rs.getString("hos_address"));
				hospitalVO.setHos_phone(rs.getString("hos_phone"));
				list.add(hospitalVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
			con = ds.getConnection();
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
	
			con = ds.getConnection();
			
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
				//System.out.println("自增主鍵值= " + next_pc_no +"(剛新增成功的部門編號)");
			} else {
				//System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增照片

			HospitalOtItemDAO dao = new HospitalOtItemDAO();

			for (HospitalOtItemVO aOT : list) {
				aOT.setHos_no(new String(next_hos_no)) ;
				dao.insert2(aOT,con);
			}			
			
	
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			//System.out.println("list.size()-B="+list.size());
			//System.out.println("新增廠商編號" + next_pc_no + "時,共有照片" + list.size()
			//		+ "張同時被新增");
		
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
		List<HospitalVO> list = new ArrayList<HospitalVO>();
		HospitalVO hospitalVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select hospital.hos_no, hospital.hos_name, hospital.hos_address, hospital.hos_phone, hospital_ot_item.ot_no from hospital, hospital_ot_item "
			          + jdbcUtil_CompositeQuery_Hos.get_WhereCondition(map)
			          + "order by hospital.hos_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(rs.getString("hos_no"));
				hospitalVO.setHos_name(rs.getString("hos_name"));
				hospitalVO.setHos_address(rs.getString("hos_address"));
				hospitalVO.setHos_phone(rs.getString("hos_phone"));
				list.add(hospitalVO); // Store the row in the List
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
