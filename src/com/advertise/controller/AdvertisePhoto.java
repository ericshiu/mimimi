package com.advertise.controller;

import java.io.*;
import java.sql.*;


import javax.servlet.*;
import javax.servlet.http.*;


import com.advertise.model.AdvertiseService;
import com.advertise.model.AdvertiseVO;

public class AdvertisePhoto extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();


		try {
			String adv_no=req.getParameter("adv_no");
			AdvertiseService advSvc=new AdvertiseService();
			AdvertiseVO advVO=advSvc.getOneAdv(new String(adv_no));
			byte[]buf=advVO.getAdv_picture();
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
