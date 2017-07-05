package com.firm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FirmDAO implements FirmDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_PSTMT = 
			"INSERT INTO firm (fir_no,fir_id,fir_password,fir_name,fir_type,fir_phone,fir_address,"
			+ "fir_email,fir_account,fir_authority,fir_eva_good,fir_eva_normal,fir_eva_bad)"
			+ "VALUES ('FIR'||lpad(to_char(firm_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_PSTMT = 
			"SELECT * FROM firm";
	private static final String FIND_BY_PK = 
			"SELECT * FROM firm where fir_no = ?";
	
	private static final String FIND_BY_ID = 
			"SELECT * FROM firm where fir_id = ?";
	private static final String DELETE = 
			"DELETE FROM firm where fir_no = ?";
	private static final String UPDATE = 
			"UPDATE firm set fir_id=?, fir_password=?, fir_name=?, fir_type=?, fir_phone=?, fir_address=?,"
			+ "fir_email=?, fir_account=?, fir_authority=? ,fir_eva_good=?, fir_eva_normal=?, fir_eva_bad=?"
			+ "where fir_no = ?";
	private static final String UPDATE_PSW = 
			"UPDATE firm set  fir_password=? where fir_no = ?";
	
	private static final String UPDATE_EVA = 
			"UPDATE firm set fir_eva_good=?, fir_eva_normal=?, fir_eva_bad=?"
			+ "where fir_no = ?";
	@Override
	public void insert(FirmVO firm) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, firm.getFir_id());
			pstmt.setString(2, firm.getFir_password());
			pstmt.setString(3, firm.getFir_name());
			pstmt.setString(4, firm.getFir_type());
			pstmt.setString(5, firm.getFir_phone());
			pstmt.setString(6, firm.getFir_address());
			pstmt.setString(7, firm.getFir_email());
			pstmt.setString(8, firm.getFir_account());
			pstmt.setString(9, firm.getFir_authority());
			pstmt.setInt(10, firm.getFir_eva_good());
			pstmt.setInt(11, firm.getFir_eva_normal());
			pstmt.setInt(12, firm.getFir_eva_bad());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException(se);
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
	public void update(FirmVO firm) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, firm.getFir_id());
			pstmt.setString(2, firm.getFir_password());
			pstmt.setString(3, firm.getFir_name());
			pstmt.setString(4, firm.getFir_type());
			pstmt.setString(5, firm.getFir_phone());
			pstmt.setString(6, firm.getFir_address());
			pstmt.setString(7, firm.getFir_email());
			pstmt.setString(8, firm.getFir_account());
			pstmt.setString(9, firm.getFir_authority());
			pstmt.setInt(10, firm.getFir_eva_good());
			pstmt.setInt(11, firm.getFir_eva_normal());
			pstmt.setInt(12, firm.getFir_eva_bad());
			pstmt.setString(13, firm.getFir_no());
			
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void delete(String fir_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, fir_no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public FirmVO findByPrimaryKey(String firm_no) {
		FirmVO fir = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, firm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fir = new FirmVO();
				fir.setFir_no(rs.getString("fir_no"));
				fir.setFir_id(rs.getString("fir_id"));
				fir.setFir_password(rs.getString("fir_password"));
				fir.setFir_name(rs.getString("fir_name"));
				fir.setFir_type(rs.getString("fir_type"));
				fir.setFir_phone(rs.getString("fir_phone"));
				fir.setFir_address(rs.getString("fir_address"));
				fir.setFir_email(rs.getString("fir_email"));
				fir.setFir_account(rs.getString("fir_account"));
				fir.setFir_authority(rs.getString("fir_authority"));
				fir.setFir_eva_good(rs.getInt("Fir_eva_good"));
				fir.setFir_eva_normal(rs.getInt("Fir_eva_normal"));
				fir.setFir_eva_bad(rs.getInt("Fir_eva_bad"));
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
		return fir;
	}
	
	
	@Override
	public FirmVO findByID(String firm_id) {
		FirmVO fir = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ID);
			pstmt.setString(1, firm_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fir = new FirmVO();
				fir.setFir_no(rs.getString("fir_no"));
				fir.setFir_id(rs.getString("fir_id"));
				fir.setFir_password(rs.getString("fir_password"));
				fir.setFir_name(rs.getString("fir_name"));
				fir.setFir_type(rs.getString("fir_type"));
				fir.setFir_phone(rs.getString("fir_phone"));
				fir.setFir_address(rs.getString("fir_address"));
				fir.setFir_email(rs.getString("fir_email"));
				fir.setFir_account(rs.getString("fir_account"));
				fir.setFir_authority(rs.getString("fir_authority"));
				fir.setFir_eva_good(rs.getInt("Fir_eva_good"));
				fir.setFir_eva_normal(rs.getInt("Fir_eva_normal"));
				fir.setFir_eva_bad(rs.getInt("Fir_eva_bad"));
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
		return fir;
	}


	@Override
	public List<FirmVO> getAll() {
		List<FirmVO> list = new ArrayList<FirmVO>();
		FirmVO firmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				firmVO = new FirmVO();
				firmVO.setFir_no(rs.getString("Fir_no"));
				firmVO.setFir_id(rs.getString("Fir_id"));
				firmVO.setFir_password(rs.getString("Fir_password"));
				firmVO.setFir_name(rs.getString("Fir_name"));
				firmVO.setFir_type(rs.getString("Fir_type"));
				firmVO.setFir_phone(rs.getString("Fir_phone"));
				firmVO.setFir_address(rs.getString("Fir_address"));
				firmVO.setFir_email(rs.getString("Fir_email"));
				firmVO.setFir_account(rs.getString("Fir_account"));
				firmVO.setFir_authority(rs.getString("Fir_authority"));
				firmVO.setFir_eva_good(rs.getInt("Fir_eva_good"));
				firmVO.setFir_eva_normal(rs.getInt("Fir_eva_normal"));
				firmVO.setFir_eva_bad(rs.getInt("Fir_eva_bad"));
				
				
				list.add(firmVO); // Store the row in the list
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
	public void updatePsw(String fir_password,String fir_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSW);
			
			pstmt.setString(1, fir_password);
			pstmt.setString(2, fir_no);
	
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void updateEvaluation(FirmVO firmVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(UPDATE_EVA);
			
			pstmt.setInt(1, firmVO.getFir_eva_good());
			pstmt.setInt(2, firmVO.getFir_eva_normal());
			pstmt.setInt(3, firmVO.getFir_eva_bad());
			pstmt.setString(4, firmVO.getFir_no());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-firm");
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

	

