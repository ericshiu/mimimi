package com.member.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO member (mem_no, mem_id, mem_password, mem_name,mem_nike, mem_sex,mem_birthday, mem_phone, mem_email, mem_address, mem_point,mem_actual_point, mem_picture, mem_authority,mem_facebook,mem_google) VALUES ('MEM'||LPAD(to_char(member_seq.NEXTVAL),7,'0'), ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =

			"SELECT mem_no,mem_id,mem_password,mem_name,mem_nike,mem_sex,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_phone,  mem_email,  mem_address, "
					+ " mem_point,  mem_actual_point,mem_picture,  mem_authority ,"
					+ " mem_facebook, mem_google FROM member order by mem_no";
	private static final String GET_ONE_STMT = "SELECT  mem_no,  mem_id,  mem_password,  mem_name,  mem_nike,  mem_sex,"
			+ " to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,  mem_phone,  mem_email,  mem_address,  mem_point,  mem_actual_point,"
			+ "mem_picture,  mem_authority, mem_facebook,mem_google FROM member where mem_no = ?";
	
	private static final String GET_ONE_ID = "SELECT  mem_no,  mem_id,  mem_password,  mem_name,  mem_nike,  mem_sex,"
			+ " to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,  mem_phone,  mem_email,  mem_address,  mem_point,  mem_actual_point,"
			+ "mem_picture,  mem_authority, mem_facebook,mem_google FROM member where mem_id = ?";
	
	private static final String DELETE = "DELETE FROM member where mem_no = ? ";
	private static final String UPDATE = "UPDATE member set  mem_id=?, mem_password=?, mem_name=?, mem_nike=?, mem_sex=?, "
			+ "mem_birthday=?, mem_phone=?, mem_email=?, mem_address=?, mem_point=?, mem_actual_point=?, "
			+ "mem_picture=?, mem_authority=?, mem_facebook=?,mem_google=? where mem_no = ?";
	private static final String UPDATE_POINT = "UPDATE member set  mem_point=? where mem_no = ?";	
	private static final String UPDATE_PSW = "UPDATE member set  mem_password=? where mem_no = ?";
	@Override
	public void insert(MemberVO member) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_password());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_nike());
			pstmt.setString(5, member.getMem_sex());
			pstmt.setDate(6, member.getMem_birthday());
			pstmt.setString(7, member.getMem_phone());
			pstmt.setString(8, member.getMem_email());
			pstmt.setString(9, member.getMem_address());
			pstmt.setInt(10, member.getMem_point());
			pstmt.setInt(11, member.getMem_actual_point());
			pstmt.setBytes(12, member.getMem_picture());
			pstmt.setString(13, member.getMem_authority());
			pstmt.setString(14, member.getMem_facebook());
			pstmt.setString(15, member.getMem_google());


			 pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MemberVO member) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_password());
			pstmt.setString(3, member.getMem_name());
			pstmt.setString(4, member.getMem_nike());
			pstmt.setString(5, member.getMem_sex());
			pstmt.setDate(6, member.getMem_birthday());
			pstmt.setString(7, member.getMem_phone());
			pstmt.setString(8, member.getMem_email());
			pstmt.setString(9, member.getMem_address());
			pstmt.setInt(10, member.getMem_point());
			pstmt.setInt(11, member.getMem_actual_point());
			pstmt.setBytes(12, member.getMem_picture());
			pstmt.setString(13, member.getMem_authority());
			pstmt.setString(14, member.getMem_facebook());
			pstmt.setString(15, member.getMem_google());
			pstmt.setString(16, member.getMem_no());
			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemberVO findByPrimaryKey(String mem_no) {

		MemberVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				member = new MemberVO();
				member.setMem_no(rs.getString("mem_no"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_password(rs.getString("mem_password"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_nike(rs.getString("mem_nike"));
				member.setMem_sex(rs.getString("mem_sex"));
				member.setMem_birthday(rs.getDate("mem_birthday"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_address(rs.getString("mem_address"));
				member.setMem_point(rs.getInt("mem_point"));
				member.setMem_actual_point(rs.getInt("mem_actual_point"));
				member.setMem_picture(rs.getBytes("mem_picture"));
				member.setMem_authority(rs.getString("mem_authority"));
				member.setMem_facebook(rs.getString("mem_facebook"));
				member.setMem_google(rs.getString("mem_google"));

			}

	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return member;
	}

	
	
	
	@Override
	public MemberVO findByID(String mem_id) {

		MemberVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ID);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				member = new MemberVO();
				member.setMem_no(rs.getString("mem_no"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_password(rs.getString("mem_password"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_nike(rs.getString("mem_nike"));
				member.setMem_sex(rs.getString("mem_sex"));
				member.setMem_birthday(rs.getDate("mem_birthday"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_address(rs.getString("mem_address"));
				member.setMem_point(rs.getInt("mem_point"));
				member.setMem_actual_point(rs.getInt("mem_actual_point"));
				member.setMem_picture(rs.getBytes("mem_picture"));
				member.setMem_authority(rs.getString("mem_authority"));
				member.setMem_facebook(rs.getString("mem_facebook"));
				member.setMem_google(rs.getString("mem_google"));

			}

			// Handle any driver errors
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return member;
	}
	
	
	
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO member = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				member = new MemberVO();
				member.setMem_no(rs.getString("mem_no"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_password(rs.getString("mem_password"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_nike(rs.getString("mem_nike"));
				member.setMem_sex(rs.getString("mem_sex"));
				member.setMem_birthday(rs.getDate("mem_birthday"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_address(rs.getString("mem_address"));
				member.setMem_point(rs.getInt("mem_point"));
				member.setMem_actual_point(rs.getInt("mem_actual_point"));
				member.setMem_picture(rs.getBytes("mem_picture"));
				member.setMem_authority(rs.getString("mem_authority"));
				member.setMem_facebook(rs.getString("mem_facebook"));
				member.setMem_google(rs.getString("mem_google"));
				list.add(member); // Store the row in the list

			}

			// Handle any driver errors
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void updatePoint(MemberVO memberVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {

     		pstmt = con.prepareStatement(UPDATE_POINT);

			pstmt.setInt(1, memberVO.getMem_point());
			pstmt.setString(2, memberVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-mem");
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
	
	
	@Override
	public void updatePsw(String mem_password,String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSW);


			pstmt.setString(1, mem_password);
			pstmt.setString(2, mem_no);
			
			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	
}
