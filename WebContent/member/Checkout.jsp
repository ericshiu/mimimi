<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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
<%
	Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist", buylist);
%>

<html>
<head>
<style>
.input-group {
	margin: 10px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="<%=request.getContextPath() %>/member/js/address.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/member/css/checkOut.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
</head>
<body bgcolor="#FFFFFF">
<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>
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
		<c:forEach var="order" items="${buylist}">
			<tr>
				<td class="product-cart-image">
				<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />	
		        	<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(order.pro_no)}">	
 	                	<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width="100px"/>	
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
	
	<div>
	<form name="checkForm" action="<%=request.getContextPath()%>/orders/ord.do" method="POST" id="formCheckOut">
		<div class="cart-forms">
			<div class="discount">
				<div class="row">			
					<div class="col-xs-12 col-sm-4">
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon">收件者姓名 :</div>
								<input type="text" class="form-control" name="rec_name" value="<%=(userVO == null) ? "" : userVO.getMem_name()%>" />
						</div>
						<div class="input-group" style="width: 100%;">
								<div class="input-group-addon">收件者電話 :</div>
								<input type="text" class="form-control" name="rec_phone" value="<%=(userVO == null) ? "" : userVO.getMem_phone()%>" />
						</div>
					</div>
					
					<div class="col-xs-12 col-sm-8">
					<div class="input-group" style="width: 95%;">
						<div class="input-group-addon">連絡地址：</div>
							<select name="city" id="city_select" style="width: 40%; height: 50px;"></select>
							<select name="area" id="area_select" style="width: 60%; height: 50px;"></select>
							<input type="hidden" name="result" id="result" >
							<input type="text" name="rec_address" class="form-control" style="height: 50px;">
						</div>
					</div>
			
				</div>
			</div>
		</div>
		
		
		<div class="col-xs-12 col-sm-12" style="height:250px; border:1px solid #dfdfdf;">
			
            <div class="col-xs-12 col-sm-6" >
                <div class="input-group" style="margin-left: 45px; margin-top:30px;">
                	<div class="input-group-addon"><i class="fa fa-credit-card"></i>信用卡卡號</div>
                	<input type="text" class="form-control" name="mem_account">
                </div>
				<div class="input-group" style="margin-left: 45px; margin-top:30px;">
                	<div class="input-group-addon">卡片到期日期</div>
			        <select style="width: 40%; height: 40px;">
			          <option>month</option>
			          <option>01</option>
			          <option>02</option>
			          <option>03</option>
			          <option>04</option>
			          <option>05</option>
			          <option>06</option>
			          <option>07</option>
			          <option>08</option>
			          <option>09</option>
			          <option>10</option>
			          <option>11</option>
			          <option>12</option>
			        </select>
			        <select style="width: 60%; height: 40px;">
			          <option>year</option>
			          <option>2017</option>
			          <option>2018</option>
			          <option>2019</option>
			          <option>2020</option>
			          <option>2021</option>
			          <option>2022</option>
			          <option>2023</option>
			          <option>2024</option>
			          <option>2025</option>
			          <option>2026</option>
			          <option>2027</option>
			          <option>2028</option>
			          <option>2029</option>
			          <option>2030</option>
			        </select>
				</div>
				<div class="input-group" style="width: 40%; margin-left: 45px; margin-top:30px;">
                    <div class="input-group-addon">卡片檢查碼 : </div>
					<input type="text" class="form-control">
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-6">
				<div class="col-xs-12 col-sm-6">
					<div style="margin-top:100px;">
						<p style="font-size:35px;">總計</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6">		
					<div class="pull-right" style="margin-top:100px;">
						<p style="font-size:30px;">$ ${ord.ord_sum}</p>
					</div>
				</div>

				<div class="col-xs-12 col-sm-12" style="margin-top:20px; text-align:right;">
        			<input type="hidden" name="action" value="PAY">
        			<input type="hidden" name="user" value="${userVO.mem_no}">
        			<input type="hidden" name="sum" value="${ord.ord_sum}">
        			<input type="hidden" name="fir_no" value="FIR0000001">
					<a href="#" style="width:250px;" class="btn btn-info" onclick="show_and_send()">結帳</a>
				</div>
				
			</div>
		</div>
	</form>			
</div>
</div>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>

<script>

function show_and_send(){
	 swal({
		  title: "付款成功!",
		  type:"success",
		  timer: 3000,
		  showConfirmButton: false
		});
	
	setTimeout(function(){
		$('#formCheckOut').submit()
	}, 3000);
}
</script>



