package com.fetal_movement.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fetal_movement.model.FetalMovementService;
import com.fetal_movement.model.FetalMovementVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class FetalMovementServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		
		
		if ("getOne_For_Display".equals(action)) { // 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1111");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fm_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("fm_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String fm_no = null;
				try {
					fm_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("fm_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FetalMovementService DDSvc = new FetalMovementService();
				FetalMovementVO fetalMovementVO = DDSvc.getOneFd_no(fm_no);
				if (fetalMovementVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("fm_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fetalMovementVO", fetalMovementVO); // 資料庫取出的empVO物件,存入req
				String url = "fm_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("fm_select.jsp");
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
				String fm_no = new String(req.getParameter("fm_no"));
				
				/***************************2.開始查詢資料****************************************/
				FetalMovementService DDSvc = new FetalMovementService();
				FetalMovementVO fetalMovementVO = DDSvc.getOneFd_no(fm_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("fetalMovementVO", fetalMovementVO);   // 資料庫取出的VO物件,存入req
				String url = "/DateDelivery/updateDD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/DateDelivery/listAllDD.jsp");
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
				String fm_no = new String(req.getParameter("fm_no").trim());
				String mem_no = req.getParameter("mem_no").trim();
								
				
				
				String fm_lv = null;
				try {
					fm_lv = new String(req.getParameter("fm_lv").trim());
				} catch (NumberFormatException e) {
					fm_lv = "1";
					errorMsgs.add("請填輸入胎動等級");
				}

				Timestamp fm_date = null;
				try {
					fm_date = java.sql.Timestamp.valueOf(req.getParameter("fm_date").trim());
				} catch (IllegalArgumentException e) {
					fm_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				FetalMovementVO fetalMovementVO = new FetalMovementVO();
				
				fetalMovementVO.setFm_no(fm_no);
				fetalMovementVO.setMem_no(mem_no);
				fetalMovementVO.setFm_date(fm_date);
				fetalMovementVO.setFm_lv(fm_lv);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fetalMovementVO", fetalMovementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("updateDD.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FetalMovementService DDSvc = new FetalMovementService();
				fetalMovementVO = DDSvc.updateFM(fm_no, mem_no, fm_date, fm_lv);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fetalMovementVO", fetalMovementVO); 
				// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				// 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/DateDeliveryVO/updateDD.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自fm_select_add.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.println("測試ˇˇ333");	
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println("測試55");	

	
				String fm_lv = null;
				try {
					fm_lv = new String(req.getParameter("fm_lv").trim());
				} catch (Exception e) {
					
					errorMsgs.add("請填輸入胎動等級");
				}

				

				FetalMovementVO fetalMovementVO = new FetalMovementVO();
				
			
				fetalMovementVO.setMem_no(mem_no);
				fetalMovementVO.setFm_lv(fm_lv);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				//Step2  建立積分記錄VO
				
				
				String pr_type = "每日";
				String pr_content = "登入寶寶進食紀錄";
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
					req.setAttribute("fetalMovementVO", fetalMovementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/fm_select_add.jsp");
					failureView.forward(req, res);
					System.out.println("add");
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FetalMovementService DDSvc = new FetalMovementService();
				fetalMovementVO = DDSvc.addFM(mem_no, fm_lv,pointRecordVO,memberVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/fm_select_add.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/fm_select_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String fm_no = new String(req.getParameter("fm_no"));
				
				
				/***************************2.開始刪除資料***************************************/
				FetalMovementService DDSvc = new FetalMovementService();
				
				DDSvc.deleteFM_no(fm_no);
				
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