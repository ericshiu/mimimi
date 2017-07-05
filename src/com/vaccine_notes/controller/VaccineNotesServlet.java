package com.vaccine_notes.controller;

import java.io.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;
import com.vaccine_notes.model.*;

public class VaccineNotesServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_vn_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("vn_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/vn_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String vn_no = null;
				try {
					vn_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/vaccine_notes/select_vn_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				VaccineNotesService VNSvc = new VaccineNotesService();
				VaccineNotesVO vaccineNotesVO = VNSvc.getOneVN(vn_no);
				if (vaccineNotesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/vaccine_notes/select_vn_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("vaccineNotesVO", vaccineNotesVO); // 資料庫取出的empVO物件,存入req
				String url = "/vaccine_notes/listOneVN.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/vaccine_notes/select_vn_page.jsp");
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
				String vn_no = new String(req.getParameter("vn_no"));
				
				/***************************2.開始查詢資料****************************************/
				VaccineNotesService VNSvc = new VaccineNotesService();
				VaccineNotesVO vaccineNotesVO = VNSvc.getOneVN(vn_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("vaccineNotesVO", vaccineNotesVO);         // 資料庫取出的empVO物件,存入req
				String url = "/member/vn_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/vn_select.jsp");
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
				String vn_no = req.getParameter("vn_no").trim();
				String vc_no = req.getParameter("vc_no").trim();
				
				java.sql.Date vn_date = null;
				try {
					vn_date = java.sql.Date.valueOf(req.getParameter("vn_date").trim());
				} catch (IllegalArgumentException e) {
					vn_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String bd_no = req.getParameter("bd_no").trim();

				VaccineNotesVO vaccineNotesVO = new VaccineNotesVO();
				
				vaccineNotesVO.setVn_no(vn_no);
				vaccineNotesVO.setVc_no(vc_no);
				vaccineNotesVO.setVn_date(vn_date);
				vaccineNotesVO.setBd_no(bd_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("vaccineNotesVO", vaccineNotesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/vn_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				VaccineNotesService VNSvc = new VaccineNotesService();
				vaccineNotesVO = VNSvc.updateVN( vn_no,bd_no,vc_no,vn_date);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("vaccineNotesVO", vaccineNotesVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/vn_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/vn_update.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String vc_no = req.getParameter("vc_no").trim();
				
				java.sql.Date vn_date = null;
				try {
					vn_date = java.sql.Date.valueOf(req.getParameter("vn_date").trim());
				} catch (IllegalArgumentException e) {
					vn_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String bd_no = req.getParameter("bd_no").trim();

				VaccineNotesVO vaccineNotesVO = new VaccineNotesVO();
				
				vaccineNotesVO.setVc_no(vc_no);
				vaccineNotesVO.setVn_date(vn_date);
				vaccineNotesVO.setBd_no(bd_no);
				//Step2  建立積分記錄VO
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				
				String mem_no = req.getParameter("mem_no").trim();
				
				String pr_type = "每日";
				String pr_content = "預防針紀錄";
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
					req.setAttribute("vaccineNotesVO", vaccineNotesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/vn_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				VaccineNotesService VNSvc = new VaccineNotesService();
				vaccineNotesVO = VNSvc.addVN( bd_no,vc_no,vn_date,pointRecordVO,memberVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/vn_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/vn_add.jsp");
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
				String vn_no = new String(req.getParameter("vn_no"));
				
				/***************************2.開始刪除資料***************************************/
				VaccineNotesService VNSvc = new VaccineNotesService();
				VNSvc.deleteVN(vn_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/vaccine_notes/listAllVN.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/vaccine_notes/listAllVN.jsp");
				failureView.forward(req, res);
			}
		}
	}
}