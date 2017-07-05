package com.mom_health.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.mom_health.model.*;
import com.point_record.model.PointRecordVO;


public class MomHealthServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1111");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mh_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mh_no = null;
				try {
					mh_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MomHealthService DDSvc = new MomHealthService();
				MomHealthVO momHealthVO = DDSvc.getOneMH_no(mh_no);
				if (momHealthVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("momHealthVO", momHealthVO); // 資料庫取出的empVO物件,存入req
				String url = "/DateDelivery/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/DateDelivery/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mh_no = new String(req.getParameter("mh_no"));
			
				/***************************2.開始查詢資料****************************************/
				MomHealthService MHSvc = new MomHealthService();
					
				MomHealthVO momHealthVO = MHSvc.getOneMH_no(mh_no);
					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("momHealthVO", momHealthVO);   // 資料庫取出的VO物件,存入req
				
				String url = "/member/mh_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update.jsp
				
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/mh_select.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mh_no = new String(req.getParameter("mh_no").trim());
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println("測試1");			
				
				java.sql.Date mh_date = null;
				try {
					mh_date = java.sql.Date.valueOf(req.getParameter("mh_date").trim());
				} catch (IllegalArgumentException e) {
					mh_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入紀錄日期!");
				}
				System.out.println("測試2");	
				Integer mh_weight = null;
				try {
					mh_weight = new Integer(req.getParameter("mh_weight").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填體重");
				}
				System.out.println("測試3");	
				Integer mh_ststolic = null;
				try {
					mh_ststolic = new Integer(req.getParameter("mh_ststolic").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填收縮壓");
				}
				System.out.println("測試4");	
				Integer mh_diastolic = null;
				try {
					mh_diastolic = new Integer(req.getParameter("mh_diastolic").trim());
				} catch (NumberFormatException e) {
					mh_diastolic = null;
					errorMsgs.add("請填舒張壓");
				}
				System.out.println("測試5");	
				Integer mh_heartbeat = null;
				try {
					mh_heartbeat = new Integer(req.getParameter("mh_heartbeat").trim());
				} catch (NumberFormatException e) {
					mh_heartbeat = null;
					errorMsgs.add("請填心跳");
				}
				System.out.println("測試6");	
				String mh_protein = null;
				try {
					mh_protein = new String(req.getParameter("mh_protein").trim());
				} catch (NumberFormatException e) {
					mh_protein = null;
					errorMsgs.add("請填尿蛋白");
				}
				System.out.println("測試7");	
				Integer mh_baby_heartbeat = null;
				try {
					mh_baby_heartbeat = new Integer(req.getParameter("mh_baby_heartbeat").trim());
				} catch (NumberFormatException e) {
					mh_baby_heartbeat = null;
					errorMsgs.add("請填寶寶心跳");
				}
				System.out.println("測試8");	
				
				MomHealthVO momHealthVO = new MomHealthVO();
				
				momHealthVO.setMh_no(mh_no);
				momHealthVO.setMem_no(mem_no);
				momHealthVO.setMh_date(mh_date);
				momHealthVO.setMh_weight(mh_weight);
				momHealthVO.setMh_diastolic(mh_ststolic);
				momHealthVO.setMh_diastolic(mh_diastolic);
				momHealthVO.setMh_heartbeat(mh_heartbeat);
				momHealthVO.setMh_protein(mh_protein);
				momHealthVO.setMh_baby_heartbeat(mh_baby_heartbeat);
				System.out.println(mh_no);	
				System.out.println(mem_no);	
				System.out.println(mh_date);	
				System.out.println(mh_weight);	
				System.out.println(mh_ststolic);	
				System.out.println(mh_diastolic);	
				System.out.println(mh_heartbeat);	
				System.out.println(mh_protein);	
				System.out.println(mh_baby_heartbeat);	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("momHealthVO", momHealthVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/mh_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				 
				/***************************2.開始修改資料*****************************************/
				MomHealthService MHSvc = new MomHealthService();
				System.out.println("測試9");	
				momHealthVO = MHSvc.updateMH(mh_no, mem_no, mh_date, mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat, mh_protein,mh_baby_heartbeat);
				System.out.println("測試10");	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("momHealthVO", momHealthVO); 
				// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/mh_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				// 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/mh_update.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addDD.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				
				
				java.sql.Date mh_date = null;
				try {
					mh_date = java.sql.Date.valueOf(req.getParameter("mh_date").trim());
				} catch (IllegalArgumentException e) {
					mh_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer mh_weight = null;
				try {
					mh_weight = new Integer(req.getParameter("mh_weight").trim());
				} catch (NumberFormatException e) {
					mh_weight = null;
					errorMsgs.add("請填體重");
				}
				Integer mh_ststolic = null;
				try {
					mh_ststolic = new Integer(req.getParameter("mh_ststolic").trim());
				} catch (NumberFormatException e) {
					mh_ststolic = null;
					errorMsgs.add("請填收縮壓");
				}
				Integer mh_diastolic = null;
				try {
					mh_diastolic = new Integer(req.getParameter("mh_diastolic").trim());
				} catch (NumberFormatException e) {
					mh_diastolic = null;
					errorMsgs.add("請填舒張壓");
				}
				
				Integer mh_heartbeat = null;
				try {
					mh_heartbeat = new Integer(req.getParameter("mh_heartbeat").trim());
				} catch (NumberFormatException e) {
					mh_heartbeat = null;
					errorMsgs.add("請填心跳");
				}
				
				String mh_protein = null;
				try {
					mh_protein = new String(req.getParameter("mh_protein").trim());
				} catch (NumberFormatException e) {
					mh_protein = null;
					errorMsgs.add("請填尿蛋白");
				}
				
				Integer mh_baby_heartbeat = null;
				try {
					mh_baby_heartbeat = new Integer(req.getParameter("mh_baby_heartbeat").trim());
				} catch (NumberFormatException e) {
					mh_baby_heartbeat = null;
					errorMsgs.add("請填寶寶心跳");
				}

				
				
				
				MomHealthVO momHealthVO = new MomHealthVO();
				
				
				momHealthVO.setMem_no(mem_no);
				momHealthVO.setMh_date(mh_date);
				momHealthVO.setMh_weight(mh_weight);
				momHealthVO.setMh_ststolic(mh_ststolic);
				momHealthVO.setMh_diastolic(mh_diastolic);
				momHealthVO.setMh_heartbeat(mh_heartbeat);
				momHealthVO.setMh_protein(mh_protein);
				momHealthVO.setMh_baby_heartbeat(mh_baby_heartbeat);
				
				//Step2  建立積分記錄VO
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				
				
				
				String pr_type = "每日";
				String pr_content = "媽媽健康紀錄";
				Integer pr_point = 10;
					
				java.sql.Date pr_date =  java.sql.Date.valueOf(dateString);
								
				PointRecordVO pointRecordVO = new PointRecordVO();
				pointRecordVO.setMem_no(mem_no);				
				pointRecordVO.setPr_type(pr_type);				
				pointRecordVO.setPr_content(pr_content);				
				pointRecordVO.setPr_date(pr_date);				
				pointRecordVO.setPr_point(pr_point);
						
				//Step3  更新會員積分
				//先找舊的
				MemberService memSVC = new MemberService();
				System.out.println("測試4");
				MemberVO memberVO_old = memSVC.getOneMem(mem_no);
				System.out.println("測試44"+mem_no);
				Integer a= (0+memberVO_old.getMem_point());
				System.out.println(a);
				Integer old_point = new Integer(0+memberVO_old.getMem_point());
				System.out.println("測試444");
				Integer new_point = old_point + pr_point;
				System.out.println("測試4444");
				
				
				//再算新的
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_point(new_point);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("momHealthVO", momHealthVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/mh_add.jsp");
					failureView.forward(req, res);
					System.out.println("add");
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				System.out.println("測試5");
				MomHealthService DDSvc = new MomHealthService();
				System.out.println("測試6");
				momHealthVO = DDSvc.addMH(mem_no, mh_date, mh_weight,mh_ststolic,mh_diastolic,mh_heartbeat,mh_protein,mh_baby_heartbeat,pointRecordVO,memberVO);
				System.out.println("測試7");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/mh_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/mh_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mh_no = new String(req.getParameter("mh_no"));
				System.out.println("1111111111111");
				
				/***************************2.開始刪除資料***************************************/
				MomHealthService DDSvc = new MomHealthService();
				DDSvc.deleteMH_no(mh_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/DateDelivery/listAllDD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/DateDelivery/listAllDD.jsp");
				failureView.forward(req, res);
			}
		}
	}
		
		
	}
