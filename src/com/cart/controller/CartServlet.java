package com.cart.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartVO;


public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		if (action.equals("addCart")) {
			boolean match = false;
			String pro_no = req.getParameter("pro_no");
			Integer subtotal = new Integer(req.getParameter("totalSend"));
			Integer qty = new Integer(req.getParameter("quantity"));
			String name = req.getParameter("pro_name");
			Integer price = new Integer(req.getParameter("pro_price"));
		
			CartVO cart = new CartVO();
			cart.setPro_no(pro_no);
			cart.setPro_quantity(qty);
			cart.setSub_total(subtotal);
			cart.setPro_name(name);
			cart.setPro_price(price);

			try{
				if (buylist == null) {
					buylist = new Vector<CartVO>();
					buylist.add(cart);

				} else {
				for (int i = 0; i < buylist.size(); i++) {
					CartVO product = buylist.get(i);
					if (product.getPro_no().equals(cart.getPro_no())) {		
						product.setPro_quantity(product.getPro_quantity()+cart.getPro_quantity());
						product.setSub_total(product.getSub_total()+cart.getSub_total());
						buylist.setElementAt(product, i);
						match = true;
						}
					}
					if (!match)
						buylist.add(cart);
				}
			session.setAttribute("shoppingcart", buylist);
			String url = "/market/listAllPro.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			} catch (Exception e){
				e.printStackTrace();
			}	
		}		
		
		if(action.equals("GOCART")){
			int total = 0;
			for(int i=0;i<buylist.size();i++) {
				CartVO cart = buylist.get(i);
				int subtotal = cart.getSub_total();
				total += subtotal;
			}
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/cart/Cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		if (action.equals("DELETE")) {
			String del = req.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.removeElementAt(d);
			int total = 0;
			for(int i=0;i<buylist.size();i++) {
				CartVO cart = buylist.get(i);
				int subtotal = cart.getSub_total();
				total += subtotal;
			}
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			session.setAttribute("shoppingcart", buylist);
			/******************************************************/
			String url = null;
			if(buylist.isEmpty()) {		
				url = "/market/listAllPro.jsp";
			} else {
				url = "/cart/Cart.jsp";
			}			
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if (action.equals("BACK")) {
			session.setAttribute("shoppingcart", buylist);
			String url = "/market/listAllPro.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}		
	}		
}
