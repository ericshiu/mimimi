package com.manager.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.manager.model.*;
import com.authority_list.model.*;
import com.forum_article.model.ForumArticleService;
import com.forum_article.model.ForumArticleVO;
import com.forum_report.model.ForumReportService;
import com.forum_report.model.ForumReportVO;
import com.member.controller.MemberMail;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.pc_report.model.*;
import com.point_record.model.PointRecordVO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import com.member.controller.MemberMail;

public class ManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("man_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				String man_no = null;
				try {
					man_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				ManagerService manSvc = new ManagerService();
				ManagerVO manVO = manSvc.getOneMan(man_no);
				if (manVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
		
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("manVO", manVO);

				String url = "/back_end/AuthorityMan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updatePCR".equals(action)) { // 來自update_emp_input.jsp的請求
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//pc_report的取值
				String pcr_no = req.getParameter("pcr_no").trim();
				String pcr_pass=req.getParameter("pcr_pass").trim();
				String mem_no = req.getParameter("mem_no").trim();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 	//自訂時間格式
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒數
				java.sql.Timestamp ts = new java.sql.Timestamp(long1);					//轉成timestamp格式
				String Stringts=ts.toString();											//轉成字串做毫秒變成0的調整
				String ts2=Stringts.substring(0, Stringts.length()-3)+"0";				//.547之類的數字變成0
				long ts3 = sdf.parse(ts2).getTime();									//再轉成秒數
				java.sql.Date pcr_date = new java.sql.Date(ts3);		
				
				String pcr_status=null;				
				
				//1代表通過,2未通過
				if(pcr_pass.equals("pass")){
					pcr_status="1";
				}else {
					pcr_status="2";
				}
	
				PcReportService PCRSvc=new PcReportService();   //利用文章號碼抓到文章的作者會員
				PcReportVO PCRVO=PCRSvc.getOnePCR(pcr_no);
				
				if(pcr_pass.equals("pass")){
					
					PCRVO.setPcr_no(pcr_no);
					PCRVO.setMem_no(mem_no);
					PCRVO.setPcr_status(pcr_status);

					PCRSvc.updatePCR(pcr_no,pcr_status,pcr_date);
					

//					****1.建立積分紀錄*****
					
					String pr_type="月子中心檢舉";
					String pr_content="被檢舉會員："+mem_no;
					String pr_no=PCRVO.getPc_no();
					
					Integer pr_point=-20;
					
	
					PointRecordVO pointRecordVO = new PointRecordVO();
					
					pointRecordVO.setMem_no(mem_no);				
					pointRecordVO.setPr_type(pr_type);				
					pointRecordVO.setPr_content(pr_content);				
					pointRecordVO.setPr_date(pcr_date);				
					pointRecordVO.setPr_point(pr_point);
					
//					***2.更新會員積分******
					MemberService memSvc = new MemberService();
					MemberVO memberVO_old = memSvc.getOneMem(mem_no);
					Integer old_point = new Integer(memberVO_old.getMem_point());
					Integer new_point = old_point + pr_point;
					
					//再算新的
					MemberVO memberVO = new MemberVO();
					memberVO.setMem_no(mem_no);
					memberVO.setMem_point(new_point);
					
					
					PCRSvc.updatePCRWithPoint(pcr_no,pcr_status,pcr_date,pointRecordVO,memberVO);
					//完成後，將新的memVO再次塞回session
					MemberVO newVO= memSvc.getOneMem(mem_no);
					session.setAttribute("userVO", newVO);
					
					

				}else {
					
					PCRVO.setPcr_no(pcr_no);
					PCRVO.setMem_no(mem_no);
					PCRVO.setPcr_status(pcr_status);

					PCRSvc.updatePCR(pcr_no,pcr_status,pcr_date);

				}

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("PCRVO", PCRVO); // 資料庫update成功後,正確的的FRVO物件,存入req
				
				//PASS要進行停權或扣款的引導
				
				List<String> SMsgs = new LinkedList<String>();
				List<String> EMsgs = new LinkedList<String>();
				if(pcr_pass.equals("pass")){		
				
					SMsgs.add("審核完成，是否要進行 "+ mem_no+ " 的停權?");
					req.setAttribute("SMsgs", SMsgs);
				}else {
					
					EMsgs.add("審核完成!");
					req.setAttribute("EMsgs", EMsgs);
				}
				
				String url = "/back_end/PC_Report.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/PC_Report.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String man_no = req.getParameter("man_no").trim();
				String man_id = req.getParameter("man_id").trim();
				String man_name = req.getParameter("man_name").trim();
				String man_password = req.getParameter("man_password").trim();
				String man_email = req.getParameter("man_email").trim();

				ManagerVO managerVO = new ManagerVO();
				managerVO.setMan_no(man_no);
				managerVO.setMan_id(man_id);
				managerVO.setMan_password(man_password);
				managerVO.setMan_name(man_name);
				managerVO.setMan_email(man_email);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_man_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 ****************************************/
				ManagerService manSvc = new ManagerService();
				managerVO = manSvc.updateMan(man_no, man_id, man_password, man_name, man_email);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("managerVO", managerVO);
				String url = "/manager/listOneMan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_man_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String man_id = req.getParameter("man_id").trim();
				String man_name = req.getParameter("man_name").trim();
				String man_email = req.getParameter("man_email").trim();

				// id驗證
				String idReg = "[(a-zA-Z0-9_)]{6,10}$";
				if (man_id == null || man_id.trim().length() == 0) {
					errorMsgs.add("請填寫ID");
				} else if (!man_id.trim().matches(idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("ID: 只能是英文字母、數字和_ , 且長度必需在6到10之間");
				}

				// 姓名驗證
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (man_name == null || man_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!man_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				// -----------------密碼開始
				String man_password = null;
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
				

				// String mailTitle = "媽咪樂寶加入會員密碼通知";
				// String mailMessage = fir_name +
				// "廠商你好，很感謝您的加入，帳號已經為您啟用,這是您的登入密碼【 " + randPsw
				// + "】，請點選此網址立即瀏覽媽咪樂寶！<a href=" + req.getContextPath() +
				// "/front_end/LoginMem.jsp>";
				// membermail.sendMail(fir_email, mailTitle, mailMessage);

				man_password = encodedPsw;
				// ---------------------密碼結束

				// email驗證

				if (man_email == null || man_email.trim().length() == 0) {
					errorMsgs.add("email請勿空白");
				} else if (!man_email.contains("@")) {
					errorMsgs.add("email格式錯誤");
				}

				ManagerVO managerVO = new ManagerVO();
				managerVO.setMan_id(man_id);
				managerVO.setMan_name(man_name);
				managerVO.setMan_password(man_password);
				managerVO.setMan_email(man_email);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ManagerService manSvc = new ManagerService();
				managerVO = manSvc.addMan(man_id, man_name, man_password, man_email);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				// 註冊成功訊息
				List<String> SMsgs = new LinkedList<String>();
				SMsgs.add("親愛的  " + man_name + "  ，歡迎您加入我們的團隊，請您至信箱收取email,取得密碼。");
				req.setAttribute("SMsgs", SMsgs);
				// 寄出信件
				MemberMail membermail = new MemberMail();
				String mailTitle = "媽咪樂寶後端管理員密碼通知";
				String mailMessage = man_name + "新夥伴你好，歡迎您的加入，帳號已經為您啟用,這是您的登入密碼【 " + randPsw + "】";
				membermail.sendMail(man_email, mailTitle, mailMessage);

				String url = "/back_end/ManagerLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String man_no = req.getParameter("man_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ManagerService manSvc = new ManagerService();
				manSvc.deleteMan(man_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/manager/listAllMan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllMan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("ManLogin".equals(action)) {
			String pageReq = (String) session.getAttribute("pageReq");
			if (pageReq == null || pageReq == "") {
				pageReq = "/back_end/ManagerLogin.jsp";
			}
			// String pageReqUrl=pageReq.substring(0, pageReq.length()-1);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String manLoginId = req.getParameter("manLoginId");
				String manLoginPsw = req.getParameter("manLoginPsw");
				if (manLoginId == null || (manLoginId.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}

				if (manLoginPsw == null || (manLoginPsw.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String url = pageReq;
				ManagerService manSvc = new ManagerService();

				try {
					ManagerVO id = manSvc.getOneManId(manLoginId);

					
					if (id.getMan_id() == "" || id.getMan_id() == "null") {
						errorMsgs.add("無此帳號!");

					} else {
						final BASE64Decoder decoder = new BASE64Decoder();
						String dataPsw = new String(id.getMan_password());
						String Psw = new String(decoder.decodeBuffer(dataPsw), "UTF-8");
					
						
						if (Psw.equals(manLoginPsw)) {

							session.setAttribute("manuserVO", id);
							errorMsgs.add("登入成功");
							RequestDispatcher successView = req.getRequestDispatcher("/back_end/ManagerINDEX.jsp");
							successView.forward(req, res);
							return;

						} else {
							errorMsgs.add("密碼錯誤");
						}
					}

				} catch (Exception e) {
					errorMsgs.add("無此帳號!");
					RequestDispatcher failureView = req.getRequestDispatcher("url");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 3,準備轉交(Send the Success view)
				 ***********/


				RequestDispatcher successView = req.getRequestDispatcher(req.getContextPath()+"/ManagerINDEX.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}

		if ("ManLogout".equals(action)) {
		
			String pageReq = (String) session.getAttribute("pageReq");
			// String pageReqUrl=pageReq.substring(0, pageReq.length()-1);

			session.removeAttribute("manuserVO");
			String url = pageReq;
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}

		if ("forget".equals(action)) {
		
			String pageReq = (String) session.getAttribute("pageReq");
			if (pageReq == null || pageReq == "") {
				pageReq = "/back_end/ManagerLogin.jsp";
			}
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

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

				ManagerService manSvc = new ManagerService();

				try {
					ManagerVO managerVO = manSvc.getOneManId(forgetid);
					String man_email=null;
				
					
					if (managerVO.getMan_email().trim().equals(forgetemail)) {
						man_email = managerVO.getMan_email().trim();
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
					
						String man_no = managerVO.getMan_no();
						String man_id = managerVO.getMan_id();
						String man_name = managerVO.getMan_name();
						
						
					
						
						managerVO = manSvc.updateMan(man_no, man_id, man_name, encodedPsw, man_email);
					
//						更改密碼訊息
						
						List<String> SMsgs = new LinkedList<String>();
						SMsgs.add("親愛的  " + managerVO.getMan_name() + "  ，新設定的密碼已寄信至您的信箱。");
						req.setAttribute("SMsgs", SMsgs);
						// 寄出重設密碼信件
						
						MemberMail membermail = new MemberMail();
						String mailTitle = "媽咪樂寶變更密碼通知";
						String mailMessage = managerVO.getMan_name() + "同仁您好!!新密碼為： 【" + randPsw + "】";
						membermail.sendMail(man_email, mailTitle, mailMessage);
						
						
						RequestDispatcher successView = req.getRequestDispatcher(pageReq);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);
						
						return;
					} else {

						errorMsgs.add("信箱錯誤");
					}

					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManagerLogin.jsp");
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
