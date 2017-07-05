package com.product_speci.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
	
public class ProductSpeciDAO implements ProductSpeci_interface {

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
			"INSERT INTO product_speci (psp_no,pro_no,psp_name,psp_list) VALUES ('PSP'||LPAD(product_speci_seq.NEXTVAL,7,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT psp_no,pro_no,psp_name,psp_list FROM product_speci order by psp_no";
	private static final String GET_ONE_STMT = 
			"SELECT psp_no,pro_no,psp_name,psp_list FROM product_speci where psp_no = ?";
	private static final String DELETE = 
			"DELETE FROM product_speci where psp_no = ?";
	private static final String UPDATE = 
			"UPDATE product_speci set pro_no=?,psp_name=?,psp_list=? where psp_no = ?";

	@Override
	public void insert(ProductSpeciVO pspVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pspVO.getPro_no());
			pstmt.setString(2, pspVO.getPsp_name());
			pstmt.setString(3, pspVO.getPsp_list());

			pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException(se.getMessage());
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
	public void update(ProductSpeciVO pspVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pspVO.getPro_no());
			pstmt.setString(2, pspVO.getPsp_name());
			pstmt.setString(3, pspVO.getPsp_list());
			pstmt.setString(4, pspVO.getPsp_no());

			pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException(se.getMessage());
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
	public void delete(String psp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, psp_no);

			pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException(se.getMessage());
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
	public ProductSpeciVO findByPrimaryKey(String psp_no) {

		ProductSpeciVO pspVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, psp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pspVO = new ProductSpeciVO();
				pspVO.setPsp_no(rs.getString("psp_no"));
				pspVO.setPro_no(rs.getString("pro_no"));
				pspVO.setPsp_name(rs.getString("psp_name"));
				pspVO.setPsp_list(rs.getString("psp_list"));
			}

			} catch (SQLException se) {
				throw new RuntimeException(se.getMessage());
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
			return pspVO;
	}

	@Override
	public List<ProductSpeciVO> getAll() {
		List<ProductSpeciVO> list = new ArrayList<ProductSpeciVO>();
		ProductSpeciVO pspVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pspVO = new ProductSpeciVO();
				pspVO.setPsp_no(rs.getString("psp_no"));
				pspVO.setPro_no(rs.getString("pro_no"));
				pspVO.setPsp_name(rs.getString("psp_name"));
				pspVO.setPsp_list(rs.getString("psp_list"));
				list.add(pspVO);
			}

			} catch (SQLException se) {
				throw new RuntimeException(se.getMessage());
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
	public void insert3(ProductSpeciVO proSpeciVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, proSpeciVO.getPro_no());
			pstmt.setString(2, proSpeciVO.getPsp_name());
			pstmt.setString(3, proSpeciVO.getPsp_list());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException(se.getMessage());
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
