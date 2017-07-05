package com.postpartum_care.model;

import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pc_application.model.PcApplicationVO;
import com.pc_picture.model.*;
import com.pc_report.model.PcReportVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_PC;

public class PostpartumCareDAO implements PostpartumCareDAO_interface{

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
		"INSERT INTO postpartum_care (pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad) VALUES ('PC' || LPAD(to_char(pc_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care order by pc_no DESC";
	private static final String GET_ONE_STMT = 
		"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care where pc_no = ?";
	private static final String DELETE = 
		"DELETE FROM postpartum_care where pc_no = ?";
	private static final String UPDATE = 
		"UPDATE postpartum_care set pc_id=?, pc_password=?, pc_name=?, pc_type=?, pc_address=?, pc_phone=?, pc_email=?, pc_summary=?, pc_bonus=?, pc_gift=?, pc_authority=?, pc_point=?, pc_eva_good=?, pc_eva_normal=?, pc_eva_bad=? where pc_no = ?";

	
	private static final String UPDATE_EVA = 
			"UPDATE postpartum_care set pc_eva_good=?, pc_eva_normal=?, pc_eva_bad=? where pc_no = ?";		
	private static final String GET_PCPs_ByPc_no_STMT = "SELECT pcp_no, pc_no, pcp_summary, pcp_picture FROM pc_picture where pc_no = ? order by pcp_no desc";
	private static final String GET_ONE_BYID = 
			"SELECT pc_no,pc_id,pc_password,pc_name,pc_type,pc_address,pc_phone,pc_email,pc_summary,pc_bonus,pc_gift,pc_authority,pc_point,pc_eva_good,pc_eva_normal,pc_eva_bad FROM postpartum_care where pc_id = ?";
	private static final String GET_APPs_ByPc_no_STMT = "SELECT pca_no,pc_no,mem_no,to_char(pca_appdate,'yyyy-mm-dd hh24:mi:ss') as pca_appdate,to_char(pca_date,'yyyy-mm-dd'),pca_memo,pca_status,to_char(pca_review_date,'yyyy-mm-dd') FROM pc_application where pc_no = ? and pca_status=? order by pca_no desc";
	private static final String GET_Reports_ByPc_no_STMT = "SELECT pcr_no,pc_no,mem_no,pcr_type,pcr_content,to_char(pcr_date,'yyyy-mm-dd'),pcr_status,to_char(pcr_review_date,'yyyy-mm-dd') FROM pc_report where pc_no = ? and pcr_status=? order by pcr_no desc";	
	@Override
	public void insert(PostpartumCareVO postpartumCareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());

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
	public void update(PostpartumCareVO postpartumCareVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());
			pstmt.setString(16, postpartumCareVO.getPc_no());
			
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
	public void delete(String pc_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pc_no);

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
	public PostpartumCareVO findByPrimaryKey(String pc_no) {
		PostpartumCareVO postpartumCareVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
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
		return postpartumCareVO;
	}

	@Override
	public List<PostpartumCareVO> getAll() {
		List<PostpartumCareVO> list = new ArrayList<PostpartumCareVO>();
		PostpartumCareVO postpartumCareVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
				list.add(postpartumCareVO); // Store the row in the list
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
		return list;
	}

