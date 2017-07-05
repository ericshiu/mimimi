package com.member.controller;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;
import com.member.model.*;
import com.pc_application.model.*;
import com.postpartum_care.model.PostpartumCareService;
import com.postpartum_care.model.PostpartumCareVO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024
		* 1024)
public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq=(String)session.getAttribute("pageReq");
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem(mem_no);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManageMem.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/ManageMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManageMem.jsp");
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
				String mem_no = new String(req.getParameter("mem_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem(mem_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMem.jsp");
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
				String mem_no = new String(req.getParameter("mem_no").trim());
				String mem_id = new String(req.getParameter("mem_id").trim());
				String mem_password = new String(req.getParameter("mem_password")).trim();
				String mem_passwordcheck = new String(req.getParameter("mem_passwordcheck")).trim();
				String mem_name = new String(req.getParameter("mem_name").trim());
				String mem_nike = new String(req.getParameter("mem_nike").trim());
				String mem_sex = new String(req.getParameter("mem_sex").trim());
				String mem_phone = new String(req.getParameter("mem_phone").trim());
				String mem_email = new String(req.getParameter("mem_email").trim());
				String mem_address = new String(req.getParameter("mem_address").trim());
				Integer mem_point = new Integer(req.getParameter("mem_point").trim());
				Integer mem_actual_point = new Integer(req.getParameter("mem_actual_point").trim());
	
				String mem_password1 = req.getParameter("mem_password").trim();
				
				mem_password =mem_password1;
					if (!mem_passwordcheck.equals("")){
						if (!mem_password1.equals(mem_passwordcheck)){
						errorMsgs.add("密碼不一致!");
						}else{
							final BASE64Encoder encoder = new BASE64Encoder();			   
						    final byte[] textByte = mem_password1.getBytes("UTF-8");
						    mem_password = encoder.encode(textByte);
						}
					}

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
				String mem_facebook = new String(req.getParameter("mem_facebook").trim());
				String mem_google = new String(req.getParameter("mem_google").trim());

				java.sql.Date mem_birthday = null;
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
			
				
				if(mem_name==null || mem_name.trim().length()==0){
					errorMsgs.add("請輸入姓名");
				}
				if(mem_password==null || mem_password.trim().length()==0){
					errorMsgs.add("請輸入密碼");
				}
				if(mem_nike==null || mem_nike.trim().length()==0){
					errorMsgs.add("請輸入暱稱");
				}
				if(mem_phone==null || mem_phone.trim().length()==0){
					errorMsgs.add("請輸入電話");
				}
				if(mem_address==null || mem_address.trim().length()==0){
					errorMsgs.add("請輸入地址");
				}
				if(mem_email==null || mem_email.trim().length()==0){
					errorMsgs.add("請輸入email");
				}
				if(mem_facebook==null || mem_facebook.trim().length()==0){
					errorMsgs.add("請輸入facebook");
				}
				if(mem_google==null || mem_google.trim().length()==0){
					errorMsgs.add("請輸入google");
				}
				
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
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
			
				session.setAttribute("userVO", memberVO);
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
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

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			// String pic=new String(req.getParameter("mem_picture").trim());
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String mem_no = req.getParameter("mem_no").trim();
				String mem_id = req.getParameter("mem_id").trim();
				String Input_mem_idCheck = req.getParameter("Input_mem_idCheck").trim();
				String mem_password = req.getParameter("mem_password").trim();
				String mem_name = req.getParameter("mem_name").trim();
				String mem_nike = req.getParameter("mem_nike").trim();
				String mem_sex = req.getParameter("mem_sex").trim();
				String mem_phone = req.getParameter("mem_phone").trim();
				String mem_email = req.getParameter("mem_email").trim();
				String mem_address = req.getParameter("mem_address").trim();
				Integer mem_point = new Integer(req.getParameter("mem_point").trim());
				Integer mem_actual_point = new Integer(req.getParameter("mem_actual_point").trim());

				InputStream in = req.getPart("mem_picture").getInputStream();
				byte[] mem_picture = new byte[in.available()]; // buff
				in.read(mem_picture);
				in.close();

				String mem_authority = req.getParameter("mem_authority").trim();
				String mem_facebook = req.getParameter("mem_facebook").trim();
				String mem_google = req.getParameter("mem_google").trim();
				java.sql.Date mem_birthday = null;
				//id驗證
				if (Input_mem_idCheck.contains("重複")){
					errorMsgs.add("此帳號已經被使用，請變更帳號!!");
				}
				
				// mem_birthday
				try {
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				} catch (IllegalArgumentException e) {
					mem_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				req.setAttribute("mem_birthday", mem_birthday);

				// 生日的驗證

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance(); // 現在時間
				long nowDate = calendar.getTime().getTime(); // 取得現在時間秒數
				String birthday = mem_birthday.toString(); // 選擇的時間
				long birthdaylDate = 0;
				try {
					birthdaylDate = sdf.parse(birthday).getTime();

				} catch (Exception e) {
					e.getMessage();
				}
				long checkdate = nowDate - birthdaylDate;
				if (checkdate < 0) {
					errorMsgs.add("日期不可以晚於今天!!");
				}
				// --姓名的驗證--

				// mem_id,
				if (!Pattern.matches("[a-zA-Z0-9]{6,20}", mem_id)) {
					errorMsgs.add("帳號請輸入6~20碼的大小寫英數字");
				}
				// mem_password
//------------------------密碼
				
				
				
				 int randCount=(int)Math.ceil(Math.random()*(10-6+1)+6-1);
			      int randInt=0;
			      String randPsw="";
			      for (int i=1;i<=randCount;i++){
			    	  randInt=(int)Math.ceil(Math.random()*(122-48+1)+48-1);
			    	  if ((randInt>=48 && randInt<=57) || (randInt>=65 && randInt<=90) || (randInt >=97 && randInt<=122)){
			    		  String achar=new Character((char)randInt).toString();
			    		  randPsw=randPsw+achar;
			    		  
			    		 
			    	  }else{
			    		  i--;
			    	  }
			      }      
			      
			      //編碼
			      final BASE64Encoder encoder = new BASE64Encoder();			   
			      final byte[] textByte = randPsw.getBytes("UTF-8");
			      final String encodedPsw = encoder.encode(textByte);

			      //解碼
			      final BASE64Decoder decoder = new BASE64Decoder();

				
				mem_password=encodedPsw;
//				---------------------密碼結束

				// mem_name
				if (mem_name == null || mem_name.trim().length() == 0 || mem_name.trim().length() > 10) {
					errorMsgs.add("姓名請輸入1~10個字元");
				}
				// mem_nike

				if (mem_nike == null || mem_nike.trim().length() == 0 || mem_nike.trim().length() > 10) {
					errorMsgs.add("暱稱請輸入1~10個字元");
				}
				// mem_sex
				if (mem_sex == null || mem_sex.trim().length() == 0) {
					errorMsgs.add("性別請選擇");
				}
				// mem_birthday
				if (mem_sex == null || mem_sex.trim().length() == 0) {
					errorMsgs.add("請選擇生日");
				}
				// mem_phone
				if (!Pattern.matches("^\\(?\\d{4}\\)?[\\s\\-]?\\d{3}\\-?\\d{3}$", mem_phone)) {
					errorMsgs.add("請輸入手機號碼0911-123456");
				}
				String mem_address2 = new String(req.getParameter("mem_address2").trim());
				if (!mem_address2.equals("null")) {
					
					String city = mem_address2.substring(3, 6);
					String area = mem_address2.substring(6, mem_address2.length());
					req.setAttribute("city", city);
					req.setAttribute("area", area);
				}else{
					errorMsgs.add("請輸入地址!");
				}
				
//				mem_email,facebook,google
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("請填寫email");
				}
				if (mem_facebook == null || mem_facebook.trim().length() == 0) {
					errorMsgs.add("請填寫facebook信箱");
				}
				if (mem_google == null || mem_google.trim().length() == 0) {
					errorMsgs.add("請填寫google信箱");
				}
				
				if(!mem_email.contains("@")){
					errorMsgs.add("email格式錯誤");
				}
				if(!mem_facebook.contains("@")){
					errorMsgs.add("facebook格式錯誤");
				}
				if(!mem_google.contains("@")){
					errorMsgs.add("google格式錯誤");
				}
				

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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/RegisterMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMem(mem_id, mem_password, mem_name, mem_nike, mem_sex, mem_birthday, mem_phone,
						mem_email, mem_address, mem_point, mem_actual_point, mem_picture, mem_authority, mem_facebook,
						mem_google);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
//				註冊成功訊息
				List<String> SMsgs = new LinkedList<String>();
				SMsgs.add("親愛的  "+mem_name+"  ，恭喜您註冊成功，請您至信箱收取email,取得密碼。");
				req.setAttribute("SMsgs", SMsgs);
//				寄出信件
				MemberMail membermail = new MemberMail();
				String mailTitle="媽咪樂寶加入會員密碼通知";
				String homeURL = "http://52.88.89.6:8081/AA107G3/front_end/frontINDEX.jsp";
				String mailMessage="<html><head><title></title></head><body>"+mem_name+"先生/小姐你好，很感謝您的加入，帳號已經為您啟用,這是您的登入密碼【 "+randPsw+"】，請點選此網址<a href="+homeURL+">媽咪樂寶</a>網頁登入更新!!<br><br>媽咪樂寶管理部關心您!!</body></html>";
			      membermail.sendMail(mem_email,mailTitle,mailMessage);
			      
			      
			      
			      
				String url = "/front_end/LoginMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/RegisterMem.jsp");
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
				String mem_no = new String(req.getParameter("mem_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteMem(mem_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("memLogin".equals(action) ) { 
			if (pageReq==null || pageReq==""){
				pageReq="/front_end/frontINDEX.jsp";
			}
//			String pageReqUrl=pageReq.substring(0, pageReq.length()-1);
			List<String> Login_EMsg = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("Login_EMsg", Login_EMsg);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String memLoginId = req.getParameter("memLoginId");
				String memLoginPsw = req.getParameter("memLoginPsw");
				if (memLoginId == null || (memLoginId.trim()).length() == 0) {
					Login_EMsg.add("請輸入會員編號");
				}
				if (memLoginPsw == null || (memLoginPsw.trim()).length() == 0) {
					Login_EMsg.add("請輸入會密碼");
				}
				// Send the use back to the form, if there were errors
				if (!Login_EMsg.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				MemberService memSvc = new MemberService();

				try {
					
					MemberVO id = memSvc.getOneID(memLoginId);
					if (id.getMem_id() == "" || id.getMem_id() == "null") {
						Login_EMsg.add("無此帳號!");
					} else {
						final BASE64Decoder decoder = new BASE64Decoder();
						String dataPsw = new String(id.getMem_password());
						String Psw = new String(decoder.decodeBuffer(dataPsw), "UTF-8");
					
						if (Psw.equals(memLoginPsw)) {
							session.setAttribute("userVO", id);
							session.setAttribute("userType", "Mem");
							
						
							RequestDispatcher successView = req.getRequestDispatcher(pageReq);
							successView.forward(req, res);
							return;

						} else {
							Login_EMsg.add("密碼錯誤");
							RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
							failureView.forward(req, res);
							return;
						}
					}

				} catch (Exception e) {
					Login_EMsg.add("無此帳號!");
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				Login_EMsg.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
		
		if ("memLogout".equals(action)) { 
//		    session.removeAttribute("userVO");
//		    session.removeAttribute("userType");
//		    session.removeAttribute("buylist");
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

			MemberService memSvc = new MemberService();

			try {
				MemberVO memVO = memSvc.getOneID(forgetid);
				String mem_email=null;
			
				
				if (memVO.getMem_email().trim().equals(forgetemail)) {
					mem_email = memVO.getMem_email().trim();
//					亂數密碼產生
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
//					編碼
				
					final BASE64Encoder encoder = new BASE64Encoder();
					
					final byte[] textByte = randPsw.getBytes("UTF-8");
				
					final String encodedPsw = encoder.encode(textByte);
				
					String mem_no = memVO.getMem_no();

					memSvc.updatePsw(encodedPsw, mem_no);
				
//					更改密碼訊息
					
					List<String> SMsgs = new LinkedList<String>();
					SMsgs.add("親愛的  " + memVO.getMem_name() + "  ，新設定的密碼已寄信至您的信箱。");
					req.setAttribute("SMsgs", SMsgs);
					// 寄出重設密碼信件
					
					MemberMail membermail = new MemberMail();
					String mailTitle = "媽咪樂寶變更密碼通知";
					String mailMessage = memVO.getMem_name() + "親愛的用戶你好!!新密碼為： 【" + randPsw + "】";
					membermail.sendMail(mem_email, mailTitle, mailMessage);
					
					
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
	
	
	
	if ("PCAEVA".equals(action)) { // 來自select_page.jsp的請求
	
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
			
		try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String fp_no = req.getParameter("fp_no");
			String mem_no = req.getParameter("mem_no");

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memSvc=new MemberService();
			MemberVO memVO=memSvc.getOneMem(mem_no);
			String mem_name=memVO.getMem_name();
			
			PostpartumCareService PCSvc=new PostpartumCareService();
			PostpartumCareVO PCVO=PCSvc.getOnePC(fp_no);
			String pc_name=PCVO.getPc_name();

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ManageMem.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("mem_no", mem_no); // 資料庫取出的empVO物件,存入req
			req.setAttribute("mem_name", mem_name);
			req.setAttribute("pc_no", fp_no);
			req.setAttribute("pc_name", pc_name);
			
		
			String url = "/member/EvaluationPC.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																			// listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/member/EvaluationPC.jsp");
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
