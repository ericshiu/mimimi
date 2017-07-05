package com.hospital.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.model.*;
import com.hospital_ot_item.model.HospitalOtItemVO;



public class HospitalServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("hos_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入醫院編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hospital/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String hos_no = new String(str);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hospital/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				HospitalService hosSvc = new HospitalService();
				HospitalVO hospitalVO = hosSvc.getOneHos(hos_no);
				if (hospitalVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hospital/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("HospitalVO", hospitalVO); // 資料庫取出的hospitalVO物件,存入req
				String url = "/hospital/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/hospital/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String hos_no = new String(req.getParameter("hos_no"));
				
				/***************************2.開始查詢資料****************************************/
				HospitalService hosSvc = new HospitalService();
				HospitalVO hospitalVO = hosSvc.getOneHos(hos_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("HospitalVO", hospitalVO);         // 資料庫取出的hospitalVO物件,存入req
				String url = "/hospital/update_hospital_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_hospital_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/hospital/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_hospital_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String hos_no = new String(req.getParameter("hos_no").trim());
				String hos_name = req.getParameter("hos_name").trim();
				String hos_address = req.getParameter("hos_address").trim();
				String hos_phone = req.getParameter("hos_phone").trim();
				

				HospitalVO hospitalVO = new HospitalVO();
				hospitalVO.setHos_no(hos_no);
				hospitalVO.setHos_name(hos_name);
				hospitalVO.setHos_address(hos_address);
				hospitalVO.setHos_phone(hos_phone);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("HospitalVO", hospitalVO); // 含有輸入格式錯誤的hospitalVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/hospital/update_hospital_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				HospitalService hosSvc = new HospitalService();
				hospitalVO = hosSvc.updateHos(hos_no, hos_name, hos_address, hos_phone);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("HospitalVO", hospitalVO); // 資料庫update成功後,正確的的hospitalVO物件,存入req
				String url = "/hospital/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/hospital/update_hospital_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String hos_name = req.getParameter("hos_name").trim();
				String hos_address = req.getParameter("hos_address").trim();
				String hos_phone = req.getParameter("hos_phone").trim();
				

				HospitalVO hospitalVO = new HospitalVO();
				hospitalVO.setHos_name(hos_name);
				hospitalVO.setHos_address(hos_address);
				hospitalVO.setHos_phone(hos_phone);
				
				
				List<HospitalOtItemVO> list = new ArrayList<HospitalOtItemVO> ();	
				int nowCount = 0;				
				String ots[] = req.getParameterValues("ot_no");
				if (ots != null) {
					for (int i = 0 ; i<ots.length ; i++){
						HospitalOtItemVO  hospitalOtItemVO = new HospitalOtItemVO();
						hospitalOtItemVO.setOt_no(ots[nowCount]);
						list.add(hospitalOtItemVO);
						nowCount++;
					}				
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("HospitalVO", hospitalVO); // 含有輸入格式錯誤的hospitalVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/optional_test/addHOS.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				HospitalService hosSvc = new HospitalService();
				if (list != null && !list.isEmpty()){
					hosSvc.insertWithOTs(hospitalVO, list);
				} else {
					hospitalVO = hosSvc.addHos(hos_name, hos_address, hos_phone);
				}
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/optional_test/addHOS.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/optional_test/addHOS.jsp");
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
				String hos_no = new String(req.getParameter("hos_no"));
				
				/***************************2.開始刪除資料***************************************/
				HospitalService hosSvc = new HospitalService();
				hosSvc.deleteHos(hos_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/hospital/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/hospital/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listHos_ByCompositeQuery".equals(action))  { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("in");
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				String hospital_ot_no = (String)session.getAttribute("hospital_ot_item.ot_no");
			    
			    if (req.getParameter("whichPage") == null){
			     System.out.println("2");
			     HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
			     HashMap<String, String[]> map2 = new HashMap<String, String[]>();
			     map2 = (HashMap<String, String[]>)map1.clone();
			     session.setAttribute("map",map2);
			     map = (HashMap<String, String[]>)req.getParameterMap();
			     hospital_ot_no = new String(req.getParameter("hospital_ot_item.ot_no"));
			     session.setAttribute("hospital_ot_item.ot_no",hospital_ot_no);
			    }
				
				/***************************2.開始複合查詢***************************************/
				HospitalService hosSvc = new HospitalService();
				List<HospitalVO> list  = hosSvc.getAll(map);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listHos_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				session.setAttribute("hospital_ot_no", hospital_ot_no);

//				Set<String> keys = map.keySet();
//
//
//				for (String key : keys) {
//					String value = map.get(key)[0];
//					if (value != null && value.trim().length() != 0	&& !"action".equals(key) && !"hos_address".equals(key)) {
//						req.setAttribute(key, value);
//						System.out.println(key+","+value);
//					} else if (value != null && value.trim().length() != 0	&& "hos_address".equals(key)){
//						req.setAttribute(key, value);
//						String hos_address = value.trim();
//						
//						String city = hos_address.substring(3, 6);
//						String area = hos_address.substring(6, hos_address.length());
//						
//						System.out.println(city+" "+area);
//						req.setAttribute("city", city);
//						req.setAttribute("area", area);		 				
//					}
//				}				
				String url = null;
				if ("listHos_ByCompositeQuery".equals(action))
					url = "/optional_test/listHos_ByCompositeQueryHome.jsp";        // 成功轉交 index.jsp
           // 成功轉交 searchhome.jsp
				
				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/optional_test/listHos_ByCompositeQueryHome.jsp");
				failureView.forward(req, res);
			}
		}				
		
		
		
		
	}	
}
