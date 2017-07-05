package com.firm_evaluation.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firm_evaluation.model.FirmEvaluationService;
import com.firm_evaluation.model.FirmEvaluationVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.point_record.model.PointRecordVO;
import com.postpartum_care.model.*;

public class FirmEvaluationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();   
		
        if ("insertToPC".equals(action)) { // 來自會員專區  我的預約
			List<String> errorMsgs = new LinkedList<String>();
			List<String> successMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("successMsgs", successMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.println("in");
				String fp_no = req.getParameter("fp_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				java.sql.Date fe_date = java.sql.Date.valueOf(req.getParameter("fe_date").trim()); 	
				String fe_type = req.getParameter("fe_type").trim();
				String fe_content = req.getParameter("fe_content").trim();
				System.out.println("ok");
				

				//Step1  建立appVO
				FirmEvaluationVO firmEvaluationVO= new FirmEvaluationVO();
				firmEvaluationVO.setFp_no(fp_no);
				firmEvaluationVO.setMem_no(mem_no);
				firmEvaluationVO.setFe_type(fe_type);
				firmEvaluationVO.setFe_content(fe_content);
				firmEvaluationVO.setFe_date(fe_date);

				//Step2  建立積分記錄VO
				String pr_type = "評價獎勵";
				String pr_content = "評價產後廠商:"+req.getParameter("fp_name").trim();;
				Integer pr_point = new Integer(req.getParameter("pr_point"));
				java.sql.Date pr_date = java.sql.Date.valueOf(req.getParameter("fe_date").trim()); 
				
				PointRecordVO pointRecordVO = new PointRecordVO();
				pointRecordVO.setMem_no(mem_no);				
				pointRecordVO.setPr_type(pr_type);				
				pointRecordVO.setPr_content(pr_content);				
				pointRecordVO.setPr_date(pr_date);				
				pointRecordVO.setPr_point(pr_point);
	
				//Step3  更新產後廠商積分
				//先找舊的算新的
				PostpartumCareService PCSVC = new PostpartumCareService();
				PostpartumCareVO postpartumCareVO_old = PCSVC.getOnePC(fp_no);
				Integer old_eva_good = new Integer(postpartumCareVO_old.getPc_eva_good());
				Integer old_eva_normal = new Integer(postpartumCareVO_old.getPc_eva_normal());
				Integer old_eva_bad = new Integer(postpartumCareVO_old.getPc_eva_bad());

				Integer new_eva_good = old_eva_good;
				Integer new_eva_normal = old_eva_normal;
				Integer new_eva_bad = old_eva_bad;	
				
				if ("good".equals(fe_type)) {
					new_eva_good++;
				} else if ("normal".equals(fe_type)) {
					new_eva_normal++;
				} else if ("bad".equals(fe_type)) {
					new_eva_bad++;
				}

				
				//再放入新的
				PostpartumCareVO postpartumCareVO = new PostpartumCareVO();
				postpartumCareVO.setPc_no(fp_no);
				postpartumCareVO.setPc_eva_good(new_eva_good);
				postpartumCareVO.setPc_eva_normal(new_eva_normal);
				postpartumCareVO.setPc_eva_bad(new_eva_bad);	
				

				
				//Step4  更新會員積分
				//先找舊的
				MemberService memSVC = new MemberService();
				MemberVO memberVO_old = memSVC.getOneMem(mem_no);
				Integer old_point = new Integer(memberVO_old.getMem_point());
				Integer new_point = old_point + pr_point;
				
				//再創新的
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_point(new_point);				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("firmEvaluationVO", firmEvaluationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/Evaluation.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				
				//Step5  由PCASvc新增交易
				FirmEvaluationService FESvc = new FirmEvaluationService();
				FESvc.insertToPC(fp_no, mem_no, fe_type, fe_content, fe_date,pointRecordVO, postpartumCareVO, memberVO);				

				/***************************3.新增完成,準備轉交(Send the Success view)***********/

			      successMsgs.add("評價成功，積分增加:"+pr_point);
			      successMsgs.add("目前積分："+new_point);
			      
			      //Step6  更新session會員資料
			      MemberVO userVO = memSVC.getOneMem(mem_no);			      
			      session.setAttribute("userVO", userVO);
			      
//				String pageReq=(String)session.getAttribute("pageReq");
				
				RequestDispatcher successView = req.getRequestDispatcher("/member/MyPCApplication.jsp"); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);			
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/EvaluationPC.jsp");  //記得改!!!!!
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}	
}
