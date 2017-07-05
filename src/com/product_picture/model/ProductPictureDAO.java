package com.product_picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProductPictureDAO implements ProductPicture_interface {

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
		"INSERT INTO Product_Picture (prp_no,pro_no,prp_picture) VALUES ('PRP'||LPAD(product_picture_seq.NEXTVAL,7,'0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT prp_no,pro_no,prp_picture FROM Product_Picture order by prp_no";
	private static final String GET_ONE_STMT = 
		"SELECT prp_no,pro_no,prp_picture FROM Product_Picture where prp_no = ?";
	private static final String DELETE = 
		"DELETE FROM Product_Picture where prp_no = ?";
	private static final String UPDATE = 
		"UPDATE Product_Picture set pro_no=?,prp_picture=? where prp_no = ?";
	private static final String GET_ONEPRO_STMT = 
			"SELECT prp_no,pro_no,prp_picture FROM Product_Picture where pro_no = ?";
	
	private static final String GET_ONEPIC_STMT = 
			"SELECT * FROM Product_Picture where pro_no=? and rownum = 1";
		
	@Override
	public void insert(ProductPictureVO prpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, prpVO.getPro_no());
			pstmt.setBytes(2, prpVO.getPrp_picture());
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
	public void update(ProductPictureVO prpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, prpVO.getPro_no());
			pstmt.setBytes(2, prpVO.getPrp_picture());
			pstmt.setString(3, prpVO.getPrp_no());

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
	public void delete(String prp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prp_no);

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
	public ProductPictureVO findByPrimaryKey(String prp_no) {

		ProductPictureVO prpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prp_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prpVO = new ProductPictureVO();
				prpVO.setPrp_no(rs.getString("prp_no"));
				prpVO.setPro_no(rs.getString("pro_no"));
				prpVO.setPrp_picture(rs.getBytes("prp_picture"));
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
		return prpVO;
	}

	@Override
	public List<ProductPictureVO> getAll() {
		List<ProductPictureVO> list = new ArrayList<ProductPictureVO>();
		ProductPictureVO prpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prpVO = new ProductPictureVO();
				prpVO.setPrp_no(rs.getString("prp_no"));
				prpVO.setPro_no(rs.getString("pro_no"));
				prpVO.setPrp_picture(rs.getBytes("prp_picture"));				
				list.add(prpVO);
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
	public void insert2(ProductPictureVO proPictureVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, proPictureVO.getPro_no());
			pstmt.setBytes(2, proPictureVO.getPrp_picture());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
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
	@Override
	public Set<ProductPictureVO> findByProNo(String pro_no) {
		Set<ProductPictureVO> set = new LinkedHashSet<ProductPictureVO>();
		ProductPictureVO prpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEPRO_STMT);

			pstmt.setString(1, pro_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prpVO = new ProductPictureVO();
				prpVO.setPrp_no(rs.getString("prp_no"));
				prpVO.setPro_no(rs.getString("pro_no"));
				prpVO.setPrp_picture(rs.getBytes("prp_picture"));
				set.add(prpVO);
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
		return set;
	}
	@Override
	public List<ProductPictureVO> findPicByProNo(String pro_no) {
		List<ProductPictureVO> list = new ArrayList<ProductPictureVO>();
		ProductPictureVO prpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEPIC_STMT);

			pstmt.setString(1, pro_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prpVO = new ProductPictureVO();
				prpVO.setPrp_no(rs.getString("prp_no"));
				prpVO.setPro_no(rs.getString("pro_no"));
				prpVO.setPrp_picture(rs.getBytes("prp_picture"));
				
				list.add(prpVO);
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
}


