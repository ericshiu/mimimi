<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.cart.controller.*,com.cart.model.* , com.member.model.* , com.orders.model.*,com.firm.model.*" import="com.postpartum_care.model.*" %>
<%
String userType = (String) session.getAttribute("userType"); 

if (userType != null){
	if (userType.equals("PC")){
		PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Fir")){
		FirmVO userVO = (FirmVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Mem")){
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");  
		pageContext.setAttribute("userVO",userVO);
	} 
} else {
	userType = "Non";
}
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/Checkout.jsp");

	OrdersVO ord = (OrdersVO) session.getAttribute("amount");
	pageContext.setAttribute("ord",ord);
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="css/checkOut.css"/>
</head>
<body bgcolor="#FFFFFF">
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>


<div class="container">
<div class="theme-block">
	<table class="shopping-cart-table" style="margin-bottom: 0px">
	<thead>
		<tr>
			<th>
				<span>產品</span>
			</th>
			<th>
				<span>價格</span>
			</th>
			<th>
				<span>數量</span>
			</th>
			<th>
				<span>小計</span>
			</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="order" items="${buylist}" varStatus="c">
		<tr>
			<td class="product-cart-image">
				<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />	
		        <c:forEach var="picVO" items="${picSvc.all}">
					<c:if test="${order.pro_no==picVO.pro_no}">
		                <img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width=150px/>
	                </c:if>
				</c:forEach>
				<span class="product-name">
					${order.pro_name}
				</span>
			</td>
			<td class="product-cart-price">
				<span class="cart-price">
					${order.pro_price}
				</span>	
			</td>
			<td data-title="Quantity" class="product-cart-actions">
					${order.pro_quantity}
			</td>
			<td class="product-cart-total">
				<span class="cart-price">
					${order.sub_total}
				</span>
			</td>			
		</tr>		
	</c:forEach>
	</tbody>
	</table>
	<form name="checkForm" action="<%=request.getContextPath()%>/orders/ord.do" method="POST">
		<div class="cart-forms">
			<div class="discount">
				<div class="row">			
					<div class="col-xs-12 col-sm-4">
						<h3>收件者資訊</h3>
						<label>收件者姓名 :</label>
						<input type="text" name="rec_name">
							<br>
							<br>
						<label>收件者電話 :</label>
							<input type="text" name="rec_phone">
					</div>
					<div class="col-xs-12 col-sm-8">
						<h3>收件者地址</h3>
						<label>收件者縣市 :</label>
							<select id="city_select" style="width:150px;" name="city"></select>
    						<select id="area_select" style="width:150px;" name="area"></select>
    						<input type="hidden" name="result" id="result">
							<br>
							<br>
						<label>地址細節 :</label>
						<input type="text" name="rec_address" style="width:300px;">
					</div>
				</div>
			</div>
		</div>
		<div class="cart-totals">
			<div class="line">
				<div class="priceLable block">
					<strong>總計</strong>
				</div>		
				<div class="price block">
					<strong>$ ${ord.ord_sum}</strong>	
				</div>
				</div>
        			<input type="hidden" name="action" value="PAY">
        			<input type="hidden" name="user" value="${userVO.mem_no}">
        			<input type="hidden" name="sum" value="${ord.ord_sum}">
        			<input type="hidden" name="fir_no" value="FIR0000001">
        			<input type="hidden" name="mem_account" value="94879487">      			
					<input type="submit" value="結帳" style="width:250px;">
				</div>
			</div>
		</div>
	</form>			
</div>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/address.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">


