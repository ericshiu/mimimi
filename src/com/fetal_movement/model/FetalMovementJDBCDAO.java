package com.fetal_movement.model;

import java.util.*;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

import java.sql.*;

public class FetalMovementJDBCDAO implements FetalMovementDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = "INSERT INTO fetal_movement (fm_no,mem_no,fm_date,fm_lv) VALUES ('FM'||LPAD(to_char(fm_seq.NEXTVAL),8,'0'), ?, sysdate, ?)";
	private static final String GET_ALL_STMT = "SELECT fm_no,mem_no,fm_date,fm_lv FROM fetal_movement order by fm_no DESC";
	private static final String GET_ONE_STMT = "SELECT fm_no,mem_no,fm_date,fm_lv FROM fetal_movement where fm_no = ?";
	
	private static final String DELETE = "DELETE FROM fetal_movement where fm_no = ?";
	
	private static final String UPDATE = "UPDATE fetal_movement set mem_no=?,fm_date=?,fm_lv=? where fm_no = ?";
	
	
//	@Override
//	public void insert(FetalMovementVO fetal_movementVO) {
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
//			pstmt.setString(1, fetal_movementVO.getMem_no());
//			pstmt.setTimestamp(2, fetal_movementVO.getFm_date());
//			pstmt.setString(3, fetal_movementVO.getFm_lv());
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
//	}
	@Override
	public void update(FetalMovementVO fetal_movementVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
				
			pstmt.setString(1, fetal_movementVO.getMem_no());
			pstmt.setTimestamp(2, fetal_movementVO.getFm_date());
			pstmt.setString(3, fetal_movementVO.getFm_lv());
			pstmt.setString(4, fetal_movementVO.getFm_no());
			

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
	public void delete(String fm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount_EMPs = 0;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, fm_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除編號"+fm_no);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
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
	public FetalMovementVO findByPrimaryKey(String fm_no) {
		// TODO Auto-generated method stub
		
		FetalMovementVO fetal_movementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
		
		return fetal_movementVO;
	}
	@Override
	public List<FetalMovementVO> getAll() {

		List<FetalMovementVO> list = new ArrayList<FetalMovementVO>();
		FetalMovementVO fetal_movementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	public Set<FetalMovementVO> getFetal_movementByFm_no(String fm_no) {
		Set<FetalMovementVO> set = new LinkedHashSet<FetalMovementVO>();
		FetalMovementVO fetal_movementVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args){
		FetalMovementJDBCDAO dao = new FetalMovementJDBCDAO();
		//新增
		
//		FetalMovementVO fetal_movementVO1 = new FetalMovementVO();
//		fetal_movementVO1.setMem_no("212010");
//		fetal_movementVO1.setFm_date(java.sql.Timestamp.valueOf("2017-04-25 13:12:22"));
//		fetal_movementVO1.setFm_lv("A");
//		dao.insert(fetal_movementVO1);
	
		//修改
//		
//		Fetal_movementVO fetal_movementVO2 = new Fetal_movementVO();
//		fetal_movementVO2.setFm_no("FM00000001");
//		fetal_movementVO2.setMem_no("測試");
//		fetal_movementVO2.setFm_date(java.sql.Timestamp.valueOf("2017-04-25 13:12:22"));
//		fetal_movementVO2.setFm_lv("測試");
//		dao.update(fetal_movementVO2);
		
		//刪除
		
//		dao.delete("FM00000001");
		
		//查詢全部
		
		List<FetalMovementVO> list = dao.getAll();
		for (FetalMovementVO aFetal_movement : list) {
			System.out.print(aFetal_movement.getFm_no() + ",");
			System.out.print(aFetal_movement.getMem_no() + ",");
			System.out.print(aFetal_movement.getFm_date() + ",");
			System.out.print(aFetal_movement.getFm_lv() + ",");
			System.out.println();
		}
		
		 //查詢某一個編號
//		Set<Fetal_movementVO> set = dao.getFetal_movementByFm_no("FM00000001");
//		for (Fetal_movementVO aFetal_movement : set) {
//			System.out.print(aFetal_movement.getFm_no() + ",");
//			System.out.print(aFetal_movement.getMem_no() + ",");
//			System.out.print(aFetal_movement.getFm_date() + ",");
//			System.out.print(aFetal_movement.getFm_lv() + ",");
//			System.out.println();
//			}
	}
	@Override
	public void insert(FetalMovementVO fetal_movementVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}
}
