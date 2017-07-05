package com.firm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.postpartum_care.model.PostpartumCareService;
import com.postpartum_care.model.PostpartumCareVO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.firm.model.*;
import com.member.controller.MemberMail;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/Firm_PC_Idcheck")
public class Firm_PC_LoginIDcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq=(String)session.getAttribute("pageReq");


		if ("fir_PC_Login".equals(action)) {
		
		
			if (pageReq == null || pageReq == "") {
				pageReq = "/front_end/frontINDEX.jsp";
			}
			// String pageReqUrl=pageReq.substring(0, pageReq.length()-1);
			List<String> Login_EMsg = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("Login_EMsg", Login_EMsg);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String fir_PC_LoginId = req.getParameter("fir_PC_LoginId");
				String fir_PC_LoginPsw = req.getParameter("fir_PC_LoginPsw");
				if (fir_PC_LoginId == null || (fir_PC_LoginId.trim()).length() == 0) {
					Login_EMsg.add("請輸入會員編號");
				}

				if (fir_PC_LoginPsw == null || (fir_PC_LoginPsw.trim()).length() == 0) {
					Login_EMsg.add("請輸入會密碼");
				}
				// Send the use back to the form, if there were errors
				if (!Login_EMsg.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				FirmService firSvc = new FirmService();
		
				try {
					
					FirmVO id = firSvc.getOneID(fir_PC_LoginId);
					if (id.getFir_id() == "" || id.getFir_id() == "null") {
						Login_EMsg.add("無此帳號!");
					} else {
						final BASE64Decoder decoder = new BASE64Decoder();
						String dataPsw = new String(id.getFir_password());
						String Psw = new String(decoder.decodeBuffer(dataPsw), "UTF-8");
					
						if (Psw.equals(fir_PC_LoginPsw)) {
							session.setAttribute("userVO", id);
							session.setAttribute("userType", "Fir");
							String url = "/firm/PersonalFir.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
							return;

						} else {
							Login_EMsg.add("密碼錯誤");
						}
					}

				} catch (Exception e) {
					
					PostpartumCareService PCSvc = new PostpartumCareService();
					PostpartumCareVO postpartumCareVO = PCSvc.getOnePCById(fir_PC_LoginId);


						if (postpartumCareVO != null) {

							 String dataPsw=new String(postpartumCareVO.getPc_password());
							 final BASE64Decoder decoder = new BASE64Decoder();
							 String Psw=new String(decoder.decodeBuffer(dataPsw), "UTF-8");
						    
							if (Psw.equals(fir_PC_LoginPsw)) {

								String url = "/postpartum_care/PersonalPC.jsp";
								PostpartumCareVO userVO = postpartumCareVO;

								session.setAttribute("userVO", userVO);
								session.setAttribute("userType", "PC");	
								
								List<String> Login_SMsg = new LinkedList<String>();
								Login_SMsg.add("會員登入成功");
								req.setAttribute("Login_SMsg", Login_SMsg);
								RequestDispatcher successView = req.getRequestDispatcher(url);
								successView.forward(req, res);
								return;
							} else {
								Login_EMsg.add("密碼錯誤!");
								
							}

						} else {
					
							
							Login_EMsg.add("查無此帳號");

						}				
				}
				/***************************
				 * 3,準備轉交(Send the Success view)
				 ***********/
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				Login_EMsg.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
			
			
			
			
			
			
			
		}

		if ("fir_PC_Logout".equals(action)) {
		
			// String pageReqUrl=pageReq.substring(0, pageReq.length()-1);

			session.invalidate();
		    String url = req.getContextPath()+"/front_end/frontINDEX.jsp";
		    //RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
		    //successView.forward(req, res);
		    res.sendRedirect(url);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
		
		
		if ("forget".equals(action)) {
			

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("SMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String forgetid = req.getParameter("forgetid");
				String forgetemail = req.getParameter("forgetemail");
				if (forgetid == null || (forgetid.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				if (forgetemail == null || (forgetemail.trim()).length() == 0) {
					errorMsgs.add("請輸入email");
				}
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				FirmService firSvc = new FirmService();

				try {
					FirmVO firVO = firSvc.getOneID(forgetid);
					String fir_email=null;
				
					
					if (firVO.getFir_email().trim().equals(forgetemail)) {
						fir_email = firVO.getFir_email().trim();
//						亂數密碼產生
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
//						編碼
					
						final BASE64Encoder encoder = new BASE64Encoder();
						
						final byte[] textByte = randPsw.getBytes("UTF-8");
					
						final String encodedPsw = encoder.encode(textByte);
					
						String fir_no = firVO.getFir_no();

						firSvc.updatePsw(encodedPsw, fir_no);
					
//						更改密碼訊息
						
						List<String> SMsgs = new LinkedList<String>();
						SMsgs.add("親愛的  " + firVO.getFir_name() + "  ，新設定的密碼已寄信至您的信箱。");
						req.setAttribute("SMsgs", SMsgs);
						// 寄出重設密碼信件
						
						MemberMail membermail = new MemberMail();
						String mailTitle = "媽咪樂寶變更密碼通知";
						String mailMessage = firVO.getFir_name() + "親愛的用戶你好!!新密碼為： 【" + randPsw + "】";
						membermail.sendMail(fir_email, mailTitle, mailMessage);
						
						
						RequestDispatcher successView = req.getRequestDispatcher(pageReq);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);
						
						return;
					} else {

						errorMsgs.add("信箱錯誤");
					}

					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);

				} catch (Exception e) {
					errorMsgs.add("無此帳號!");
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");
				failureView.forward(req, res);
			}

		}
	}

}
