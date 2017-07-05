package com.manager.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.authority_list.model.*;
import com.manager.model.*;

public class ChangeManServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("changeManAut".equals(action)) { // 來自update_emp_input.jsp的請求
			
			String pageReq=(String)session.getAttribute("pageReq");

			List<String> errorMsgs = new LinkedList<String>();
	
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String man_no=req.getParameter("man_no").trim();
				String[] array_aut_no=req.getParameterValues("aut_no");
				
				
				
				
				AuthorityListVO autlistVO = new AuthorityListVO();
				/*************************** 2.開始修改資料 *****************************************/
				AuthorityListService autlistSvc= new AuthorityListService();
				autlistSvc.deleteAutlist(man_no);
				for (int i=0;i<array_aut_no.length;i++){
					String aut_no=array_aut_no[i];
					
					autlistVO = autlistSvc.addAutlist(man_no, aut_no);
				}

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				ManagerVO manVO=new ManagerVO();
				ManagerService manSvc=new ManagerService();
				manVO=manSvc.getOneMan(man_no);
				req.setAttribute("manVO", manVO);
				req.setAttribute("autlistVO", autlistVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
	
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
	

	
	}

}