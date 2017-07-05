<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
ProductVO proVO = (ProductVO) request.getAttribute("proVO");
%>

<html>
<head>
<title>單一商品資料查詢 - listOnePro.jsp</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="../css/css.css"/>
</head>
<body bgcolor='white'>

<table border='1' bordercolor='#CCCCFF' width='1900'>
	<tr>
		<th>商品編號</th>
		<th>商品類別編號</th>
		<th>商品照片</th>
		<th>商品名稱</th>
		<th>商品介紹</th>
		<th>商品價格</th>
		<th>媽媽適用商品起始週</th>
		<th>媽媽適用商品結束週</th>
		<th>寶寶適用商品起始月</th>
		<th>寶寶適用商品結束月</th>

	</tr>
	<tr align='center' valign='middle'>
		<td>${proVO.pro_no}</td>
		<td>${proVO.prc_no}</td>
		<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
		<td>
			<c:forEach var="picVO" items="${picSvc.all}">
				<c:if test="${proVO.pro_no==picVO.pro_no}">
	        		<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width=150px/>
	        	</c:if>
			</c:forEach>
		</td>
		<td>${proVO.pro_name}</td>
		<td>${proVO.pro_desc}</td>
		<td>${proVO.pro_price}</td>
		<td>${proVO.pro_age_ms}</td>
		<td>${proVO.pro_age_me}</td>
		<td>${proVO.pro_age_cs}</td>
		<td>${proVO.pro_age_ce}</td>
	</tr>
	</FORM>
</table>
</body>
</html>
