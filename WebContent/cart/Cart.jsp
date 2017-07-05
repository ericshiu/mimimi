<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.cart.controller.*,com.cart.model.*, com.member.model.*" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>購物車</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script	src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
<!--固定的 -->

<style>
th{
	text-align:center;
}
</style>
</head>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	MemberVO uesrVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq","/member/Checkout.jsp");
	Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist",buylist);
	String amount =  (String) request.getAttribute("amount");
%>
<body>

<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>


<div class="container">
<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				購物車清單
			</div>
		<table class="table table-hover table-striped" align="center">
	<tr bgcolor="#999999">
		<th width="100">商品照片</th>
		<th width="50">商品名稱</th>
		<th width="50">商品單價</th>
		<th width="50">商品數量</th>
		<th width="50">小計</th>
		<th width="50">刪除</th>
	</tr>
	<c:set var="isEmpty" value="true"></c:set>
	<c:forEach var="order" items="${buylist}" varStatus="c">
		<c:set var="isEmpty" value="false"></c:set>
		<tr>
			<td width="100"><div align="center"><b>
			<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />	
		        <c:forEach var="picVO" items="${picSvc.getOnePicByProNo(order.pro_no)}">	
 	                <img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width="100px"/>	
 				</c:forEach>
			</td>
			<td width="100"><div align="center">${order.pro_name}</div></td>
			<td width="50"><div align="center">${order.pro_price}</div></td>
			<td width="50"><div align="center">${order.pro_quantity}</div></td>			
			<td width="50"><div align="center">${order.sub_total}</div></td>	
			<td width="50"><div align="center">
			<form name="deleteForm" action="<%=request.getContextPath()%>/cart/car.do" method="POST">
        		<input type="hidden" name="action" value="DELETE">
				<input type="hidden" name="del" value="${c.index}">
				<button type="submit" class="btn btn-danger">刪除</button>
			</form>	
        	</td>
		</tr>		
	</c:forEach>
	<c:if test="${isEmpty}">
		<tr>
			<td width="200" colspan="8">NO DATA</td>
		</tr>
	</c:if>
	
	<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td>
		<div align="left"><font color="red"><b style="font-size:30px;">總金額：</b><b style="font-size:30px;">$<%=amount%></b></font></div>
	</td>
	
	</tr>
	<tr>
	<td></td>
	<td>
		<form name="checkoutForm" action="<%=request.getContextPath()%>/cart/car.do" method="POST">
       	 	<input type="hidden" name="action"	value="BACK">
       		<button type="submit" class="btn btn-success">繼續購物</button>
   		 </form>
	</td>
	<td></td>
	<td></td>
	<td>
	</td>
	<td>
	<form name="checkoutForm" action="<%=request.getContextPath()%>/orders/ord.do" method="POST">
    	<input type="hidden" name="ord_sum"	value="<%=amount%>">
        <input type="hidden" name="action"	value="CHECKOUT">
        <button type="submit" class="btn btn-warning">付款結帳</button>
    </form>
	</td>
	</tr>
	
</table>
</div>


<p>
    
    <br>
  
    
</div>    
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>