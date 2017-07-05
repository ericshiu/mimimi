package com.inspection.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.inspection.model.InspectionService;
import com.inspection.model.InspectionVO;
import com.member.controller.MemberMail;
import com.member.model.MemberService;


public class insScheduleServlet extends HttpServlet{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timer timer ; 
    int count = 1;        
    public void destroy(){
        timer.cancel();
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    }
            
    public void init(){        
        timer = new Timer();

        	Date now=new Date();  
//        	cal = new GregorianCalendar(new Integer(year), new Integer(menth) -1, new Integer(day), 0, 0, 0);   
       	
            //要做的事情
            TimerTask task = new TimerTask(){
            	
            	
            	//要做的事情      
                public void run(){
                	InspectionService InsSVC =new InspectionService();
                    List<InspectionVO> list = InsSVC.getAll();
                    
                    
                    MemberService memSvc =new MemberService();
//                    List<MemberVO> list2 = memSvc.getAll();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    DateTime sysdate = new DateTime();

                    
                    for(InspectionVO inspectionVO:list){
                    	
                    	
                    	String mem_name = memSvc.getOneMem(inspectionVO.getMem_no()).getMem_name();
                    	String mail = memSvc.getOneMem(inspectionVO.getMem_no()).getMem_email();
                    	Timestamp ins_date = inspectionVO.getIns_date();
                    	String st_ins_date = ins_date.toString();
                    	String hospital = inspectionVO.getIns_hospital();
                    	Integer ins_no = inspectionVO.getIns_resetvation_no();
                    	System.out.println(mem_name+" "+mail);
                    	
                    	Timestamp cal2=inspectionVO.getIns_date();
                    	DateTime dateEnd = new DateTime(cal2.getTime());
                    	
                    	String ins_date1=inspectionVO.getIns_date().toString();
                    	String date = ins_date1.substring(0,10);
                    	String year = ins_date1.substring(0,4);
                    	String menth = ins_date1.substring(5,7);
                    	String day = ins_date1.substring(8,10);
                    	System.out.println(year+" "+menth+" "+day+" "+date);
                    	 int vs =  Days.daysBetween(sysdate, dateEnd).getDays();
                    	 System.out.println(vs);
                	
	                	if(vs>=0&&vs<3){
	                		insMail membermail = new insMail();
	        				String mailTitle="媽咪樂寶產檢通知";
	        				String mailMessage=mem_name+"先生/小姐你好，很感謝您的媽咪樂寶產檢日通知！"
	        						+ "您的產檢醫院為："+hospital+
	        						" 時間為："+st_ins_date+
	        						" 預約號碼："+ins_no;
	        			    membermail.sendMail(mail,mailTitle,mailMessage);
	                        System.out.println("This is Task"+ count);
	                        System.out.println("工作排定的時間 = " + new Date(scheduledExecutionTime()));
	                        System.out.println("工作執行的時間  = " + new Date() + "\n");                
	                        count++;
	                	}
                	                    
                    }                    
                }                                
            };        	        	
            timer.scheduleAtFixedRate(task, now, 24*60*60*1000);  //多久跑一次	
    }
}
