package com.pc_picture.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PcPictureDAO implements PcPictureDAO_interface{

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pcPictureVO.getPc_no());
			pstmt.setBytes(2, pcPictureVO.getPcp_picture());
			pstmt.setString(3, pcPictureVO.getPcp_summary());

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
	public void update(PcPictureVO pcPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcPictureVO.getPc_no());
			pstmt.setBytes(2, pcPictureVO.getPcp_picture());
			pstmt.setString(3, pcPictureVO.getPcp_summary());
			pstmt.setString(4, pcPictureVO.getPcp_no());
			
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
	public void delete(String pcp_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pcp_no);

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
	public PcPictureVO findByPrimaryKey(String pcp_no) {
		PcPictureVO pcPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
					System.err.println("rolled back-由-emp");
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

}
