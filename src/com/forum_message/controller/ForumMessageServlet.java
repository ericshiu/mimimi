package com.forum_message.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.forum_article.model.ForumArticleService;
import com.forum_article.model.ForumArticleVO;
import com.forum_message.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public class ForumMessageServlet extends HttpServlet {

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
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
			if (pageReq==null || pageReq==""){
				pageReq="/front_end/frontINDEX.jsp";
			}
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String fa_no = req.getParameter("fa_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				String fm_content=req.getParameter("fm_content").trim();
			
				
//				建立FMVO
				ForumMessageVO FMVO = new ForumMessageVO();
				FMVO.setFa_no(fa_no);
				FMVO.setMem_no(mem_no);
				FMVO.setFm_content(fm_content);
				
				
				//Step1  建立積分記錄VO
				String pr_type = "討論區積分";
				String pr_content = "發表留言:"+fa_no;
				Integer pr_point=2;
				//開始時間為現在
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒									//再轉成秒數
				java.sql.Date pr_date = new java.sql.Date(long1);
				
				PointRecordVO pointRecordVO = new PointRecordVO();
				pointRecordVO.setMem_no(mem_no);				
				pointRecordVO.setPr_type(pr_type);				
				pointRecordVO.setPr_content(pr_content);				
				pointRecordVO.setPr_date(pr_date);				
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
				
			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FMVO", FMVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ForumMessageService FMSvc = new ForumMessageService();
				FMSvc.addFmWithPoint(FMVO, pointRecordVO,memberVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				ForumArticleService faSvc = new ForumArticleService();
				ForumArticleVO FAVO = faSvc.getOneFa(fa_no);
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_no);
				req.setAttribute("FAVO", FAVO);
				req.setAttribute("memVO", memVO);
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(pageReq);
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
				String fm_no = req.getParameter("fm_no");
				
				/***************************2.開始刪除資料***************************************/
				ForumMessageService FMSvc = new ForumMessageService();
				FMSvc.deleteFm(fm_no);
				
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
