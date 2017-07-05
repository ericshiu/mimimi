package com.inspection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class InspectionDAO implements InspectionDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT ="INSERT INTO inspection (ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date) VALUES ('INS'||LPAD(to_char(ins_seq.NEXTVAL),7,'0'), ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT ="SELECT ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date FROM inspection order by ins_date DESC";
	private static final String GET_ONE_STMT ="SELECT ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date FROM inspection WHERE ins_no = ?";
	private static final String DELETE ="DELETE FROM inspection where ins_no = ?";
	private static final String UPDATE ="UPDATE inspection set mem_no=?, ins_hospital=?, ins_outoatuent=?, ins_resetvation_no=?,ins_date=? where ins_no = ?";
	
	public void insert(InspectionVO inspectionVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

	
			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			
            pstmt.setString(1, inspectionVO.getMem_no());
            pstmt.setString(2, inspectionVO.getIns_hospital());
            pstmt.setString(3,inspectionVO.getIns_outoatuent());
            pstmt.setInt(4,inspectionVO.getIns_resetvation_no());
            pstmt.setTimestamp(5,inspectionVO.getIns_date());
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

	public void update(InspectionVO inspectionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {


			con = ds.getConnection();;
			pstmt = con.prepareStatement(UPDATE);
				
			
            pstmt.setString(1, inspectionVO.getMem_no());
            pstmt.setString(2, inspectionVO.getIns_hospital());
            pstmt.setString(3,inspectionVO.getIns_outoatuent());
            pstmt.setInt(4,inspectionVO.getIns_resetvation_no());
            pstmt.setTimestamp(5,inspectionVO.getIns_date());
			pstmt.setString(6, inspectionVO.getIns_no());
			

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

	public void delete(String ins_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

		
			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ins_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除胎教音樂編號"+ins_no);
			
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

	public InspectionVO findByPrimaryKey(String ins_no) {
		// TODO Auto-generated method stub
		InspectionVO inspectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ins_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// growingVO 也稱為Domain objects
				inspectionVO = new InspectionVO();
				inspectionVO.setIns_no(rs.getString("ins_no"));
				inspectionVO.setMem_no(rs.getString("mem_no"));
				inspectionVO.setIns_hospital(rs.getString("ins_hospital"));
				inspectionVO.setIns_outoatuent(rs.getString("ins_outoatuent"));
				inspectionVO.setIns_resetvation_no(rs.getInt("ins_resetvation_no"));
				inspectionVO.setIns_date(rs.getTimestamp("ins_date"));
				

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
		return inspectionVO;
	}
	
	public List<InspectionVO> getAll() {
		// TODO Auto-generated method stub
		List<InspectionVO> list = new ArrayList<InspectionVO>();
		InspectionVO inspectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				inspectionVO = new InspectionVO();
				inspectionVO.setIns_no(rs.getString("ins_no"));
				inspectionVO.setMem_no(rs.getString("mem_no"));
				inspectionVO.setIns_hospital(rs.getString("ins_hospital"));
				inspectionVO.setIns_outoatuent(rs.getString("ins_outoatuent"));
				inspectionVO.setIns_resetvation_no(rs.getInt("ins_resetvation_no"));
				inspectionVO.setIns_date(rs.getTimestamp("ins_date"));
				list.add(inspectionVO); // Store the row in the list
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

}
