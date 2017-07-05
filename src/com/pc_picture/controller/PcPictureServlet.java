package com.pc_picture.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.pc_picture.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024 * 1024)
public class PcPictureServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		   

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPC.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pcp_no = new String(req.getParameter("pcp_no"));
				
				/***************************2.開始查詢資料****************************************/
				PcPictureService PCPSvc = new PcPictureService();
				PcPictureVO pcPictureVO = PCPSvc.getOnePCP(pcp_no);
												
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pcPictureVO", pcPictureVO);         // 資料庫取出的empVO物件,存入req
				String url = "/pc_picture/update_pcp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PC_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pc_picture/listAllPCP.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_PC_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				String pcp_no = req.getParameter("pcp_no").trim();

				PcPictureService PCPSvc = new PcPictureService();
				PcPictureVO pcPictureVOa = PCPSvc.getOnePCP(pcp_no);
				String pc_no = pcPictureVOa.getPc_no();
	
				String pcp_summary = req.getParameter("pcp_summary").trim();

				
				Part part = req.getPart("pcp_picture");
				byte[] pcp_picture =null;
				
					if (getFileNameFromPart(part) != null) {
					
						InputStream in = req.getPart("pcp_picture").getInputStream();
						pcp_picture = new byte[in.available()]; //buff
						in.read(pcp_picture);
						in.close();						
					} else {
						
						PcPictureVO pcPictureVO2 = PCPSvc.getOnePCP(pcp_no);
						
						pcp_picture = pcPictureVO2.getPcp_picture();

					}
				

				
				PcPictureVO pcPictureVO = new PcPictureVO();
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcPictureVO", pcPictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pc_picture/listPCPs_ByPc_no_personal.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				pcPictureVO = PCPSvc.updatePCP(pcp_no, pc_no, pcp_picture, pcp_summary);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pcPictureVO", pcPictureVO);

				String url =  "/postpartum_care/PC.do?action=listPCPs_ByPc_no&pc_no="+pc_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pc_picture/listPCPs_ByPc_no_personal.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPC.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String pc_no = req.getParameter("pc_no").trim();
				String pcp_summary = req.getParameter("pcp_summary").trim();
				InputStream in = req.getPart("pcp_picture").getInputStream();
				byte[] pcp_picture = new byte[in.available()]; //buff
				in.read(pcp_picture);
				in.close();					

				PcPictureVO pcPictureVO = new PcPictureVO();



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pcPictureVO", pcPictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pc_picture/listPCPs_ByPc_no_personal.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PcPictureService PCPSvc = new PcPictureService();
				pcPictureVO = PCPSvc.addPCP(pc_no, pcp_picture,	pcp_summary);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/postpartum_care/PC.do?action=listPCPs_ByPc_no&pc_no="+pc_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);			
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pc_picture/listPCPs_ByPc_no_personal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllPC.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pcp_no = new String(req.getParameter("pcp_no"));
				
				/***************************2.開始刪除資料***************************************/
				PcPictureService PCPSvc = new PcPictureService();
				PcPictureVO pcPictureVO = PCPSvc.getOnePCP(pcp_no);
				String pc_no = pcPictureVO.getPc_no();
				PCPSvc.deletePCP(pcp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/postpartum_care/PC.do?action=listPCPs_ByPc_no&pc_no="+pc_no;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);	
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pc_picture/listAllPCP.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");

		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}	
}
