<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.cart.controller.* ,com.cart.model.* ,com.product_picture.model.*"%>
<%
	Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist",buylist);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="top-cart-content">	
<c:forEach var="order" items="${buylist}">	
<ol class="mini-products-list">
	<li>
		<div class="product-image">
		<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />	
			<c:forEach var="picVO" items="${picSvc.all}">
				<c:if test="${order.pro_no==picVO.pro_no}">
					<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width=25px/>
				</c:if>
			</c:forEach> 
        </div>
        <div class="product-details">
			<p class="cartproduct-name">
				${order.pro_name}
			</p>
			<strong class="qty">QTY : ${order.pro_quantity}</strong>
			<span class="sig-price">${order.sub_total}</span>
		</div>
	</li>
</ol>
</c:forEach>
<div "cart-actions">
    <form name="checkoutForm" action="<%=request.getContextPath()%>/cart/car.do" method="POST">
    	<input type="hidden" name="action" value="GOCART">
        <input type="submit" value="前往購物車">
	</form>
</div>
</div>											

</body>
</html>