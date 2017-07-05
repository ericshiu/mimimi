<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO proVO = (ProductVO) request.getAttribute("proVO");
%>

<html>
<head>
<style>
p {
	font-family:ch;
}
th {
	font-family:ch;
}
span{
	font-family:ch;
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="css/marketList.css"> -->

<link rel="stylesheet" href="css/style.css">	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body bgcolor='white'>
<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>

<div class="wrapper container">
	<div class="col threeQuarter">
    	<div class="section pic" align="center">
    	<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
      		<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
 	        	<img style="width:400px;height:500px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" draggable="false" class="itemImg" alt="Item Image"/>	
 			</c:forEach>
    	</div>
    	<div class="section gallery">
			<c:forEach var="picVO" items="${picSvc.all}">
				<c:if test="${proVO.pro_no==picVO.pro_no}">
	        		<img style="width: 180px;height: 200px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width=150px/>
	        	</c:if>
			</c:forEach>
    	</div>
    
    	<div class="section last">
			<p>${proVO.pro_no}</p>
    	</div>
	</div>

	<div class="col quarter padding">
		<h2>${proVO.pro_name}</h2>
    	<div class="section">
  	  		<p>${proVO.pro_desc}</p>
    	</div>
    	<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />
    	<c:forEach var="firVO" items="${firSvc.allFir}">
    	<c:if test="${firVO.fir_no==proVO.fir_no}">
    	<table class="table table-hover" style="border:1px">
			<tr>
				<th style="text-align: center;"><i class="fa fa-thumbs-o-up" ></i>正評</th>
				<th style="text-align: center;"><i class="fa fa-smile-o" ></i>普通</th>
				<th style="text-align: center;"><i class="fa fa-thumbs-o-down" ></i>負評</th>
			</tr>
			<tr>
				<td style="text-align: center;">${firVO.fir_eva_good}</td>
				<td style="text-align: center;">${firVO.fir_eva_normal}</td>
				<td style="text-align: center;">${firVO.fir_eva_bad}</td>
			</tr>
		</table>
		</c:if>
    	</c:forEach>

    	<div class="section price" style="text-align: center;">
    		<p style="display: -webkit-inline-box; color: orange;">Price :</p>
    		<div id="price" style="font-size:20px;">${proVO.pro_price}</div>
    	</div>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/car.do">
    	<div style="text-align: center; padding-bottom: 15px;margin-bottom: 15px;border-bottom: 1px solid #ddd;">
      		<td data-title="Quantity" class="product-cart-actions" >
				<div class="prd-quantity" data-title="Quantity">
        		<input value="-" class="qtyminus btn" data-field="quantity" type="button">
        		<input type="text" name="quantity" value="1" class="qty" id="quantity" readonly>
        		<input value="+" class="qtyplus btn" data-field="quantity" type="button">
        	</td>
		</div>   
    </div>

    <div class="section price" style="text-align: center;font-size:20PX;">
    	<p style="display: -webkit-inline-box; color: orange;">SUBTOTAL :</p>
    	<p style="display: -webkit-inline-box;"><span id="total" style="color:#BA9D75;"></span></p>
    	<input type="hidden" name="totalSend" id="totalSend">
    </div>

    <div class="section last">
		<input type="submit" value="Add to Cart" class="button" />
		<input type="hidden" name="pro_no" value="${proVO.pro_no}">
		<input type="hidden" name="pro_name"value="${proVO.pro_name}">
		<input type="hidden" name="pro_price"value="${proVO.pro_price}">
		<input type="hidden" name="action"value="addCart">
    </div>
    </FORM>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>

<script>
$(document).ready(function($) {
	valueshow();	
	$(".qtyplus").on( "click", function(e){
		e.preventDefault();
		var fieldName = $(this).attr("data-field");
		var currentVal = parseInt($('input[name='+fieldName+']').val(),10);
		if (!isNaN(currentVal)) {
			$('input[name='+fieldName+']').val(currentVal + 1);
			valueshow();
		} else {
			$(this).find('input[name='+fieldName+']').val(1);
			valueshow();
		}
	});

	$(".qtyminus").on( "click" , function(e) {		
		e.preventDefault();		
		var fieldName = $(this).attr('data-field');		
		var currentVal = parseInt($('input[name='+fieldName+']').val(),10);		
		if (!isNaN(currentVal) && currentVal > 1) {			
			$('input[name='+fieldName+']').val(currentVal - 1);
			valueshow();
		} else {			
			$('input[name='+fieldName+']').val(1);
			valueshow();
		}
	});
});

function valueshow(){
	var quantity=document.getElementById("quantity").value;
	var price=document.getElementById("price").innerHTML;
	
	total = quantity*price;
	document.getElementById("total").innerText= total;
	$("#totalSend").val(total);
}
</script>