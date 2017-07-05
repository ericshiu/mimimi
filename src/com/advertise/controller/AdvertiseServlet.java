package com.advertise.controller;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.advertise.model.*;

import com.firm.model.*;
import com.member.controller.MemberMail;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024
		* 1024)
public class AdvertiseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq = (String) session.getAttribute("pageReq");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String adv_no = req.getParameter("adv_no");
				if (adv_no == null || (adv_no.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdvertiseService advSvc = new AdvertiseService();
				AdvertiseVO advVO = advSvc.getOneAdv(adv_no);
				if (advVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("advVO", advVO); // 資料庫取出的advVO物件,存入req
				String url = "/advertise/listOneAdv.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/advertise/select_page.jsp");
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
				String adv_no = new String(req.getParameter("adv_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdvertiseService advSvc = new AdvertiseService();
				AdvertiseVO advVO = advSvc.getOneAdv(adv_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				req.setAttribute("advVO", advVO); // 資料庫取出的advVO物件,存入req
				String url = "/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String adv_pass=req.getParameter("adv_pass").trim();
			

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				
				String adv_no = req.getParameter("adv_no").trim();
				String fir_no = req.getParameter("fir_no").trim();
				String adv_title = req.getParameter("adv_title").trim();
				java.sql.Timestamp adv_propose_date = null;
				try {
					adv_propose_date = java.sql.Timestamp.valueOf(req.getParameter("adv_propose_date").trim());
				} catch (IllegalArgumentException e) {
					adv_propose_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				byte[] adv_picture = null;

					AdvertiseService advSvc2 = new AdvertiseService();
					AdvertiseVO advberVO = advSvc2.getOneAdv(adv_no);
					adv_picture = advberVO.getAdv_picture();
//				}

//			---------以上為取到的值----------------------
				String adv_review =req.getParameter("adv_review");
				String adv_status = req.getParameter("adv_status");
				
				if (adv_pass.equals("1")){
					adv_review="1";
					adv_status="1";
				}else if(adv_pass.equals("2")){
					adv_review="2";
					adv_status="2";
				}else if(adv_pass.equals("3")){
					adv_status="2";
				}
				
				//開始時間為現在
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0"); 	//自訂時間格式
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒數
				java.sql.Timestamp ts = new java.sql.Timestamp(long1);					//轉成timestamp格式
				String Stringts=ts.toString();											//轉成字串做毫秒變成0的調整
				String ts2=Stringts.substring(0, Stringts.length()-3)+"0";				//.547之類的數字變成0
				long ts3 = sdf.parse(ts2).getTime();									//再轉成秒數
				java.sql.Timestamp ts4 = new java.sql.Timestamp(ts3);					//轉成timestamp格式
				
				java.sql.Timestamp adv_start = ts4;										//完成
				
				
				//結束時間，設定廣告時間為15天半個月
				long endtime = du.getTime() + 15 * 24 * 60 * 60 * 1000L;
				
				java.sql.Timestamp end = new java.sql.Timestamp(endtime);	
					
				
				java.sql.Timestamp adv_end = end;

				java.sql.Timestamp adv_review_date = ts4;
				

				AdvertiseVO advVO = new AdvertiseVO();
				advVO.setAdv_no(adv_no);
				advVO.setFir_no(fir_no);
				advVO.setAdv_start(adv_propose_date);
				advVO.setAdv_start(adv_start);
				advVO.setAdv_end(adv_end);
				advVO.setAdv_review(adv_review);
				advVO.setAdv_review_date(adv_review_date);
				advVO.setAdv_status(adv_status);
				advVO.setAdv_picture(adv_picture);
				advVO.setAdv_title(adv_title);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advVO", advVO); // 含有輸入格式錯誤的advVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdvertiseService advSvc = new AdvertiseService();
				advVO = advSvc.updateAdv(adv_no, fir_no, adv_propose_date, adv_start, adv_end, adv_review,
						adv_review_date, adv_status, adv_picture, adv_title);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				if (adv_pass.equals("1")){
					FirmService firSvc=new FirmService();
					MemberMail membermail = new MemberMail();
					String mailTitle="媽咪樂寶廣告申請成功通知";
					String mailMessage=firSvc.getOneFir(fir_no).getFir_name()+"廠商你好，廣告申請已經通過！已經立即為您上架，上架時間為15天!如果還有廣告需求，麻煩您再次使用本系統申請廣告，感謝您的支持!";
				    membermail.sendMail(firSvc.getOneFir(fir_no).getFir_email(),mailTitle,mailMessage);
				    errorMsgs.add("已寄發email告知會員!");
				}else if(adv_pass.equals("2")){
					errorMsgs.add("審核完成!");
				}else{
					errorMsgs.add("已下架!");
				}
				req.setAttribute("advVO", advVO); // 資料庫update成功後,正確的的advVO物件,存入req
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

		if ("insertadv".equals(action)) { // 來自addEmp.jsp的請求

			if (pageReq == null || pageReq == "") {
				pageReq = "/advertise/AdvertiseApply.jsp";
			}

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String fir_no = req.getParameter("fir_no").trim();
				String fir_name = req.getParameter("fir_name").trim();
				String adv_title = req.getParameter("adv_title").trim();

				if (adv_title.trim().length() == 0 || adv_title == null) {
					errorMsgs.add("請輸入廣告標題");
				}

				InputStream in = req.getPart("adv_picture").getInputStream();
				byte[] adv_picture = new byte[in.available()]; // buff
				in.read(adv_picture);
				in.close();

				String adv_review = "0";
				String adv_status = "0";
				java.sql.Timestamp adv_start = null;
				java.sql.Timestamp adv_end = null;
				java.sql.Timestamp adv_review_date = null;

				AdvertiseVO advVO = new AdvertiseVO();

				advVO.setFir_no(fir_no);
				advVO.setAdv_start(adv_start);
				advVO.setAdv_end(adv_end);
				advVO.setAdv_review(adv_review);
				advVO.setAdv_review_date(adv_review_date);
				advVO.setAdv_status(adv_status);
				advVO.setAdv_picture(adv_picture);
				advVO.setAdv_title(adv_title);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("advVO", advVO); // 含有輸入格式錯誤的advVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdvertiseService advSvc = new AdvertiseService();
				advVO = advSvc.addAdv(fir_no, adv_start, adv_end, adv_review, adv_review_date, adv_status, adv_picture,
						adv_title);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				// 申請成功訊息
				List<String> SMsgs = new LinkedList<String>();
				SMsgs.add("親愛的  " + fir_name + "  ，廣告已經送出申請，待審核後將以email的方式通知您。");
				req.setAttribute("SMsgs", SMsgs);
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
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
				String adv_no = req.getParameter("adv_no");

				/*************************** 2.開始刪除資料 ***************************************/
				AdvertiseService advSvc = new AdvertiseService();
				advSvc.deleteAdv(adv_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}

		}
		if ("compositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();

				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}

				/*************************** 2.開始複合查詢 ***************************************/
				AdvertiseService advSvc = new AdvertiseService();
				List<AdvertiseVO> list = advSvc.getAllAdv(map);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/

				req.setAttribute("compositeQuery", list); // 資料庫取出的list物件,存入request
				req.setAttribute("query", "query");
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/AdvertiseReview.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/AdvertiseReview.jsp");
				failureView.forward(req, res);
			}
		}

		if ("advDetail".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adv_no = req.getParameter("adv_no");

				/*************************** 2.開始刪除資料 ***************************************/
				AdvertiseService advSvc = new AdvertiseService();
				AdvertiseVO advVO=(AdvertiseVO)advSvc.getOneAdv(adv_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/back_end/AdvertiseDetail.jsp";
				req.setAttribute("advVO", advVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/AdvertiseDetail.jsp");
				failureView.forward(req, res);

			}

		}

	}


}
