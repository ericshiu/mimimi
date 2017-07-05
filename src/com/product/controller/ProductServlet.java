package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
import com.product_picture.model.ProductPictureService;
import com.product_picture.model.ProductPictureVO;
import com.product_speci.model.ProductSpeciVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5000 * 1024 * 1024, maxRequestSize = 5 * 5000 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String pro_no = req.getParameter("pro_no");
			ProductService ProSvc = new ProductService();
			ProductVO proVO = ProSvc.getOneProduct(pro_no);
			if (proVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("proVO", proVO);
			String url = "/product/listOnePro.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);																			
			successView.forward(req, res);
		} 
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pro_no = new String(req.getParameter("pro_no"));

				ProductService ProSvc = new ProductService();
				ProductVO proVO = ProSvc.getOneProduct(pro_no);

				req.setAttribute("proVO", proVO);
				String url = "/firm/update_pro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/listAllPro.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {				
				String pro_no = req.getParameter("pro_no").trim();
				String prc_no = req.getParameter("prc_no").trim();
				String fir_no = req.getParameter("fir_no").trim();
				String pro_name = req.getParameter("pro_name").trim();
				String pro_desc = req.getParameter("pro_desc").trim();
				Integer pro_price = new Integer(req.getParameter("pro_price").trim());
				Integer pro_age_ms = new Integer(req.getParameter("pro_age_ms").trim());
				Integer pro_age_me = new Integer(req.getParameter("pro_age_me").trim());
				Integer pro_age_cs = new Integer(req.getParameter("pro_age_cs").trim());
				Integer pro_age_ce = new Integer(req.getParameter("pro_age_ce").trim());


				ProductVO proVO = new ProductVO();
				proVO.setPro_no(pro_no);
				proVO.setPrc_no(prc_no);
				proVO.setFir_no(fir_no);
				proVO.setPro_name(pro_name);
				proVO.setPro_desc(pro_desc);
				proVO.setPro_price(pro_price);
				proVO.setPro_range(null);
				proVO.setPro_age_ms(pro_age_ms);
				proVO.setPro_age_me(pro_age_me);
				proVO.setPro_age_cs(pro_age_cs);
				proVO.setPro_age_ce(pro_age_ce);
				proVO.setPro_qa(null);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("proVO", proVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/firm/update_pro.jsp");
					failureView.forward(req, res);
					return;
				}

				ProductService ProSvc = new ProductService();
				proVO = ProSvc.updateProduct(pro_no, prc_no, fir_no, pro_name, pro_desc, pro_price, null,
						pro_age_ms, pro_age_me, pro_age_cs, pro_age_ce, null);

				req.setAttribute("proVO", proVO);
				String url = "/firm/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:");
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/update_pro.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String prc_no = req.getParameter("prc_no").trim();
				String fir_no = req.getParameter("fir_no").trim();
				String pro_name = req.getParameter("pro_name").trim();
				String pro_desc = req.getParameter("pro_desc").trim();
				Integer pro_price = new Integer(req.getParameter("pro_price").trim());
				String pro_range = req.getParameter("pro_range");
				Integer pro_age_ms = new Integer(req.getParameter("pro_age_ms").trim());
				Integer pro_age_me = new Integer(req.getParameter("pro_age_me").trim());
				Integer pro_age_cs = new Integer(req.getParameter("pro_age_cs").trim());
				Integer pro_age_ce = new Integer(req.getParameter("pro_age_ce").trim());
				String pro_qa = req.getParameter("pro_qa");

				ProductVO proVO = new ProductVO();

				proVO.setPrc_no(prc_no);
				proVO.setFir_no(fir_no);
				proVO.setPro_name(pro_name);
				proVO.setPro_desc(pro_desc);
				proVO.setPro_price(pro_price);
				proVO.setPro_range(pro_range);
				proVO.setPro_age_ms(pro_age_ms);
				proVO.setPro_age_me(pro_age_me);
				proVO.setPro_age_cs(pro_age_cs);
				proVO.setPro_age_ce(pro_age_ce);
				proVO.setPro_qa(pro_qa);

				Collection<Part> prp_parts = req.getParts();
				List<ProductPictureVO> pic = new ArrayList<ProductPictureVO>();	
				if(prp_parts != null && !prp_parts.isEmpty()){
					for (Part part :prp_parts){
						if (getFileNameFromPart(part) != null && part.getContentType()!=null){
						InputStream in = part.getInputStream();					
						byte[] prp_picture = new byte[in.available()];

						in.read(prp_picture);
						in.close();			

						ProductPictureVO proPictureVO = new ProductPictureVO();
						proPictureVO.setPrp_picture(prp_picture);
						pic.add(proPictureVO);

						}
					}			
				}
				List<ProductSpeciVO> setSpeci = new ArrayList<ProductSpeciVO>();
				ProductSpeciVO proSpeci = new ProductSpeciVO();
				String psp_name = "規格";
				String psp_list = "紅色";
				proSpeci.setPsp_name(psp_name);
				proSpeci.setPsp_list(psp_list);
				setSpeci.add(proSpeci);

				ProductService proSvc = new ProductService();
				proSvc.addProduct(proVO, pic, setSpeci);

				String url = "/firm/listAllPro.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/firm/addPro.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOnePro".equals(action)) {
			String pro_no = req.getParameter("pro_no");
			ProductService ProSvc = new ProductService();
			ProductVO proVO = ProSvc.getOneProduct(pro_no);
			
			req.setAttribute("proVO", proVO);
			String url = "/market/listOnePro.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);																			
			successView.forward(req, res);
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
