package com.postpartum_care.controller;

import java.io.*;

import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.*;
import com.pc_application.model.PcApplicationVO;
import com.pc_picture.model.PcPictureService;
import com.pc_picture.model.PcPictureVO;
import com.postpartum_care.model.*;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024 * 1024)
public class PostpartumCareServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllPC.jsp的請求
	if ("listPCPs_ByPc_no_AllPC".equals(action)) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ****************************************/
			String pc_no = new String(req.getParameter("pc_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			PostpartumCareService PCSvc = new PostpartumCareService();
			Set<PcPictureVO> set = PCSvc.getPCPsByPc_no(pc_no);
			PostpartumCareVO postpartumCareVO = PCSvc.getOnePC(pc_no);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listPCPs_ByPc_no", set);// 資料庫取出的set物件,存入request
			req.setAttribute("postpartumCareVO", postpartumCareVO);


			String url = "/pc_picture/listAllPCP_ByPc_no.jsp";              // 成功轉交 dept/listAllPC.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	if ("listPCPs_ByPc_no".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ****************************************/
			String pc_no = new String(req.getParameter("pc_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			PostpartumCareService PCSvc = new PostpartumCareService();
			Set<PcPictureVO> set = PCSvc.getPCPsByPc_no(pc_no);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listPCPs_ByPc_no", set);// 資料庫取出的set物件,存入request


			String url = "/postpartum_care/PersonalPC_pic.jsp";              // 成功轉交 dept/listAllPC.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}	
	    
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("pc_no");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產後廠商編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String pc_no = str;
								
				
				/***************************2.開始查詢資料*****************************************/
				PostpartumCareService PCSvc = new PostpartumCareService();
				PostpartumCareVO postpartumCareVO = PCSvc.getOnePC(pc_no);
				
				if (postpartumCareVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 2-2.順便帶照片走 ************/
				Set<PcPictureVO> set = PCSvc.getPCPsByPc_no(pc_no);				
				session.setAttribute("listPCPs_ByPc_no", set);				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("postpartumCareVO", postpartumCareVO); // 資料庫取出的empVO物件,存入req
				String url = "/postpartum_care/ViewOnePC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPC.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String pc_no = new String(req.getParameter("pc_no"));
				
				/***************************2.開始查詢資料****************************************/
				PostpartumCareService PCSvc = new PostpartumCareService();
				PostpartumCareVO postpartumCareVO = PCSvc.getOnePC(pc_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postpartumCareVO", postpartumCareVO);         // 資料庫取出的empVO物件,存入req
				String url = "/postpartum_care/update_pc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PC_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/listAllPC.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_PC_input.jsp的請求
			System.out.println("come");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String pc_no = req.getParameter("pc_no").trim();
				System.out.println(pc_no);
				String pc_authority = req.getParameter("pc_authority").trim();
				System.out.println(pc_authority);
				Integer pc_eva_good = new Integer(req.getParameter("pc_eva_good").trim());
				System.out.println(pc_eva_good);
				Integer pc_eva_normal = new Integer(req.getParameter("pc_eva_normal").trim());	
				System.out.println(pc_eva_normal);				
				Integer pc_eva_bad = new Integer(req.getParameter("pc_eva_bad").trim());
				System.out.println(pc_eva_bad);	
				String pc_bonus = req.getParameter("pc_bonus").trim();	
				System.out.println(pc_bonus);	
				String pc_gift = req.getParameter("pc_gift").trim();
				System.out.println(pc_gift);	
				Integer pc_point = new Integer(req.getParameter("pc_point").trim());
				System.out.println(pc_point);					
				String pc_id = req.getParameter("pc_id").trim();
				System.out.println(pc_id);
				String pc_password_in = req.getParameter("pc_password").trim();
				System.out.println(pc_password_in);
				String pc_name = req.getParameter("pc_name").trim();
				System.out.println(pc_name);
				String pc_address = req.getParameter("pc_address").trim();
				System.out.println(pc_address);
				String pc_phone = req.getParameter("pc_phone").trim();
				System.out.println(pc_phone);
				String pc_email = req.getParameter("pc_email").trim();
				System.out.println(pc_email);
				String pc_type = req.getParameter("pc_type").trim();
				System.out.println(pc_type);		
				String pc_summary = req.getParameter("pc_summary").trim();
				System.out.println(pc_summary);				


				
				final BASE64Encoder encoder = new BASE64Encoder();			   
			    final byte[] textByte = pc_password_in.getBytes("UTF-8");
			    String pc_password = encoder.encode(textByte);				
			      System.out.println("編碼= "+pc_password);
			      //解碼
			      final BASE64Decoder decoder = new BASE64Decoder();
			      System.out.println("解碼= "+new String(decoder.decodeBuffer(pc_password), "UTF-8"));				
				

				PostpartumCareVO postpartumCareVO = new PostpartumCareVO();



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postpartumCareVO", postpartumCareVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/PersonalPC.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PostpartumCareService PCSvc = new PostpartumCareService();
				postpartumCareVO = PCSvc.updatePC(pc_no, pc_id, pc_password,pc_name,
						pc_address, pc_phone, pc_email, pc_type, pc_eva_good, 
						pc_eva_normal, pc_eva_bad, pc_summary, pc_bonus, pc_gift,	
						pc_point, pc_authority);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				session.setAttribute("userVO", postpartumCareVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/postpartum_care/PersonalPC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/PersonalPC.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPC.jsp的請求  
		
			List<String> errorMsgs = new LinkedList<String>();
			List<PcPictureVO> errorMsgs_pic = new LinkedList<PcPictureVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("errorMsgs_pic", errorMsgs_pic);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String pc_id = req.getParameter("pc_id").trim();				
				if (!Pattern.matches("[a-zA-Z0-9]{6,20}", pc_id)) {
					errorMsgs.add("帳號請輸入6~20碼的大小寫英數字");
				}								
				
//				String pc_password = req.getParameter("pc_password").trim();
				String pc_name = req.getParameter("pc_name").trim();
				
				if (pc_name == null || pc_name.trim().length() == 0 ){
					errorMsgs.add("廠商名稱不可為空");
				}				
				
				String pc_address = req.getParameter("pc_address").trim();
				String pc_address2 = new String(req.getParameter("pc_address2").trim());				
				if (pc_address2.length() != 0) {
					String city = pc_address2.substring(3, 6);
					String area = pc_address2.substring(6, pc_address2.length());
					req.setAttribute("city", city);
					req.setAttribute("area", area);
				}
				
				if (pc_address == null || pc_address.trim().length() < 10 ){

					if (pc_address2.length() != 0) {
						errorMsgs.add("廠商地址請填寫完整");
					}else{
						errorMsgs.add("廠商地址不可為空");
					}
				}				

				String pc_phone = req.getParameter("pc_phone").trim();
				if (pc_phone == null || pc_phone.trim().length() == 0 ){
					errorMsgs.add("廠商電話不可為空");
				}				
				
				String pc_email = req.getParameter("pc_email").trim();	
				if (pc_email == null || pc_email.trim().length() == 0 ){
					errorMsgs.add("廠商電子信箱不可為空");
				}
				
				
				String pc_type = null;
				try {
				pc_type = req.getParameter("pc_type").trim();
				} catch (Exception e){
					errorMsgs.add("請選擇廠商類型");
				}
				

				String pc_summary = req.getParameter("pc_summary").trim();
				if (pc_phone == null || pc_phone.trim().length() == 0 ){
					errorMsgs.add("請填寫廠商簡介");
				}
				
				String pc_bonus = null;
				try {
				pc_bonus = req.getParameter("pc_bonus").trim();
				} catch (Exception e){
					errorMsgs.add("請選擇是否提供預約下訂折扣");
				}
				
				String pc_gift = null;
				try {
				pc_gift = req.getParameter("pc_gift").trim();
				} catch (Exception e){
					errorMsgs.add("請選擇是否提供預約小禮");
				}	
				
				Integer pc_point = new Integer(req.getParameter("pc_point").trim());
				
				
				String pc_authority = "Y";								
				Integer pc_eva_good = new Integer(0);
				Integer pc_eva_normal = new Integer(0);
				Integer pc_eva_bad = new Integer(0);

				PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_id(pc_id);
				postpartumCareVO.setPc_name(pc_name);
				postpartumCareVO.setPc_type(pc_type);
				postpartumCareVO.setPc_address(pc_address);
				postpartumCareVO.setPc_phone(pc_phone);
				postpartumCareVO.setPc_email(pc_email);
				postpartumCareVO.setPc_summary(pc_summary);
				postpartumCareVO.setPc_bonus(pc_bonus);
				postpartumCareVO.setPc_gift(pc_gift);
				postpartumCareVO.setPc_authority(pc_authority);
				postpartumCareVO.setPc_point(pc_point);
				postpartumCareVO.setPc_eva_good(pc_eva_good);
				postpartumCareVO.setPc_eva_normal(pc_eva_normal);
				postpartumCareVO.setPc_eva_bad(pc_eva_bad);
				
				
				//照片看看痾
				String pcp_summarys[] = req.getParameterValues("pcp_summary");
				Collection<Part> pcp_parts = req.getParts();
				
				List<PcPictureVO> list = new ArrayList<PcPictureVO> ();	
				int nowCount = 0;

				if (pcp_summarys != null) {
//					if(pcp_parts != null && !pcp_parts.isEmpty()){
//						List<Part> parts = new ArrayList(pcp_parts);
//						for (int i=0; i<pcp_summarys.length; i++){
//							if (getFileNameFromPart(part) != null && part.getContentType()!=null){
//							Part part = (Part)parts.get(i);
//							InputStream in = part.getInputStream();
//							
//							byte[] pcp_picture = new byte[in.available()];
//							System.out.println(pcp_picture);
//							in.read(pcp_picture);
//							in.close();			
//							
//							PcPictureVO pcPictureVO = new PcPictureVO();
//							pcPictureVO.setPcp_picture(pcp_picture);
//							pcPictureVO.setPcp_summary(pcp_summarys[i]);
//							
//							list.add(pcPictureVO);
//							errorMsgs_pic.add(pcPictureVO);
//							}
//						}
					
					if(pcp_parts != null && !pcp_parts.isEmpty()){
					List<Part> parts = new ArrayList(pcp_parts);
					for (Part part :parts){
						if (getFileNameFromPart(part) != null && part.getContentType()!=null){
						
						InputStream in = part.getInputStream();
						
						byte[] pcp_picture = new byte[in.available()];
						System.out.println(pcp_picture);
						in.read(pcp_picture);
						in.close();			
						
						PcPictureVO pcPictureVO = new PcPictureVO();
						pcPictureVO.setPcp_picture(pcp_picture);
						pcPictureVO.setPcp_summary(pcp_summarys[nowCount]);
						
						list.add(pcPictureVO);
						errorMsgs_pic.add(pcPictureVO);
						nowCount++;
						}
					}					
					
					
					}
					
				}
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("postpartumCareVO", postpartumCareVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/postpartum_care/RegisterPC.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MailService mail = new MailService();
				 int randCount=(int)Math.ceil(Math.random()*(10-6+1)+6-1);
			      int randInt=0;
			      String randPsw="";
			      System.out.println("randCount="+randCount);
			      for (int i=1;i<=randCount;i++){
			    	  randInt=(int)Math.ceil(Math.random()*(122-48+1)+48-1);
//			    	 System.out.println(i);
			    	  if ((randInt>=48 && randInt<=57) || (randInt>=65 && randInt<=90) || (randInt >=97 && randInt<=122)){
			    		  String achar=new Character((char)randInt).toString();
			    		  randPsw=randPsw+achar;
			    		  
			    		 
			    	  }else{
			    		  i--;
			    	  }
			      }      
			      System.out.println("randPsw= "+randPsw);
			      
			      //編碼
			      final BASE64Encoder encoder = new BASE64Encoder();			   
			      final byte[] textByte = randPsw.getBytes("UTF-8");
			      final String encodedPsw = encoder.encode(textByte);
			      System.out.println("編碼= "+encodedPsw);
			      //解碼
			      final BASE64Decoder decoder = new BASE64Decoder();
			      System.out.println("解碼= "+new String(decoder.decodeBuffer(encodedPsw), "UTF-8"));
				
				  String mailTitle="媽咪樂寶新朋友-密碼通知";
				  String homeURL = "http://52.88.89.6:8081/AA107G3/front_end/frontINDEX.jsp";
				  String mailMessage="<html><head><title></title></head><body>"+pc_name+" 貴寶號您好：媽咪樂寶感謝您的加入，帳號已經為您啟用。<br>這是您的登入密碼【"+randPsw+"】，請至<a href="+homeURL+">媽咪樂寶</a>網頁登入更新!!<br><br>媽咪樂寶管理部關心您!!</body></html>";
			      mail.sendMail(pc_email,mailTitle,mailMessage);

				String pc_password=encodedPsw;
				postpartumCareVO.setPc_password(pc_password);
				
				
				PostpartumCareService PCSvc = new PostpartumCareService();
				
				if (list != null && !list.isEmpty()){
					PCSvc.insertWithPCPs(postpartumCareVO, list);
					
				} else{
				
				postpartumCareVO = PCSvc.addPC(pc_id, pc_password,pc_name,
						pc_address, pc_phone, pc_email, pc_type, pc_eva_good, 
						pc_eva_normal, pc_eva_bad, pc_summary, pc_bonus, pc_gift,	
						pc_point, pc_authority);				
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				List<String> SMsgs = new LinkedList<String>();	
				SMsgs.add("~媽咪樂寶歡迎您的加入~");
				SMsgs.add("請至信箱收取密碼後登入修改!");				
				
				req.setAttribute("SMsgs", SMsgs);
				
				String url = "/front_end/LoginFir.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPC.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/RegisterPC.jsp");
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
				String pc_no = new String(req.getParameter("pc_no"));
				
				/***************************2.開始刪除資料***************************************/
				PostpartumCareService PCSvc = new PostpartumCareService();
				PCSvc.deletePC(pc_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/postpartum_care/listAllPC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/listAllPC.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if (("listPCs_ByCompositeQuery".equals(action)) || ("listPCs_ByCompositeQueryHome".equals(action))) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//				String order = new String(req.getParameter("order"));
				
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				PostpartumCareService postpartumCareSvc = new PostpartumCareService();
				List<PostpartumCareVO> list  = postpartumCareSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPCs_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				session.setAttribute("order", order);
				
				Set<String> keys = map.keySet();


				for (String key : keys) {
					String value = map.get(key)[0];
					if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"pc_address".equals(key)) {
						req.setAttribute(key, value);
						System.out.println(key+","+value);
					} else if (value != null && value.trim().length() != 0	&& "pc_address".equals(key)){
						req.setAttribute(key, value);
						String pc_address = value.trim();
						
						String city = pc_address.substring(3, 6);
						String area = pc_address.substring(6, pc_address.length());
						
						System.out.println(city+" "+area);
						req.setAttribute("city", city);
						req.setAttribute("area", area);		 				
					}
				}				
				String url = null;
				if ("listPCs_ByCompositeQuery".equals(action))
					url = "/postpartum_care/listPCs_ByCompositeQueryHome.jsp";        // 成功轉交 index.jsp
				else if ("listPCs_ByCompositeQueryHome".equals(action))
					url = "/postpartum_care/listPCs_ByCompositeQueryHome.jsp";              // 成功轉交 searchhome.jsp
				
				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/PostpartumCare_Index.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
		if ("PC_application".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String pageReq=(String)session.getAttribute("pageReq");

			try {
				/***************************1.接收請求參數****************************************/
				String pc_no = new String(req.getParameter("pc_no"));
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService MemSvc = new MemberService();
				MemberVO memberVO = MemSvc.getOneMem(mem_no);
				
				if (memberVO==null) {
					System.out.println("請先登入會員");
					errorMsgs.add("請先登入會員");
					
					RequestDispatcher failureView = req
							.getRequestDispatcher(pageReq);
					failureView.forward(req, res);	
					return;
				}
			
				PostpartumCareService PCSvc = new PostpartumCareService();
				PostpartumCareVO postpartumCareVO = PCSvc.getOnePC(pc_no);
				
				Integer pc_point = postpartumCareVO.getPc_point();
				Integer mem_point = memberVO.getMem_point();
				
				if (mem_point < pc_point) {
					System.out.println("積分不足");
					errorMsgs.add("積分不足，請加油蒐集唷!!");
					RequestDispatcher failureView = req
							.getRequestDispatcher(pageReq);
					failureView.forward(req, res);	
					return;					
				}
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postpartumCareVO", postpartumCareVO);   // 資料庫取出的empVO物件,存入req
				String url = "/postpartum_care/ApplicationPC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PC_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/postpartum_care/ViewOnePC.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listApps_ByPc_no".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String pc_no = new String(req.getParameter("pc_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				PostpartumCareService PCSvc = new PostpartumCareService();
				Set<PcApplicationVO> set0 = PCSvc.getAppsByPc_no(pc_no,"0");
				Set<PcApplicationVO> set1 = PCSvc.getAppsByPc_no(pc_no,"1");
				Set<PcApplicationVO> set2 = PCSvc.getAppsByPc_no(pc_no,"2");
				Set<PcApplicationVO> set3 = PCSvc.getAppsByPc_no(pc_no,"3");
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listApps_ByPc_no_review0", set0);
				req.setAttribute("listApps_ByPc_no_review1", set1);
				req.setAttribute("listApps_ByPc_no_review2", set2);
				req.setAttribute("listApps_ByPc_no_review3", set3);// 資料庫取出的set物件,存入request


				String url = "/postpartum_care/PersonalPC_app.jsp";              // 成功轉交

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
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
