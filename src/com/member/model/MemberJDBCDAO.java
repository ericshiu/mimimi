package com.member.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "aa107g3";
	String passwd = "aa107g3";

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
	
	private static final String DELETE = "DELETE FROM member where mem_no = ?";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

//			 byte[] pic = getPictureByteArray("items/FC_Barcelona.png");
//			 pstmt.setBytes(3, pic);
//			
			 pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_PSW);

				pstmt.setString(1, mem_password);
				pstmt.setString(2, mem_no);
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
				// Handle any SQL errors
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
	
	public static void main(String[] args) throws IOException{

		MemberJDBCDAO dao = new MemberJDBCDAO();
		
//		 新增
//		MemberVO member1 = new MemberVO();
//		member1.setMem_id("ban1");
//		member1.setMem_password("banana");
//		member1.setMem_name("黃黃今朝新增");
//		member1.setMem_nike("香蕉");
//		member1.setMem_sex("男");
//		member1.setMem_birthday(java.sql.Date.valueOf("1977-07-07"));
//		member1.setMem_phone("0977777777");
//		member1.setMem_email("banana@gmail.com");
//		member1.setMem_address("屏東縣東港鎮東隆路1號");
//		member1.setMem_point(100);
//		member1.setMem_actual_point(100);
//		Path path = Paths.get("src/image/myphone.png");
//		byte[] data = Files.readAllBytes(path);
//		member1.setMem_picture(data);
//		member1.setMem_authority("Y");
//		member1.setMem_facebook("banana@gmail.com");
//		member1.setMem_google("banana@gmail.com");
//		dao.insert(member1);
//		
//		
//		byte[] data=new byte[1];
//		ByteArrayInputStream in = new ByteArrayInputStream(new BufferedInputStream(new File("src/image/myphone.png")));
//		ByteArrayOutputStream out=new ByteArrayOutputStream();
//		
//		while(in.read(data)!=-1){
//			out.write(data);
//		}
//		out.close();
//		in.close();
//		data=out.toByteArray();

		
		// 修改
//		MemberVO member2 = new MemberVO();
//		member2.setMem_no("MEM0000004");
//		member2.setMem_id("banana嬌嬌嬌3");
//		member2.setMem_password("banana2");
//		member2.setMem_name("阿蕉");
//		member2.setMem_nike("蕉蕉");
//		member2.setMem_sex("女");
//		member2.setMem_birthday(Date.valueOf("1977-07-22"));
//		member2.setMem_phone("0977777772");
//		member2.setMem_email("banana2@gmail.com");
//		member2.setMem_address("屏東縣東港鎮東隆路2號");
//		member2.setMem_point(1200);
//		member2.setMem_actual_point(1200);
//		Path path=Paths.get("src/image/myphone.png");
//		byte[] data=Files.readAllBytes(path);
//		member2.setMem_picture(data);
//		member2.setMem_authority("N");
//		member2.setMem_facebook("banana2@gmail.com");
//		member2.setMem_google("banana2@gmail.com");
//		
//		dao.update(member2);


		//新增圖片
//		MemberVO memberpic=new MemberVO();
//		Path path=Paths.get("src/image/myphone.png")
//		memberpic.setMem_picture();
		
//		// 刪除
//		dao.delete("MEM0000004");
//
//		// 查詢
//		MemberVO member3 = dao.findByPrimaryKey("MEM0000004");
//		System.out.print(member3.getMem_no() + ",");
//		System.out.print(member3.getMem_id() + ",");
//		System.out.print(member3.getMem_password() + ",");
//		System.out.print(member3.getMem_name() + ",");
//		System.out.print(member3.getMem_nike() + ",");
//		System.out.print(member3.getMem_sex() + ",");
//		System.out.print(member3.getMem_birthday() + ",");
//		System.out.println(member3.getMem_phone());
//		System.out.print(member3.getMem_email() + ",");
//		System.out.print(member3.getMem_address() + ",");
//		System.out.print(member3.getMem_point() + ",");
//		System.out.print(member3.getMem_actual_point() + ",");
//		System.out.print(member3.getMem_picture()+",");
//		System.out.print(member3.getMem_authority() + ",");
//		System.out.print(member3.getMem_facebook() + ",");
//		System.out.print(member3.getMem_google() + ",");
//		System.out.println("---------------------");

		
		
		
		// 查詢
//		MemberVO member3 = dao.findByID("123123");
//		System.out.print(member3.getMem_no() + ",");
//		System.out.print(member3.getMem_id() + ",");
//		System.out.print(member3.getMem_password() + ",");
//		System.out.print(member3.getMem_name() + ",");
//		System.out.print(member3.getMem_nike() + ",");
//		System.out.print(member3.getMem_sex() + ",");
//		System.out.print(member3.getMem_birthday() + ",");
//		System.out.println(member3.getMem_phone());
//		System.out.print(member3.getMem_email() + ",");
//		System.out.print(member3.getMem_address() + ",");
//		System.out.print(member3.getMem_point() + ",");
//		System.out.print(member3.getMem_actual_point() + ",");
//		System.out.print(member3.getMem_picture()+",");
//		System.out.print(member3.getMem_authority() + ",");
//		System.out.print(member3.getMem_facebook() + ",");
//		System.out.print(member3.getMem_google() + ",");
//		System.out.println("---------------------");
		
		
		
//	
//		
//		// 查詢
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO mem : list) {
//			System.out.print(mem.getMem_no()+",");
//			System.out.print(mem.getMem_id() + ",");
//			System.out.print(mem.getMem_password() + ",");
//			System.out.print(mem.getMem_name() + ",");
//			System.out.print(mem.getMem_nike() + ",");
//			System.out.print(mem.getMem_sex() + ",");
//			System.out.print(mem.getMem_birthday() + ",");
//			System.out.println(mem.getMem_phone());
//			System.out.print(mem.getMem_email() + ",");
//			System.out.print(mem.getMem_address() + ",");
//			System.out.print(mem.getMem_point() + ",");
//			System.out.print(mem.getMem_actual_point() + ",");
//			System.out.print(mem.getMem_picture()+",");
//			System.out.print(mem.getMem_authority() + ",");
//			System.out.print(mem.getMem_facebook() + ",");
//			System.out.print(mem.getMem_google() + ",");
//			System.out.println();
//		}
		
		
		
		// 修改密碼
		MemberVO member2 = new MemberVO();
		String mem_password="123123";
		String mem_no="MEM0000001";
		
		
		dao.updatePsw(mem_password,mem_no);
		
	}
	
}
