package com.baby_data.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class BabyDataDAO implements BabyDataDAO_interface {

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, babyDataVO.getMem_no());
			pstmt.setInt(2, babyDataVO.getBd_order());
			pstmt.setString(3, babyDataVO.getBd_name());
			pstmt.setString(4, babyDataVO.getBd_sex());
			pstmt.setDate(5, babyDataVO.getBd_pre());
			pstmt.setDate(6, babyDataVO.getBd_birthday());
			pstmt.setBytes(7, babyDataVO.getBd_pictures());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bd_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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
			
			con = ds.getConnection();
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

			con = ds.getConnection();
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

}