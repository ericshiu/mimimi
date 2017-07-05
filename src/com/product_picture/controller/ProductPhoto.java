package com.product_picture.controller;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.product_picture.model.ProductPictureService;
import com.product_picture.model.ProductPictureVO;

public class ProductPhoto extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();


		try {
			String prp_no=req.getParameter("prp_no");
			ProductPictureService PRPSvc=new ProductPictureService();
			ProductPictureVO prpVO=PRPSvc.getOneProductPicture(prp_no);
			byte[]buf=prpVO.getPrp_picture();
			out.write(buf);
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/product_picture/images/香草奶嘴.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	
}


