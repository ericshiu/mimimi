package com.baby_gcurve.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.baby_gcurve.model.*;

public class BabyGCurveServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_bgc_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bgc_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_gcurve/select_bgc_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String bgc_no = null;
				try {
					bgc_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_gcurve/select_bgc_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BabyGCurveService BGCSvc = new BabyGCurveService();
				BabyGCurveVO babyGCurveVO = BGCSvc.getOneBGC(bgc_no);
				if (babyGCurveVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_gcurve/select_bgc_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("babyGCurveVO", babyGCurveVO); // 資料庫取出的empVO物件,存入req
				String url = "/baby_gcurve/listOneBGC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/baby_gcurve/select_bgc_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		

	}
}
