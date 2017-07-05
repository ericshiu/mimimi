package com.inspection.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.inspection.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;



public class InspectionServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("1111");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ins_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ins_no = null;
				try {
					ins_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				InspectionService DDSvc = new InspectionService();
				InspectionVO inspectionVO = DDSvc.getOneIns_no(ins_no);
				if (inspectionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/DateDelivery/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("inspectionVO", inspectionVO); // 資料庫取出的empVO物件,存入req
				String url = "/DateDelivery/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/DateDelivery/select_page.jsp");
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
				String ins_no = new String(req.getParameter("ins_no"));
				
				/***************************2.開始查詢資料****************************************/
				InspectionService DDSvc = new InspectionService();
				InspectionVO inspectionVO = DDSvc.getOneIns_no(ins_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("inspectionVO", inspectionVO);   // 資料庫取出的VO物件,存入req
				String url = "/member/ins_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/ins_select.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("update");		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ins_no = new String(req.getParameter("ins_no").trim());
				System.out.println("update2222222222222");
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println("update23333333333333");
								
				
				
				String ins_hospital = null;
				try {
					ins_hospital = new String(req.getParameter("ins_hospital").trim());
				} catch (NumberFormatException e) {
					ins_hospital = null;
					errorMsgs.add("請填醫院名稱");
				}
				System.out.println("update2");
				String ins_outoatuent = null;
				try {
					ins_outoatuent = new String(req.getParameter("ins_outoatuent").trim());
				} catch (NumberFormatException e) {
					ins_outoatuent = null;
					errorMsgs.add("請填診別");
				}
				System.out.println("update3");
				Integer ins_resetvation_no = null;
				try {
					ins_resetvation_no = new Integer(req.getParameter("ins_resetvation_no").trim());
				} catch (NumberFormatException e) {
					ins_resetvation_no = null;
					errorMsgs.add("請填預約編號");
				}
				System.out.println("update4");
				java.sql.Timestamp ins_date = null;
				try {
					ins_date = java.sql.Timestamp.valueOf(req.getParameter("ins_date").trim());
				} catch (IllegalArgumentException e) {
					ins_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
System.out.println("11111111");
				InspectionVO inspectionVO = new InspectionVO();
				
				inspectionVO.setIns_no(ins_no);
				inspectionVO.setMem_no(mem_no);
				inspectionVO.setIns_hospital(ins_hospital);
				inspectionVO.setIns_outoatuent(ins_outoatuent);
				inspectionVO.setIns_resetvation_no(ins_resetvation_no);
				inspectionVO.setIns_date(ins_date);
System.out.println("222222");			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("inspectionVO", inspectionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/ins_update.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				InspectionService DDSvc = new InspectionService();
				inspectionVO = DDSvc.updateINS(ins_no, mem_no, ins_hospital, ins_outoatuent,ins_resetvation_no,ins_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("inspectionVO", inspectionVO); 
				// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/ins_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				// 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/ins_update.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addDD.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
				String mem_no = req.getParameter("mem_no").trim();
								
				System.out.println("aaaaa");
				
				String ins_hospital = null;
				try {
					ins_hospital = new String(req.getParameter("ins_hospital").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填醫院名稱");
				}
				System.out.println("aaaaa2");
				String ins_outoatuent = null;
				try {
					ins_outoatuent = new String(req.getParameter("ins_outoatuent").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填診別");
				}
				Integer ins_resetvation_no = null;
				try {
					ins_resetvation_no = new Integer(req.getParameter("ins_resetvation_no").trim());
				} catch (NumberFormatException e) {
					
					errorMsgs.add("請填預約編號");
				}

				Timestamp ins_date = null;
				try {
					ins_date = java.sql.Timestamp.valueOf(req.getParameter("ins_date").trim());
				} catch (IllegalArgumentException e) {
					ins_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				InspectionVO inspectionVO = new InspectionVO();
				
				inspectionVO.setMem_no(mem_no);
				inspectionVO.setIns_hospital(ins_hospital);
				inspectionVO.setIns_outoatuent(ins_outoatuent);
				inspectionVO.setIns_resetvation_no(ins_resetvation_no);
				inspectionVO.setIns_date(ins_date);
				
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date sysdate = new Date();
				String dateString = sdf.format(sysdate);
				
				//Step2  建立積分記錄VO
				
				
				String pr_type = "每日";
				String pr_content = "產檢日期紀錄";
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
					req.setAttribute("inspectionVO", inspectionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/ins_add.jsp");
					failureView.forward(req, res);
					System.out.println("add");
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				InspectionService InsSvc = new InspectionService();
				inspectionVO = InsSvc.addINS(mem_no, ins_hospital, ins_outoatuent,ins_resetvation_no,ins_date,pointRecordVO,memberVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/ins_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/ins_add.jsp");
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
				String ins_no = new String(req.getParameter("ins_no"));
				System.out.println("1111111111111");
				
				/***************************2.開始刪除資料***************************************/
				InspectionService DDSvc = new InspectionService();
				DDSvc.deleteIns_no(ins_no);
				
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
		
		

