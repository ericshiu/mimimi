package com.advertise.controller;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import com.advertise.model.AdvertiseService;
import com.advertise.model.AdvertiseVO;
import com.firm.model.FirmService;
import com.firm.model.FirmVO;
import com.member.controller.MemberMail;

import java.util.*;

public class ADVTimer extends HttpServlet {
   
    
    Timer timer;
    int i=0;      
   
    public void init() throws ServletException {
      TimerTask task = new TimerTask(){ 
    	  
    	  AdvertiseService advSvc=new AdvertiseService();
	        public void run() {
	     
	        	
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
	   	     System.out.println("每秒都要印");
	   	     List<AdvertiseVO> advlist=advSvc.getStatus("1");
//	   	     for (AdvertiseVO adv : advlist) {
//	   	    
//	   	     java.sql.Timestamp adv_end = adv.getAdv_end();
//	   	     System.out.println(adv_end);
//	   	     Date nowTime = new Date();   
//	   	     Long end=adv_end.getTime();
//	   	     Long now=nowTime.getTime();
//	   	     System.out.println("有在運行Timer");
//	   	     if (now>end){
//
//	   	      advSvc.updateAdv(adv.getAdv_no(), adv.getFir_no(), 
//	   	        adv.getAdv_propose_date(), adv.getAdv_start(), 
//	   	        adv.getAdv_end(), adv.getAdv_review(), adv.getAdv_review_date(),
//	   	        "2", adv.getAdv_picture(), adv.getAdv_title());
//	   	     FirmService firSvc=new FirmService();
//	   	     FirmVO firVO=firSvc.getOneFir(adv.getFir_no());
//	   	     
//	   	     MemberMail membermail = new MemberMail();
//	   	     String mailTitle="廣告到期通知";
//	   	     String mailMessage=firVO.getFir_name()+" 廠商您好，您的廣告 " +adv.getAdv_title() +" 於 "+ nowTime+ " 到期下架，期待您請再次申請，感謝您的支持。";
//	   	     membermail.sendMail(firVO.getFir_email(), mailTitle, mailMessage);
//	   	      
//	   	     }
//
//	   	     
//	   	     }  
	        	
	        	
	
	        } 
      };
      timer = new Timer(); 
//      Calendar cal = new GregorianCalendar(2017, Calendar.MAY, 28, 22, 33, 0);
      Date now=new Date();    
      
//      System.out.println(now);
//      timer.scheduleAtFixedRate(task, now, 1*1000);   //now的位子要放 date型態
      System.out.println("已建立排程!");       
    }
    

    public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    }                           	

    public void destroy() {
        timer.cancel();
        System.out.println("已移除排程!");
    }
    
}