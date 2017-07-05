
package com.forum_article.controller;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.forum_article.model.*;
import com.member.model.*;
import com.point_record.model.*;
import com.forum_message.model.*;

public class ForumArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String pageReq = (String) session.getAttribute("pageReq");

		if ("getOne_For_Display".equals(action)|| "getOne_For_DisplayModify".equals(action)) { // 來自select_page.jsp的請求
			
			if ("getOne_For_DisplayModify".equals(action)){
				pageReq="/forum/ArticleModify.jsp";
			}else{
				pageReq="/forum/ArticleDetail.jsp";
			}
			
//			if ("getOne_For_DisplayModify".equals(action)){
//				pageReq="/forum/ArticleModify.jsp";
//			}
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String fa_no = req.getParameter("fa_no");
				String mem_no=req.getParameter("mem_no");

				/*************************** 2.開始查詢資料 *****************************************/
				ForumArticleService faSvc = new ForumArticleService();
				ForumArticleVO FAVO = faSvc.getOneFa(fa_no);
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_no);
//				ForumMessageService fmSvc = new ForumMessageService();
//				List<ForumMessageVO> FMVO = fmSvc.getOneFm_fa_no(fa_no);
				if (FAVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("FAVO", FAVO); // 資料庫取出的FAVO物件,存入req
				req.setAttribute("memVO", memVO);
//				req.setAttribute("FMVO", FMVO);
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}

		
if ("listArticle_BycompositeQuery_index".equals(action)) { // 來自select_page.jsp的請求
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String fa_no = req.getParameter("fa_no");
				String mem_no=req.getParameter("mem_no");

				/*************************** 2.開始查詢資料 *****************************************/
				ForumArticleService faSvc = new ForumArticleService();
				ForumArticleVO FAVO = faSvc.getOneFa(fa_no);
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(mem_no);
//			
				if (FAVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("FAVO", FAVO); // 資料庫取出的FAVO物件,存入req
				req.setAttribute("memVO", memVO);
//				req.setAttribute("FMVO", FMVO);
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher("/forum/listArticles_ByCompositeQuery.jsp"); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			pageReq="/forum/ArticleDetail.jsp";
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String fa_no = req.getParameter("fa_no").trim();
				ForumArticleDAO FADAO=new ForumArticleDAO();
				ForumArticleVO FAVO=FADAO.findByPK(fa_no);		
				
				String mem_no = req.getParameter("mem_no").trim();
				String fa_title = req.getParameter("fa_title").trim();
				String fa_content = req.getParameter("fa_content").trim();
				Integer fa_like=(Integer)FAVO.getFa_like();
				Integer fa_dislike=(Integer)FAVO.getFa_dislike();
				java.sql.Timestamp fa_publish_date = FAVO.getFa_publish_date();
			
				//開始時間為現在
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒數
				java.sql.Timestamp ts = new java.sql.Timestamp(long1);					//轉成timestamp格式
				String Stringts=ts.toString();											//轉成字串做毫秒變成0的調整
				String ts2=Stringts.substring(0, Stringts.length()-3)+"0";				//.547之類的數字變成0
				long ts3 = sdf.parse(ts2).getTime();									//再轉成秒數
				java.sql.Timestamp fa_modify_date = new java.sql.Timestamp(ts3);					//轉成timestamp格式
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FAVO", FAVO); // 含有輸入格式錯誤的FAVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/forum/ArticleModify.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				
				ForumArticleService faSvc = new ForumArticleService();
				
				FAVO = faSvc.updateFa(fa_no, mem_no, fa_title, fa_content, fa_like, fa_dislike,
						fa_publish_date,fa_modify_date);
				
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/		
				
				req.setAttribute("FAVO", FAVO); // 資料庫update成功後,正確的的FAVO物件,存入req
				String url = pageReq;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {				
				
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forum/ArticleModify.jsp");
				failureView.forward(req, res);
			}

		}

		
		if ("update_dislike".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				
				String fa_no = req.getParameter("fa_no").trim();
				Integer fa_dislike=0;
				System.out.println("文章編號為："+fa_no);
			
				if (!errorMsgs.isEmpty()) {
					System.out.println("1");
					RequestDispatcher failureView = req.getRequestDispatcher("/forum/ArticleModify.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				ForumArticleService FASvc = new ForumArticleService();
				FASvc.update_dislike(fa_no,fa_dislike);
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/		

				String url = pageReq;
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {				
				System.out.println("5");
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forum/ArticleModify.jsp");
				failureView.forward(req, res);
			}

		}

		
		
		if ("insert".equals(action)) { 

			if (pageReq == null || pageReq == "") {
				pageReq = "/member/MyPCApplication.jsp";
			}
			System.out.println(pageReq);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String mem_no = req.getParameter("mem_no").trim();
				String fa_title = req.getParameter("fa_title").trim();
				String fa_content = req.getParameter("fa_content").trim();
			

				if (fa_title.trim().length() == 0 || fa_title == null) {
					errorMsgs.add("請輸入文章標題");
				}
				
				if (fa_content.trim().length() == 0 || fa_content == null) {
					errorMsgs.add("請輸入文章內容");
				}
				
				Integer fa_like=0;
				Integer fa_dislike=1;
				java.sql.Timestamp fa_modify_date=null;
		
				
//				****1.建立積分紀錄*****
				
				String pr_type="討論區積分";
				String pr_content="發表文章："+fa_title;
				//現在時間轉timestamp
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date du = new java.util.Date();							 	//時間物件
				long long1 = du.getTime();												//現在時間的秒數
				java.sql.Timestamp ts = new java.sql.Timestamp(long1);					//轉成timestamp格式
				String Stringts=ts.toString();											//轉成字串做毫秒變成0的調整
				String ts2=Stringts.substring(0, Stringts.length()-3)+"0";				//.547之類的數字變成0
				long ts3 = sdf.parse(ts2).getTime();	
				//再轉成秒數
				java.sql.Date pr_date = new java.sql.Date(ts3);
				Integer pr_point=20;
				
				PointRecordVO pointRecordVO = new PointRecordVO();
				pointRecordVO.setMem_no(mem_no);				
				pointRecordVO.setPr_type(pr_type);				
				pointRecordVO.setPr_content(pr_content);				
				pointRecordVO.setPr_date(pr_date);				
				pointRecordVO.setPr_point(pr_point);
				
//				***2.更新會員積分******
				MemberService memSvc = new MemberService();
				MemberVO memberVO_old = memSvc.getOneMem(mem_no);
				Integer old_point = new Integer(memberVO_old.getMem_point());
				Integer new_point = old_point + pr_point;
				
				//再算新的
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_point(new_point);
				

//				**3.新增文章*************
				ForumArticleVO FAVO = new ForumArticleVO();

				FAVO.setMem_no(mem_no);
				FAVO.setFa_title(fa_title);
				FAVO.setFa_content(fa_content);
				FAVO.setFa_like(fa_like);
				FAVO.setFa_dislike(fa_dislike);
				FAVO.setFa_modify_date(fa_modify_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FAVO", FAVO); // 含有輸入格式錯誤的FAVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(pageReq);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ForumArticleService faSvc = new ForumArticleService();
				
				faSvc.addFaWithPoint(FAVO,pointRecordVO,memberVO);
				//完成後，將新的memVO再次塞回session
				MemberVO newVO= memSvc.getOneMem(mem_no);
				session.setAttribute("userVO", newVO);
				req.setAttribute("FAVO",FAVO);
				
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				
				String url =pageReq;
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
				String fa_no = req.getParameter("fa_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ForumArticleService faSvc = new ForumArticleService();
//				faSvc.deleteAdv(fa_no);

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
		if ("listArticle_BycompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				ForumArticleService faSvc = new ForumArticleService();
				List<ForumArticleVO> list = faSvc.getAll(map);
				System.out.println("從dao出來");
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				System.out.println("有到set");
				req.setAttribute("listArticles_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				req.setAttribute("query", "query");
				RequestDispatcher successView = req.getRequestDispatcher("/forum/listArticles_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forum/listArticles_ByCompositeQuery.jsp");
				failureView.forward(req, res);
			}
		}

	}

	
}