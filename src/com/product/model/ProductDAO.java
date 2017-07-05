package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_picture.model.ProductPictureDAO;
import com.product_picture.model.ProductPictureVO;
import com.product_speci.model.ProductSpeciDAO;
import com.product_speci.model.ProductSpeciVO;

public class ProductDAO implements Product_interface {

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
			"INSERT INTO Product (pro_no,prc_no,fir_no,pro_name,pro_desc,pro_price,pro_range,pro_age_ms,"
			+ "pro_age_me,pro_age_cs,pro_age_ce,pro_qa) VALUES "
			+ "('PRO'||LPAD(product_seq.NEXTVAL,7,'0'), ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT pro_no,prc_no,fir_no,pro_name,pro_desc,pro_price,pro_range,pro_age_ms,pro_age_me,pro_age_cs,pro_age_ce,pro_qa FROM PRODUCT order by pro_no";
		private static final String GET_ONE_STMT = 
			"SELECT pro_no,prc_no,fir_no,pro_name,pro_desc,pro_price,pro_range,pro_age_ms,pro_age_me,pro_age_cs,pro_age_ce,pro_qa FROM PRODUCT where pro_no = ?";
		private static final String DELETE = 
			"DELETE FROM Product where pro_no = ?";
		private static final String UPDATE = 
			"UPDATE Product set prc_no=?, fir_no=?, pro_name=?, pro_desc=?, pro_price=?, pro_range=?, pro_age_ms=?, pro_age_me=?, pro_age_cs=?, pro_age_ce=?, pro_qa=? where pro_no = ?";

		private static final String GET_PRO_WITH_FIRM_STMT = 
				"SELECT pro_no,fir_no,pro_name,pro_price FROM PRODUCT where fir_no = ?";
		
	@Override
	public void insertProWithPicSpeci(ProductVO proVO, List<ProductPictureVO> listPic, List<ProductSpeciVO> listSpeci) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = {"pro_no"};			
			pstmt = con.prepareStatement(INSERT_STMT,cols);

			pstmt.setString(1, proVO.getPrc_no());
			pstmt.setString(2, proVO.getFir_no());
			pstmt.setString(3, proVO.getPro_name());
			pstmt.setString(4, proVO.getPro_desc());
			pstmt.setInt(5, proVO.getPro_price());
			pstmt.setString(6, proVO.getPro_range());
			pstmt.setInt(7, proVO.getPro_age_ms());
			pstmt.setInt(8, proVO.getPro_age_me());
			pstmt.setInt(9, proVO.getPro_age_cs());
			pstmt.setInt(10, proVO.getPro_age_ce());
			pstmt.setString(11, proVO.getPro_qa());
			pstmt.executeUpdate();
			
			String next_pro_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_pro_no = rs.getString(1);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			//圖片
			ProductPictureDAO dao = new ProductPictureDAO();
			for (ProductPictureVO aPro : listPic) {
				aPro.setPro_no(next_pro_no);
				dao.insert2(aPro,con);
			}
			//規格
			ProductSpeciDAO dao2 = new ProductSpeciDAO();
			for (ProductSpeciVO aPro : listSpeci) {
				aPro.setPro_no(next_pro_no);
				dao2.insert3(aPro, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException(excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ProductVO proVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, proVO.getPrc_no());
			pstmt.setString(2, proVO.getFir_no());
			pstmt.setString(3, proVO.getPro_name());
			pstmt.setString(4, proVO.getPro_desc());
			pstmt.setInt(5, proVO.getPro_price());
			pstmt.setString(6, proVO.getPro_range());
			pstmt.setInt(7, proVO.getPro_age_ms());
			pstmt.setInt(8, proVO.getPro_age_me());
			pstmt.setInt(9, proVO.getPro_age_cs());
			pstmt.setInt(10, proVO.getPro_age_ce());
			pstmt.setString(11, proVO.getPro_qa()); 
			pstmt.setString(12,proVO.getPro_no());
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
	public ProductVO findByPrimaryKey(String pro_no) {

		ProductVO proVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pro_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				proVO = new ProductVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPrc_no(rs.getString("prc_no"));
				proVO.setFir_no(rs.getString("fir_no"));
				proVO.setPro_name(rs.getString("pro_name"));
				proVO.setPro_desc(rs.getString("pro_desc"));
				proVO.setPro_price(rs.getInt("pro_price"));
				proVO.setPro_range(rs.getString("pro_range"));
				proVO.setPro_age_ms(rs.getInt("pro_age_ms"));
				proVO.setPro_age_me(rs.getInt("pro_age_me"));
				proVO.setPro_age_cs(rs.getInt("pro_age_cs"));
				proVO.setPro_age_ce(rs.getInt("pro_age_ce"));
				proVO.setPro_qa(rs.getString("pro_qa"));
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
		return proVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				proVO = new ProductVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setPrc_no(rs.getString("prc_no"));
				proVO.setFir_no(rs.getString("fir_no"));
				proVO.setPro_name(rs.getString("pro_name"));
				proVO.setPro_desc(rs.getString("pro_desc"));
				proVO.setPro_price(rs.getInt("pro_price"));
				proVO.setPro_range(rs.getString("pro_range"));
				proVO.setPro_age_ms(rs.getInt("pro_age_ms"));
				proVO.setPro_age_me(rs.getInt("pro_age_me"));
				proVO.setPro_age_cs(rs.getInt("pro_age_cs"));
				proVO.setPro_age_ce(rs.getInt("pro_age_ce"));
				proVO.setPro_qa(rs.getString("pro_qa"));
				list.add(proVO); // Store the row in the list
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
	public List<ProductVO> getProByFir(String fir_no) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO proVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PRO_WITH_FIRM_STMT);
			pstmt.setString(1, fir_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				proVO = new ProductVO();
				proVO.setPro_no(rs.getString("pro_no"));
				proVO.setFir_no(rs.getString("fir_no"));
				proVO.setPro_name(rs.getString("pro_name"));
				proVO.setPro_price(rs.getInt("pro_price"));

				list.add(proVO);
			}

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
