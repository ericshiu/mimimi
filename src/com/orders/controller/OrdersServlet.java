package com.orders.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartVO;
import com.firm.model.FirmService;
import com.firm.model.FirmVO;
import com.firm_evaluation.model.FirmEvaluationService;
import com.firm_evaluation.model.FirmEvaluationVO;
import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductVO;


public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();	
		Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
		OrdersVO ord = (OrdersVO) session.getAttribute("amount");
		String action = req.getParameter("action");
		
		if(action.equals("CHECKOUT")) {
			Integer amount = new Integer(req.getParameter("ord_sum"));
			ord = new OrdersVO();
			
			ord.setOrd_sum(amount);

			session.setAttribute("amount", ord);
			
			String url = "/member/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);	
		}	
		if(action.equals("PAY")) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String ord_rec_name = null;
				try {
					ord_rec_name = req.getParameter("rec_name").trim();
				} catch (Exception e) {
					ord_rec_name = "";
					errorMsgs.add("請輸入收件者姓名");
				}
				String address = req.getParameter("result");
				String address_detail = null;
				try {
					address_detail = req.getParameter("rec_address").trim();
				} catch (NumberFormatException e) {
					address_detail = "";
					errorMsgs.add("請輸入收件者地址");
				}
				String ord_rec_phone = null;
				try {
					ord_rec_phone = req.getParameter("rec_phone").trim();
				} catch (NumberFormatException e) {
					ord_rec_phone = "";
					errorMsgs.add("請輸入收件者電話");
				}

				String mem_no = req.getParameter("user");
				String fir_no = req.getParameter("fir_no");
				String mem_account = req.getParameter("mem_account");
				Integer ord_sum = new Integer(req.getParameter("sum"));

				OrdersVO odVO = new OrdersVO();
				odVO.setMem_no(mem_no);
				odVO.setFir_no(fir_no);
				odVO.setOrd_sum(ord_sum);
				odVO.setOrd_status("01");
				odVO.setOrd_rec_name(ord_rec_name);
				odVO.setOrd_rec_address(address+address_detail);
				odVO.setOrd_rec_phone(ord_rec_phone);
				odVO.setMem_account(mem_account);
			
			
				List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
				for(int i=0;i<buylist.size();i++) {
					OrderDetailVO ordDVO = new OrderDetailVO();
					Integer cartQTY = buylist.get(i).getPro_quantity();
					String pro_no = buylist.get(i).getPro_no();
					ordDVO.setPro_quantity(cartQTY);
					ordDVO.setPro_no(pro_no);
					list.add(ordDVO);
				}		
				OrdersService ordSvc = new OrdersService();
				ordSvc.addOrd(odVO, list);
				String url = "/member/memOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/member/Checkout.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{	
				String ord_no = req.getParameter("ord_no");
				OrdersService ordSvc = new OrdersService();
				OrdersVO ordVO = ordSvc.getOneOrd(ord_no);

				req.setAttribute("ordVO", ordVO);
				String url = "/orders/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
			  errorMsgs.add("無法取得資料:" + e.getMessage());
			  RequestDispatcher failureView = req.getRequestDispatcher("/orders/select_ord_page.jsp");
			  failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String ord_no = req.getParameter("ord_no");

				OrdersService ordSvc = new OrdersService();
				ordSvc.updateOrdStatus("02", ord_no);
				OrdersVO ordVO = ordSvc.getOneOrd(ord_no);							

				req.setAttribute("ordVO", ordVO);
				String url = "/member/update_ord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/listAllOrd .jsp");
				failureView.forward(req, res);
			}
		}	
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ord_no = req.getParameter("ord_no").trim();
				Timestamp ord_date = Timestamp.valueOf((req.getParameter("ord_date").trim()));
				String mem_no = req.getParameter("mem_no").trim();
				String fir_no = req.getParameter("fir_no").trim();
				Integer ord_sum = new Integer (req.getParameter("ord_sum").trim());
				String ord_status = "01";
				String ord_ship_no = req.getParameter("ord_ship_no").trim();
				
				String ord_rec_name = null;
				try {
					ord_rec_name = req.getParameter("ord_rec_name").trim();
				} catch (Exception e) {
					ord_rec_name = "";
					errorMsgs.add("請輸入收件者姓名");
				}
				String ord_rec_address = null;
				try {
					ord_rec_address = req.getParameter("ord_rec_address").trim();
				} catch (NumberFormatException e) {
					ord_rec_address = "";
					errorMsgs.add("請輸入收件者地址");
				}
				String ord_rec_phone = null;
				try {
					ord_rec_phone = req.getParameter("ord_rec_phone").trim();
				} catch (NumberFormatException e) {
					ord_rec_phone = "";
					errorMsgs.add("請輸入收件者電話");
				}

				String mem_account = new String(req.getParameter("mem_account").trim());
				
				OrdersVO ordVO = new OrdersVO();

				ordVO.setOrd_no(ord_no);
				ordVO.setOrd_date(ord_date);
				ordVO.setMem_no(mem_no);
				ordVO.setFir_no(fir_no);
				ordVO.setOrd_sum(ord_sum);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_ship_no(ord_ship_no);
				ordVO.setOrd_rec_name(ord_rec_name);
				ordVO.setOrd_rec_address(ord_rec_address);
				ordVO.setOrd_rec_phone(ord_rec_phone);
				ordVO.setMem_account(mem_account);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/update_ord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}	
				/***************************2.開始修改資料*****************************************/
				OrdersService ordSvc = new OrdersService();
				ordVO = ordSvc.updateOrd(mem_no, fir_no, ord_sum, ord_status, ord_ship_no,
						ord_rec_name, ord_rec_address, ord_rec_phone, mem_account, ord_date, ord_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); 
				String url = "/member/memOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/update_ord_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("finishOrd".equals(action)){
			try {
				String ord_no = req.getParameter("ord_no");
				String ord_ship_no = req.getParameter("ord_ship_no").trim();
				OrdersService ordSvc = new OrdersService();
				ordSvc.updateShipDate(ord_no);
				ordSvc.updateOrdShipNo(ord_ship_no, "04", ord_no);
				String url = "/firm/firmOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				System.out.println(e);
			}	
		}
		
		if ("PREPARE".equals(action)){
			try {
				String ord_no = req.getParameter("ord_no");

				OrdersService ordSvc = new OrdersService();
				ordSvc.updateOrdStatus("03", ord_no);
				
				String url = "/firm/firmOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		if ("forEvaluation".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String ord_no = req.getParameter("ord_no");
				String mem_no = req.getParameter("mem_no");
				String fe_type = req.getParameter("fe_type");
				String fe_content = req.getParameter("fe_content").trim();
				java.util.Date utilDate = new Date();
				java.sql.Date fe_date = new java.sql.Date(utilDate.getTime());
				
				OrdersService ordSvc = new OrdersService();	
				List<ProductVO> firms = ordSvc.getFirmByOrdNo(ord_no);
				for(ProductVO firs:firms) {
					String fp_no = firs.getFir_no();
					
//					新增各個廠商的評價
					
					FirmEvaluationVO firmEvaluationVO= new FirmEvaluationVO();
					firmEvaluationVO.setFp_no(fp_no);
					firmEvaluationVO.setMem_no(mem_no);
					firmEvaluationVO.setFe_type(fe_type);
					firmEvaluationVO.setFe_content(fe_content);
					firmEvaluationVO.setFe_date(fe_date);
					
					FirmService firSvc = new FirmService();
					FirmVO firVO_old = firSvc.getOneFir(fp_no);
					
					Integer old_eva_good = new Integer(firVO_old.getFir_eva_good());
					Integer old_eva_normal = new Integer(firVO_old.getFir_eva_normal());
					Integer old_eva_bad = new Integer(firVO_old.getFir_eva_bad());
					
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
					//再放入新的評價分數
					FirmVO firVO= new FirmVO();
					firVO.setFir_no(fp_no);
					firVO.setFir_eva_good(new_eva_good);
					firVO.setFir_eva_normal(new_eva_normal);
					firVO.setFir_eva_bad(new_eva_bad);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("firmEvaluationVO", firmEvaluationVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/memOrd.jsp");
						failureView.forward(req, res);
						return;
					}
					
					FirmEvaluationService evaSvc = new FirmEvaluationService();
					evaSvc.insertEvaToFirm(fp_no, mem_no, fe_type, fe_content, fe_date, firVO);
				}
				OrdersService orderSvc = new OrdersService();
				orderSvc.updateRecDate(ord_no);
				orderSvc.updateOrdStatus("05", ord_no);
				
				String url = "/member/memOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/memOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
