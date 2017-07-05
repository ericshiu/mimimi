package com.education_music.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class EducationMusicDAO implements EducationMusicDAO_interface {
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO education_music (em_no,em_name,em_introduction,em_content) VALUES ('EM'||LPAD(to_char(em_seq.NEXTVAL),8,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT em_no,em_name,em_introduction,em_content FROM education_music";
	private static final String GET_ONE_STMT = "SELECT em_no,em_name,em_introduction,em_content FROM education_music where em_no = ?";
	
	private static final String DELETE = "DELETE FROM education_music where em_no = ?";
	
	private static final String UPDATE = "UPDATE education_music  set em_name=?,em_introduction=?,em_content=? where em_no=?";

	
	
	
	public void insert(EducationMusicVO education_musicVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, education_musicVO.getEm_name());
			pstmt.setString(2, education_musicVO.getEm_introduction());
			pstmt.setBytes(3, education_musicVO.getEm_content());
            
			

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
	public void update(EducationMusicVO education_musicVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, education_musicVO.getEm_name());
			pstmt.setString(2, education_musicVO.getEm_introduction());
			pstmt.setBytes(3, education_musicVO.getEm_content());
			pstmt.setString(4, education_musicVO.getEm_no());
			

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
	public void delete(String em_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			
			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, em_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除編號"+em_no);
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
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
	public EducationMusicVO findByPrimaryKey(String fm_no) {
		// TODO Auto-generated method stub
		
		EducationMusicVO education_musicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// growingVO 也稱為Domain objects
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));
				

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
		
		
		return education_musicVO;
	}
	@Override
	public List<EducationMusicVO> getAll() {

		List<EducationMusicVO> list = new ArrayList<EducationMusicVO>();
		EducationMusicVO education_musicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));
				list.add(education_musicVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	
	public Set<EducationMusicVO> getEducation_musicByEm_no(String em_no) {
		Set<EducationMusicVO> set = new LinkedHashSet<EducationMusicVO>();
		EducationMusicVO education_musicVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, em_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));	
				set.add(education_musicVO); // Store the row in the vector
			}
	
			// Handle any driver errors
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
	
	
	
}
