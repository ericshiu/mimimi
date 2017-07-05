package com.product_speci.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product_speci.model.*;


public class ProductSpeciServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			
				String psp_no = req.getParameter("psp_no");
							
				ProductSpeciService PSPSvc = new ProductSpeciService();
				ProductSpeciVO pspVO = PSPSvc.getOneProductSpeci(psp_no);
				if (pspVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_speci/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("pspVO", pspVO);
				String url = "/product_speci/listOnePSP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

		} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_speci/select_page.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String pro_no = new String(req.getParameter("psp_no"));

			ProductSpeciService PSPSvc = new ProductSpeciService();
			ProductSpeciVO pspVO = PSPSvc.getOneProductSpeci(pro_no);
								
			req.setAttribute("pspVO", pspVO);
			String url = "/product_speci/update_psp_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if ("update".equals(action)) {	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String psp_no = req.getParameter("psp_no").trim();		
				String pro_no = (req.getParameter("pro_no").trim());
				String psp_name = (req.getParameter("psp_name").trim());
				String psp_list = (req.getParameter("psp_list").trim());

				ProductSpeciVO pspVO = new ProductSpeciVO();
				pspVO.setPsp_no(psp_no);
				pspVO.setPro_no(pro_no);
				pspVO.setPsp_name(psp_name);
				pspVO.setPsp_list(psp_list);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pspVO", pspVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_speci/update_psp_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				ProductSpeciService PSPSvc = new ProductSpeciService();
				pspVO = PSPSvc.updateProductSpeci(psp_no,pro_no,psp_name,psp_list);
				
				req.setAttribute("pspVO", pspVO); 
				String url = "/product_speci/listOnePSP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_speci/update_psp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {		
				String pro_no = (req.getParameter("pro_no").trim());
				String psp_name = (req.getParameter("psp_name").trim());
				String psp_list = (req.getParameter("psp_list").trim());

				ProductSpeciVO pspVO = new ProductSpeciVO();
				pspVO.setPro_no(pro_no);
				pspVO.setPsp_name(psp_name);
				pspVO.setPsp_list(psp_list);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pspVO", pspVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_speci/addPSP.jsp");
					failureView.forward(req, res);
					return;
				}
				ProductSpeciService PSPSvc = new ProductSpeciService();
				pspVO = PSPSvc.addProductSpeci(pro_no,psp_name,psp_list);
				
				String url = "/product_speci/listAllPSP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_speci/addPSP.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				String psp_no = new String(req.getParameter("psp_no"));
				
				ProductSpeciService PSPSvc = new ProductSpeciService();
				PSPSvc.deleteProductSpeci(psp_no);
				
				String url = "/product_speci/listAllPSP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_speci/listAllPSP.jsp");
				failureView.forward(req, res);
			}
		}
	}
}






