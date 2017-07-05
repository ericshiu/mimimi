package com.baby_growth.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.baby_eating.model.BabyEatingService;
import com.baby_eating.model.BabyEatingVO;
import com.baby_growth.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class BabyGrowthServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_bg_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("bg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_growth/select_bg_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String bg_no = null;
				try {
					bg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_growth/select_bg_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BabyGrowthService BGSvc = new BabyGrowthService();
				BabyGrowthVO babyGrowthVO = BGSvc.getOneBG(bg_no);
				if (babyGrowthVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_growth/select_bg_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("babyGrowthVO", babyGrowthVO); // 資料庫取出的empVO物件,存入req
				String url = "/baby_growth/listOneBG.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/baby_growth/select_bg_page.jsp");
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
				String bg_no = new String(req.getParameter("bg_no"));
				
				/***************************2.開始查詢資料****************************************/		
				BabyGrowthService BGSvc = new BabyGrowthService();
				BabyGrowthVO babyGrowthVO = BGSvc.getOneBG(bg_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("babyGrowthVO", babyGrowthVO);         // 資料庫取出的empVO物件,存入req
				String url = "/member/bg_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/bg_select.jsp");
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
				String bg_no = req.getParameter("bg_no").trim();
				Integer bg_height = new Integer(req.getParameter("bg_height").trim());
				Integer bg_weight = new Integer(req.getParameter("bg_weight").trim());
				Integer bg_head = new Integer(req.getParameter("bg_head").trim());				
				String bd_no = req.getParameter("bd_no").trim();
System.out.println(123);
				java.sql.Date bg_date = null;
				try {
					bg_date = java.sql.Date.valueOf(req.getParameter("bg_date").trim());
				} catch (IllegalArgumentException e) {
					bg_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				BabyGrowthVO babyGrowthVO = new BabyGrowthVO();
				
				babyGrowthVO.setBg_no(bg_no);
				babyGrowthVO.setBd_no(bd_no);
				babyGrowthVO.setBg_height(bg_height);
				babyGrowthVO.setBg_weight(bg_weight);
				babyGrowthVO.setBg_head(bg_head);
				babyGrowthVO.setBg_date(bg_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("babyGrowthVO", babyGrowthVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/bg_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BabyGrowthService BGSvc = new BabyGrowthService();
				System.out.println(bg_head);
				babyGrowthVO = BGSvc.updateBG(bd_no,bg_height,bg_weight,bg_head,bg_no,bg_date);
				System.out.println("3333333333");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("babyGrowthVO", babyGrowthVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/bg_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/bg_update.jsp");
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
				Integer bg_height = new Integer(req.getParameter("bg_height").trim());
				Integer bg_weight = new Integer(req.getParameter("bg_weight").trim());
				Integer bg_head = new Integer(req.getParameter("bg_head").trim());				
				String bd_no = req.getParameter("bd_no").trim();
				java.sql.Date bg_date = null;
				try {
					bg_date = java.sql.Date.valueOf(req.getParameter("bg_date").trim());
				} catch (IllegalArgumentException e) {
					bg_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				BabyGrowthVO babyGrowthVO = new BabyGrowthVO();
				
 
				babyGrowthVO.setBd_no(bd_no);
				babyGrowthVO.setBg_height(bg_height);
				babyGrowthVO.setBg_weight(bg_weight);
				babyGrowthVO.setBg_head(bg_head);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				//Step2  建立積分記錄VO
				String mem_no = req.getParameter("mem_no").trim();
				
				String pr_type = "每日";
				String pr_content = "寶寶成長紀錄";
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
					req.setAttribute("babyGrowthVO", babyGrowthVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/bg_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BabyGrowthService BGSvc = new BabyGrowthService();
				babyGrowthVO = BGSvc.addBG( bg_height, bg_weight,bg_head,bd_no,bg_date,pointRecordVO,memberVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/bg_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/bg_add.jsp");
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
				String bg_no = new String(req.getParameter("bg_no"));
				
				/***************************2.開始刪除資料***************************************/
				BabyGrowthService BGSvc = new BabyGrowthService();
				BGSvc.deleteBG(bg_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/baby_growth/listAllBG.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/baby_growth/listAllBG.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listBG_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			System.out.println("listBG_ByCompositeQuery");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session1 = req.getSession();
				System.out.println("1");
				Map<String, String[]> map = (Map<String, String[]>)session1.getAttribute("map");
				System.out.println("2");
				if (req.getParameter("whichPage") == null){
					System.out.println("3");
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					System.out.println("4");
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session1.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				System.out.println("5");
				/***************************2.開始複合查詢***************************************/
				BabyGrowthService BGSvc = new BabyGrowthService();
				System.out.println("6");
				List<BabyGrowthVO> list  = BGSvc.getAll(map);
				System.out.println("66");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lisBG_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				System.out.println(list);
				RequestDispatcher successView = req.getRequestDispatcher("/member/bg_onebd.jsp"); // 成功轉交
				
				successView.forward(req, res);
				System.out.println("66666");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println("7");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/bg_select.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
}