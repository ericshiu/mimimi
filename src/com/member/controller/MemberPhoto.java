package com.member.controller;

import java.io.*;
import java.sql.*;


import javax.servlet.*;
import javax.servlet.http.*;


import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MemberPhoto extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();


		try {
			String mem_no=req.getParameter("mem_no");
			MemberService memSvc=new MemberService();
			MemberVO memberVO=memSvc.getOneMem(new String(mem_no));
			byte[]buf=memberVO.getMem_picture();
			out.write(buf);
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/member/images/back1.gif");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	
}
