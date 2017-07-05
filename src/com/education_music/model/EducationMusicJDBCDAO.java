package com.education_music.model;

import java.util.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class EducationMusicJDBCDAO implements EducationMusicDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";
	
	private static final String INSERT_STMT = "INSERT INTO education_music (em_no,em_name,em_introduction,em_content) VALUES ('EM'||LPAD(to_char(em_seq.NEXTVAL),8,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT em_no,em_name,em_introduction,em_content FROM education_music";
	private static final String GET_ONE_STMT = "SELECT em_no,em_name,em_introduction,em_content FROM education_music where em_no = ?";
	
	private static final String DELETE = "DELETE FROM education_music where em_no = ?";
	
	private static final String UPDATE = "UPDATE education_music  set em_name=?,em_introduction=?,em_content=? where em_no=?";
	
	
	@Override
	public void insert(EducationMusicVO education_musicVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, education_musicVO.getEm_name());
			pstmt.setString(2, education_musicVO.getEm_introduction());
			pstmt.setBytes(3, education_musicVO.getEm_content());
            
			

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
	public void update(EducationMusicVO education_musicVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, education_musicVO.getEm_name());
			pstmt.setString(2, education_musicVO.getEm_introduction());
			pstmt.setBytes(3, education_musicVO.getEm_content());
			pstmt.setString(4, education_musicVO.getEm_no());
			

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
	public void delete(String em_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, em_no);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除編號"+em_no);
			
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
	public EducationMusicVO findByPrimaryKey(String fm_no) {
		// TODO Auto-generated method stub
		
		EducationMusicVO education_musicVO = null;
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
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));
				

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
		
		
		return education_musicVO;
	}
	@Override
	public List<EducationMusicVO> getAll() {

		List<EducationMusicVO> list = new ArrayList<EducationMusicVO>();
		EducationMusicVO education_musicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));
				list.add(education_musicVO); // Store the row in the list
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
	
	
	public Set<EducationMusicVO> getEducation_musicByEm_no(String em_no) {
		Set<EducationMusicVO> set = new LinkedHashSet<EducationMusicVO>();
		EducationMusicVO education_musicVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, em_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				education_musicVO = new EducationMusicVO();
				education_musicVO.setEm_no(rs.getString("em_no"));
				education_musicVO.setEm_name(rs.getString("em_name"));
				education_musicVO.setEm_introduction(rs.getString("em_introduction"));
				education_musicVO.setEm_content(rs.getBytes("em_content"));	
				set.add(education_musicVO); // Store the row in the vector
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
	
	

	public static void main(String[] args) throws IOException{
		
		EducationMusicJDBCDAO dao = new EducationMusicJDBCDAO();
		//新增
//		
//		EducationMusicVO education_musicVO2 = new EducationMusicVO();
//		education_musicVO2.setEm_name("潑水歌");
//		education_musicVO2.setEm_name("幸福的道路");
//		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
//		Path path = Paths.get("src/music/潑水歌.mp3");
//		Path path = Paths.get("src/music/幸福的道路.mp3");
//		byte[] em_content = Files.readAllBytes(path);
//		education_musicVO2.setEm_content(em_content);
		
//		education_musicVO1.setEm_name("測試");
//		education_musicVO1.setEm_introduction("測試");
//		education_musicVO1.setEm_content(null);
//		dao.insert(education_musicVO2);
		
		//修改
//		
		EducationMusicVO education_musicVO2 = new EducationMusicVO();
//		education_musicVO2.setEm_no("EM00000002");
//		education_musicVO2.setEm_name("幸福的道路");
//		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
//		Path path = Paths.get("src/music/幸福的道路.mp3");
		
		
//		education_musicVO2.setEm_no("EM00000003");
//		education_musicVO2.setEm_name("勃拉姆斯【搖籃曲】");
//		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
//		Path path = Paths.get("src/music/勃拉姆斯【搖籃曲】.mp3");
//		
//		education_musicVO2.setEm_no("EM00000004");
//		education_musicVO2.setEm_name("甜蜜入夢");
//		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
//		Path path = Paths.get("src/music/甜蜜入夢.mp3");
		
//		education_musicVO2.setEm_no("EM00000005");
//		education_musicVO2.setEm_name("潑水歌");
//		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
//		Path path = Paths.get("src/music/潑水歌.mp3");
		
		
		education_musicVO2.setEm_no("EM00000001");
		education_musicVO2.setEm_name("帕赫貝爾鋼琴");
		education_musicVO2.setEm_introduction("是一種撫人心靈的樂曲，一般是希望以熟悉的聲音和甜美的旋律用作哄小孩入睡");
		Path path = Paths.get("src/music/帕赫貝爾鋼琴.mp3");
		
		
		
		byte[] em_content = Files.readAllBytes(path);
		education_musicVO2.setEm_content(em_content);		
		dao.update(education_musicVO2);
		
		//刪除
		
//		dao.delete("EM00000004");
		
		//查詢全部
		
//		List<Education_musicVO> list = dao.getAll();
//		for (Education_musicVO aEducation_music : list) {
//			System.out.print(aEducation_music.getEm_no() + ",");
//			System.out.print(aEducation_music.getEm_name() + ",");
//			System.out.print(aEducation_music.getEm_introduction() + ",");
//			System.out.print(aEducation_music.getEm_content() + ",");
//			System.out.println();
//		}
		
		// 查詢某一個編號的媽媽
//		Set<Education_musicVO> set = dao.getEducation_musicByEm_no("EM00000002");
//		for (Education_musicVO aMusicByDd_no : set) {
//			System.out.print(aMusicByDd_no.getEm_no() + ",");
//			System.out.print(aMusicByDd_no.getEm_name() + ",");
//			System.out.print(aMusicByDd_no.getEm_introduction() + ",");
//			System.out.print(aMusicByDd_no.getEm_content() + ",");
//			System.out.println();
//			}
		
		
	}
	
}
