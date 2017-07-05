package com.pc_application.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.*;
import com.pc_application.model.*;
import com.pc_report.model.PcReportVO;
import com.point_record.model.*;
import com.postpartum_care.controller.MailService;
import com.postpartum_care.model.PostpartumCareService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024 * 1024)
public class PcApplicationServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();   

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPC.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pca_no = new String(req.getParameter("pc_no"));
				
				/***************************2.開始查詢資料****************************************/
				PcApplicationService PCPSvc = new PcApplicationService();
				PcApplicationVO pcApplicationVO = PCPSvc.getOnePCA(pca_no);
												
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pcApplicationVO", pcApplicationVO);         // 資料庫取出的empVO物件,存入req
				String url = "/postpartum_care/update_pcp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PC_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/listAllPCP.jsp");
				failureView.forward(req, res);
			}
		}
		
		//狀態更改
		if ("update".equals(action)) { // 來自update_PC_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//更新application及回傳需要資料
				String pc_no = req.getParameter("pc_no").trim();	
				String pca_no = req.getParameter("pca_no").trim();				
				String pca_status = req.getParameter("pca_status").trim();				
				java.sql.Date pca_review_date = java.sql.Date.valueOf(req.getParameter("pca_review_date").trim());
						
				String review_id = req.getParameter("review_id").trim();	
				
				PcApplicationVO pcApplicationVO = new PcApplicationVO();
				
				
				//取得會員編號給積分異動用
				PcApplicationService PCASvc = new PcApplicationService();
				String mem_no = PCASvc.getOnePCA(pca_no).getMem_no();
				
				//取得廠商名稱及預約積分給積分異動用
				PostpartumCareService PCSvc = new PostpartumCareService();
				String pc_name = PCSvc.getOnePC(pc_no).getPc_name();
			
				Integer pr_point = PCSvc.getOnePC(pc_no).getPc_point();

				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcApplicationVO", pcApplicationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/PersonalPC_app.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				
				//單純改成已通過(1)或是完成(2)
				if (pca_status.equals("1") || pca_status.equals("2")) {
					pcApplicationVO = PCASvc.updatePCA(pca_no, pca_status, pca_review_date);
				//未通過(3)合併退積分
				} else if (pca_status.equals("3")) {

					//Step1  建立appVO					
					pcApplicationVO.setPc_no(pc_no);
					pcApplicationVO.setPca_date(pca_review_date);
					pcApplicationVO.setPca_status(pca_status);

					
					//Step1  建立積分記錄VO
					String pr_type = "退返預約積分";
					String pr_content = "預約廠商:"+pc_name;
					System.out.println(pr_content);
					PointRecordVO pointRecordVO = new PointRecordVO();
					pointRecordVO.setMem_no(mem_no);				
					pointRecordVO.setPr_type(pr_type);				
					pointRecordVO.setPr_content(pr_content);				
					pointRecordVO.setPr_date(pca_review_date);				
					pointRecordVO.setPr_point(pr_point);
					

					//Step2  更新會員積分
					//先找舊的
					MemberService memSVC = new MemberService();
					MemberVO memberVO_old = memSVC.getOneMem(mem_no);
					Integer old_point = new Integer(memberVO_old.getMem_point());
					Integer new_point = old_point + pr_point;
					
					//再算新的
					MemberVO memberVO = new MemberVO();
					memberVO.setMem_no(mem_no);
					memberVO.setMem_point(new_point);					
					System.out.println("memVO ok");
					
					PCASvc.updatePCAWithPoint(pca_no, pca_status, pca_review_date,pointRecordVO,memberVO);
					System.out.println("update ok");
				}
				
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pcApplicationVO", pcApplicationVO);
				req.setAttribute("review_id", review_id);

				String url = "/postpartum_care/PC.do?action=listApps_ByPc_no&pc_no="+pc_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/PersonalPC_app.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPC.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<String> successMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("successMsgs", successMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String pc_no = req.getParameter("pc_no").trim();
				String pc_name = req.getParameter("pc_name").trim();
				String pc_email = req.getParameter("pc_email").trim();
				String mem_no = req.getParameter("mem_no").trim();
				java.sql.Timestamp pca_appdate = null;
				try {
					pca_appdate = java.sql.Timestamp.valueOf(req.getParameter("pca_appdate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入參觀日期!");
				}
				java.sql.Date pca_date = java.sql.Date.valueOf(req.getParameter("pca_date").trim()); 	
				String pca_status = req.getParameter("pca_status").trim();
				String pca_memo = req.getParameter("pca_memo").trim();

				//Step1  建立appVO
				PcApplicationVO pcApplicationVO = new PcApplicationVO();
				pcApplicationVO.setPc_no(pc_no);
				pcApplicationVO.setMem_no(mem_no);
				pcApplicationVO.setPca_appdate(pca_appdate);
				pcApplicationVO.setPca_memo(pca_memo);
				pcApplicationVO.setPca_date(pca_date);
				pcApplicationVO.setPca_status(pca_status);
				
				//Step2  建立積分記錄VO
				String pr_type = req.getParameter("pr_type").trim();
				String pr_content = req.getParameter("pr_content").trim();
				Integer pr_point = new Integer(req.getParameter("pr_point"));
				java.sql.Date pr_date = java.sql.Date.valueOf(req.getParameter("pca_date").trim()); 
				
				PointRecordVO pointRecordVO = new PointRecordVO();
				pointRecordVO.setMem_no(mem_no);				
				pointRecordVO.setPr_type(pr_type);				
				pointRecordVO.setPr_content(pr_content);				
				pointRecordVO.setPr_date(pca_date);				
				pointRecordVO.setPr_point(-pr_point);
				
				//Step3  更新會員積分
				//先找舊的
				MemberService memSVC = new MemberService();
				MemberVO memberVO_old = memSVC.getOneMem(mem_no);
				Integer old_point = new Integer(memberVO_old.getMem_point());
				Integer new_point = old_point - pr_point;
				
				//再算新的
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_point(new_point);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcApplicationVO", pcApplicationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/ApplicationPC.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				
				//Step4  由PCASvc新增交易
				PcApplicationService PCASvc = new PcApplicationService();
				pcApplicationVO = PCASvc.addPCA(pc_no, mem_no, pca_appdate, pca_date, pca_memo, pca_status,pointRecordVO,memberVO);				

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				MailService mail = new MailService();
				  String mailTitle="媽咪樂寶-會員申請案件通知";
				  String homeURL = "http://52.88.89.6:8081/AA107G3/front_end/frontINDEX.jsp";
				  String mailMessage="<html><head><title></title></head><body>"+pc_name+" 貴寶號您好：<br>有會員提出【預約參觀/試吃】申請囉！<br>為了保障您與會員的權益，請盡速至<a href="+homeURL+">媽咪樂寶</a>網頁登入審核!!<br><br>媽咪樂寶管理部關心您!!</body></html>";
			      mail.sendMail(pc_email,mailTitle,mailMessage);
			      successMsgs.add("目前剩餘積分："+new_point);
			      successMsgs.add("預約成功，請靜待廠商審核結果");
			      
			      //Step5  更新session會員資料
			      MemberVO userVO = memSVC.getOneMem(mem_no);			      
			      session.setAttribute("userVO", userVO);
			      
				String pageReq=(String)session.getAttribute("pageReq");
				
				RequestDispatcher successView = req.getRequestDispatcher(pageReq); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);			
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/PostpartumCare_Index.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateWithReport".equals(action)) { // 來自update_PC_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				//更新application及回傳需要資料					
				String pca_no = req.getParameter("pca_no").trim();				
				String pca_status = req.getParameter("pca_status").trim();				
				java.sql.Date pca_review_date = java.sql.Date.valueOf(req.getParameter("pcr_date").trim());
						
				String review_id = req.getParameter("review_id").trim();	
				
				PcApplicationVO pcApplicationVO = new PcApplicationVO();
				
				
				//取得會員編號給檢舉用
				PcApplicationService PCASvc = new PcApplicationService();
				String mem_no = PCASvc.getOnePCA(pca_no).getMem_no();
				
				//取得廠商編號給檢舉用
				String pc_no = req.getParameter("pc_no").trim();			
				
				//其他檢舉表格所需資料
				String pcr_type = req.getParameter("pcr_type").trim();				
				String pcr_content = req.getParameter("pcr_content").trim();						
				java.sql.Date pcr_date = pca_review_date;
				String pcr_status = req.getParameter("pcr_status").trim();
				
				PcReportVO pcReportVO = new PcReportVO();
				pcReportVO .setMem_no(mem_no);
				pcReportVO .setPc_no(pc_no);						
				pcReportVO .setPcr_type(pcr_type);				
				pcReportVO .setPcr_content(pcr_content);				
				pcReportVO .setPcr_date(pcr_date);		
				pcReportVO .setPcr_status(pcr_status);	
		
				
						
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcApplicationVO", pcApplicationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/PersonalPC_app.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料並新增檢舉*****************************************/
				PCASvc.updatePCAWithReport(pca_no, pca_status, pca_review_date,pcReportVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pcApplicationVO", pcApplicationVO);
				req.setAttribute("review_id", review_id);

				String url = "/postpartum_care/PC.do?action=listApps_ByPc_no&pc_no="+pc_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/PersonalPC_app.jsp");
				failureView.forward(req, res);
			}
		}        
        
        
		

	}
	

}

