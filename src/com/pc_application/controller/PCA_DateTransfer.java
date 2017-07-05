package com.pc_application.controller;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import com.pc_application.model.PcApplicationService;
import com.pc_application.model.PcApplicationVO;


public class PCA_DateTransfer extends HttpServlet {


  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
     
//	req.setCharacterEncoding("UTF-8"); 
	res.setContentType("text/plain;charset=UTF-8");

    PrintWriter out = res.getWriter();
    
    try {
      
        String pca_no  = req.getParameter("pca_no");
        PcApplicationService PCASvc = new PcApplicationService();
        PcApplicationVO PCAVO = PCASvc.getOnePCA(pca_no);       
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aaa");
        String app_date = myFormat.format(PCAVO.getPca_appdate());
        
        //String app_date2 = new String(app_date.getBytes("ISO-8859-1"),"UTF-8");
        out.print(app_date);
        
    } catch(Exception e) {

       System.out.println(e);

    }
  }
 
  

	
}
