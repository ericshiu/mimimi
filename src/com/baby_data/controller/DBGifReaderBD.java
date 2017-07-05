package com.baby_data.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.baby_data.model.*;

public class DBGifReaderBD extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String bd_no = req.getParameter("bd_no");
			BabyDataService BDSvc = new BabyDataService();
			BabyDataVO babyDataVO = BDSvc.getOneBD(bd_no);
			byte[] buf = babyDataVO.getBd_pictures();
			out.write(buf);
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("nopic/back1.gif");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

}
