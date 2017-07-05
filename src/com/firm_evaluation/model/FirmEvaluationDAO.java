package com.firm_evaluation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.firm.model.*;
import com.member.model.*;
import com.point_record.model.*;
import com.postpartum_care.model.*;

public class FirmEvaluationDAO implements FirmEvaluationDAO_interface{
	
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
			"INSERT INTO firm_evaluation (fe_no,fp_no,mem_no,fe_type,fe_content,fe_date) VALUES ('FE' || LPAD(to_char(pca_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT_SELECTED = 
			"SELECT fe_no,fp_no,mem_no,fe_type,fe_content,to_char(fe_date,'yyyy-mm-dd') FROM firm_evaluation where fp_no=? and fe_type=? order by fe_no desc";

//	private static final String GET_ALL_STMT_MEMBER = 
//			"SELECT fe_no,fp_no,mem_no,fe_type,fe_content,to_char(fe_date,'yyyy-mm-dd') FROM firm_evaluation where mem_no=? order by fe_no desc";
	
	private static final String GET_ALL_STMT_MEMBER = 
			"SELECT fe_no,fp_no,mem_no,fe_type,fe_content,to_char(fe_date,'yyyy-mm-dd') FROM firm_evaluation where fp_no=? and mem_no=? order by fe_no desc";
	
	private static final String GET_ALL_STMT_EVAED = 
			"SELECT fe_no,fp_no,mem_no,fe_type,fe_content,to_char(fe_date,'yyyy-mm-dd') FROM firm_evaluation where mem_no=? order by fe_no desc";	
	
	@Override
	public Set<FirmEvaluationVO> getAllByFirmAndType(String fp_no, String fe_type) {
		Set<FirmEvaluationVO> set = new LinkedHashSet<FirmEvaluationVO>();
		FirmEvaluationVO firmEvaluationVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_SELECTED);
			pstmt.setString(1, fp_no);
			pstmt.setString(2, fe_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firmEvaluationVO = new FirmEvaluationVO();
				firmEvaluationVO.setFe_no(rs.getString("fe_no"));
				firmEvaluationVO.setFp_no(rs.getString("fp_no"));
				firmEvaluationVO.setMem_no(rs.getString("mem_no"));
				firmEvaluationVO.setFe_type(rs.getString("fe_type"));
				firmEvaluationVO.setFe_content(rs.getString("fe_content"));
				firmEvaluationVO.setFe_date(rs.getDate("to_char(fe_date,'yyyy-mm-dd')"));
				set.add(firmEvaluationVO); // Store the row in the vector
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
	public void insertToFirm(FirmEvaluationVO firmEvaluationVO, PointRecordVO pointRecordVO, FirmVO firmVO, MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, firmEvaluationVO.getFp_no());
			pstmt.setString(2, firmEvaluationVO.getMem_no());
			pstmt.setString(3, firmEvaluationVO.getFe_type());
			pstmt.setString(4, firmEvaluationVO.getFe_content());
			pstmt.setDate(5, firmEvaluationVO.getFe_date());



			pstmt.executeUpdate();
			
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);

			
			//step3 更新賣場廠商評價
			FirmDAO firmDAO = new FirmDAO();
			firmDAO.updateEvaluation(firmVO, con);
			
			
			//step4 更新會員積分
			MemberDAO memDAO = new MemberDAO();
			memDAO.updatePoint(memberVO, con);				
			
			//step5  全部完成
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
	public void insertToPC(FirmEvaluationVO firmEvaluationVO, PointRecordVO pointRecordVO,
			PostpartumCareVO postpartumCareVO, MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, firmEvaluationVO.getFp_no());
			pstmt.setString(2, firmEvaluationVO.getMem_no());
			pstmt.setString(3, firmEvaluationVO.getFe_type());
			pstmt.setString(4, firmEvaluationVO.getFe_content());
			pstmt.setDate(5, firmEvaluationVO.getFe_date());

			pstmt.executeUpdate();


			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);


			//step3 更新產後廠商評價
			PostpartumCareDAO postpartumCareDAO = new PostpartumCareDAO();
			postpartumCareDAO.updateEvaluation(postpartumCareVO, con);

			//step4 更新會員積分
			MemberDAO memDAO = new MemberDAO();
			memDAO.updatePoint(memberVO, con);			

			//step5  全部完成
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
	public Set<FirmEvaluationVO> getAllByMem_no(String mem_no) {
		Set<FirmEvaluationVO> set = new LinkedHashSet<FirmEvaluationVO>();
		FirmEvaluationVO firmEvaluationVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMBER);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firmEvaluationVO = new FirmEvaluationVO();
				firmEvaluationVO.setFe_no(rs.getString("fe_no"));
				firmEvaluationVO.setFp_no(rs.getString("fp_no"));
				firmEvaluationVO.setMem_no(rs.getString("mem_no"));
				firmEvaluationVO.setFe_type(rs.getString("fe_type"));
				firmEvaluationVO.setFe_content(rs.getString("fe_content"));
				firmEvaluationVO.setFe_date(rs.getDate("to_char(fe_date,'yyyy-mm-dd')"));
				set.add(firmEvaluationVO); // Store the row in the vector
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
	public Set<FirmEvaluationVO> getAllByEvaMem_no(String mem_no) {
		Set<FirmEvaluationVO> set = new LinkedHashSet<FirmEvaluationVO>();
		FirmEvaluationVO firmEvaluationVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_EVAED);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				firmEvaluationVO = new FirmEvaluationVO();
				firmEvaluationVO.setFe_no(rs.getString("fe_no"));
				firmEvaluationVO.setFp_no(rs.getString("fp_no"));
				firmEvaluationVO.setMem_no(rs.getString("mem_no"));
				firmEvaluationVO.setFe_type(rs.getString("fe_type"));
				firmEvaluationVO.setFe_content(rs.getString("fe_content"));
				firmEvaluationVO.setFe_date(rs.getDate("to_char(fe_date,'yyyy-mm-dd')"));
				set.add(firmEvaluationVO); // Store the row in the vector
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
	public FirmEvaluationVO getEvaByFp_no(String fp_no, String mem_no) {
		FirmEvaluationVO firmEvaluationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMBER);
			pstmt.setString(1, fp_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				firmEvaluationVO = new FirmEvaluationVO();
				firmEvaluationVO.setFe_no(rs.getString("fe_no"));
				firmEvaluationVO.setFp_no(rs.getString("fp_no"));
				firmEvaluationVO.setMem_no(rs.getString("mem_no"));
				firmEvaluationVO.setFe_type(rs.getString("fe_type"));
				firmEvaluationVO.setFe_content(rs.getString("fe_content"));
				firmEvaluationVO.setFe_date(rs.getDate("to_char(fe_date,'yyyy-mm-dd')"));
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
		return firmEvaluationVO;
	}





	@Override
	public void insertFirmSimple(FirmEvaluationVO firmEvaluationVO, FirmVO firmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, firmEvaluationVO.getFp_no());
			pstmt.setString(2, firmEvaluationVO.getMem_no());
			pstmt.setString(3, firmEvaluationVO.getFe_type());
			pstmt.setString(4, firmEvaluationVO.getFe_content());
			pstmt.setDate(5, firmEvaluationVO.getFe_date());

			pstmt.executeUpdate();

			//step2 更新賣場廠商評價
			FirmDAO firmDAO = new FirmDAO();
			firmDAO.updateEvaluation(firmVO, con);
						
			
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
