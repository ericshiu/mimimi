package com.pc_picture.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.postpartum_care.model.PostpartumCareService;
import com.pc_picture.model.*;

public class PC_PictureTransfer extends HttpServlet {


  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
     
    res.setContentType("image/gif");
    res.setContentType("image/jpg");
    res.setContentType("image/png");
    ServletOutputStream out = res.getOutputStream();
    
    try {
      
        String pcp_no  = req.getParameter("pcp_no");
        PcPictureService PCPSvc = new PcPictureService();
		PcPictureVO PCPVO = PCPSvc.getOnePCP(pcp_no);
        byte[] buf = PCPVO.getPcp_picture();
       out.write(buf);
    } catch(Exception e) {
       InputStream in = getServletContext().getResourceAsStream("/nopic/pic404.png");
       byte[] buf = new byte[in.available()];
       in.read(buf);
       out.write(buf);
       in.close();
    }
  }
 
  

	
}
