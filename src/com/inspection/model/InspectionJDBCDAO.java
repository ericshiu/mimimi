package com.inspection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;




public class InspectionJDBCDAO implements InspectionDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT ="INSERT INTO inspection (ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date) VALUES ('INS'||LPAD(to_char(ins_seq.NEXTVAL),7,'0'), ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT ="SELECT ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date FROM inspection";
	private static final String GET_ONE_STMT ="SELECT ins_no, mem_no, ins_hospital, ins_outoatuent, ins_resetvation_no,ins_date FROM inspection WHERE ins_no";
	private static final String DELETE ="DELETE FROM inspection where ins_no = ?";
	private static final String UPDATE ="UPDATE inspection set mem_no=?, ins_hospital=?, ins_outoatuent=?, ins_resetvation_no=?,ins_date=? where ins_no = ?";
//	@Override
//	public void insert(InspectionVO inspectionVO) {
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
//            pstmt.setString(1, inspectionVO.getMem_no());
//            pstmt.setString(2, inspectionVO.getIns_hospital());
//            pstmt.setString(3,inspectionVO.getIns_outoatuent());
//            pstmt.setInt(4,inspectionVO.getIns_resetvation_no());
//            pstmt.setTimestamp(5,inspectionVO.getIns_date());
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
	public void update(InspectionVO inspectionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
				
			
            pstmt.setString(1, inspectionVO.getMem_no());
            pstmt.setString(2, inspectionVO.getIns_hospital());
            pstmt.setString(3,inspectionVO.getIns_outoatuent());
            pstmt.setInt(4,inspectionVO.getIns_resetvation_no());
            pstmt.setTimestamp(5,inspectionVO.getIns_date());
			pstmt.setString(6, inspectionVO.getIns_no());
			

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
	public void delete(String ins_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ins_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除胎教音樂編號"+ins_no);
			
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
	public InspectionVO findByPrimaryKey(String ins_no) {
		// TODO Auto-generated method stub
		InspectionVO inspectionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return inspectionVO;
	}
	@Override
	public List<InspectionVO> getAll() {
		// TODO Auto-generated method stub
		List<InspectionVO> list = new ArrayList<InspectionVO>();
		InspectionVO inspectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	
	public Set<InspectionVO> inspectionVOByIns_no(Integer ins_no) {
		Set<InspectionVO> set = new LinkedHashSet<InspectionVO>();
		InspectionVO inspectionVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ins_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				inspectionVO = new InspectionVO();
				inspectionVO = new InspectionVO();
				inspectionVO.setIns_no(rs.getString("ins_no"));
				inspectionVO.setMem_no(rs.getString("mem_no"));
				inspectionVO.setIns_hospital(rs.getString("ins_hospital"));
				inspectionVO.setIns_outoatuent(rs.getString("ins_outoatuent"));
				inspectionVO.setIns_resetvation_no(rs.getInt("ins_resetvation_no"));
				inspectionVO.setIns_date(rs.getTimestamp("ins_date"));
				set.add(inspectionVO); // Store the row in the vector
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
	
	
	
	private InspectionDAO_interface dao;
	public static void main(String[] args){
		InspectionJDBCDAO dao = new InspectionJDBCDAO();
		//新增
//		InspectionVO inspectionVO1 = new InspectionVO();
//		inspectionVO1.setMem_no("測試");
//		inspectionVO1.setIns_hospital("長庚");
//		inspectionVO1.setIns_outoatuent("婦產科");
//		inspectionVO1.setIns_resetvation_no(87);
//		inspectionVO1.setIns_date(java.sql.Date.valueOf("2017-04-25"));
//		dao.insert(inspectionVO1);
//		
		//修改
//		
//		InspectionVO inspectionVO2 = new InspectionVO();
//		inspectionVO2.setIns_no("INS0000003");
//		inspectionVO2.setMem_no("測試");
//		inspectionVO2.setIns_hospital("長庚");
//		inspectionVO2.setIns_outoatuent("婦產科");
//		inspectionVO2.setIns_resetvation_no(87);
//		inspectionVO2.setIns_date(java.sql.Timestamp.valueOf("2017-04-25 12:12:12"));
//		dao.update(inspectionVO2);
		
		//刪除
		
//		dao.delete("INS0000003");
		
		//查詢全部
		
		List<InspectionVO> list = dao.getAll();
		for (InspectionVO ainspection : list) {
			System.out.print(ainspection.getIns_no() + ",");
			System.out.print(ainspection.getMem_no() + ",");
			System.out.print(ainspection.getIns_hospital() + ",");
			System.out.print(ainspection.getIns_outoatuent() + ",");
			System.out.print(ainspection.getIns_resetvation_no() + ",");
			System.out.print(ainspection.getIns_date() + ",");
			System.out.println();
		}
		
		 //查詢某一個編號
//		Set<GrowingVO> set = dao.getGrowingVOByGro_no(750001);
//		for (GrowingVO aGrowingByGro_no : set) {
//			System.out.print(aGrowingByGro_no.getGro_no() + ",");
//			System.out.print(aGrowingByGro_no.getGro_week() + ",");
//			System.out.print(aGrowingByGro_no.getGro_introduction() + ",");
//			System.out.print(aGrowingByGro_no.getGro_precaution() + ",");
//			System.out.print(aGrowingByGro_no.getGro_inspection_introduction() + ",");
//			System.out.print(aGrowingByGro_no.getGro_image() + ",");
//			System.out.println();
//			}
	}
	@Override
	public void insert(InspectionVO inspectionVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}
	
}
