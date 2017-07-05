package com.baby_data.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.baby_data.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024
		* 1024)
public class BabyDataServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_bd_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("bd_no");
				System.out.print(str);

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入寶寶編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/baby_data/select_bd_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String bd_no = null;
				try {
					bd_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/baby_data/select_bd_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				BabyDataService BDSvc = new BabyDataService();
				BabyDataVO babyDataVO = BDSvc.getOneBD(bd_no);
				if (babyDataVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/baby_data/select_bd_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("babyDataVO", babyDataVO); // 資料庫取出的empVO物件,存入req
				String url = "/baby_data/listOneBD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/baby_data/select_bd_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或
													// /dept/listEmps_ByDeptno.jsp
													// 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String bd_no = new String(req.getParameter("bd_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				BabyDataService BDSvc = new BabyDataService();
				BabyDataVO babyDataVO = BDSvc.getOneBD(bd_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("babyDataVO", babyDataVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/bd_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/bd_select.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String bd_no = req.getParameter("bd_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				Integer bd_order = new Integer(req.getParameter("bd_order").trim());
				String bd_name = req.getParameter("bd_name").trim();
				String bd_sex = req.getParameter("bd_sex").trim();

				java.sql.Date bd_pre = null;
				try {
					bd_pre = java.sql.Date.valueOf(req.getParameter("bd_pre").trim());
				} catch (IllegalArgumentException e) {
					bd_pre = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date bd_birthday = null;
				try {
					bd_birthday = java.sql.Date.valueOf(req.getParameter("bd_birthday").trim());
				} catch (IllegalArgumentException e) {
					bd_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}


				
				byte[] bd_pictures = null;
				if (getFileNameFromPart(req.getPart("bd_pictures")) != null) {
					InputStream in = req.getPart("bd_pictures").getInputStream();
					bd_pictures = new byte[in.available()]; // buff
					in.read(bd_pictures);
					in.close();
				} else {
					BabyDataService BDSvc = new BabyDataService();
					BabyDataVO babyDataVO = BDSvc.getOneBD(bd_no);
					bd_pictures = babyDataVO.getBd_pictures();
				}

				BabyDataVO babyDataVO = new BabyDataVO();

				// 修改
				babyDataVO.setBd_no(bd_no);
				babyDataVO.setMem_no(mem_no);
				babyDataVO.setBd_order(bd_order);
				babyDataVO.setBd_name(bd_name);
				babyDataVO.setBd_sex(bd_sex);
				babyDataVO.setBd_pre(bd_pre);
				babyDataVO.setBd_birthday(bd_birthday);
				babyDataVO.setBd_pictures(bd_pictures);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("babyDataVO", babyDataVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/member/bd_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				BabyDataService BDSvc = new BabyDataService();
				babyDataVO = BDSvc.updateBD(bd_no, mem_no, bd_order, bd_name, bd_sex, bd_pre, bd_birthday, bd_pictures);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("babyDataVO", babyDataVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/member/bd_select.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/bd_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("bd進入1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				System.out.println("進入1");
				String mem_no = req.getParameter("mem_no").trim();
				System.out.println("進入2");
				Integer bd_order = new Integer(req.getParameter("bd_order").trim());
				System.out.println("進入3");
				String bd_name = req.getParameter("bd_name").trim();
				System.out.println("進入4");
				String bd_sex = req.getParameter("bd_sex").trim();
				System.out.println("進入5");

				java.sql.Date bd_pre = null;
				try {
					bd_pre = java.sql.Date.valueOf(req.getParameter("bd_pre").trim());
				} catch (IllegalArgumentException e) {
					bd_pre = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期2222!");
				}
				System.out.println("進入6");
				java.sql.Date bd_birthday = null;
				try {
					bd_birthday = java.sql.Date.valueOf(req.getParameter("bd_birthday").trim());
				} catch (IllegalArgumentException e) {
					bd_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期1111!");
				}
				System.out.println("進入7");
				
				
				InputStream in = req.getPart("bd_pictures").getInputStream();
				System.out.println("進入77");
				byte[] bd_pictures = new byte[in.available()]; // buff
				System.out.println("進入777");
				in.read(bd_pictures);
				in.close();
				
				System.out.println("進入8134");
				BabyDataVO babyDataVO = new BabyDataVO();

				babyDataVO.setMem_no(mem_no);
				babyDataVO.setBd_order(bd_order);
				babyDataVO.setBd_name(bd_name);
				babyDataVO.setBd_sex(bd_sex);
				babyDataVO.setBd_pre(bd_pre);
				babyDataVO.setBd_birthday(bd_birthday);
				babyDataVO.setBd_pictures(bd_pictures);
				System.out.println("進入91234");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("babyDataVO", babyDataVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/member/bd_add.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println("進入10");	
				/*************************** 2.開始新增資料 ***************************************/
				BabyDataService BDSvc = new BabyDataService();
				
				babyDataVO = BDSvc.addBD(mem_no, bd_order, bd_name, bd_sex, bd_pre, bd_birthday, bd_pictures);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				System.out.println("213");
				String url = "/member/bd_select.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/bd_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bd_no = new String(req.getParameter("bd_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				BabyDataService babyDataSvc = new BabyDataService();
				babyDataSvc.deleteBD(bd_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/baby_data/listAllBD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/baby_data/listAllBD.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");// 得到的Header就是上傳上去的相關資料名稱
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}