	@Override
	public Set<PcPictureVO> getPCPsByPc_no(String pc_no) {
		Set<PcPictureVO> set = new LinkedHashSet<PcPictureVO>();
		PcPictureVO pcPictureVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PCPs_ByPc_no_STMT);
			pstmt.setString(1, pc_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				pcPictureVO = new PcPictureVO();
				pcPictureVO.setPcp_no(rs.getString("pcp_no"));
				pcPictureVO.setPc_no(rs.getString("pc_no"));
				pcPictureVO.setPcp_summary(rs.getString("pcp_summary"));
				pcPictureVO.setPcp_picture(rs.getBytes("pcp_picture"));
				set.add(pcPictureVO); // Store the row in the vector
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
	public PostpartumCareVO findById(String pc_id) {
		PostpartumCareVO postpartumCareVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYID);

			pstmt.setString(1, pc_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
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
		return postpartumCareVO;
	}

	@Override
	public void insertWithPCPs(PostpartumCareVO postpartumCareVO, List<PcPictureVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	
		try {
	
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先新增廠商
			String cols[] = {"pc_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, postpartumCareVO.getPc_id());
			pstmt.setString(2, postpartumCareVO.getPc_password());
			pstmt.setString(3, postpartumCareVO.getPc_name());
			pstmt.setString(4, postpartumCareVO.getPc_type());
			pstmt.setString(5, postpartumCareVO.getPc_address());
			pstmt.setString(6, postpartumCareVO.getPc_phone());
			pstmt.setString(7, postpartumCareVO.getPc_email());
			pstmt.setString(8, postpartumCareVO.getPc_summary());
			pstmt.setString(9, postpartumCareVO.getPc_bonus());
			pstmt.setString(10, postpartumCareVO.getPc_gift());
			pstmt.setString(11, postpartumCareVO.getPc_authority());
			pstmt.setInt(12, postpartumCareVO.getPc_point());
			pstmt.setInt(13, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(14, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(15, postpartumCareVO.getPc_eva_bad());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_pc_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_pc_no = rs.getString(1);
				//System.out.println("自增主鍵值= " + next_pc_no +"(剛新增成功的部門編號)");
			} else {
				//System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增照片
			PcPictureDAO dao = new PcPictureDAO();
			//System.out.println("list.size()-A="+list.size());
			for (PcPictureVO aPCP : list) {
				aPCP.setPc_no(new String(next_pc_no)) ;
				dao.insert2(aPCP,con);
			}
	
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			//System.out.println("list.size()-B="+list.size());
			//System.out.println("新增廠商編號" + next_pc_no + "時,共有照片" + list.size()
			//		+ "張同時被新增");
		
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pc");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public List<PostpartumCareVO> getAll(Map<String, String[]> map) {
		List<PostpartumCareVO> list = new ArrayList<PostpartumCareVO>();
		PostpartumCareVO postpartumCareVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			int mapCount = map.size();	
			System.out.println(mapCount);
			con = ds.getConnection();
			String finalSQL = "select * from postpartum_care "
		          + jdbcUtil_CompositeQuery_PC.get_WhereCondition(map);
//		          + "order by pc_no";
//			String finalSQL = null;
//			if (order.equals("pc_no")){
//				finalSQL = tmpSQL + "order by pc_no DESC";				
//			} else if (order.equals("pc_eva_good")){
//				finalSQL = tmpSQL + "order by pc_eva_good ASC";
//			} else if (order.equals("pc_point")){
//				if (mapCount < 7){
//					finalSQL = tmpSQL + "where pc_point > 0 order by pc_point ASC";
//				} else {
//					finalSQL = tmpSQL + "and pc_point > 0 order by pc_point ASC";
//				}
//			}

			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(rs.getString("pc_no"));
				postpartumCareVO.setPc_id(rs.getString("pc_id"));
				postpartumCareVO.setPc_password(rs.getString("pc_password"));
				postpartumCareVO.setPc_name(rs.getString("pc_name"));
				postpartumCareVO.setPc_type(rs.getString("pc_type"));
				postpartumCareVO.setPc_address(rs.getString("pc_address"));
				postpartumCareVO.setPc_phone(rs.getString("pc_phone"));
				postpartumCareVO.setPc_email(rs.getString("pc_email"));
				postpartumCareVO.setPc_summary(rs.getString("pc_summary"));
				postpartumCareVO.setPc_bonus(rs.getString("pc_bonus"));
				postpartumCareVO.setPc_gift(rs.getString("pc_gift"));
				postpartumCareVO.setPc_authority(rs.getString("pc_authority"));
				postpartumCareVO.setPc_point(rs.getInt("pc_point"));
				postpartumCareVO.setPc_eva_good(rs.getInt("pc_eva_good"));
				postpartumCareVO.setPc_eva_normal(rs.getInt("pc_eva_normal"));
				postpartumCareVO.setPc_eva_bad(rs.getInt("pc_eva_bad"));
				list.add(postpartumCareVO);; // Store the row in the List
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
		return list;
	
	}

	@Override
	public Set<PcApplicationVO> getAppsByPc_no(String pc_no, String pca_status) {
		Set<PcApplicationVO> set = new LinkedHashSet<PcApplicationVO>();
		PcApplicationVO pcApplicationVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_APPs_ByPc_no_STMT);
			pstmt.setString(1, pc_no);
			pstmt.setString(2, pca_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pcApplicationVO = new PcApplicationVO();
				pcApplicationVO.setPca_no(rs.getString("pca_no"));
				pcApplicationVO.setPc_no(rs.getString("pc_no"));
				pcApplicationVO.setMem_no(rs.getString("mem_no"));
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aaa");
		        String app_date = myFormat.format(rs.getTimestamp("pca_appdate"));
				pcApplicationVO.setPca_appdate(rs.getTimestamp("pca_appdate"));
				pcApplicationVO.setPca_appdate_format(app_date);
				pcApplicationVO.setPca_date(rs.getDate("to_char(pca_date,'yyyy-mm-dd')"));
				pcApplicationVO.setPca_memo(rs.getString("pca_memo"));
				pcApplicationVO.setPca_status(rs.getString("pca_status"));
				pcApplicationVO.setPca_review_date(rs.getDate("to_char(pca_review_date,'yyyy-mm-dd')"));
				set.add(pcApplicationVO); // Store the row in the vector
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
	public Set<PcReportVO> getReportsByPc_no(String pc_no, String pcr_status) {
		Set<PcReportVO> set = new LinkedHashSet<PcReportVO>();
		PcReportVO pcReportVO= null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Reports_ByPc_no_STMT);
			pstmt.setString(1, pc_no);
			pstmt.setString(2, pcr_status);
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
				set.add(pcReportVO); // Store the row in the vector
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
	public void updateEvaluation(PostpartumCareVO postpartumCareVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(UPDATE_EVA);
			
			pstmt.setInt(1, postpartumCareVO.getPc_eva_good());
			pstmt.setInt(2, postpartumCareVO.getPc_eva_normal());
			pstmt.setInt(3, postpartumCareVO.getPc_eva_bad());
			pstmt.setString(4, postpartumCareVO.getPc_no());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-pc");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}

}
