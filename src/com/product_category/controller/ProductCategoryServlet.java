package com.product_category.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product_category.model.ProductCategoryVO;
import com.product_category.model.*;


public class ProductCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {  //來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/				
				String str = req.getParameter("prc_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prc_no = null;
				try {
					prc_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductCategoryService PRCSvc = new ProductCategoryService();
				ProductCategoryVO prcVO = PRCSvc.getOneProductCategory(prc_no);
				if (prcVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_picture/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prcVO", prcVO); /// 資料庫取出的empVO物件,存入req
				String url = "/product_category/listOnePRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_picture/select_page.jsp");
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
				String prc_no = new String(req.getParameter("prc_no"));
				System.out.println(prc_no);
				
				/***************************2.開始查詢資料****************************************/
				ProductCategoryService PRCSvc = new ProductCategoryService();
				ProductCategoryVO prcVO = PRCSvc.getOneProductCategory(prc_no);
								
				req.setAttribute("prcVO", prcVO);         // 資料庫取出的empVO物件,存入req
				String url = "/product_category/update_prc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/listAllPRC.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { //來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                 String prc_no = (req.getParameter("prc_no").trim());
				 String prc_main = (req.getParameter("prc_main").trim());
				 String prc_sub = (req.getParameter("prc_sub").trim());

				 ProductCategoryVO prcVO = new ProductCategoryVO();
					prcVO.setPrc_no(prc_no);
					prcVO.setPrc_main(prc_main);
					prcVO.setPrc_sub(prc_sub);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prcVO", prcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/update_prc_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductCategoryService PRCSvc = new ProductCategoryService();
				prcVO = PRCSvc.updateProductCategory(prc_no,prc_main,prc_sub);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prcVO", prcVO); 
				String url = "/product_category/listOnePRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/update_prc_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String prc_main = (req.getParameter("prc_main").trim());
				String prc_sub = (req.getParameter("prc_sub").trim());
				
				ProductCategoryVO prcVO = new ProductCategoryVO();
				prcVO.setPrc_main(prc_main);
				prcVO.setPrc_sub(prc_sub);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prcVO", prcVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/addPRC.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductCategoryService PRCSvc = new ProductCategoryService();
				prcVO = PRCSvc.addProductCategory(prc_main,prc_sub);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/product_category/listAllPRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/addPRC.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String prc_no = new String(req.getParameter("prc_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductCategoryService PRCSvc = new ProductCategoryService();
				PRCSvc.deleteProductCategory(prc_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/product_category/listAllPRC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/listAllPRC.jsp");
				failureView.forward(req, res);
			}
		}
	}
}





