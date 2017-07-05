package com.pc_application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;
import com.pc_report.model.PcReportVO;
import com.point_record.model.PointRecordVO;

public class PcApplicationJDBCDAO implements PcApplicationDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pcApplicationVO.getPc_no());
			pstmt.setString(2, pcApplicationVO.getMem_no());
			pstmt.setTimestamp(3, pcApplicationVO.getPca_appdate());
			pstmt.setDate(4, pcApplicationVO.getPca_date());
			pstmt.setString(5, pcApplicationVO.getPca_memo());
			pstmt.setString(6, pcApplicationVO.getPca_status());
			pstmt.setDate(7, pcApplicationVO.getPca_review_date());
			
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
	public void update(PcApplicationVO pcApplicationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, pcApplicationVO.getPca_status());
			pstmt.setDate(2, pcApplicationVO.getPca_review_date());
			pstmt.setString(3, pcApplicationVO.getPca_no());

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
	public void delete(String pca_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pca_no);

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
	public PcApplicationVO findByPrimaryKey(String pca_no) {
		PcApplicationVO pcApplicationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(pcApplicationVO);// Store the row in the list
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
		PcApplicationJDBCDAO dao = new PcApplicationJDBCDAO();
		
		// 新增
//		PcApplicationVO pcApplicationVO = new PcApplicationVO();
//		pcApplicationVO.setPc_no("PC00000001");
//		pcApplicationVO.setMem_no("MEM0000001");
//		pcApplicationVO.setPca_appdate(java.sql.Timestamp.valueOf("2017-04-28 18:30:00"));
//		pcApplicationVO.setPca_date(java.sql.Date.valueOf("2017-04-20"));
//		pcApplicationVO.setPca_memo("有帶小朋友");
//		pcApplicationVO.setPca_status("0");
//		dao.insert(pcApplicationVO);

		// 修改
//		PcApplicationVO pcApplicationVO2 = new PcApplicationVO();
//		pcApplicationVO2.setPca_no("PCA0000004");
//		pcApplicationVO2.setPca_status("1");
//		pcApplicationVO2.setPca_review_date(java.sql.Date.valueOf("2017-05-10"));
//		dao.update(pcApplicationVO2);

		// 刪除
//		dao.delete("PCA0000001");

		// 查詢
//		PcApplicationVO pcApplicationVO3 = dao.findByPrimaryKey("PCA0000004");
//		System.out.print(pcApplicationVO3.getPca_no() + ",");
//		System.out.print(pcApplicationVO3.getPc_no() + ",");
//		System.out.print(pcApplicationVO3.getMem_no() + ",");
//		System.out.print(pcApplicationVO3.getPca_appdate() + ",");
//		System.out.print(pcApplicationVO3.getPca_date() + ",");
//		System.out.print(pcApplicationVO3.getPca_memo()+",");
//		System.out.print(pcApplicationVO3.getPca_status() + ",");
//		System.out.println(pcApplicationVO3.getPca_review_date());
//		System.out.println("---------------------");

		// 查詢
		List<PcApplicationVO> list = dao.getAll("1");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aaa");
		for (PcApplicationVO aPcA : list) {
			System.out.print(aPcA.getPca_no() + ",");
			System.out.print(aPcA.getPc_no() + ",");
			System.out.print(aPcA.getMem_no() + ",");
			System.out.print(myFormat.format(aPcA.getPca_appdate()) + ",");
			System.out.print(aPcA.getPca_date() + ",");
			System.out.print(aPcA.getPca_memo()+",");
			System.out.print(aPcA.getPca_status() + ",");
			System.out.println(aPcA.getPca_review_date());
			System.out.println();
		}
	
	}

	@Override
	public void updateWithPoint(PcApplicationVO pcApplicationVO, PointRecordVO pointRecordVO, MemberVO memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWithReport(PcApplicationVO pcApplicationVO, PcReportVO pcReportVO) {
		// TODO Auto-generated method stub
		
	}

}
