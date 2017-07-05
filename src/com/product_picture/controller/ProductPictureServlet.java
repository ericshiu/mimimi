package com.product_picture.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_picture.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024 * 1024)
public class ProductPictureServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_Prp_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pro_no = req.getParameter("pro_no");

				ProductPictureService PRPSvc = new ProductPictureService();
				Set<ProductPictureVO> prpVO = PRPSvc.getPicByProNO(pro_no);
				ProductPictureVO proVO = new ProductPictureVO();
				proVO.setPro_no(pro_no);
				
				
				if (prpVO == null) {
					errorMsgs.add("查無資料");
				}
				
				req.setAttribute("getPicByPro", prpVO);
				String url = "/firm/listAllPrpByProNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_picture/select_page.jsp");
				failureView.forward(req, res);
			}
		}
			
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("測試地方");
			try {
				String prp_no = new String(req.getParameter("prp_no"));
					
				ProductPictureService PRPSvc = new ProductPictureService();
				ProductPictureVO prpVO = PRPSvc.getOneProductPicture(prp_no);
				req.setAttribute("prpVO", prpVO);
				String url = "/firm/update_prp_input.jsp";
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_picture/listAllPRP.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
            String prp_no = req.getParameter("prp_no").trim();
			/**照片***/
			Part part = req.getPart("prp_picture");
				
			byte[] prp_picture= null;
			if (getFileNameFromPart(part)!=null) {
				InputStream out = part.getInputStream();
				prp_picture=new byte[out.available()];
				out.read(prp_picture);
				out.close();
			} else {
				ProductPictureService PRPSvc = new ProductPictureService();
				ProductPictureVO prpVO = PRPSvc.getOneProductPicture(prp_no);
				prp_picture= prpVO.getPrp_picture();
			}	
			String pro_no = (req.getParameter("pro_no").trim());
			ProductPictureVO prpVO = new ProductPictureVO();
			prpVO.setPrp_no(prp_no);
			prpVO.setPrp_picture(prp_picture);
			prpVO.setPro_no(pro_no);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("prpVO", prpVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/product_picture/update_prp_inputXXX.jsp");
				failureView.forward(req, res);
				return;
			}
				
			ProductPictureService PRPSvc = new ProductPictureService();
			prpVO = PRPSvc.updateProductPicture(prp_no, prp_picture, pro_no);
			
			Set<ProductPictureVO> returnVO = PRPSvc.getPicByProNO(pro_no);
				
			req.setAttribute("getPicByPro", returnVO);
			String url = "/firm/listAllPrpByProNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("addPic".equals(action)) {
			
			try {
				String pro_no = req.getParameter("pro_no");
				ProductService proSvc = new ProductService();
				ProductVO proVO = proSvc.getOneProduct(pro_no);
				
				req.setAttribute("proVO", proVO);
				String url = "/firm/addPRP.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				e.getMessage();	
			}
		}

        if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pro_no = req.getParameter("pro_no").trim();
				System.out.println();
				/**照片***/
				InputStream out = req.getPart("prp_picture").getInputStream();
				byte[] prp_picture=new byte[out.available()];//buffer
				out.read(prp_picture);
				out.close();

				ProductPictureVO prpVO = new ProductPictureVO();
				prpVO.setPrp_picture(prp_picture);
				prpVO.setPro_no(pro_no);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prpVO", prpVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_picture/addPRP.jsp");
					failureView.forward(req, res);
					return;
				}
				ProductPictureService PRPSvc = new ProductPictureService();
				prpVO = PRPSvc.addProductPicture(prp_picture,pro_no);
				
				Set<ProductPictureVO> returnVO = PRPSvc.getPicByProNO(pro_no);
				req.setAttribute("getPicByPro", returnVO);
				String url = "/firm/listAllPrpByProNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_picture/addPRP.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				String prp_no = req.getParameter("prp_no");
				
				ProductPictureService PRPSvc = new ProductPictureService();
				PRPSvc.deleteProductPicture(prp_no);
				
				String pro_no = req.getParameter("pro_no");
				Set<ProductPictureVO> returnVO = PRPSvc.getPicByProNO(pro_no);				
				req.setAttribute("getPicByPro", returnVO);
				String url = "/firm/listAllPrpByProNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_picture/listAllPRP.jsp");
				failureView.forward(req, res);
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




