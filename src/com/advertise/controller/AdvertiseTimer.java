package com.advertise.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertise.model.AdvertiseService;
import com.advertise.model.AdvertiseVO;
import com.firm.model.*;
import com.member.controller.MemberMail;



public class AdvertiseTimer extends HttpServlet{
 private static final long serialVersionUID=1L;
 Timer timer = null;
 public AdvertiseTimer(){
  super();
 }
    
 public void init(){
  timer = new Timer();
  
  TimerTask task = new TimerTask(){
	   
	   AdvertiseService advSvc=new AdvertiseService();
	    public void run(){
	    
	     System.out.println("每秒都要印");
	     List<AdvertiseVO> advlist=advSvc.getStatus("1");
	     for (AdvertiseVO adv : advlist) {
	    
	     Timestamp adv_end = adv.getAdv_end();
	     Date nowTime = new Date();   
	     Long end=adv_end.getTime();
	     Long now=nowTime.getTime();
	     System.out.println("有在運行Timer");
	     if (now>end){

	      advSvc.updateAdv(adv.getAdv_no(), adv.getFir_no(), 
	        adv.getAdv_propose_date(), adv.getAdv_start(), 
	        adv.getAdv_end(), adv.getAdv_review(), adv.getAdv_review_date(),
	        "2", adv.getAdv_picture(), adv.getAdv_title());
	     FirmService firSvc=new FirmService();
	     FirmVO firVO=firSvc.getOneFir(adv.getFir_no());
	     
	     MemberMail membermail = new MemberMail();
	     String mailTitle="廣告下架通知";
	     String mailMessage=firVO.getFir_name()+" 廠商您好，您的廣告 " +adv.getAdv_title() +" 於 "+ nowTime+ " 下架，期待您請再次申請，感謝您的支持。";
	     membermail.sendMail(firVO.getFir_email(), mailTitle, mailMessage);
	      
	     }

	     
	     }   
	     }
	   };
	    
	  System.out.println("開始了嗎");
	  Calendar cal = new GregorianCalendar(2017,Calendar.MAY,28);
	   timer.scheduleAtFixedRate(task,cal.getTime(),1*1000);
  
  
 }
  
 public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
  doPost(req,res);
 }
 
 public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
  

 }
 public void destory(){
  super.destroy();
  timer.cancel();
 }
 
 
}