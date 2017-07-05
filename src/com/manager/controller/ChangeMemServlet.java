package com.manager.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.member.model.*;
import com.manager.model.*;
import com.firm.model.*;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024
* 1024)
public class ChangeMemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("memupdate".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("有近來");
			String pageReq=(String)session.getAttribute("pageReq");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_no = new String(req.getParameter("mem_no").trim());
				String mem_id = new String(req.getParameter("mem_id").trim());
				String mem_password = new String(req.getParameter("mem_password")).trim();
				String mem_name = new String(req.getParameter("mem_name").trim());
				String mem_nike = new String(req.getParameter("mem_nike").trim());
				String mem_sex = new String(req.getParameter("mem_sex").trim());
				String mem_phone = new String(req.getParameter("mem_phone").trim());
				String mem_email = new String(req.getParameter("mem_email").trim());
				String mem_address = new String(req.getParameter("mem_address").trim());
				Integer mem_point = new Integer(req.getParameter("mem_point").trim());
				Integer mem_actual_point = new Integer(req.getParameter("mem_actual_point").trim());

				byte[] mem_picture = null;
				if (getFileNameFromPart(req.getPart("mem_picture")) != null ) {
					InputStream in = req.getPart("mem_picture").getInputStream();
					mem_picture = new byte[in.available()]; // buff
					in.read(mem_picture);
					in.close();
				} else {
					MemberService memSvc = new MemberService();
					MemberVO memberVO = memSvc.getOneMem(mem_no);
					mem_picture = memberVO.getMem_picture();
				}
				String mem_authority = new String(req.getParameter("mem_authority").trim());
				String change_aut = new String(req.getParameter("change_aut").trim());
				String mem_facebook = new String(req.getParameter("mem_facebook").trim());
				String mem_google = new String(req.getParameter("mem_google").trim());

				if (change_aut.equals("change")){
					if(mem_authority.equals("y")){
						mem_authority="n";
						errorMsgs.add(mem_name+"已停止權限。");
					}else{
						mem_authority="y";
						errorMsgs.add(mem_name+"已恢復權限。");
					}
				}
				
				java.sql.Date mem_birthday = null;
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				System.out.println("2");
				
				MemberVO memberVO = new MemberVO();

				memberVO.setMem_no(mem_no);
				memberVO.setMem_id(mem_id);
				memberVO.setMem_password(mem_password);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nike(mem_nike);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_address(mem_address);
				memberVO.setMem_point(mem_point);
				memberVO.setMem_actual_point(mem_actual_point);
				memberVO.setMem_picture(mem_picture);
				memberVO.setMem_authority(mem_authority);
				memberVO.setMem_facebook(mem_facebook);
				memberVO.setMem_google(mem_google);
				System.out.println("3");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("4");
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				
	
				/*************************** 2.開始修改資料 *****************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMem(mem_no, mem_id, mem_password, mem_name, mem_nike, mem_sex, mem_birthday,
						mem_phone, mem_email, mem_address, mem_point, mem_actual_point, mem_picture, mem_authority,
						mem_facebook, mem_google);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				System.out.println("2");
				session.setAttribute("userVO", memberVO);
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
System.out.println("最後");
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
	

	
	if ("firupdate".equals(action)) { // 來自update_emp_input.jsp的請求
		String pageReq=(String)session.getAttribute("pageReq");

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String fir_no = new String(req.getParameter("fir_no").trim());
			String fir_id = new String(req.getParameter("fir_id").trim());
			String fir_password = new String(req.getParameter("fir_password")).trim();
			String fir_name = new String(req.getParameter("fir_name").trim());
			String fir_type = new String(req.getParameter("fir_type").trim());
			String fir_phone = new String(req.getParameter("fir_phone").trim());
			String fir_email = new String(req.getParameter("fir_email").trim());
			String fir_address = new String(req.getParameter("fir_address").trim());
			String fir_account = new String(req.getParameter("fir_account").trim());
			Integer fir_eva_good = new Integer(req.getParameter("fir_eva_good").trim());
			Integer fir_eva_normal = new Integer(req.getParameter("fir_eva_normal").trim());
			Integer fir_eva_bad = new Integer(req.getParameter("fir_eva_bad").trim());
			String fir_authority = new String(req.getParameter("fir_authority").trim());
			String change_aut = new String(req.getParameter("change_aut").trim());
			
			if (change_aut.equals("change")){
				if(fir_authority.equals("y")){
					fir_authority="n";
					errorMsgs.add(fir_name+"已停止權限。");
				}else{
					fir_authority="y";
					errorMsgs.add(fir_name+"已恢復權限。");
				}
			}
			
		
			
			FirmVO firVO = new FirmVO();

			firVO.setFir_no(fir_no);
			firVO.setFir_id(fir_id);
			firVO.setFir_password(fir_password);
			firVO.setFir_name(fir_name);
			firVO.setFir_type(fir_type);
			firVO.setFir_phone(fir_phone);
			firVO.setFir_email(fir_email);
			firVO.setFir_address(fir_address);
			firVO.setFir_eva_good(fir_eva_good);
			firVO.setFir_eva_normal(fir_eva_normal);
			firVO.setFir_eva_bad(fir_eva_bad);
			firVO.setFir_authority(fir_authority);
			firVO.setFir_account(fir_account);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				
				req.setAttribute("firVO", firVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
				return; // 程式中斷
			}

			

			/*************************** 2.開始修改資料 *****************************************/
			FirmService firSvc = new FirmService();
			firVO = firSvc.updateFir(  fir_id,  fir_password,  fir_name,  fir_type,
					  fir_phone,  fir_address,  fir_email,  fir_account,
					  fir_authority, fir_eva_good, fir_eva_normal, fir_eva_bad,
					 fir_no);

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			errorMsgs.add("更好");
			session.setAttribute("firuserVO", firVO);
			req.setAttribute("firVO", firVO); // 資料庫update成功後,正確的的empVO物件,存入req
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
	
	
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");// 得到的Header就是上傳上去的相關資料名稱
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}