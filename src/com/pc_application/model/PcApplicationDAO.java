package com.pc_application.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.*;
import com.pc_report.model.*;
import com.point_record.model.*;

public class PcApplicationDAO implements PcApplicationDAO_interface{

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
			"INSERT INTO pc_application (pca_no,pc_no,mem_no,pca_appdate,pca_date,pca_memo,pca_status,pca_review_date) VALUES ('PCA' || LPAD(to_char(pca_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT pca_no,pc_no,mem_no,to_char(pca_appdate,'yyyy-mm-dd hh24:mi:ss') as pca_appdate,to_char(pca_date,'yyyy-mm-dd'),pca_memo,pca_status,to_char(pca_review_date,'yyyy-mm-dd') FROM pc_application where pca_status=? order by pca_no";
	private static final String GET_ONE_STMT = 
			"SELECT pca_no,pc_no,mem_no,to_char(pca_appdate,'yyyy-mm-dd hh24:mi:ss') as pca_appdate,to_char(pca_date,'yyyy-mm-dd'),pca_memo,pca_status,to_char(pca_review_date,'yyyy-mm-dd') FROM pc_application where pca_no = ?";
	private static final String DELETE = 
			"DELETE FROM pc_application where pca_no = ?";
	private static final String UPDATE = 
			"UPDATE pc_application SET pca_status=?, pca_review_date=? where pca_no = ? ";	
	
	@Override
	public void insert(PcApplicationVO pcApplicationVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pcApplicationVO.getPc_no());
			pstmt.setString(2, pcApplicationVO.getMem_no());
			pstmt.setTimestamp(3, pcApplicationVO.getPca_appdate());
			pstmt.setDate(4, pcApplicationVO.getPca_date());
			pstmt.setString(5, pcApplicationVO.getPca_memo());
			pstmt.setString(6, pcApplicationVO.getPca_status());
			pstmt.setDate(7, pcApplicationVO.getPca_review_date());

			pstmt.executeUpdate();
			
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);

			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);
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
	public void update(PcApplicationVO pcApplicationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcApplicationVO.getPca_status());
			pstmt.setDate(2, pcApplicationVO.getPca_review_date());
			pstmt.setString(3, pcApplicationVO.getPca_no());
			
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
	public void delete(String pca_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pca_no);

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
	public PcApplicationVO findByPrimaryKey(String pca_no) {
		PcApplicationVO pcApplicationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pca_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcApplicationVO = new PcApplicationVO();
				pcApplicationVO.setPca_no(rs.getString("pca_no"));
				pcApplicationVO.setPc_no(rs.getString("pc_no"));
				pcApplicationVO.setMem_no(rs.getString("mem_no"));
				pcApplicationVO.setPca_appdate(rs.getTimestamp("pca_appdate"));
				pcApplicationVO.setPca_date(rs.getDate("to_char(pca_date,'yyyy-mm-dd')"));
				pcApplicationVO.setPca_memo(rs.getString("pca_memo"));
				pcApplicationVO.setPca_status(rs.getString("pca_status"));
				pcApplicationVO.setPca_review_date(rs.getDate("to_char(pca_review_date,'yyyy-mm-dd')"));
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
		return pcApplicationVO;
	}

	@Override
	public List<PcApplicationVO> getAll(String pca_status) {
		List<PcApplicationVO> list = new ArrayList<PcApplicationVO>();
		PcApplicationVO pcApplicationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
						
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1, pca_status);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcApplicationVO = new PcApplicationVO();
				pcApplicationVO.setPca_no(rs.getString("pca_no"));
				pcApplicationVO.setPc_no(rs.getString("pc_no"));
				pcApplicationVO.setMem_no(rs.getString("mem_no"));
				pcApplicationVO.setPca_appdate(rs.getTimestamp("pca_appdate"));
				pcApplicationVO.setPca_date(rs.getDate("to_char(pca_date,'yyyy-mm-dd')"));
				pcApplicationVO.setPca_memo(rs.getString("pca_memo"));
				pcApplicationVO.setPca_status(rs.getString("pca_status"));
				pcApplicationVO.setPca_review_date(rs.getDate("to_char(pca_review_date,'yyyy-mm-dd')"));
				list.add(pcApplicationVO); // Store the row in the list
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
	public void updateWithPoint(PcApplicationVO pcApplicationVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcApplicationVO.getPca_status());
			pstmt.setDate(2, pcApplicationVO.getPca_review_date());
			pstmt.setString(3, pcApplicationVO.getPca_no());

			pstmt.executeUpdate();
			
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);

			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);
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
	public void updateWithReport(PcApplicationVO pcApplicationVO, PcReportVO pcReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcApplicationVO.getPca_status());
			pstmt.setDate(2, pcApplicationVO.getPca_review_date());
			pstmt.setString(3, pcApplicationVO.getPca_no());

			pstmt.executeUpdate();
			
			//step2  同時新增一筆point-record資料
			PcReportDAO pcReportDAO = new PcReportDAO();
			pcReportDAO.insert(pcReportVO, con);

			
			//step3  全部完成
			con.commit();
			con.setAutoCommit(true);
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

}
