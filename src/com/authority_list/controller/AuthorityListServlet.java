package com.authority_list.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.authority_list.model.*;

public class AuthorityListServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq=(String)session.getAttribute("pageReq");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String man_no = req.getParameter("man_no");
				if (man_no == null || (man_no.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				AuthorityListService autlistSvc = new AuthorityListService();
				AuthorityListVO autlistVO = autlistSvc.getOneAutlist(man_no);
				if (autlistVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("autlistVO", autlistVO); // 資料庫取出的autlistVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
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
				String man_no = req.getParameter("man_no");
				
				/***************************2.開始查詢資料****************************************/
				AuthorityListService autlistSvc = new AuthorityListService();
				AuthorityListVO autlistVO = autlistSvc.getOneAutlist(man_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("autlistVO", autlistVO);         // 資料庫取出的autlistVO物件,存入req
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
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
				
				String man_no = req.getParameter("man_no").trim();
				String aut_no = req.getParameter("aut_no").trim();				
				
				
				AuthorityListVO autlistVO = new AuthorityListVO();
				autlistVO.setMan_no(man_no);
				autlistVO.setAut_no(aut_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("autlistVO", autlistVO); // 含有輸入格式錯誤的autlistVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AuthorityListService autlistSvc = new AuthorityListService();
				autlistVO = autlistSvc.updateAutlist(man_no, aut_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("autlistVO", autlistVO); // 資料庫update成功後,正確的的autlistVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp");
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
				String man_no = req.getParameter("man_no").trim();
				String aut_no = req.getParameter("aut_no").trim();

				AuthorityListVO autlistVO = new AuthorityListVO();
				autlistVO.setMan_no(man_no);
				autlistVO.setAut_no(aut_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("autlistVO", autlistVO); // 含有輸入格式錯誤的autlistVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AuthorityListService autlistSvc = new AuthorityListService();
				autlistVO = autlistSvc.addAutlist(man_no,aut_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
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
				String man_no = req.getParameter("man_no");
				
				/***************************2.開始刪除資料***************************************/
				AuthorityListService autlistSvc = new AuthorityListService();
				autlistSvc.deleteAutlist(man_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
