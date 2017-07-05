package com.forum_report.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.forum_article.model.*;
import com.forum_report.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;

public class ForumReportServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq=(String)session.getAttribute("pageReq");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			pageReq="/forum/ArticleReport.jsp";
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String fr_am_no = req.getParameter("fr_am_no");
				String mem_no = req.getParameter("mem_no");
				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
		
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("fr_am_no", fr_am_no);// 資料庫取出的FRVO物件,存入req
				req.setAttribute("mem_no", mem_no);
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String fr_no = req.getParameter("fr_no").trim();
				String fr_pass=req.getParameter("fr_pass").trim();
			
				ForumReportService FRSvc=new ForumReportService();
				ForumReportVO FRVO=FRSvc.getOneFr(fr_no);
			
				String mem_no=FRVO.getMem_no();
				String fr_am_no=FRVO.getFr_am_no();
				String fr_type=FRVO.getFr_type();
				String fr_title=FRVO.getFr_title();
				String fr_content=FRVO.getFr_content();
				String fr_status=null;				
				java.sql.Timestamp fr_publish_date = FRVO.getFr_publish_date();
				
				if(fr_pass.equals("pass")){
					fr_status="1";
				}else {
					fr_status="2";
				}
			
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 	//自訂時間格式
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒數
				java.sql.Timestamp ts = new java.sql.Timestamp(long1);					//轉成timestamp格式
				String Stringts=ts.toString();											//轉成字串做毫秒變成0的調整
				String ts2=Stringts.substring(0, Stringts.length()-3)+"0";				//.547之類的數字變成0
				long ts3 = sdf.parse(ts2).getTime();									//再轉成秒數
				java.sql.Timestamp ts4 = new java.sql.Timestamp(ts3);					//轉成timestamp格式
				
				java.sql.Timestamp fr_review_date = ts4;							
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FRVO", FRVO); // 含有輸入格式錯誤的FRVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
			
				ForumArticleService FASvc=new ForumArticleService();   //利用文章號碼抓到文章的作者會員
				
				ForumArticleVO FAVO=FASvc.getOneFa(fr_am_no);
				System.out.println(FAVO.getFa_content());
				
				if(fr_pass.equals("pass")){
					
					FRVO.setFr_no(fr_no);
					FRVO.setMem_no(mem_no);
					FRVO.setFr_am_no(fr_am_no);
					FRVO.setFr_type(fr_type);
					FRVO.setFr_title(fr_title);
					FRVO.setFr_content(fr_content);
					FRVO.setFr_publish_date(fr_publish_date);
					FRVO.setFr_status(fr_status);
					FRVO.setFr_review_date(fr_review_date);
					
					
//					****1.建立積分紀錄*****
					
					String pr_type="討論區檢舉";
					String pr_content="被檢舉文章："+FRVO.getFr_am_no();
					
					java.sql.Date pr_date = new java.sql.Date(ts3);
					
					Integer pr_point=-20;
				
					String pr_mem_no=FAVO.getMem_no();
			
					PointRecordVO pointRecordVO = new PointRecordVO();
					pointRecordVO.setMem_no(pr_mem_no);				
				
					pointRecordVO.setPr_type(pr_type);	
					
					pointRecordVO.setPr_content(pr_content);
					
					pointRecordVO.setPr_date(pr_date);	
				
					pointRecordVO.setPr_point(pr_point);
					
//					***2.更新會員積分******
					MemberService memSvc = new MemberService();
					MemberVO memberVO_old = memSvc.getOneMem(pr_mem_no);
					Integer old_point = new Integer(memberVO_old.getMem_point());
					Integer new_point = old_point + pr_point;
					
					//再算新的
					MemberVO memberVO = new MemberVO();
					memberVO.setMem_no(pr_mem_no);
					memberVO.setMem_point(new_point);
					
					
					FRSvc.updateWithPoint(FRVO,pointRecordVO,memberVO);
					//完成後，將新的memVO再次塞回session
					MemberVO newVO= memSvc.getOneMem(mem_no);
					session.setAttribute("userVO", newVO);
					

				
				}else {
				
					FRVO.setFr_no(fr_no);
					FRVO.setMem_no(mem_no);
					FRVO.setFr_am_no(fr_am_no);
					FRVO.setFr_type(fr_type);
					FRVO.setFr_title(fr_title);
					FRVO.setFr_content(fr_content);
					FRVO.setFr_publish_date(fr_publish_date);
					FRVO.setFr_status(fr_status);
					FRVO.setFr_review_date(fr_review_date);
	
					
					FRVO = FRSvc.updateFr(fr_no,mem_no, fr_am_no,fr_type,fr_title,fr_content,fr_publish_date,fr_status,fr_review_date);
					
				}
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("FRVO", FRVO); // 資料庫update成功後,正確的的FRVO物件,存入req
				req.setAttribute("FAVO", FAVO);
				//PASS要進行停權或扣款的引導
				
				List<String> SMsgs = new LinkedList<String>();
				List<String> EMsgs = new LinkedList<String>();
				if(fr_pass.equals("pass")){		
				
					SMsgs.add("審核完成，是否要進行 "+ FRVO.getMem_no()+ " 的停權?");
					req.setAttribute("SMsgs", SMsgs);
				}else {
					
					EMsgs.add("審核完成!");
					req.setAttribute("EMsgs", EMsgs);
				}
				
				String url = "/back_end/ForumReport.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/ForumReport.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String fr_am_no = req.getParameter("fr_am_no").trim();
				String fr_type = req.getParameter("fr_type").trim();
				String fr_title = req.getParameter("fr_title").trim();
				String fr_content = req.getParameter("fr_content").trim();
				String fr_status = "0";
				java.sql.Timestamp fr_review_date=null;
				ForumReportVO FRVO = new ForumReportVO();
				pageReq="/forum/FA.do?fa_no="+fr_am_no+"&mem_no="+mem_no+"&action=getOne_For_Display";
//				pageReq="/forum/ArticleDetail.jsp?fa_no="+fr_am_no+"&mem_no="+mem_no;
				
				FRVO.setMem_no(mem_no);
				FRVO.setFr_am_no(fr_am_no);
				FRVO.setFr_type(fr_type);
				FRVO.setFr_title(fr_title);
				FRVO.setFr_content(fr_content);
				FRVO.setFr_status(fr_status);
				FRVO.setFr_review_date(fr_review_date);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FRVO", FRVO); // 含有輸入格式錯誤的FRVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;
					
				}
				
				/***************************2.開始新增資料***************************************/
				ForumReportService FRSvc = new ForumReportService();
				FRVO = FRSvc.addFr(mem_no,fr_am_no,fr_type,fr_title,fr_content,fr_status,fr_review_date);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				List<String> FRSMsgs = new LinkedList<String>();
				FRSMsgs.add("檢舉已經申請成功，我們將盡速為您處理!");
				req.setAttribute("FRSMsgs", FRSMsgs);
				
//				
				RequestDispatcher successView = req.getRequestDispatcher(pageReq); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
//				Integer fa_no = new Integer(req.getParameter("fa_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				ForumReportService FRSvc = new ForumReportService();
////				FRSvc.deleteEmp(fa_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			ss	successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listFourmRepoerts_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
		
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
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
			
				/***************************2.開始複合查詢***************************************/
				ForumReportService FRSvc = new ForumReportService();
				List<ForumReportVO> list  = FRSvc.getAll(map);
			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listForumReports_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/listForumReports_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
			
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
