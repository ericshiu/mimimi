package com.pc_report.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class PcReportDAO implements PcReportDAO_interface{
	
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
			"INSERT INTO pc_report (pcr_no,pc_no,mem_no,pcr_type,pcr_content,pcr_date,pcr_status,pcr_review_date) VALUES ('PCR' || LPAD(to_char(pcr_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT pcr_no,pc_no,mem_no,pcr_type,pcr_content,to_char(pcr_date,'yyyy-mm-dd'),pcr_status,to_char(pcr_review_date,'yyyy-mm-dd') FROM pc_report order by pcr_no";
	private static final String GET_STATUS = 
			"SELECT pcr_no,pc_no,mem_no,pcr_type,pcr_content,to_char(pcr_date,'yyyy-mm-dd'),pcr_status,to_char(pcr_review_date,'yyyy-mm-dd') FROM pc_report where pcr_status=? order by pcr_no ";
	
	private static final String GET_ONE_STMT = 
			"SELECT pcr_no,pc_no,mem_no,pcr_type,pcr_content,to_char(pcr_date,'yyyy-mm-dd'),pcr_status,to_char(pcr_review_date,'yyyy-mm-dd') FROM pc_report where pcr_no = ?";
	private static final String DELETE = 
			"DELETE FROM pc_report where pcr_no = ?";
	private static final String UPDATE = 
			"UPDATE pc_report SET pcr_status=?, pcr_review_date=? where pcr_no = ?";	
	
	@Override
	public void insert(PcReportVO pcReportVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pcReportVO.getPc_no());
			pstmt.setString(2, pcReportVO.getMem_no());
			pstmt.setString(3, pcReportVO.getPcr_type());
			pstmt.setString(4, pcReportVO.getPcr_content());
			pstmt.setDate(5, pcReportVO.getPcr_date());
			pstmt.setString(6, pcReportVO.getPcr_status());
			pstmt.setDate(7, pcReportVO.getPcr_review_date());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pc-r");
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

	@Override
	public void update(PcReportVO pcReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcReportVO.getPcr_status());
			pstmt.setDate(2, pcReportVO.getPcr_review_date());
			pstmt.setString(3, pcReportVO.getPcr_no());
			
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
	public void delete(String pcr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pcr_no);

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
	public PcReportVO findByPrimaryKey(String pcr_no) {
		PcReportVO pcReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pcr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcReportVO = new PcReportVO();
				pcReportVO.setPcr_no(rs.getString("pcr_no"));
				pcReportVO.setPc_no(rs.getString("pc_no"));
				pcReportVO.setMem_no(rs.getString("mem_no"));
				pcReportVO.setPcr_type(rs.getString("pcr_type"));
				pcReportVO.setPcr_content(rs.getString("pcr_content"));
				pcReportVO.setPcr_date(rs.getDate("to_char(pcr_date,'yyyy-mm-dd')"));
				pcReportVO.setPcr_status(rs.getString("pcr_status"));
				pcReportVO.setPcr_review_date(rs.getDate("to_char(pcr_review_date,'yyyy-mm-dd')"));
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
		return pcReportVO;
	}

	@Override
	public List<PcReportVO> getAll() {
		List<PcReportVO> list = new ArrayList<PcReportVO>();
		PcReportVO pcReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcReportVO = new PcReportVO();
				pcReportVO.setPcr_no(rs.getString("pcr_no"));
				pcReportVO.setPc_no(rs.getString("pc_no"));
				pcReportVO.setMem_no(rs.getString("mem_no"));
				pcReportVO.setPcr_type(rs.getString("pcr_type"));
				pcReportVO.setPcr_content(rs.getString("pcr_content"));
				pcReportVO.setPcr_date(rs.getDate("to_char(pcr_date,'yyyy-mm-dd')"));
				pcReportVO.setPcr_status(rs.getString("pcr_status"));
				pcReportVO.setPcr_review_date(rs.getDate("to_char(pcr_review_date,'yyyy-mm-dd')"));
				list.add(pcReportVO); // Store the row in the list
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
	public List<PcReportVO> getAllStatus(String pcr_status) {
		List<PcReportVO> list = new ArrayList<PcReportVO>();
		PcReportVO pcReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STATUS);

			pstmt.setString(1, pcr_status);

			rs = pstmt.executeQuery();
			
		

			while (rs.next()) {
				pcReportVO = new PcReportVO();
				pcReportVO.setPcr_no(rs.getString("pcr_no"));
				pcReportVO.setPc_no(rs.getString("pc_no"));
				pcReportVO.setMem_no(rs.getString("mem_no"));
				pcReportVO.setPcr_type(rs.getString("pcr_type"));
				pcReportVO.setPcr_content(rs.getString("pcr_content"));
				pcReportVO.setPcr_date(rs.getDate("to_char(pcr_date,'yyyy-mm-dd')"));
				pcReportVO.setPcr_status(rs.getString("pcr_status"));
				pcReportVO.setPcr_review_date(rs.getDate("to_char(pcr_review_date,'yyyy-mm-dd')"));
				list.add(pcReportVO); // Store the row in the list
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
	public void updateWithPoint(PcReportVO pcReportVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcReportVO.getPcr_status());
			pstmt.setDate(2, pcReportVO.getPcr_review_date());
			pstmt.setString(3, pcReportVO.getPcr_no());

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


}
