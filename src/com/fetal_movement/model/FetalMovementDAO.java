package com.fetal_movement.model;

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

import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordDAO;
import com.point_record.model.PointRecordVO;

public class FetalMovementDAO implements FetalMovementDAO_interface {
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO fetal_movement (fm_no,mem_no,fm_date,fm_lv) VALUES ('FM'||LPAD(to_char(fm_seq.NEXTVAL),8,'0'), ?, sysdate, ?)";
	private static final String GET_ALL_STMT = "SELECT fm_no,mem_no,fm_date,fm_lv FROM fetal_movement order by fm_no DESC";
	private static final String GET_ONE_STMT = "SELECT fm_no,mem_no,fm_date,fm_lv FROM fetal_movement where fm_no = ?";
	
	private static final String DELETE = "DELETE FROM fetal_movement where fm_no = ?";
	
	private static final String UPDATE = "UPDATE fetal_movement set mem_no=?,fm_date=?,fm_lv=? where fm_no = ?";
	
	
//	
	public void insert(FetalMovementVO fetal_movementVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

		
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, fetal_movementVO.getMem_no());
//			pstmt.setTimestamp(2, fetal_movementVO.getFm_date());
			pstmt.setString(2, fetal_movementVO.getFm_lv());
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

	public void update(FetalMovementVO fetal_movementVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
				
			pstmt.setString(1, fetal_movementVO.getMem_no());
			pstmt.setTimestamp(2, fetal_movementVO.getFm_date());
			pstmt.setString(3, fetal_movementVO.getFm_lv());
			pstmt.setString(4, fetal_movementVO.getFm_no());
			

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

	public void delete(String fm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount_EMPs = 0;

		try {

			
			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, fm_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除編號"+fm_no);
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 
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
	
	public FetalMovementVO findByPrimaryKey(String fm_no) {
		// TODO Auto-generated method stub
		
		FetalMovementVO fetal_movementVO = null;
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
				fetal_movementVO = new FetalMovementVO();
				fetal_movementVO.setFm_no(rs.getString("fm_no"));
				fetal_movementVO.setMem_no(rs.getString("mem_no"));
				fetal_movementVO.setFm_date(rs.getTimestamp("fm_date"));
				fetal_movementVO.setFm_lv(rs.getString("fm_lv"));
				

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
		
		
		return fetal_movementVO;
	}
	
	public List<FetalMovementVO> getAll() {

		List<FetalMovementVO> list = new ArrayList<FetalMovementVO>();
		FetalMovementVO fetal_movementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fetal_movementVO = new FetalMovementVO();
				fetal_movementVO.setFm_no(rs.getString("fm_no"));
				fetal_movementVO.setMem_no(rs.getString("mem_no"));
				fetal_movementVO.setFm_date(rs.getTimestamp("fm_date"));
				fetal_movementVO.setFm_lv(rs.getString("fm_lv"));
				list.add(fetal_movementVO); // Store the row in the list
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
	
	
	public Set<FetalMovementVO> getFetal_movementByFm_no(String fm_no) {
		Set<FetalMovementVO> set = new LinkedHashSet<FetalMovementVO>();
		FetalMovementVO fetal_movementVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, fm_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				fetal_movementVO = new FetalMovementVO();
				fetal_movementVO.setFm_no(rs.getString("fm_no"));
				fetal_movementVO.setMem_no(rs.getString("mem_no"));
				fetal_movementVO.setFm_date(rs.getTimestamp("fm_date"));
				fetal_movementVO.setFm_lv(rs.getString("fm_lv"));	
				set.add(fetal_movementVO); // Store the row in the vector
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
