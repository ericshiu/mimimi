package com.pc_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public class PcReportJDBCDAO implements PcReportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
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
			"UPDATE pc_report SET pc_no=?, mem_no=?, pcr_type=?, pcr_content=?, pcr_date=?, pcr_status=?, pcr_review_date=? where pcr_no = ?";	
	
	@Override
	public void insert(PcReportVO pcReportVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(PcReportVO pcReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pcReportVO.getPc_no());
			pstmt.setString(2, pcReportVO.getMem_no());
			pstmt.setString(3, pcReportVO.getPcr_type());
			pstmt.setString(4, pcReportVO.getPcr_content());
			pstmt.setDate(5, pcReportVO.getPcr_date());
			pstmt.setString(6, pcReportVO.getPcr_status());
			pstmt.setDate(7, pcReportVO.getPcr_review_date());
			pstmt.setString(8, pcReportVO.getPcr_no());

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
	public void delete(String pcr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pcr_no);

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
	public PcReportVO findByPrimaryKey(String pcr_no) {
		PcReportVO pcReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(pcReportVO);// Store the row in the list
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS);
			pstmt.setString(1,pcr_status);
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
				list.add(pcReportVO);// Store the row in the list
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
		return list;
	}
	
	
	
	public static void main(String[] args) {
			PcReportJDBCDAO dao = new PcReportJDBCDAO();
	
			// 新增
//			PcReportVO pcReportVO = new PcReportVO();
//			pcReportVO.setPc_no("PC00000003");
//			pcReportVO.setMem_no("MEM0000001");
//			pcReportVO.setPcr_type("預約參觀未到");
//			pcReportVO.setPcr_content("時間到時才打電話來取消");
//			pcReportVO.setPcr_date(java.sql.Date.valueOf("2017-04-20"));
//			pcReportVO.setPcr_status("審核中");
//			dao.insert(pcReportVO);
	
			// 修改
//			PcReportVO pcReportVO2 = new PcReportVO();
//			pcReportVO2.setPcr_no("PCR0000004");
//			pcReportVO2.setPc_no("PC00000003");
//			pcReportVO2.setMem_no("MEM0000001");
//			pcReportVO2.setPcr_type("預約參觀未到");
//			pcReportVO2.setPcr_content("時間到時才打電話來取消222");
//			pcReportVO2.setPcr_date(java.sql.Date.valueOf("2017-04-20"));
//			pcReportVO2.setPcr_status("已完成");
//			pcReportVO2.setPcr_review_date(java.sql.Date.valueOf("2017-04-22"));
//			dao.update(pcReportVO2);
	
			// 刪除
//			dao.delete("PCR0000004");
	
//			// 查詢
//			PcReportVO pcReportVO3 = dao.findByPrimaryKey("PCR0000001");
//			System.out.print(pcReportVO3.getPcr_no() + ",");
//			System.out.print(pcReportVO3.getPc_no() + ",");
//			System.out.print(pcReportVO3.getMem_no() + ",");
//			System.out.print(pcReportVO3.getPcr_type() + ",");
//			System.out.print(pcReportVO3.getPcr_content() + ",");
//			System.out.print(pcReportVO3.getPcr_date() + ",");
//			System.out.print(pcReportVO3.getPcr_status() + ",");
//			System.out.println(pcReportVO3.getPcr_review_date());
//			System.out.println("---------------------");
	
			// 查詢
			List<PcReportVO> list = dao.getAllStatus("0");
			for (PcReportVO aPcR : list) {
				System.out.print(aPcR.getPcr_no() + ",");
				System.out.print(aPcR.getPc_no() + ",");
				System.out.print(aPcR.getMem_no() + ",");
				System.out.print(aPcR.getPcr_type() + ",");
				System.out.print(aPcR.getPcr_content() + ",");
				System.out.print(aPcR.getPcr_date() + ",");
				System.out.print(aPcR.getPcr_status() + ",");
				System.out.println(aPcR.getPcr_review_date());
				System.out.println();
			}
		
		}

	@Override
	public void updateWithPoint(PcReportVO pcReportVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}


}
