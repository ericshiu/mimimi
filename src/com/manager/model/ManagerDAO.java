package com.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerDAO implements ManagerDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/aa107g3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MANAGER values('MAN'||lpad(to_char(manager_seq.NEXTVAL),7,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from manager";
	private static final String GET_ONE_STMT = "select * from manager where man_no=?";
	private static final String GET_ONE_ID = "select * from manager where man_id=?";
	private static final String DELETE = "DELETE FROM MANAGER WHERE MAN_NO=?";
	private static final String UPDATE = "update manager set man_id=?,man_name=?,man_password=?,man_email=? where man_no=?";

	@Override
	public void insert(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("有到dao的insert");
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_name());
			pstmt.setString(3, managerVO.getMan_password());
			pstmt.setString(4, managerVO.getMan_email());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void update(ManagerVO managerVO) {
		System.out.println("有近來dao");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			System.out.println("id"+managerVO.getMan_id());
			
			System.out.println("name"+managerVO.getMan_name());
			System.out.println("password"+managerVO.getMan_password());
			System.out.println(managerVO.getMan_email());
			System.out.println(managerVO.getMan_no());
			pstmt.setString(1, managerVO.getMan_id());
			pstmt.setString(2, managerVO.getMan_name());
			pstmt.setString(3, managerVO.getMan_password());
			pstmt.setString(4, managerVO.getMan_email());
			pstmt.setString(5, managerVO.getMan_no());
			System.out.println("有近來dao2");
			
			pstmt.executeUpdate();
			System.out.println("有近來dao3");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(String man_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public ManagerVO findByPrimaryKey(String man_no) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, man_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_password(rs.getString("man_password"));
				managerVO.setMan_email(rs.getString("man_email"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occrued." + se.getMessage());
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
		return managerVO;
	}

	
	@Override
	public ManagerVO findById(String man_id) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ID);
			pstmt.setString(1, man_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_password(rs.getString("man_password"));
				managerVO.setMan_email(rs.getString("man_email"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occrued." + se.getMessage());
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
		return managerVO;
	}
	
	
	@Override
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				managerVO = new ManagerVO();
				managerVO.setMan_no(rs.getString("man_no"));
				managerVO.setMan_id(rs.getString("man_id"));
				managerVO.setMan_name(rs.getString("man_name"));
				managerVO.setMan_password(rs.getString("man_password"));
				managerVO.setMan_email(rs.getString("man_email"));
				list.add(managerVO); // Store the row in the list
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


	
}

