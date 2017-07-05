package com.mom_health.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class MomHealthJDBCDAO implements MomHealthDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT ="INSERT INTO mom_health (mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat) VALUES ('MOM'||LPAD(to_char(mh_seq.NEXTVAL),7,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT ="SELECT mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat FROM mom_health";
	private static final String GET_ONE_STMT ="SELECT mh_no,mem_no,mh_date,mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat FROM mom_health WHERE mh_no = ? ";
	private static final String DELETE ="DELETE FROM mom_health where mh_no = ?";
	private static final String UPDATE ="UPDATE mom_health set mem_no=?,mh_date=?,mh_weight=?,mh_ststolic=?,mh_diastolic=?,mh_heartbeat=?,mh_protein=?,mh_baby_heartbeat=? where mh_no = ?";
//	@Override
//	public void insert(MomHealthVO mom_healthVO) {
//		// TODO Auto-generated method stub
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			
//            pstmt.setString(1, mom_healthVO.getMem_no());
//            pstmt.setDate(2, mom_healthVO.getMh_date());
//            pstmt.setInt(3, mom_healthVO.getMh_weight());
//            pstmt.setInt(4,mom_healthVO.getMh_ststolic());
//            pstmt.setInt(5,mom_healthVO.getMh_diastolic());
//            pstmt.setInt(6,mom_healthVO.getMh_heartbeat());
//            pstmt.setString(7, mom_healthVO.getMh_protein());
//            pstmt.setInt(8,mom_healthVO.getMh_baby_heartbeat());
//           
//           
//            
//			
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//	}
	@Override
	public void update(MomHealthVO mom_healthVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, mh_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除媽媽健康編號"+mh_no);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mh_no);

			rs = pstmt.executeQuery();

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	
	public Set<MomHealthVO> getMom_healthVOBymh_no(String mh_no) {
		Set<MomHealthVO> set = new LinkedHashSet<MomHealthVO>();
		MomHealthVO mom_healthVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, mh_no);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public List<MomHealthVO> getAll() {
		// TODO Auto-generated method stub
		List<MomHealthVO> list = new ArrayList<MomHealthVO>();
		MomHealthVO mom_healthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return list;
	}
	public static void main(String[] args){
		MomHealthJDBCDAO dao = new MomHealthJDBCDAO();
		//新增
//		MomHealthVO mom_healthVO1 = new MomHealthVO();
//		mom_healthVO1.setMem_no("MEM0000001");
//		mom_healthVO1.setMh_date(java.sql.Date.valueOf("2017-04-25"));
//		mom_healthVO1.setMh_weight(87);
//		mom_healthVO1.setMh_ststolic(87);
//		mom_healthVO1.setMh_diastolic(242);
//		mom_healthVO1.setMh_heartbeat(80);
//		mom_healthVO1.setMh_protein("測試");
//		mom_healthVO1.setMh_baby_heartbeat(150);		
//		dao.insert(mom_healthVO1);
//		
		//修改
//		
//		MomHealthVO mom_healthVO2 = new MomHealthVO();
//		mom_healthVO2.setMh_no("MOM0000003");
//		mom_healthVO2.setMem_no("測試");
//		mom_healthVO2.setMh_date(java.sql.Date.valueOf("2017-04-25"));
//		mom_healthVO2.setMh_weight(87);
//		mom_healthVO2.setMh_ststolic(87);
//		mom_healthVO2.setMh_diastolic(242);
//		mom_healthVO2.setMh_heartbeat(80);
//		mom_healthVO2.setMh_protein("測試成功");
//		mom_healthVO2.setMh_baby_heartbeat(150);
//		dao.update(mom_healthVO2);
//		
		//刪除
		
//		dao.delete("MOM0000003");
		
		//查詢全部
		
//		List<Mom_healthVO> list = dao.getAll();
//		for (Mom_healthVO aMom_healthVO : list) {
//			System.out.print(aMom_healthVO.getMh_no() + ",");
//			System.out.print(aMom_healthVO.getMem_no() + ",");
//			System.out.print(aMom_healthVO.getMh_date() + ",");
//			System.out.print(aMom_healthVO.getMh_weight() + ",");
//			System.out.print(aMom_healthVO.getMh_ststolic() + ",");
//			System.out.print(aMom_healthVO.getMh_diastolic() + ",");
//			System.out.print(aMom_healthVO.getMh_heartbeat() + ",");
//			System.out.print(aMom_healthVO.getMh_protein() + ",");
//			System.out.print(aMom_healthVO.getMh_baby_heartbeat() + ",");
//			
//			System.out.println();
//		}
//		
		 //查詢某一個編號
		Set<MomHealthVO> set = dao.getMom_healthVOBymh_no("MOM0000001");
		for (MomHealthVO aMom_healthByGro_no : set) {
			System.out.print(aMom_healthByGro_no.getMh_no() + ",");
			System.out.print(aMom_healthByGro_no.getMem_no() + ",");
			System.out.print(aMom_healthByGro_no.getMh_date() + ",");
			System.out.print(aMom_healthByGro_no.getMh_weight() + ",");
			System.out.print(aMom_healthByGro_no.getMh_ststolic() + ",");
			System.out.print(aMom_healthByGro_no.getMh_diastolic() + ",");
			System.out.print(aMom_healthByGro_no.getMh_heartbeat() + ",");
			System.out.print(aMom_healthByGro_no.getMh_protein() + ",");
			System.out.print(aMom_healthByGro_no.getMh_baby_heartbeat() + ",");
			System.out.println();
			}
	}
	@Override
	public void insert(MomHealthVO mom_healthVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}
	
}
