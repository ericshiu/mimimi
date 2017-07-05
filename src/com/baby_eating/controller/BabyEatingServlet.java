package com.baby_eating.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.baby_eating.model.*;
import com.forum_report.model.ForumReportService;
import com.forum_report.model.ForumReportVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;


public class BabyEatingServlet extends HttpServlet {

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
				String str = req.getParameter("be_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_eating/select_be_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String be_no = null;
				try {
					be_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_eating/select_be_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BabyEatingService BESvc = new BabyEatingService();
				BabyEatingVO babyEatingVO = BESvc.getOneBE(be_no);
				if (babyEatingVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/baby_eating/select_be_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("babyEatingVO", babyEatingVO); // 資料庫取出的empVO物件,存入req
				String url = "/baby_eating/listOneBE.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/baby_eating/select_be_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneBD".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String bd_no = req.getParameter("bd_no");

				
				/***************************2.開始查詢資料*****************************************/
				System.out.println("4");
				BabyEatingService BESvc = new BabyEatingService();

				BabyEatingVO babyEatingVO = BESvc.getOneBDD(bd_no);

				
				if (babyEatingVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/be_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				System.out.println("5");
				req.setAttribute("babyEatingVO", babyEatingVO); // 資料庫取出的empVO物件,存入req
				System.out.println(babyEatingVO);
				String url = "/member/be_onebd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/be_select.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String  be_no = new String(req.getParameter("be_no"));
				
				
				/***************************2.開始查詢資料****************************************/
				BabyEatingService BESvc = new BabyEatingService();
				BabyEatingVO babyEatingVO = BESvc.getOneBE(be_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("babyEatingVO", babyEatingVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/be_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/be_select.jsp");
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
				String be_no = req.getParameter("be_no").trim();		
				String be_sort = req.getParameter("be_sort").trim();			
				
				java.sql.Timestamp be_date = null;
				try {
					be_date = java.sql.Timestamp.valueOf(req.getParameter("be_date").trim());
				} catch (IllegalArgumentException e) {
					be_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer be_mete = new Integer(req.getParameter("be_mete").trim());
				String bd_no = req.getParameter("bd_no").trim();
				
				BabyEatingVO babyEatingVO = new BabyEatingVO();
				
				// 修改
				babyEatingVO.setBe_no(be_no);
				babyEatingVO.setBe_date(be_date);
				babyEatingVO.setBe_sort(be_sort);
				babyEatingVO.setBe_mete(be_mete);
				babyEatingVO.setBd_no(bd_no);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("babyEatingVO", babyEatingVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/be_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BabyEatingService BESvc = new BabyEatingService();
				babyEatingVO = BESvc.updateBE(be_no,be_date,be_sort,be_mete,bd_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("babyEatingVO", babyEatingVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/be_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/be_update.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	System.out.println("測試1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String bd_no = req.getParameter("bd_no").trim();
				String be_sort = req.getParameter("be_sort").trim();
				String mem_no = req.getParameter("mem_no").trim();

				
				Timestamp be_date = null;
				try {
					be_date = java.sql.Timestamp.valueOf(req.getParameter("be_date").trim());
				} catch (IllegalArgumentException e) {
					be_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
					System.out.println("測試ˋ");
				}

				Integer be_mete = new Integer(req.getParameter("be_mete").trim());
				
				
				BabyEatingVO babyEatingVO = new BabyEatingVO();

				babyEatingVO.setBe_date(be_date);
				babyEatingVO.setBe_sort(be_sort);
				babyEatingVO.setBe_mete(be_mete);
				babyEatingVO.setBd_no(bd_no);
				


				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				//Step2  建立積分記錄VO
				String pr_type = "每日";
				String pr_content = "寶寶進食紀錄";
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
				System.out.println("測試5");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("babyEatingVO", babyEatingVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/be_add.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BabyEatingService babyEatingSvc = new BabyEatingService();
				babyEatingVO = babyEatingSvc.addBE(be_date,be_sort,be_mete,bd_no,pointRecordVO,memberVO);
		
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/be_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/be_add.jsp");
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
				String be_no = new String(req.getParameter("be_no"));
				
				/***************************2.開始刪除資料***************************************/
				BabyEatingService BESvc = new BabyEatingService();
				BESvc.deleteBE(be_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/baby_eating/listAllBE.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/baby_eating/listAllBE.jsp");
				failureView.forward(req, res);
			}
		}
        if ("listBE_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			System.out.println("listBE_ByCompositeQuery");
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
				BabyEatingService BESvc = new BabyEatingService();
				System.out.println("6");
				List<BabyEatingVO> list  = BESvc.getAll(map);
				System.out.println("66");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("lisBE_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				System.out.println(list);
				RequestDispatcher successView = req.getRequestDispatcher("/member/be_onebd.jsp"); // 成功轉交
				
				successView.forward(req, res);
				System.out.println("66666");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				System.out.println("7");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/be_select.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
	}
}