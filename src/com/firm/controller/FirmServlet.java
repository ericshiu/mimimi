package com.firm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firm.model.FirmService;
import com.firm.model.FirmVO;
import com.member.controller.MemberMail;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("fir_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/firm/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String fir_no = null;
				try {
					fir_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廠商編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/firm/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FirmService firSvc = new FirmService();
				FirmVO firVO = firSvc.getOneFir(fir_no);
				if (firVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/firm/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("firVO", firVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/ManageFir.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String fir_no = req.getParameter("fir_no");

				/*************************** 2.開始查詢資料 ****************************************/
				FirmService firSvc = new FirmService();
				FirmVO firVO = firSvc.getOneFir(fir_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("firVO", firVO); // 資料庫取出的empVO物件,存入req
				String url = "/firm/update_firm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/PersonalFir");
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
				String fir_no = req.getParameter("fir_no").trim();
				String fir_authority = req.getParameter("fir_authority").trim();
				Integer fir_eva_good = new Integer(req.getParameter("fir_eva_good").trim());
				Integer fir_eva_normal = new Integer(req.getParameter("fir_eva_normal").trim());
				Integer fir_eva_bad = new Integer(req.getParameter("fir_eva_bad").trim());

				String fir_name = req.getParameter("fir_name").trim();
				String fir_id = req.getParameter("fir_id").trim();
				
				String fir_password1 = req.getParameter("fir_password").trim();
				
				String fir_passwordcheck = new String(req.getParameter("fir_passwordcheck")).trim();
				String fir_password =fir_password1;
				if (!fir_passwordcheck.equals("")){
					if (!fir_password1.equals(fir_passwordcheck)){
					errorMsgs.add("密碼不一致!");
					}else{
						final BASE64Encoder encoder = new BASE64Encoder();			   
					    final byte[] textByte = fir_password1.getBytes("UTF-8");
					    fir_password = encoder.encode(textByte);
					}
				}
				 
			     
				
			
				String fir_phone = null;
				try {
					fir_phone = req.getParameter("fir_phone").trim();
				} catch (NumberFormatException e) {
					fir_phone = "";
					errorMsgs.add("請輸入電話.");
				}
				
				
				String fir_address = new String(req.getParameter("fir_address").trim());
				String fir_email = new String(req.getParameter("fir_email").trim());
				String fir_account = new String(req.getParameter("fir_account").trim());
				String fir_type = new String(req.getParameter("fir_type").trim());
				
				
				
				if (fir_name == null || fir_name.trim().length() == 0 ) {
					errorMsgs.add("姓名請輸入1~10個字元");
				}
				if (fir_password == null || fir_password.trim().length() == 0 ) {
					errorMsgs.add("請輸入密碼");
				}
//				if (fir_passwordcheck == null || fir_passwordcheck.trim().length() == 0 ) {
//					errorMsgs.add("請輸密碼");
//				}
				if (fir_phone == null || fir_phone.trim().length() == 0 ) {
					errorMsgs.add("請輸入聯絡電話");
				}
				if (fir_address == null || fir_address.trim().length() == 0 ) {
					errorMsgs.add("請輸入地址");
				}
				if (fir_email == null || fir_email.trim().length() == 0 ) {
					errorMsgs.add("請輸入email");
				}
				if (fir_account == null || fir_account.trim().length() == 0 ) {
					errorMsgs.add("請輸入帳戶號碼");
				}
				
				
				
				FirmVO firVO = new FirmVO();
				firVO.setFir_no(fir_no);
				firVO.setFir_name(fir_name);
				firVO.setFir_id(fir_id);
				firVO.setFir_password(fir_password);
				firVO.setFir_phone(fir_phone);
				firVO.setFir_address(fir_address);
				firVO.setFir_email(fir_email);
				firVO.setFir_account(fir_account);
				firVO.setFir_type(fir_type);
				firVO.setFir_authority(fir_authority);
				firVO.setFir_eva_good(fir_eva_good);
				firVO.setFir_eva_normal(fir_eva_normal);
				firVO.setFir_eva_bad(fir_eva_bad);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("firVO", firVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/firm/PersonalFir.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				FirmService firSvc = new FirmService();
				firVO = firSvc.updateFir(fir_id, fir_password, fir_name, fir_type, fir_phone, fir_address, fir_email,
						fir_account, fir_authority, fir_eva_good, fir_eva_normal, fir_eva_bad, fir_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				session.setAttribute("userVO", firVO);
				req.setAttribute("firVO", firVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/firm/PersonalFir.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/PersonalFir.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String fir_id = req.getParameter("fir_id").trim();
				// String Input_fir_idCheck =
				// req.getParameter("Input_fir_idCheck").trim();
				String fir_name = null;
				try {
					fir_name = new String(req.getParameter("fir_name").trim());
				} catch (Exception e) {
					fir_name = "請填入名稱";
					errorMsgs.add("請填入名稱");
				}
				String fir_type = req.getParameter("fir_type").trim();
				
				String fir_phone = req.getParameter("fir_phone").trim();
			
				String fir_address = req.getParameter("fir_address").trim();
			
				String fir_email = req.getParameter("fir_email").trim();
				String fir_account = req.getParameter("fir_account").trim();
				String fir_authority = req.getParameter("fir_authority");
				Integer fir_eva_good = new Integer(req.getParameter("fir_eva_good"));
				Integer fir_eva_normal = new Integer(req.getParameter("fir_eva_normal"));
				Integer fir_eva_bad = new Integer(req.getParameter("fir_eva_bad"));
			
				// 帳號驗證

				// if (Input_fir_idCheck.contains("重複")){
				// errorMsgs.add("此帳號已經被使用，請變更帳號!!");
				// }
				//
				// -----------------密碼開始
				String fir_password = null;
				
				int randCount = (int) Math.ceil(Math.random() * (10 - 6 + 1) + 6 - 1);
				int randInt = 0;
				String randPsw = "";
			
				for (int i = 1; i <= randCount; i++) {
					randInt = (int) Math.ceil(Math.random() * (122 - 48 + 1) + 48 - 1);
					
					if ((randInt >= 48 && randInt <= 57) || (randInt >= 65 && randInt <= 90)
							|| (randInt >= 97 && randInt <= 122)) {
						String achar = new Character((char) randInt).toString();
						randPsw = randPsw + achar;

					} else {
						i--;
					}
				}

				// 編碼
				final BASE64Encoder encoder = new BASE64Encoder();
				final byte[] textByte = randPsw.getBytes("UTF-8");
				final String encodedPsw = encoder.encode(textByte);
				
				// 解碼
				final BASE64Decoder decoder = new BASE64Decoder();
				
//				String mailTitle = "媽咪樂寶加入會員密碼通知";
//				String mailMessage = fir_name + "廠商你好，很感謝您的加入，帳號已經為您啟用,這是您的登入密碼【 " + randPsw
//						+ "】，請點選此網址立即瀏覽媽咪樂寶！<a href=" + req.getContextPath() + "/front_end/LoginMem.jsp>";
//				membermail.sendMail(fir_email, mailTitle, mailMessage);

				fir_password = encodedPsw;
				// ---------------------密碼結束
				

				// id驗證
				if (!Pattern.matches("[a-zA-Z0-9]{6,20}", fir_id)) {
					errorMsgs.add("帳號請輸入6~20碼的大小寫英數字");
				}
				// 姓名驗證
				if (fir_name == null || fir_name.trim().length() == 0) {
					errorMsgs.add("姓名請輸入1~10個字元");
				}
				// 類別驗證
				if (fir_type == null || fir_type.trim().length() == 0) {
					errorMsgs.add("姓名請輸入1~10個字元");
				}
				// 電話驗證
				if (fir_phone == null || fir_phone.trim().length() == 0) {
					errorMsgs.add("請輸入連絡電話");
				}
				if (!Pattern.matches("^\\(?\\d{4}\\)?[\\s\\-]?\\d{3}\\-?\\d{3}$", fir_phone)) {
					errorMsgs.add("請輸入手機號碼0911-123456");
				}
				// email驗證
				if (fir_email == null || fir_email.trim().length() == 0) {
					errorMsgs.add("請輸入email");
				}
				if (!fir_email.contains("@")) {
					errorMsgs.add("email格式錯誤");
				}
				// 地址驗證
				if (fir_address == null || fir_address.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}
				String fir_address2 = new String(req.getParameter("fir_address2").trim());
				System.out.println(fir_id);
				if (!fir_address2.equals("null")) {

					String city = fir_address2.substring(3, 6);
					String area = fir_address2.substring(6, fir_address2.length());
					req.setAttribute("city", city);
					req.setAttribute("area", area);
				} else {
					errorMsgs.add("請輸入地址!");
				}
				// 帳戶號碼驗證
				if (fir_account == null || fir_account.trim().length() == 0) {
					errorMsgs.add("請輸入帳戶號碼");
				}

				

				FirmVO firVO = new FirmVO();
				firVO.setFir_id(fir_id);
				firVO.setFir_password(fir_password);
				firVO.setFir_name(fir_name);
				firVO.setFir_type(fir_type);
				firVO.setFir_phone(fir_phone);
				firVO.setFir_address(fir_address);
				firVO.setFir_email(fir_email);
				firVO.setFir_account(fir_account);
				firVO.setFir_authority(fir_authority);
				firVO.setFir_eva_good(fir_eva_good);
				firVO.setFir_eva_normal(fir_eva_normal);
				firVO.setFir_eva_bad(fir_eva_bad);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("firVO", firVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/RegisterFir.jsp");
					failureView.forward(req, res);
					return;
				}

				
				/*************************** 2.開始新增資料 ***************************************/
				FirmService firSvc = new FirmService();
				firVO = firSvc.addFir(fir_id, fir_password, fir_name, fir_type, fir_phone, fir_address, fir_email,
						fir_account, fir_authority, fir_eva_good, fir_eva_normal, fir_eva_bad);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
//				註冊成功訊息
				List<String> SMsgs = new LinkedList<String>();
				SMsgs.add("親愛的  "+fir_name+"  ，恭喜您註冊成功，請您至信箱收取email,取得密碼。");
				req.setAttribute("SMsgs", SMsgs);
//				寄出信件
				MemberMail membermail = new MemberMail();
				
				String mailTitle="媽咪樂寶加入會員密碼通知";
				String homeURL = "http://52.88.89.6:8081/AA107G3/front_end/frontINDEX.jsp";
				String mailMessage="<html><head><title></title></head><body>"+fir_name+"廠商你好，很感謝您的加入，帳號已經為您啟用,這是您的登入密碼【 "+randPsw+"】，請點選此網址<a href="+homeURL+">媽咪樂寶</a>網頁登入更新!!<br><br>媽咪樂寶管理部關心您!!</body></html>";

				membermail.sendMail(fir_email, mailTitle, mailMessage);
						
				String url = "/front_end/LoginFir.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/RegisterFir.jsp");
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
				String fir_no = req.getParameter("fir_no");

				/*************************** 2.開始刪除資料 ***************************************/
				FirmService firSvc = new FirmService();
				firSvc.deleteFir(fir_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/firm/listAllFirm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/listAllFirm.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
