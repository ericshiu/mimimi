package com.order_detail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrderDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);

//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("ord_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入廠商編號");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/firm/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String fir_no = null;
//				try {
//					fir_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("廠商編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/firm/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
//				FirmService firSvc = new FirmService();
//				FirmVO firVO = firSvc.getOneFir(fir_no);
//				if (firVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/firm/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("firVO", firVO); // 資料庫取出的empVO物件,存入req
//				String url = "/firm/listOneFirm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/firm/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String fir_no = req.getParameter("fir_no");
//				
//				/***************************2.開始查詢資料****************************************/
//				FirmService firSvc = new FirmService();
//				FirmVO firVO = firSvc.getOneFir(fir_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("firVO", firVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/firm/update_firm_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/firm/listAllFirm .jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String fir_no = req.getParameter("fir_no").trim();
//				String fir_authority = req.getParameter("fir_authority").trim();
//				Integer fir_eva_good = new Integer(req.getParameter("fir_eva_good").trim());
//				Integer fir_eva_normal = new Integer(req.getParameter("fir_eva_normal").trim());
//				Integer fir_eva_bad = new Integer(req.getParameter("fir_eva_bad").trim());
//				
//				String fir_name = req.getParameter("fir_name").trim();
//				String fir_id = req.getParameter("fir_id").trim();				
//
//				String fir_password = null;
//				try {
//					fir_password = req.getParameter("fir_password").trim();
//				} catch (Exception e) {
//					fir_password = "";
//					errorMsgs.add("請輸入密碼.");
//				}
//
//				String fir_phone = null;
//				try {
//					fir_phone = req.getParameter("fir_phone").trim();
//				} catch (NumberFormatException e) {
//					fir_phone = "";
//					errorMsgs.add("請輸入電話.");
//				}
//
//				String fir_address = new String(req.getParameter("fir_address").trim());
//				String fir_email = new String(req.getParameter("fir_email").trim());
//				String fir_account = new String(req.getParameter("fir_account").trim());
//				String fir_type = new String(req.getParameter("fir_type").trim());
//				
//				FirmVO firVO = new FirmVO();
//				firVO.setFir_no(fir_no);
//				firVO.setFir_name(fir_name);
//				firVO.setFir_id(fir_id);
//				firVO.setFir_password(fir_password);
//				firVO.setFir_phone(fir_phone);
//				firVO.setFir_address(fir_address);
//				firVO.setFir_email(fir_email);
//				firVO.setFir_account(fir_account);
//				firVO.setFir_type(fir_type);
//				firVO.setFir_authority(fir_authority);
//				firVO.setFir_eva_good(fir_eva_good);
//				firVO.setFir_eva_normal(fir_eva_normal);
//				firVO.setFir_eva_bad(fir_eva_bad);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("firVO", firVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/firm/update_firm_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				FirmService firSvc = new FirmService();
//				firVO = firSvc.updateFir(fir_id, fir_password, fir_name, fir_type, fir_phone,
//						fir_address, fir_email, fir_account,fir_authority,fir_eva_good,
//						fir_eva_normal,fir_eva_bad, fir_no);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("firVO", firVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/firm/listOneFirm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/firm/update_firm_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String fir_id = req.getParameter("fir_id").trim();
//				String fir_password = req.getParameter("fir_password").trim();
//				
//				String fir_name = null;
//				try {
//					fir_name = new String(req.getParameter("fir_name").trim());
//				} catch (Exception e) {
//					fir_name = "請填入名稱";
//					errorMsgs.add("請填入名稱");
//				}
//				String fir_type = req.getParameter("fir_type").trim();
//				String fir_phone = req.getParameter("fir_phone").trim();
//				String fir_address = req.getParameter("fir_address").trim();
//				String fir_email = req.getParameter("fir_email").trim();
//				String fir_account = req.getParameter("fir_account").trim();
//				String fir_authority = req.getParameter("fir_authority");
//				Integer fir_eva_good = new Integer(req.getParameter("fir_eva_good"));
//				Integer fir_eva_normal = new Integer(req.getParameter("fir_eva_normal"));
//				Integer fir_eva_bad = new Integer(req.getParameter("fir_eva_bad"));		
//				
//				FirmVO firVO = new FirmVO();
//				firVO.setFir_id(fir_id);
//				firVO.setFir_password(fir_password);
//				firVO.setFir_name(fir_name);
//				firVO.setFir_type(fir_type);
//				firVO.setFir_phone(fir_phone);
//				firVO.setFir_address(fir_address);
//				firVO.setFir_email(fir_email);
//				firVO.setFir_account(fir_account);
//				firVO.setFir_authority(fir_authority);
//				firVO.setFir_eva_good(fir_eva_good);
//				firVO.setFir_eva_normal(fir_eva_normal);
//				firVO.setFir_eva_bad(fir_eva_bad);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("firVO", firVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				FirmService firSvc = new FirmService();
//				firVO = firSvc.addFir(fir_id, fir_password, fir_name, fir_type, fir_phone,
//						fir_address, fir_email, fir_account, fir_authority, fir_eva_good,
//						fir_eva_normal, fir_eva_bad);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/firm/listAllFirm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/firm/addFir.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String fir_no = req.getParameter("fir_no");
//				
//				/***************************2.開始刪除資料***************************************/
//				FirmService firSvc = new FirmService();
//				firSvc.deleteFir(fir_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/firm/listAllFirm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/firm/listAllFirm.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
