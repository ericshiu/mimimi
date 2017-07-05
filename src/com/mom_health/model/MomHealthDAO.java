package com.mom_health.model;

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

public class MomHealthDAO implements MomHealthDAO_interface {
	
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	
	private static final String INSERT_STMT ="INSERT INTO mom_health (mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat) VALUES ('MOM'||LPAD(to_char(mh_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT ="SELECT mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat FROM mom_health order by mh_date DESC";
	private static final String GET_ONE_STMT ="SELECT mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat FROM mom_health WHERE mh_no = ?";
	private static final String DELETE ="DELETE FROM mom_health where mh_no = ?";
	private static final String UPDATE ="UPDATE mom_health set mem_no=?,mh_date=?,mh_weight=?,mh_ststolic=?,mh_diastolic=?,mh_heartbeat=?,mh_protein=?,mh_baby_heartbeat=? where mh_no = ?";

	public void insert(MomHealthVO mom_healthVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

		
			con = ds.getConnection();
			
			// step1
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			System.out.println("dao1");
			
            pstmt.setString(1, mom_healthVO.getMem_no());
            System.out.println("dao11-1");
            pstmt.setDate(2, mom_healthVO.getMh_date());
            System.out.println("dao11-1");
            pstmt.setInt(3, mom_healthVO.getMh_weight());
            System.out.println("dao11-1");
            pstmt.setInt(4,mom_healthVO.getMh_ststolic());
            System.out.println("dao11");
            pstmt.setInt(5,mom_healthVO.getMh_diastolic());
            System.out.println("dao111");
            pstmt.setInt(6,mom_healthVO.getMh_heartbeat());
            pstmt.setString(7, mom_healthVO.getMh_protein());
            pstmt.setInt(8,mom_healthVO.getMh_baby_heartbeat());
            System.out.println("dao1111");
            pstmt.executeUpdate();
			System.out.println("dao3");
			//step2  同時新增一筆point-record資料
			PointRecordDAO pointRecordDAO = new PointRecordDAO();
			pointRecordDAO.insert(pointRecordVO, con);
			System.out.println("dao4");
			
			//step3 更新會員積分
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.updatePoint(memberVO, con);
			System.out.println("dao5");
			//step3  全部完成
			con.commit();
			System.out.println("dao6");
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
	
	public void update(MomHealthVO mom_healthVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, mom_healthVO.getMem_no());
            pstmt.setDate(2, mom_healthVO.getMh_date());
            pstmt.setInt(3, mom_healthVO.getMh_weight());
            pstmt.setInt(4,mom_healthVO.getMh_ststolic());
            pstmt.setInt(5,mom_healthVO.getMh_diastolic());
            pstmt.setInt(6,mom_healthVO.getMh_heartbeat());
            pstmt.setString(7, mom_healthVO.getMh_protein());
            pstmt.setInt(8,mom_healthVO.getMh_baby_heartbeat());
			pstmt.setString(9, mom_healthVO.getMh_no());
			

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
	public void delete(String mh_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			
			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mh_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除媽媽健康編號"+mh_no);
			
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
	public MomHealthVO findByPrimaryKey(String mh_no) {
		// TODO Auto-generated method stub
		MomHealthVO mom_healthVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("daoaaaaa");
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mh_no);

			rs = pstmt.executeQuery();
			System.out.println("dao");
			while (rs.next()) {
				// growingVO 也稱為Domain objects
				mom_healthVO = new MomHealthVO();
				mom_healthVO.setMh_no(rs.getString("mh_no"));
				mom_healthVO.setMem_no(rs.getString("mem_no"));
				mom_healthVO.setMh_date(rs.getDate("mh_date"));				
				mom_healthVO.setMh_weight(rs.getInt("mh_weight"));
				mom_healthVO.setMh_ststolic(rs.getInt("mh_ststolic"));				
				mom_healthVO.setMh_diastolic(rs.getInt("mh_diastolic"));
				mom_healthVO.setMh_heartbeat(rs.getInt("mh_heartbeat"));
				mom_healthVO.setMh_protein(rs.getString("mh_protein"));
				mom_healthVO.setMh_baby_heartbeat(rs.getInt("mh_baby_heartbeat"));

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
		return mom_healthVO;
	}
	
	
	
	public Set<MomHealthVO> getMom_healthVOBymh_no(Integer mh_no) {
		Set<MomHealthVO> set = new LinkedHashSet<MomHealthVO>();
		MomHealthVO mom_healthVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, mh_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				mom_healthVO.setMh_no(rs.getString("mh_no"));
				mom_healthVO.setMem_no(rs.getString("mem_no"));
				mom_healthVO.setMh_date(rs.getDate("mh_date"));				
				mom_healthVO.setMh_weight(rs.getInt("mh_weight"));
				mom_healthVO.setMh_ststolic(rs.getInt("mh_ststolic"));				
				mom_healthVO.setMh_diastolic(rs.getInt("mh_diastolic"));
				mom_healthVO.setMh_heartbeat(rs.getInt("mh_heartbeat"));
				mom_healthVO.setMh_protein(rs.getString("mh_protein"));
				mom_healthVO.setMh_baby_heartbeat(rs.getInt("mh_baby_heartbeat"));
				set.add(mom_healthVO); // Store the row in the vector
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
	
	
	
	
	public List<MomHealthVO> getAll() {
		// TODO Auto-generated method stub
		List<MomHealthVO> list = new ArrayList<MomHealthVO>();
		MomHealthVO mom_healthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mom_healthVO = new MomHealthVO();
				mom_healthVO.setMh_no(rs.getString("mh_no"));
				mom_healthVO.setMem_no(rs.getString("mem_no"));
				mom_healthVO.setMh_date(rs.getDate("mh_date"));				
				mom_healthVO.setMh_weight(rs.getInt("mh_weight"));
				mom_healthVO.setMh_ststolic(rs.getInt("mh_ststolic"));				
				mom_healthVO.setMh_diastolic(rs.getInt("mh_diastolic"));
				mom_healthVO.setMh_heartbeat(rs.getInt("mh_heartbeat"));
				mom_healthVO.setMh_protein(rs.getString("mh_protein"));
				mom_healthVO.setMh_baby_heartbeat(rs.getInt("mh_baby_heartbeat"));
				list.add(mom_healthVO); // Store the row in the list
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
