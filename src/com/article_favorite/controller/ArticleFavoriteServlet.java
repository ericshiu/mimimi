package com.article_favorite.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.article_favorite.model.*;


public class ArticleFavoriteServlet extends HttpServlet {

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
		PrintWriter out=res.getWriter();
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArticleFavoriteService FASvc = new ArticleFavoriteService();
				ArticleFavoriteVO FAVO = FASvc.getOneAF(mem_no);
				if (FAVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("FAVO", FAVO); // 資料庫取出的FAVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		



        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
			String success="success";
			String fail="fail";
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String fa_no=req.getParameter("fa_no");
				
				
				ArticleFavoriteVO AFVO = new ArticleFavoriteVO();
				AFVO.setMem_no(mem_no);
				AFVO.setFa_no(fa_no);
	out.write("測試xhr抓到值");
	System.out.println("測試xhr抓到值");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					result( fail);
					
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleFavoriteService AFSvc = new ArticleFavoriteService();
				AFVO = AFSvc.addAF(mem_no,fa_no);
				result(success);
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				result (fail);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			String success="success";
			String fail="fail";
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String fa_no=req.getParameter("fa_no");
				ArticleFavoriteVO AFVO = new ArticleFavoriteVO();
				AFVO.setMem_no(mem_no);
				AFVO.setFa_no(fa_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					result( fail);
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleFavoriteService AFSvc = new ArticleFavoriteService();
				AFSvc.deleteAF(mem_no,fa_no);
				result(success);
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				
				result (fail);
			}
		}
		
		
	
		
	}
	
	
	public String result(String str) {
		return str;
	}

	
}
